package vis.dyna.client;

import vis.dyna.shared.Technique;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ParseCSVServiceAsync {
	void getTechniqueById(String input, AsyncCallback<Technique> callback)
			throws IllegalArgumentException;
}
