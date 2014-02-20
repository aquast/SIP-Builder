/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import org.junit.Test;

/**
 * @author aquast
 *
 */
public class ServiceTests {

	/**
	 * 
	 */
	public ServiceTests() {
		// TODO Auto-generated constructor stub
	}

	
	@Test public void testConvertFromUrl(){
		ConvertFromUrl cU = new ConvertFromUrl();
		
		
		String fileListUrl = "http://nyx.hbz-nrw.de/hkiSB/InListFile.txt";
		String paramFileUrl = "http://nyx.hbz-nrw.de/hkiSB/sipBuilderParams.txt";
		String rightsFileUrl = "http://nyx.hbz-nrw.de/hkiSB/standardRights.xml";
		cU.postConvertFromUrl(fileListUrl, paramFileUrl, rightsFileUrl);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceTests servTest = new ServiceTests();
		servTest.testConvertFromUrl();
		

	}

}
