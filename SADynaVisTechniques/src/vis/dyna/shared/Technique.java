package vis.dyna.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Technique implements IsSerializable{

	private int id;
	private String name;
	private String description;
	private List<Technique> cross_reference;
	private List<Tool> tools;
	private String represented_data;
	private String advantages;
	private String disadvantages;
	private String screenshot_url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Technique> getCross_reference() {
		return cross_reference;
	}
	public void setCross_reference(List<Technique> cross_reference) {
		this.cross_reference = cross_reference;
	}
	
	public void addCrossRefs(Technique crossRef) {
		if(this.cross_reference == null) {
			cross_reference = new ArrayList<Technique>();
		}
		
		this.cross_reference.add(crossRef);
	}
	
	public List<Tool> getTools() {
		return tools;
	}
	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	
	public void addTools(Tool tool){
		if(this.tools == null) {
			tools = new ArrayList<Tool>();
		}
		tools.add(tool);
	}
	public String getRepresented_data() {
		return represented_data;
	}
	public void setRepresented_data(String represented_data) {
		this.represented_data = represented_data;
	}
	public String getAdvantages() {
		return advantages;
	}
	public void setAdvantages(String advantages) {
		this.advantages = advantages;
	}
	public String getDisadvantages() {
		return disadvantages;
	}
	public void setDisadvantages(String disadvantages) {
		this.disadvantages = disadvantages;
	}
	public String getScreenshot_url() {
		return screenshot_url;
	}
	public void setScreenshot_url(String screenshot_url) {
		this.screenshot_url = screenshot_url;
	}
	
}
