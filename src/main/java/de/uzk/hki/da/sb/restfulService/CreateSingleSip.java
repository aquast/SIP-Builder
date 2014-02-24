/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;


/**
 * @author Andres Quast
 *
 */
@Path("/api")
public class CreateSingleSip {

	// Initiate Logger for class
	private static Logger log = Logger.getLogger(CreateSingleSip.class);

	private  String fileIdent = null;

	@Path("/createSingleSip")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public SipBuilderResult postCreateSingleSip(@QueryParam("fileList") String fileListUrl, 
			@QueryParam("parameterFile") String paramFileUrl,
			@QueryParam("rights") String rightsFileUrl){
		SipBuilderResult response = null;

		// copy parameter file to server
		fileIdent = TimePrefix.getTimePrefix();
		String paramFileName = FileUtil.saveUrlToFile(fileIdent + "/param.txt", paramFileUrl);
		log.info(paramFileName);

		// read in parameters from file
		Properties builderProp = readProperties(paramFileName);

		// copy filelist file to server
		String fileListName = FileUtil.saveUrlToFile(fileIdent + "/filelist.txt", fileListUrl);

		// copy rights file to server
		String fileRights = FileUtil.saveUrlToFile(fileIdent + "/rights.xml", rightsFileUrl);
		
		response = createSingleSip(builderProp, fileListName);
		
		return response;
	}

	@Path("/createNamedSingleSip")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public SipBuilderResult postCreateNamedSingleSip(@QueryParam("fileList") String fileListUrl, 
			@QueryParam("parameterFile") String paramFileUrl,
			@QueryParam("rights") String rightsFileUrl,
			@QueryParam("packageName") String packageName
			){
		SipBuilderResult response = null;

		// copy parameter file to server
		fileIdent = TimePrefix.getTimePrefix();
		String paramFileName = FileUtil.saveUrlToFile(fileIdent + "/param.txt", paramFileUrl);
		log.info(paramFileName);

		// read in parameters from file
		Properties builderProp = readProperties(paramFileName);
		
		// insert PackageName into Properties
		builderProp.setProperty("packageName", packageName);

		// copy filelist file to server
		String fileListName = FileUtil.saveUrlToFile(fileIdent + "/filelist.txt", fileListUrl);

		// copy rights file to server
		String fileRights = FileUtil.saveUrlToFile(fileIdent + "/rights.xml", rightsFileUrl);
		
		
		response = createSingleSip(builderProp, fileListName);
		
		return response;
	}
	
	
	private Properties readProperties(String fileName){
		
		// first get default properties
		Properties builderProp = SipBuilderParam.getDefaultProperties();
		
		// set actual server paths
		builderProp.setProperty("source", Configuration.getTempDirPath() + fileIdent + "/source/" );
		builderProp.setProperty("destination", Configuration.getTempDirPath() + fileIdent + "/result/" );
		builderProp.setProperty("rights", Configuration.getTempDirPath() + fileIdent + "/rights.xml");
		// now try to read in Properties from ParamFile
        try {
    		log.info("Reading Parameters File: " + fileName);
            FileInputStream fis = null;;
			fis = new FileInputStream(new File(fileName));
			BufferedInputStream bis = new BufferedInputStream(fis);
			builderProp.load(bis);
		} catch (Exception e) {
			log.warn("Parameter file could not be loaded, using default parameter");
			//e.printStackTrace();
		}
		return builderProp;
		
	}
	
	
	private SipBuilderResult createSingleSip(Properties paramProp, String fileList){
		
		
		SipBuilderResult builderResult = null;
		
		// copy remote Object to temporary Server Directory
		ArrayList<String[]> remoteAssemblage = null;
		try {
			remoteAssemblage = FileUtil.readFromFile(fileList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> localAssemblage = new ArrayList<String>();
		
		Iterator<String[]> aIt =  remoteAssemblage.iterator();
		while(aIt.hasNext()){
			String remoteUrl = aIt.next()[0]; 
			String fileName = new String();
			
			if(remoteUrl.startsWith("http")){
				fileName = FileUtil.saveUrlToFile(fileIdent + "/source/" + FileUtil.getRemoteFileName(remoteUrl), remoteUrl);
			}else{
				log.warn("Url: " + remoteUrl + " is usable for this functionality\n"
						+ "please provide an HTTP-Url");
			}
			localAssemblage.add(fileName);
			
		}
		
		// delegate SIP building
		SipBuilderRunner sbRunner = new SipBuilderRunner();
		sbRunner.execute(paramProp);

		return builderResult;

		
	}
	
}
