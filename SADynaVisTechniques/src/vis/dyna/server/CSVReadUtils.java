package vis.dyna.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import vis.dyna.shared.Technique;
import vis.dyna.shared.TechniqueNotExistException;
import vis.dyna.shared.Tool;

public class CSVReadUtils {

	private final static String CSV_FILENAME = "resources/Visualisation.csv";
	private final static Logger logger = Logger.getLogger(CSVReadUtils.class.getName());

	/**
	 * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
	 * columns are read as null (hence the NotNull() for mandatory columns).
	 * 
	 * @return the cell processors
	 */
	private static CellProcessor[] getProcessors() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new UniqueHashCode(), 	// id
	                new NotNull(), 			// name
	                new NotNull(),			// description
	                new Optional(), // crossRef
	                new Optional(), 				// tools
	                new Optional(), // represData
	                new Optional(), // advs
	                new Optional(), 				// disAdvs
	                new Optional(), // screenUrl
	        };
	        
	        return processors;
	}
	
	/**
	 * An example of reading using CsvBeanReader.
	 */
	public static Technique readWithCsvBeanReader(int id) throws TechniqueNotExistException {
		logger.info("CsvBeanReader, Trying to read csv file! id:" + id);
		ICsvBeanReader beanReader = null;
		Technique mainTech = null;
		try {
			logger.info("CsvBeanReader, inside try!, reading csv");
			beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME),
					CsvPreference.STANDARD_PREFERENCE);
			logger.info("CsvBeanReader, file is read into beanReader!");
			// the header elements are used to map the values to the bean (names
			// must match)
			final String[] header = beanReader.getHeader(true);
			final CellProcessor[] processors = getProcessors();
			logger.info("CSVReadUtils, going into while!");
			VisTechniqueBean tempTechnique;
			boolean found = false;
			while ((tempTechnique = (VisTechniqueBean) beanReader.read(VisTechniqueBean.class, header, processors)) != null) {
				logger.info("CSVReadUtils, Inside While id:" + tempTechnique.getId() + " to Int");
				Integer tempId = Integer.parseInt(tempTechnique.getId()); 
				logger.info("CSVReadUtils, Inside While read id:" + tempTechnique.getId() + " given id:" + id);
				if (tempId == id) { found = true;
					logger.info("CSVReadUtils, Inside While>if > found technique!");
					mainTech = new Technique();
					// set id
					mainTech.setId(id);
					// set name
					mainTech.setName(tempTechnique.getName());
					// set description
					mainTech.setDescription(tempTechnique.getDescription());
					// set cross references
					String crossRefStr = tempTechnique.getCrossReference();
					String[] crossRefArray = crossRefStr.split("\\|");
					for (String str : crossRefArray) {
						String[] crNameAndId = str.split(":");
						Technique crossRefTemp = new Technique();
						crossRefTemp.setId(Integer.parseInt(crNameAndId[1].trim()));
						crossRefTemp.setName(crNameAndId[0].trim());
						mainTech.addCrossRefs(crossRefTemp);
					}

					// set tools
					String toolsStr = tempTechnique.getTools();
					String[] toolsArray = toolsStr.split("\\|");
					for (String toolStr : toolsArray) {
						Tool tool = new Tool();
						tool.setName(toolStr.trim());
						mainTech.addTools(tool);
					}

					// set represented data
					mainTech.setRepresented_data(tempTechnique
							.getRepresentedData());

					// set advantages
					mainTech.setAdvantages(tempTechnique.getAdvantages());

					// set disadvantages
					mainTech.setDisadvantages(tempTechnique.getDisadvantages());

					// set screens url
					mainTech.setScreenshot_url(tempTechnique.getScreenshotUrl());

				}
			}
			
			if (!found) {
				if (beanReader != null) {
					try {
						beanReader.close();
					} catch (IOException e) {
						logger.info("CsvBeanReader, Problem closing beanReader!, IOException");
						e.printStackTrace();
					}
				}
				
				throw new TechniqueNotExistException("Selected technique could not be found!");
			}
			

		} catch (FileNotFoundException ex) {
			logger.info("CsvBeanReader, FileNotFOundEx.\n" + ex.getMessage());
			logger.info("CsvBeanReader, Closing beanReader!");
			if (beanReader != null) {
				try {
					beanReader.close();
				} catch (IOException e) {
					logger.info("CsvBeanReader, Problem closing beanReader!, IOException");
					e.printStackTrace();
				}
			}
			ex.printStackTrace();
		} catch (IOException ex) {
			logger.info("CsvBeanReader, IOException.\n" + ex.getMessage());
			ex.printStackTrace();
		}

		return mainTech;
	}
}
