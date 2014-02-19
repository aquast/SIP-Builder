/**
 * 
 */
package de.uzk.hki.da.sb.restfulClient;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.uzk.hki.da.sb.restfulService.Configuration;

/**
 * @author aquast
 *
 */
public class ClientTests {

	// Initiate Logger for TestRestClient
	private static Logger log = Logger.getLogger(ClientTests.class);
	
	private String uri = Configuration.getServiceUrl();

	
	/**
	 * 
	 */
	public ClientTests() {
		// TODO Auto-generated constructor stub
	}

	
	@Test public void testConvertFromUrl(){
		Client client = createClient();
		WebResource wResource = client.resource(uri + "api/convertFromUrl");
		wResource = wResource.queryParam("fileList", "http://nyx.hbz-nrw.de/hkiSB/InListFile.txt")
			.queryParam("parameterFile", "http://nyx.hbz-nrw.de/hkiSB/sipBuilderParams.txt")
			.queryParam("rights", "http://nyx.hbz-nrw.de/hkiSB/standardRights.xml");
		
		log.info(wResource);
		log.info(wResource.post(String.class).toString());
		
		
		
	}

	private Client createClient(){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		return client;
	}

		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientTests cTest = new ClientTests();
		cTest.testConvertFromUrl();
		
	}

}
