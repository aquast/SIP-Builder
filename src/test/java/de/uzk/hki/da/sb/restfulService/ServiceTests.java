/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author aquast
 *
 */
public class ServiceTests {

	// Initiate Logger for TestRestClient
	private static Logger log = Logger.getLogger(ServiceTests.class);

	/**
	 * 
	 */
	public ServiceTests() {
		// TODO Auto-generated constructor stub
	}

	
	@Test public void testCreateSingleSip(){
		CreateSingleSip cU = new CreateSingleSip();
		
		
		String fileListUrl = "http://nyx.hbz-nrw.de/hkiSB/InListFile.txt";
		String paramFileUrl = "http://nyx.hbz-nrw.de/hkiSB/sipBuilderParams.txt";
		String rightsFileUrl = "http://nyx.hbz-nrw.de/hkiSB/standardRights.xml";

		//fileListUrl = "http://localhost/hkiSB/filelist.txt";
		//paramFileUrl = "http://localhost/hkiSB/param.txt";
		//rightsFileUrl = "http://localhost/hkiSB/standardRights.xml";
		SipBuilderResult result = cU.postCreateSingleSip(fileListUrl, paramFileUrl, rightsFileUrl);
		
		log.info(result.getExitStateInt());
		log.info(result.getSipFileUrl());


	}
	
	@Test public void testCreateNamedSingleSip(){
		CreateSingleSip cU = new CreateSingleSip();
		
		
		String fileListUrl = "http://nyx.hbz-nrw.de/hkiSB/InListFile.txt";
		String paramFileUrl = "http://nyx.hbz-nrw.de/hkiSB/sipBuilderParams.txt";
		String rightsFileUrl = "http://nyx.hbz-nrw.de/hkiSB/standardRights.xml";
		String packageName = "testPackage";

		//fileListUrl = "http://localhost/hkiSB/filelist.txt";
		//paramFileUrl = "http://localhost/hkiSB/param.txt";
		//rightsFileUrl = "http://localhost/hkiSB/standardRights.xml";
		
		SipBuilderResult result = cU.postCreateNamedSingleSip(fileListUrl, paramFileUrl, rightsFileUrl, packageName);
		
		log.info(result.getExitStateInt());
		log.info(result.getSipFileUrl());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceTests servTest = new ServiceTests();
		servTest.testCreateSingleSip();
		servTest.testCreateNamedSingleSip();
		

	}

}
