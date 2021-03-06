/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.uzk.hki.da.sb.RestProgressManager;
import de.uzk.hki.da.sb.SIPFactory;

/**
 * @author aquast
 *
 */
public class ParamMapper {

	/**
	 * 
	 */
	public ParamMapper() {
		// TODO Auto-generated constructor stub
	}
	
	// Initiate Logger for TestRestClient
	private static Logger log = Logger.getLogger(ParamMapper.class);

	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method adds Parameters to SIPFactory, using SipBuilderParam</p>
	 * 
	 * @param sbParam
	 * @param sipFactory
	 * @return 
	 */
	public static SIPFactory setParam(SipBuilderParam  sbParam, SIPFactory sipFactory){
		
		// first set default Params
    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.MULTIPLE_FOLDERS);
    	sipFactory.setCreateCollection(false);
    	sipFactory.setIgnoreZeroByteFiles(true);
    	sipFactory.setCompress(true);
    	sipFactory.setName("package");

		// set mandatory Params
    	sipFactory.setSourcePath(sbParam.getProperty("source"));
		log.info(sbParam.getProperty("destination"));
		sipFactory.setDestinationPath(sbParam.getProperty("destination"));
		sipFactory.setRightsSourcePremisFile(new File(sbParam.getProperty("rights")));
		
		// TODO clarify if this code block is required for Restful Services:
		if(sbParam.getProperties().containsKey("modSingleOrMultiple") 
				&& sbParam.getProperty("modSingleOrMultiple").equals("multiple")){
	    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.MULTIPLE_FOLDERS);			
		}
		if(sbParam.getProperties().containsKey("modSingleOrMultiple") 
				&& sbParam.getProperty("modSingleOrMultiple").equals("single")){
	    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.SINGLE_FOLDER);
	    	
		}

		// Set modSingleOrMultiple according the kind of Restful Service
		if(sbParam.getProperties().containsKey("filelist")){
			sipFactory.setKindofSIPBuilding(sbParam.getProperty("filelist"));
			sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.SINGLE_FOLDER);
	    	
		}

		if(sbParam.getProperties().containsKey("siplist")){
			sipFactory.setKindofSIPBuilding(sbParam.getProperty("siplist"));
			sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.MULTIPLE_FOLDERS);
		}

		if(sbParam.getProperties().containsKey("packageName") 
				&& !sbParam.getProperty("packageName").isEmpty()){
			sipFactory.setName(sbParam.getProperty("packageName"));
		}

		if(sbParam.getProperties().containsKey("ignoreFileExtension")){
			String[] ignoreExt = sbParam.getProperty("ignoreFileExtension").split(";");
			ArrayList<String> ignoreList = new ArrayList<String>();
			for(int i = 0; i < ignoreExt.length; i++){
				ignoreList.add(ignoreExt[i]);
			}
			sipFactory.setForbiddenFileExtensions(ignoreList);
		}
		
		return sipFactory;
	}

}
