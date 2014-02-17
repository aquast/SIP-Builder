/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import de.uzk.hki.da.sb.SIPFactory;

/**
 * @author aquast
 *
 */
public class SipBuilderRunner {

	private SIPFactory sip = new SIPFactory();

	/**
	 * 
	 */
	public SipBuilderRunner() {
		// TODO Auto-generated constructor stub
	}

	public void executeSipBuilder(){

		sip = configureSip(); 

	}

	private SIPFactory configureSip(){
		SIPFactory sipC = new SIPFactory();
		
		
		return sipC;
	}
	
}
