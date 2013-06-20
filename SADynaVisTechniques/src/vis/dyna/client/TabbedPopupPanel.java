package vis.dyna.client;


import vis.dyna.shared.Technique;
import vis.dyna.shared.Tool;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TabbedPopupPanel extends DialogBox {

	private Technique technique; 
	private VerticalPanel contentPanel = new VerticalPanel();
	private TabPanel tab = new TabPanel();
	private Label title = new Label();
	private Button closeButton = new Button("Close");
	public TabbedPopupPanel(){
		
		contentPanel.add(title);
		contentPanel.add(tab);
		Label note = new Label("NOTE: You can drag the window, to position it as convenient!");
		contentPanel.add(note);
		contentPanel.add(closeButton);
		
		add(contentPanel);
		closeButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
        });

		// styling
		tab.setSize("500px", "400px");
		contentPanel.setSize("500px", "400px");
		setHeight("450px");
		setWidth("500px");
		
		
	}
	
	
	public Technique getTechnique() {
		return technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
		title.setText(technique.getName());
	}

	public void updateLayout(){
		tab.clear();
		VerticalPanel contentGeneral = new VerticalPanel();
		
		Label idLabel = new Label("Id:");
		idLabel.setWidth("110px");
		Label nameLabel = new Label("Name:");
		nameLabel.setWidth("110px");
		Label representedDataLabel = new Label("Represented data:");
		representedDataLabel.setWidth("110px");
		Label advLabel = new Label("Advantages:");
		advLabel.setWidth("110px");
		Label disAdvLabel = new Label("Disadvantages:");
		disAdvLabel.setWidth("110px");
		
		Label id = new Label(technique.getId()+"");
		Label name = new Label(technique.getName());
		
		Label representedData = new Label(technique.getRepresented_data());
		Label adv = new Label(technique.getAdvantages());
		Label disAdv = new Label(technique.getDisadvantages());
		
		HorizontalPanel idHPanel = new HorizontalPanel();
		HorizontalPanel nameHPanel = new HorizontalPanel();
		HorizontalPanel representedDataHPanel = new HorizontalPanel();
		HorizontalPanel advHPanel = new HorizontalPanel();
		HorizontalPanel disAdvHPanel = new HorizontalPanel();
		
		idHPanel.add(idLabel);
		idHPanel.add(id);
		nameHPanel.add(nameLabel);
		nameHPanel.add(name);
		representedDataHPanel.add(representedDataLabel);
		representedDataHPanel.add(representedData);
		advHPanel.add(advLabel);
		advHPanel.add(adv);
		disAdvHPanel.add(disAdvLabel);
		disAdvHPanel.add(disAdv);
		
		contentGeneral.add(idHPanel);
		contentGeneral.add(nameHPanel);
		contentGeneral.add(representedDataHPanel);
		contentGeneral.add(advHPanel);
		contentGeneral.add(disAdvHPanel);
		tab.add(contentGeneral, "General");
		tab.setAnimationEnabled(true);
		
		Label description = new Label(technique.getDescription());
		description.setStyleName("paddingTop");
		tab.add(description, "Description");
		
		//System.out.println("technique is null?!"+technique == null);
		VerticalPanel contentCrossRef = new VerticalPanel();
		contentCrossRef.setStyleName("paddingTop");
		if (technique.getCross_reference() != null) {
			for (final Technique tech : technique.getCross_reference()) {
				final Label techLabel = new Label("Id: " + tech.getId() + " | Name: "
						+ tech.getName());
				contentCrossRef.add(techLabel);
				techLabel.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						TabbedPopupPanel newPopup = new TabbedPopupPanel();
						newPopup.setTechnique(tech);
						newPopup.setAnimationEnabled(true);
						newPopup.updateLayout();
						newPopup.show();
						newPopup.center();
					}
				});
				
				techLabel.addMouseOverHandler(new MouseOverHandler() {
					@Override
					public void onMouseOver(MouseOverEvent event) {
						techLabel.setStyleName("mouseOver");
					}
				});
				
				techLabel.addMouseOutHandler(new MouseOutHandler() {
					@Override
					public void onMouseOut(MouseOutEvent event) {
						techLabel.setStyleName("mouseOut");
					}
				});

			}
		}
		tab.add(contentCrossRef, "Cross references");
		
		VerticalPanel contentTools = new VerticalPanel();
		contentTools.setStyleName("paddingTop");
		if (technique.getTools() != null) {
			for (Tool tool : technique.getTools()) {
				Label toolLabel = new Label("Name: " + tool.getName()
						+ " | Year: " + tool.getYear());
				contentTools.add(toolLabel);
			}
		}
		tab.add(contentTools, "Tools");
		
		Anchor urlLabel = new Anchor();
		urlLabel.setStyleName("paddingTop");
		if(technique.getScreenshot_url() != null){
		 urlLabel.setText("Click to go to screenshots");
		 urlLabel.setHref(technique.getScreenshot_url());
		}
		tab.add(urlLabel, "Screenshots");
		
		tab.selectTab(0);
	}
	
	public void addCloseButtonClickHandler(ClickHandler handler){
		closeButton.addClickHandler(handler);
	}
}
