/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: method adds Parameters to SIPFactory, using SipBuilderParam</p>
	 * 
	 * @param sbParam
	 * @param sipFactory
	 * @return 
	 */
	public SIPFactory setParam(SipBuilderParam  sbParam, SIPFactory sipFactory){
		
		// first set default Params
    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.MULTIPLE_FOLDERS);
    	sipFactory.setCreateCollection(false);
    	sipFactory.setIgnoreZeroByteFiles(true);
    	sipFactory.setCompress(true);

		// set mandatory Params
    	sipFactory.setSourcePath(sbParam.getProperty("source"));
		sipFactory.setDestinationPath(sbParam.getProperty("destination"));
		
		if(sbParam.getProperties().containsKey("modSingleOrMultiple") 
				&& sbParam.getProperty("modSingleOrMultiple").equals("multiple")){
	    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.MULTIPLE_FOLDERS);			
		}

		if(sbParam.getProperties().containsKey("modSingleOrMultiple") 
				&& sbParam.getProperty("modSingleOrMultiple").equals("single")){
	    	sipFactory.setKindofSIPBuilding(SIPFactory.KindOfSIPBuilding.SINGLE_FOLDER);
	    	
		}

		if(sbParam.getProperties().containsKey("filelist")){
			sipFactory.setKindofSIPBuilding(sbParam.getProperty("filelist"));
		}

		if(sbParam.getProperties().containsKey("siplist")){
			sipFactory.setKindofSIPBuilding(sbParam.getProperty("siplist"));
		}

		if(sbParam.getProperties().containsKey("ignoreFileExtension")){
			String[] ignoreExt = sbParam.getProperty("ignoreFileExtension").split(";");
			ArrayList<String> ignoreList = new ArrayList<String>();
			
			//sipFactory.setForbiddenFileExtensions((sbParam.getProperty("ignoreFileExtension"));
		}

		return sipFactory;
	}

}
