package vis.dyna.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Tool implements IsSerializable {

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	private String year;
}
