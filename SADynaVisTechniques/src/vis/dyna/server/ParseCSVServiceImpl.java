package vis.dyna.server;

import java.util.logging.Logger;

import vis.dyna.client.ParseCSVService;
import vis.dyna.shared.Technique;
import vis.dyna.shared.TechniqueNotExistException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ParseCSVServiceImpl extends RemoteServiceServlet implements
		ParseCSVService {
	Logger logger = Logger.getLogger(ParseCSVServiceImpl.class.getName());
	public Technique getTechniqueById(String input) throws TechniqueNotExistException {
		logger.info("Server call, log!! getting technique id:"+input);
		// Verify that the input is valid.
		input = escapeHtml(input);
		Integer id = Integer.parseInt(input);
		Technique technique = null;
			logger.info("Inside try, getting technique id:"+id);
			technique = CSVReadUtils.readWithCsvBeanReader(id);
			logger.info("Got technique id:"+technique.getId()+" name:"+technique.getName());
		 
		return technique;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
