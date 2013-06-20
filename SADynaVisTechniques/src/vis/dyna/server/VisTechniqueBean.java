
   /*
2    * Copyright 2007 Kasper B. Graversen
3    * 
4    * Licensed under the Apache License, Version 2.0 (the "License");
5    * you may not use this file except in compliance with the License.
6    * You may obtain a copy of the License at
7    * 
8    *     http://www.apache.org/licenses/LICENSE-2.0
9    * 
10   * Unless required by applicable law or agreed to in writing, software
11   * distributed under the License is distributed on an "AS IS" BASIS,
12   * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
13   * See the License for the specific language governing permissions and
14   * limitations under the License.
15   */
package vis.dyna.server;

  
   
  public class VisTechniqueBean {
  	
  	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCrossReference() {
		return crossReference;
	}

	public void setCrossReference(String crossReference) {
		this.crossReference = crossReference;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getRepresentedData() {
		return representedData;
	}

	public void setRepresentedData(String representedData) {
		this.representedData = representedData;
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

	public String getScreenshotUrl() {
		return screenshotUrl;
	}

	public void setScreenshotUrl(String screenshotUrl) {
		this.screenshotUrl = screenshotUrl;
	}

	private String name;
	private String description;
	private String crossReference;
	private String tools;
	private String representedData;
	private String advantages;
	private String disadvantages;
	private String screenshotUrl;
  	
  	/**
 	 * Default Constructor.
  	 */
  	public VisTechniqueBean() {
 	}
  	
 	/**
  	 * Constructs a VisTechniqueBean.
 	 * "id";"name";"description";"crossReference";"tools";"representedData";"advantages";"disadvantages";"screenshotUrl"

  	 * @param id
  	 * @param name
 	 * @param description
  	 * @param crossReference
  	 * @param tools
  	 * @param representedData
 	 * @param advantages
  	 * @param disadvantages
  	 * @param screenshotUrl
 	 */
  	public VisTechniqueBean(final String id, final String name, final String description, final String crossReference, final String tools, final String representedData, final String advantages, final String disadvantages, final String screenshotUrl) {
  		
  		this.id = id;
  		this.name = name;
  		this.description = description;
  		this.crossReference = crossReference;
  		this.tools = tools;
  		this.representedData = representedData;
  		this.advantages = advantages;
  		this.disadvantages = disadvantages;
  		this.screenshotUrl = screenshotUrl ;
  	}
 	
  	
 	@Override
 	public int hashCode() {
 		final int prime = 31;
 		int result = super.hashCode();
 		result = prime * result + ((id == null) ? 0 : id.hashCode());
 		result = prime * result + ((name == null) ? 0 : name.hashCode());
 		result = prime * result + ((description == null) ? 0 : description.hashCode());
 		result = prime * result + ((crossReference == null) ? 0 : crossReference.hashCode());
 		result = prime * result + ((tools == null) ? 0 : tools.hashCode());
 		result = prime * result + ((representedData == null) ? 0 : representedData.hashCode());
 		result = prime * result + ((advantages == null) ? 0 : advantages.hashCode());
 		result = prime * result + ((disadvantages == null) ? 0 : disadvantages.hashCode());
 		result = prime * result + ((screenshotUrl == null) ? 0 : screenshotUrl.hashCode());
 		
 		return result;
	}
 	
 	@Override
 	public boolean equals(final Object obj) {
 		if( this == obj ) {
			return true;
 		}
 		if( !(obj instanceof VisTechniqueBean) ) {
 			return false;
 		}
 		
 		VisTechniqueBean visObj = (VisTechniqueBean) obj;
 		if( id == null ) {
 			if( visObj.id != null ) {
 				return false;
			}
		} else if( !id.equals(visObj.id) ) {
 			return false;
 		}
 		if( name == null ) {
 			if( visObj.name != null ) {
 				return false;
			}
		} else if( !name.equals(visObj.name) ) {
 			return false;
 		}
 		if( description == null ) {
 			if( visObj.description != null ) {
 				return false;
			}
		} else if( !description.equals(visObj.description) ) {
 			return false;
 		}
 		if( crossReference == null ) {
 			if( visObj.crossReference != null ) {
 				return false;
			}
		} else if( !crossReference.equals(visObj.crossReference) ) {
 			return false;
 		}
 		if( tools == null ) {
 			if( visObj.tools != null ) {
 				return false;
			}
		} else if( !tools.equals(visObj.tools) ) {
 			return false;
 		}
 		if( advantages == null ) {
 			if( visObj.advantages != null ) {
 				return false;
			}
		} else if( !advantages.equals(visObj.advantages) ) {
 			return false;
 		}
 		
 		if( disadvantages == null ) {
 			if( visObj.disadvantages != null ) {
 				return false;
			}
		} else if( !disadvantages.equals(visObj.disadvantages) ) {
 			return false;
 		}
 		if( representedData == null ) {
 			if( visObj.representedData != null ) {
 				return false;
			}
		} else if( !representedData.equals(visObj.representedData) ) {
 			return false;
 		}
 		if( screenshotUrl == null ) {
 			if( visObj.screenshotUrl != null ) {
 				return false;
			}
		} else if( !screenshotUrl.equals(visObj.screenshotUrl) ) {
 			return false;
 		}
 		
		return true;
 	}
 	
 	@Override
 	public String toString() {
 		return String
 			.format(
 				"Technique [id:%s, name:%s, description:%s, crossRef:%s, tools:%s, represData:%s, avds:%s, disAdvs:%s, url:%s]",
 				getId(), getName(), getDescription(),getCrossReference(), getTools(), getRepresentedData(), getAdvantages(), getDisadvantages(), getScreenshotUrl());
 	}
	
 }
