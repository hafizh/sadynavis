package vis.dyna.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TechniqueNotExistException extends Exception implements IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TechniqueNotExistException() {
		super();
	}

	public TechniqueNotExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TechniqueNotExistException(String arg0) {
		super(arg0);
	}

	public TechniqueNotExistException(Throwable arg0) {
		super(arg0);
	}

}
