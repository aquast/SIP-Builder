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
public class ConvertFromUrl {

	// Initiate Logger for class
	private static Logger log = Logger.getLogger(ConvertFromUrl.class);

	private  String fileIdent = null;

	@Path("/convertFromUrl")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public SipBuilderResult postConvertFromUrl(@QueryParam("fileList") String fileListUrl, 
			@QueryParam("parameterFile") String paramFileUrl,
			@QueryParam("rigths") String rightsFileUrl){
		SipBuilderResult response = null;

		// copy parameter file to server
		fileIdent = TimePrefix.getTimePrefix();
		String paramFileName = FileUtil.saveUrlToFile(fileIdent + "/param.txt", paramFileUrl);

		// read in parameters from file
		Properties builderProp = readProperties(paramFileName);

		// copy filelist file to server
		String fileListName = FileUtil.saveUrlToFile(fileIdent + "/filelist.txt", fileListUrl);

		
		response = convertFromUrl(builderProp, fileListName);
		
		return response;
	}

	/*
	@Path("/autoConf")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public SipBuilderResult postConvertFromUrl(@QueryParam("inputFile") String inputFileUrl){
		SipBuilderResult response = null;

		// first run
		InputStream paramStream =  this.getClass().getResourceAsStream("/conf/defaultParam1.cfg");

		if(paramStream == null){
			log.error("Failed loading defaultParams: not found in classpath");
		}
		FileUtil.saveInputStreamToTempFile(paramStream, "defaultParam1.txt");

		String jobIdent = TimePrefix.getTimePrefix();

		Properties paramProp = SipBuilderParam.getDefaultProperties();
		
        try {
    		log.info("Reading Parameters File");
            FileInputStream fis;
			fis = new FileInputStream(new File(Configuration.getTempDirPath() + "defaultParam1.txt"));
	        BufferedInputStream bis = new BufferedInputStream(fis);
			paramProp.load(bis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response = convertFromUrl(paramProp, inputFileUrl);
			
		return response;
	}
*/
	
	private Properties readProperties(String fileName){
		
		// first get default properties
		Properties builderProp = SipBuilderParam.getDefaultProperties();
		
		// set actual local paths
		builderProp.setProperty("source", Configuration.getTempDirPath() + "/" + fileIdent + "/source/" );
		builderProp.setProperty("destination", Configuration.getTempDirPath() + "/" + fileIdent + "/result/" );
		// now try to read in Properties from ParamFile
        try {
    		log.info("Reading Parameters File");
            FileInputStream fis;
			fis = new FileInputStream(new File(Configuration.getTempDirPath() + fileName));
	        BufferedInputStream bis = new BufferedInputStream(fis);
			builderProp.load(bis);
		} catch (Exception e) {
			log.warn("Parameter file could not be loaded, using default parameter");
			//e.printStackTrace();
		}
		return builderProp;
		
	}
	
	
	private SipBuilderResult convertFromUrl(Properties paramProp, String fileList){
		
		
		SipBuilderResult builderResult = null;
		
		// copy remote Object to temporary Directory
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
		
		
		//executeString = "cp " + Configuration.getTempfiledir() + fileName + " " + Configuration.getTempfiledir() + "result_" + fileName;
		
		SipBuilderRunner sbRunner = new SipBuilderRunner();
		sbRunner.execute(paramProp);

		/*
		builderResult.setInputFileUrl(inputFileUrl);

		String reportFile = Configuration.getResultDirPath() + fileName.replace(".pdf", "." + paramType.getReportFormat()[0].getValue().toLowerCase());

		log.info(reportFile);
		//append report if report exists
		if(new File(reportFile).isFile()){
			builderResult.setReportFileUrl(Configuration.getResultDirUrl() + fileName.replace(".pdf", "." + paramType.getReportFormat()[0].getValue().toLowerCase()));
		}else{
			builderResult.setReportFileUrl("no report file generated");
		}
		

		if(sbRunner.getExitState() != null && sbRunner.getExitState().equals("0")){
			pResultsetResultFileUrl(Configuration.getResultDirUrl() + fileName);
		}*/
		return builderResult;

		
	}
	
}
