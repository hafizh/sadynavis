package vis.dyna.client;

import java.io.IOException;

import vis.dyna.shared.Technique;
import vis.dyna.shared.TechniqueNotExistException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("parse")
public interface ParseCSVService extends RemoteService {
	Technique getTechniqueById(String name) throws TechniqueNotExistException, IOException;
}
