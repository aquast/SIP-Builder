/**
  *
 */
package de.uzk.hki.da.sb.restfulService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class SipBuilderParam
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 13.02.2014
 *
 */
public class SipBuilderParam {

	// Initiate Logger for TimePrefix
	private static Logger log = Logger.getLogger(SipBuilderParam.class);
	
	private static Properties bProp = null;
	
	/**
	 * 
	 */
	public SipBuilderParam() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * <p><em>Title: </em></p>
	 * <p>Description: load default Properties from properties.file</p>
	 *  
	 */
	private static void loadDefaultProp(){
		bProp = new Properties();
		
		
		InputStream paramStream =  bProp.getClass().getResourceAsStream("/conf/defaultParam.properties");

		if(paramStream != null){
			try {
				bProp.load(paramStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				createDefaultProperties();
			} 
			
		}
	}
	
	private static void createDefaultProperties(){
		
		// set accessing Params makes no sense to set them here!
		bProp.setProperty("source", "http://localhost/sip"); // url to the directory containing content
		bProp.setProperty("filelist", "http://localhost/sip" + "/fileList.txt"); // url to txt-file with a line for each content file-url
		bProp.setProperty("siplist",  "http://localhost/sip" + "/sipList.xml"); // url to xml-file for batch Sip processing
		bProp.setProperty("destination",  "http://localhost/sip" + "/result/"); // url to xml-file for batch Sip processing
		
		
		//set processing Params
		bProp.setProperty("modSingleOrMultiple", "multiple"); // generate either one SIP for each subdirectory or a single SIP for all the sub tree
		// bProp.setProperty("single", "dummy SIPName"); // not supported by restful service!
		bProp.setProperty("collection", "http://localhost/sip" + "/result/collection"); // if is set, bundle created sips to one delivery 
		bProp.setProperty("modOverwrite", "never"); // should we overwrite an existing sip with the newly generated?		
		bProp.setProperty("ignoreFileExtension", "html;rtf;doc;odt"); // files that should not be part of sip
		bProp.setProperty("ignoreZeroByteFiles", "true");
	}

	public static Properties getDefaultProperties(){
		loadDefaultProp();
		return bProp;
	}
	
	public void setProperties(Properties prop){
		//lets have a working instance of SipBuilderParam if some Properties are missing
		loadDefaultProp();
		//overwrite defaults
		bProp = prop;
	}
	
	public Properties getProperties(){
		return bProp;
	}

	public void setProperty(String key, String value){
		bProp.setProperty(key, value);
	}
	
	public String getProperty(String key){
		return bProp.getProperty(key);
	}

	public String setParameterString(Properties prop){
		String paramString = null;
		Enumeration key = prop.keys();
		
		while(key.hasMoreElements()){
			
		}
		return paramString;
		
	}
	
	
}
