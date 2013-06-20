package vis.dyna.client;

import vis.dyna.shared.Technique;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SADynaVisTechniques implements EntryPoint {

	private ParseCSVServiceAsync parseService;
	private static TabbedPopupPanel panel = new TabbedPopupPanel();
	//public static List<Technique> techniques = new ArrayList<Technique>();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
        
        NodeList<Element> elems = Document.get().getElementsByTagName("p");
        PEventHandler handler = new PEventHandler();
        for(int i = 0; i<elems.getLength(); i++){
        	Element elem = elems.getItem(i);
        	com.google.gwt.user.client.Element castedElem = (com.google.gwt.user.client.Element) elem;
        	DOM.sinkEvents(castedElem, (Event.ONCLICK|Event.ONMOUSEOVER|Event.ONMOUSEOUT));
            DOM.setEventListener(castedElem, handler);
        }

        //initTechniques();

	}
        
        class PEventHandler implements EventListener {
        	  private Element pElement;
        	  @Override
        	  public void onBrowserEvent(Event event) {
        		  
        	    if (event.getTypeInt() == Event.ONMOUSEOVER) {
        	      pElement = Element.as(((NativeEvent) event).getEventTarget());
        	      //pElement.setPropertyString("background-color", "#FF0000");
        	    } else if (event.getTypeInt() == Event.ONMOUSEOUT) {
        	    	pElement = Element.as(((NativeEvent) event).getEventTarget());
        	    	//pElement.setAttribute("background-color", "");
        	    }else if (event.getTypeInt() == Event.ONCLICK) {
        	    	pElement = Element.as(((NativeEvent) event).getEventTarget());
        	    	//Window.alert(pElement.getInnerText()+"clicked!\n id:"+pElement.getId());
        	    	//pElement.setAttribute("background-color", "");
        	    	String pid = pElement.getId();
        	    	
        	    	if(parseService == null){
        	    		parseService = GWT.create(ParseCSVService.class);
        	    	}
        	    	
        	    	parseService.getTechniqueById(pid, new AsyncCallback<Technique>() {
						@Override
						public void onSuccess(Technique result) {
							panel.setTechnique(result);
		                    panel.updateLayout();
		                    panel.setAnimationEnabled(true);
		                    panel.show();
		                    panel.center();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Technique localTech = new Technique();
							GWT.log("PID is not set! "+pElement.getInnerText().trim()+" "+pElement.getNodeValue()+"\n"+caught.getMessage());
							localTech = new Technique();
	        	    		localTech.setId(0);
	        	    		localTech.setName(pElement.getInnerText().trim());
	        	    		localTech.setDescription("This node described by its child nodes. Click on it's children!");
	        	    		
	        	    		panel.setTechnique(localTech);
		                    panel.updateLayout();
		                    panel.setAnimationEnabled(true);
		                    panel.show();
		                    panel.center();
						}
					});
   	      
  	    }
  	  }
 	}
    
    /*
    public void initTechniques(){
    	// techniques
    	Technique seqDiag = new Technique();	
    	Technique tmfd = new Technique(); 		
    	Technique scedDiag = new Technique();	
    	Technique sceneDiag = new Technique();	
    	Technique transFlows = new Technique();	
    	Technique interDiag = new Technique(); 	
    	Technique callTree = new Technique();
    	Technique execPattView = new Technique();
    	Technique refPattView = new Technique();
    	Technique progTracView = new Technique();
    	Technique coneTree = new Technique();
    	Technique depGraph = new Technique();
    	Technique mapMetView = new Technique();
    	Technique interGraph = new Technique();
    	Technique polymetricView = new Technique();
    	Technique circBundleView = new Technique();
    	Technique threeDHeb = new Technique();
    	Technique seqContextView = new Technique();
    	Technique histoView = new Technique();
    	Technique markov = new Technique();
    	Technique stateChart = new Technique();
    	Technique infoMural = new Technique();
    	Technique execView = new Technique();
    	Technique massSeqView = new Technique();
    	Technique execBar = new Technique();
    	Technique codeViewer = new Technique();
    	
    	
    	Tool kieker = new Tool();
    	Tool scene = new Tool();
    	Tool sced = new Tool();
    	Tool tptp = new Tool();
    	Tool vet = new Tool();
    	Tool oasis = new Tool();
    	Tool jive = new Tool();
    	Tool diver = new Tool();
    	Tool fsv = new Tool();
    	Tool reticella = new Tool();
    	Tool cb = new Tool();
    	Tool wsn = new Tool();
    	Tool isvis = new Tool();
    	Tool programExpl = new Tool();
    	Tool shimba = new Tool();
    	Tool ovation = new Tool();
    	Tool jinsight = new Tool();
    	Tool zinsight = new Tool();
    	Tool seat = new Tool();
    	Tool fireDetect = new Tool();
    	Tool cerno = new Tool();
    	Tool rigi = new Tool();
    	Tool avid = new Tool();
    	Tool extravis = new Tool();
    	Tool shrimp = new Tool();
    	Tool codeCrawl = new Tool();
    	Tool codeMap = new Tool();
    	Tool vitrail = new Tool();
    	Tool stan = new Tool();
    	Tool gammatella = new Tool();
    	
    	// init tools
    	kieker.setName("Kieker");
    	kieker.setYear("2012");
    	scene.setName("Scene");
    	scene.setYear("2012");
    	sced.setName("SCED");
    	sced.setYear("2012");
    	tptp.setName("TPTP");
    	tptp.setYear("2012");
    	vet.setName("VET");
    	vet.setYear("2004");
    	oasis.setName("OASIS");
    	oasis.setYear("2008");
    	jive.setName("JIVE");
    	jive.setYear("2010");
    	diver.setName("Diver");
    	diver.setYear("2010");
    	fsv.setName("FSV");
    	fsv.setYear("2010");
    	reticella.setName("Reticella");
    	reticella.setYear("2012");
    	cb.setName("Collaboration browser");
    	cb.setYear("2004");
    	wsn.setName("Web service navigator");
    	wsn.setYear("2004");
    	isvis.setName("IsVis");
    	isvis.setYear("2000");
    	programExpl.setName("Program explorer");
    	programExpl.setYear("2003");
    	shimba.setName("ShimBa");
    	shimba.setYear("2002");
    	ovation.setName("Ovation");
    	ovation.setYear("2003");
    	jinsight.setName("Jinsight");
    	jinsight.setYear("2004");
    	zinsight.setName("Zinsight");
    	zinsight.setYear("2012");
    	seat.setName("SEAT");
    	seat.setYear("2004");
    	fireDetect.setName("FireDetective");
    	fireDetect.setYear("2012");
    	cerno.setName("Cerno");
    	rigi.setName("Rigi");
    	avid.setName("AVID");
    	extravis.setName("Extravis");
    	shrimp.setName("SHriMp");
    	codeCrawl.setName("CodeCrawler");
    	codeMap.setName("CodeMap");
    	vitrail.setName("VITRAIL");
    	stan.setName("Stan");
    	
    	// inter diag
    	interDiag.setId(1);
    	interDiag.setName("Interaction diagram");
    	interDiag.setDescription("A general name for sequence diagram like diagrams.");
    	interDiag.addCrossRefs(seqDiag);
    	interDiag.addCrossRefs(scedDiag);
    	interDiag.addCrossRefs(sceneDiag);
    	interDiag.addCrossRefs(tmfd);
    	interDiag.addCrossRefs(transFlows);
		interDiag.addTools(programExpl);
		interDiag.setScreenshot_url("https://www.dropbox.com/s/rhinfdzp73v357d/interaction_diagram_program_explorer.png");
    	
    	// sequence d.
    	seqDiag.setId(2);
    	seqDiag.setName("Sequence diagram");
    	seqDiag.setDescription("no need!");
    	seqDiag.addCrossRefs(tmfd);
    	seqDiag.addCrossRefs(interDiag);
    	seqDiag.addCrossRefs(scedDiag);
    	seqDiag.addCrossRefs(sceneDiag);
    	seqDiag.addCrossRefs(transFlows);
    	seqDiag.addTools(kieker);
    	seqDiag.addTools(scene);
    	seqDiag.addTools(sced);
    	seqDiag.addTools(tptp);
    	seqDiag.addTools(vet);
    	seqDiag.addTools(oasis);
    	seqDiag.addTools(jive);
    	seqDiag.addTools(diver);
    	seqDiag.addTools(fsv);
    	seqDiag.addTools(reticella);
    	seqDiag.addTools(cb);
    	seqDiag.addTools(wsn);
    	seqDiag.setRepresented_data("Interaction, trace data");
		seqDiag.setAdvantages("Clearly depicts interaction");
		seqDiag.setDisadvantages("Not scalable");
		seqDiag.setScreenshot_url("https://www.dropbox.com/sh/0c8llpxt5n5o3kr/j9bNeTR-M1");
		
		// tmfd
		tmfd.setId(3);
		tmfd.setName("Temporal msg flow diagram");
		tmfd.setDescription("Sequence diagram origin. Alternatively called sequence charts, interaction diagrams, event traces, or actor diagrams, are illustrations of a system’s global message-passing activity over time, and a pictorial aid to understanding the system’s behavior.");
		tmfd.addCrossRefs(seqDiag);
		tmfd.addCrossRefs(scedDiag);
		tmfd.addCrossRefs(sceneDiag);
		tmfd.addCrossRefs(interDiag);
		tmfd.addCrossRefs(transFlows);
		tmfd.addTools(isvis);
		tmfd.addTools(programExpl);
		tmfd.setRepresented_data("Interaction, trace data");
		tmfd.setAdvantages("Clearly depicts interaction");
		tmfd.setDisadvantages("Not scalable");
		tmfd.setScreenshot_url("https://www.dropbox.com/sh/gwbdfn209txa3kj/njsQvqFGJJ");
		
		// sced d
		scedDiag.setId(4);
		scedDiag.setName("SCED scenario diagram");
		scedDiag.setDescription("A sequence diagram with extra syntax for loop constructs, conditional statements.");
		scedDiag.addCrossRefs(seqDiag);
		scedDiag.addCrossRefs(interDiag);
		scedDiag.addCrossRefs(tmfd);
		scedDiag.addCrossRefs(sceneDiag);
		scedDiag.addCrossRefs(transFlows);
		scedDiag.addTools(sced);
		scedDiag.addTools(shimba);
		scedDiag.setRepresented_data("Interaction, trace data.");
		scedDiag.setAdvantages("For loop syntax to avoid repetition.");
		scedDiag.setDisadvantages("Not scalable enough for architectural level. Can be a bit confusing because of too many conditional statements, loop constructs. ");
		scedDiag.setScreenshot_url("https://www.dropbox.com/sh/uqrhp3dv1vl327z/MwBiNAnV1H");
		
		
		// scene d
		sceneDiag.setId(5);
		sceneDiag.setName("Scene diagram");
		sceneDiag.setDescription("A bit different syntax from sequence diagram. Includes collapsing of calls, partitioning of long calls to view 1 part at once and allocation of instances.");
		sceneDiag.addCrossRefs(seqDiag);
		sceneDiag.addCrossRefs(interDiag);
		sceneDiag.addCrossRefs(tmfd);
		sceneDiag.addCrossRefs(scedDiag);
		sceneDiag.addCrossRefs(transFlows);
		sceneDiag.addTools(scene);
		sceneDiag.setRepresented_data("Interaction, trace data.");
		sceneDiag.setAdvantages("Clearly depicts interaction. Introduces some syntax to hide unnecessary traces and/or objects to deal with scalability.");
		sceneDiag.setDisadvantages("Still not scalable enough for architecture level.");
		sceneDiag.setScreenshot_url("https://www.dropbox.com/sh/rh4cc76vyr53kbe/WrurxgnZwI");
		
		// trans flows
		transFlows.setId(6);
		transFlows.setName("Transaction flows view");
    	transFlows.setDescription("Same syntax as sequence diagram. Also includes static structure, as Packages on horizontal axis.");
    	transFlows.addCrossRefs(seqDiag);
    	transFlows.addCrossRefs(interDiag);
    	transFlows.addCrossRefs(tmfd);
    	transFlows.addCrossRefs(scedDiag);
    	transFlows.addCrossRefs(sceneDiag);
    	transFlows.addTools(wsn);
    	transFlows.setRepresented_data("Interaction, trace data.");
    	transFlows.setAdvantages("Introduces horizontal abstraction by collapsing packages, thereby hiding respective traces.");
    	transFlows.setDisadvantages("Not scalable enough for architecture level.");
    	transFlows.setScreenshot_url("https://www.dropbox.com/sh/3l9gssyo42wqqhq/Pg-_gFKnSN");
    
    	// call tree
    	callTree.setId(7);
    	callTree.setName("Call tree");
    	callTree.setDescription("No need");
    	callTree.addCrossRefs(progTracView);
    	callTree.addCrossRefs(refPattView);
    	callTree.addCrossRefs(execPattView);
    	callTree.addCrossRefs(coneTree);
    	callTree.addTools(ovation);
    	callTree.addTools(fireDetect);
    	callTree.addTools(seat);
    	callTree.addTools(zinsight);
    	callTree.addTools(jinsight);
    	callTree.addTools(kieker);
    	callTree.addTools(diver);
    	callTree.addTools(programExpl);
    	callTree.setRepresented_data("Interaction, trace data");
    	callTree.setAdvantages("depicts interaction very good, clear representation of instances of classes. Saves more space than sequence diagrams.");
    	callTree.setDisadvantages("Not scalable without abstraction.");
    	callTree.setScreenshot_url("https://www.dropbox.com/sh/aq0lgqbg94xcsjl/Fii277QdpJ");
    	
    	// Execution pattern view
    	execPattView.setId(8);
    	execPattView.setName("Execution pattern view");
    	execPattView.setDescription("Call tree like structure, horizontal. Time increases over the y-axis, down. The height of each node represents the duration it was executed.  Nodes can be collapsed.");
    	execPattView.addCrossRefs(callTree);
    	execPattView.addCrossRefs(progTracView);
    	execPattView.addCrossRefs(refPattView);
    	execPattView.addCrossRefs(coneTree);
    	execPattView.addTools(ovation);
    	execPattView.setRepresented_data("Interaction, trace data.");
    	execPattView.setAdvantages("Dealing with scalability very good. Pattern recognition for abstraction.");
    	execPattView.setDisadvantages("Still not scalable enough for architecture level.");
    	execPattView.setScreenshot_url("https://www.dropbox.com/sh/yj0iq5fm4lcujug/Q9Q4gNy3D9");
    	

    	// Reference pattern view
    	refPattView.setId(9);
    	refPattView.setName("Reference pattern view");
    	refPattView.setDescription("Very similar to #8(Execution pattern view). It also groups instances of objects.");
    	refPattView.addCrossRefs(callTree);
    	refPattView.addCrossRefs(progTracView);
    	refPattView.addCrossRefs(execPattView);
    	refPattView.addCrossRefs(coneTree);
    	refPattView.addTools(jinsight);
    	refPattView.addTools(zinsight);
    	refPattView.setRepresented_data("static and trace data");
    	refPattView.setAdvantages("Groups instances to save space and groups methos calls from each instance into one edge.");
    	refPattView.setDisadvantages("Not scalable enough for the whole trace data. Needs to be sliced.");
    	refPattView.setScreenshot_url("https://www.dropbox.com/sh/yj0iq5fm4lcujug/Q9Q4gNy3D9");
    	
    	// Program traces view
    	progTracView.setId(10);
    	progTracView.setName("Program traces view");
    	progTracView.setDescription("Call tree like structure, allows collapse and expand on nodes. Eclipse style. Very similar to #8(Execution pattern view). It also displays separate threads. However it only dispays list of traces, and not the whole call stack. It is used in synchronization with sequence diagram, in the given tool.");
    	progTracView.addCrossRefs(callTree);
    	progTracView.addCrossRefs(refPattView);
    	progTracView.addCrossRefs(execPattView);
    	progTracView.addCrossRefs(coneTree);
    	progTracView.addTools(diver);
    	progTracView.setRepresented_data("static and trace data");
    	progTracView.setAdvantages("Collapsing saves space.");
    	progTracView.setDisadvantages("Not scalable. Not enough information is displayed(call stack)");
    	progTracView.setScreenshot_url("https://www.dropbox.com/sh/sbihwtc4gj9ym5c/LqVsEtnkWH");
    	
    	// Dependency graph
    	depGraph.setId(11);
    	depGraph.setName("Dependency graph");
    	depGraph.setDescription("Dependencies contain: method calls, imports, or associations between entities.Interactions among objects constitute runtime dependencies among these system entities, which can be described using weighted directed dependency graphs: each entity is assigned a node and each dependency relation an edge; the edge is directed from an entity using a particular service to the entity providing that service; the edges are augmented with the total number of call actions among the respective entities observed in the considered set of traces.");
    	depGraph.addCrossRefs(mapMetView);
    	depGraph.addCrossRefs(interGraph);
    	depGraph.addCrossRefs(polymetricView);
    	depGraph.addCrossRefs(circBundleView);
    	depGraph.addCrossRefs(threeDHeb);
    	depGraph.addCrossRefs(seqContextView);
    	depGraph.addCrossRefs(histoView);
    	depGraph.addTools(kieker);
    	depGraph.addTools(cerno);
    	depGraph.addTools(shimba);
    	depGraph.addTools(rigi);
    	depGraph.addTools(jinsight);
    	depGraph.addTools(avid);
    	depGraph.addTools(extravis);
    	depGraph.addTools(shrimp);
    	depGraph.addTools(codeCrawl);
    	depGraph.addTools(codeMap);
    	depGraph.addTools(vitrail);
    	depGraph.addTools(stan);
    	depGraph.addTools(zinsight);
    	depGraph.setRepresented_data("Mostly represent static structure data, but may also include method call or class allocation dependency data");
    	depGraph.setAdvantages("A good way to represent static information together with dynamic. Possible to introduce several levels of abstraction.");
    	depGraph.setDisadvantages("Does not include time information.");
    	depGraph.setScreenshot_url("https://www.dropbox.com/sh/7cvx3nun2ig85by/nwDqKtjtZu");
    	
    	// Map metaphor views
    	mapMetView.setId(12);
    	mapMetView.setName("Map metaphor views");
    	mapMetView.setDescription("Number of components in more abstract components represented by size of nodes, edge width, number of dependencies.");
    	mapMetView.addCrossRefs(depGraph);
    	mapMetView.addCrossRefs(interGraph);
    	mapMetView.addCrossRefs(polymetricView);
    	mapMetView.addCrossRefs(circBundleView);
    	mapMetView.addCrossRefs(threeDHeb);
    	mapMetView.addCrossRefs(seqContextView);
    	mapMetView.addCrossRefs(histoView);
    	mapMetView.addTools(cerno);
    	mapMetView.setRepresented_data("tatic structure and trace data");
    	mapMetView.setAdvantages("Good for representing several levels of abstraction.");
    	mapMetView.setDisadvantages("Is not suitable for representing more fine grained, concrete level data(no time information).");
    	mapMetView.setScreenshot_url("https://www.dropbox.com/s/vuyo6bdwjdlfs2q/dependency_graph_cerno.png");

    	// Interaction graph
    	interGraph.setId(13);
    	interGraph.setName("Interaction graph");
    	interGraph.setDescription("Simple directed graph. Labels on the edges represent # of dependencies between components.");
    	interGraph.addCrossRefs(depGraph);
    	interGraph.addCrossRefs(interGraph);
    	interGraph.addCrossRefs(polymetricView);
    	interGraph.addCrossRefs(circBundleView);
    	interGraph.addCrossRefs(threeDHeb);
    	interGraph.addCrossRefs(seqContextView);
    	interGraph.addCrossRefs(histoView);
    	interGraph.addTools(programExpl);
    	interGraph.setRepresented_data("Static structure and trace data");
    	interGraph.setAdvantages("Good for representing several levels of abstraction.");
    	interGraph.setDisadvantages("Is not suitable for representing more fine grained, concrete level data(no time information).");
    	interGraph.setScreenshot_url("");
    	
    	// Polymetric view
    	polymetricView.setId(14);
    	polymetricView.setName("Polymetric view");
    	polymetricView.setDescription("A tree structure representing dependencies between components. Nodes are components, edges, dependencies(e.g. calls). The height, width, color, etc of nodes are mapped to static metrics(lines of code, etc).");
    	polymetricView.addCrossRefs(depGraph);
    	polymetricView.addCrossRefs(mapMetView);
    	polymetricView.addCrossRefs(interGraph);
    	polymetricView.addCrossRefs(circBundleView);
    	polymetricView.addCrossRefs(threeDHeb);
    	polymetricView.addCrossRefs(seqContextView);
    	polymetricView.addCrossRefs(histoView);
    	polymetricView.addTools(codeCrawl);
    	polymetricView.setRepresented_data("Static structure data");
    	polymetricView.setAdvantages("Provides clear representation of classes and their importance in the context, their level of dependences to each other.");
    	polymetricView.setDisadvantages("Does not support aggregation of several components into more abstract ones, because size and other properties of nodes are mapped to concrete level metrics.");
    	polymetricView.setScreenshot_url("https://www.dropbox.com/s/drks1g7jdfjh415/dependency_graph_polymetric.png");
    	
    	// Circular bundle view
    	circBundleView.setId(15);
    	circBundleView.setName("Circular bundle view");
    	circBundleView.setDescription("All components and their child components(structured by package) are gathered around a circle, where edges represent dependencies. Components can be collapsed and hide their child comp-s.");
    	circBundleView.addCrossRefs(depGraph);
    	circBundleView.addTools(extravis);
    	circBundleView.setRepresented_data("Static structure and trace data");
    	circBundleView.setAdvantages("Circular form and collapsing helps to cope with scalability.");
    	circBundleView.setDisadvantages("Does not support abstraction very well, because the structure is defined according to packages.");
    	circBundleView.setScreenshot_url("https://www.dropbox.com/s/o2f1i3h858jacdi/dependency_extravis.png");
    	

    	// 3D-HEB
    	threeDHeb.setId(16);
    	threeDHeb.setName("3D-Hierarchical Eedge Bundle(3D-HEB)");
    	threeDHeb.setDescription("Same as circular bundle, but instead of circle, the components are structured in a CodeCity.");
    	threeDHeb.addCrossRefs(depGraph);
    	threeDHeb.addTools(vitrail);
    	threeDHeb.setRepresented_data("Static structure and trace data");
    	threeDHeb.setAdvantages("Provides clear representation of classes and their importance in the context, their level of dependences to each other.");
    	threeDHeb.setDisadvantages("Does not support abstraction very well, because the structure is defined according to packages.");
    	threeDHeb.setScreenshot_url("https://www.dropbox.com/sh/pthecapc2dlesyz/6WPH4E3tco");

    	
    	// Sequence context view
    	seqContextView.setId(17);
    	seqContextView.setName("Sequence context view");
    	seqContextView.setDescription("A tree like structure, showing the context, the method in the trace was called, meaning several steps of method calls before this call. Edges represent aggregation of method calls. They can be reveiled.");
    	seqContextView.addCrossRefs(depGraph);
    	seqContextView.addTools(zinsight);
    	seqContextView.setRepresented_data("Static structure and trace data");
    	seqContextView.setAdvantages("A good way to represent static information together with dynamic. Aggregation of dependencies(method calls) helps to cope with massive trace data.");
    	seqContextView.setDisadvantages("Does not include time information. Is only suitable for subset of trace data, not scalable otherwise.");
    	seqContextView.setScreenshot_url("https://www.dropbox.com/sh/2eekbm9g9y1vccl/jvjdUBRUKt");

    	// Histogram view
    	histoView.setId(18);
    	histoView.setName("Histogram view");
    	histoView.setDescription("The Histogram view is a basic visualization of resource consumption (CPU and memory) in terms of classes, instances, and methods. It gives an overview of hot spots in a program’s execution. The Histogram view arranges information by class.");
    	histoView.addCrossRefs(depGraph);
    	histoView.addTools(jinsight);
    	histoView.setRepresented_data("Static structure and trace data");
    	histoView.setAdvantages("");
    	histoView.setDisadvantages("Pretty messed up, hard to read visuals.");
    	histoView.setScreenshot_url("https://www.dropbox.com/s/hhvz9qktxoh8o17/dependency_graph_jinsight.png");

    	// Markov chains
    	markov.setId(19);
    	markov.setName("Markov chains");
    	markov.addCrossRefs(depGraph);
    	markov.addTools(kieker);
    	markov.setRepresented_data("interaction, trace data.");
    	
    	// State chart
    	stateChart.setId(20);
    	stateChart.setName("StateChart");
    	stateChart.addTools(sced);
    	stateChart.setRepresented_data("interaction, trace data.");

    	// Information mural diagram
    	infoMural.setId(21);
    	infoMural.setName("Information mural diagram");
    	infoMural.setDescription("When zoomed in, mural views are either message flow diagrams or call tree like structure. But, usually comprises all of trace data in one view compressed up to 1 pixel. Is mostly used for navigation over the data. ");
    	infoMural.addCrossRefs(execView);
    	infoMural.addCrossRefs(massSeqView);
    	infoMural.addCrossRefs(execBar);
    	infoMural.addTools(jinsight);
    	infoMural.addTools(zinsight);
    	infoMural.addTools(gammatella);
    	infoMural.addTools(extravis);
    	infoMural.addTools(isvis);
    	infoMural.setRepresented_data("interaction, trace data.");
    	infoMural.setAdvantages("Very useful to display massive amounts of trace data. Helps user detect different patterns in data.");
    	infoMural.setDisadvantages("Cannot be used separately, as does not include enough information.");
    	infoMural.setScreenshot_url("https://www.dropbox.com/sh/eet2bjqtabv6yg9/k_JffwORnG");

    	// Execution view
    	execView.setId(22);
    	execView.setName("Execution view");
    	execView.setDescription("Call tree based information mural, Vertical.");
    	execView.addCrossRefs(infoMural);
    	execView.addCrossRefs(massSeqView);
    	execView.addCrossRefs(execBar);
    	execView.addTools(jinsight);
    	execView.addTools(zinsight);
    	execView.setRepresented_data("interaction, trace data.");
    	execView.setAdvantages("Very useful to display massive amounts of trace data. Helps user detect different patterns in data.");
    	execView.setDisadvantages("Cannot be used separately, as does not include enough information.");
    	execView.setScreenshot_url("https://www.dropbox.com/sh/rvag6yq9mmb56ae/X1qhRkDdSu");

    	// Massive sequence view
    	massSeqView.setId(23);
    	massSeqView.setName("Massive sequence view");
    	massSeqView.setDescription("Sequence diagram based information mural, Vertical.");
    	massSeqView.addCrossRefs(infoMural);
    	massSeqView.addCrossRefs(execView);
    	massSeqView.addCrossRefs(execBar);
    	massSeqView.addTools(extravis);
    	massSeqView.setRepresented_data("interaction, trace data.");
    	massSeqView.setAdvantages("Very useful to display massive amounts of trace data. Helps user detect different patterns in data.");
    	massSeqView.setDisadvantages("Cannot be used separately, as does not include enough information.");
    	massSeqView.setScreenshot_url("https://www.dropbox.com/sh/xnfvos8qiz2shm1/Ewhu55XD45");
    	

    	// Execution Bar
    	execBar.setId(24);
    	execBar.setName("Execution Bar");
    	execBar.setDescription("Sequence diagram based information mural, Horizontal.");
    	execBar.addCrossRefs(infoMural);
    	execBar.addCrossRefs(execView);
    	execBar.addCrossRefs(massSeqView);
    	execBar.addTools(extravis);
    	execBar.setRepresented_data("interaction, trace data.");
    	execBar.setAdvantages("Very useful to display massive amounts of trace data. Helps user detect different patterns in data.");
    	execBar.setDisadvantages("Cannot be used separately, as does not include enough information.");
    	execBar.setScreenshot_url("https://www.dropbox.com/s/ljz81491l9ygrid/execution bar.png");

    	// CodeViewer
    	codeViewer.setId(25);
    	codeViewer.setName("CodeViewer");
    	codeViewer.setDescription("Source code mural, colored and zoomed out.");
    	codeViewer.addCrossRefs(infoMural);
    	codeViewer.addCrossRefs(execView);
    	codeViewer.addCrossRefs(massSeqView);
    	codeViewer.addCrossRefs(execBar);
    	codeViewer.addTools(gammatella);
    	codeViewer.setRepresented_data("static data(code, colored).");
    	codeViewer.setScreenshot_url("https://www.dropbox.com/s/52f4skkqpyatl0v/code_viewer.png");

    	
    	
    	// Order is important
    	techniques.add(interDiag);
    	techniques.add(seqDiag);
    	techniques.add(tmfd);
    	techniques.add(sceneDiag);
    	techniques.add(transFlows);
    	techniques.add(scedDiag);
    	techniques.add(callTree);
    	techniques.add(execPattView);
    	techniques.add(refPattView);
    	techniques.add(progTracView);
    	techniques.add(depGraph);
    	techniques.add(mapMetView);
    	techniques.add(interGraph);
    	techniques.add(polymetricView);
    	techniques.add(circBundleView);
    	techniques.add(threeDHeb);
    	techniques.add(seqContextView);
    	techniques.add(histoView);
    	techniques.add(markov);
    	techniques.add(stateChart);
    	techniques.add(infoMural);
    	techniques.add(execView);
    	techniques.add(massSeqView);
    	techniques.add(execBar);
    	techniques.add(codeViewer);
    	
    }
    */
}

