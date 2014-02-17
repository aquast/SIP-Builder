/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import de.uzk.hki.da.sb.SIPFactory;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author aquast
 *
 */
public class SipBuilderRunner {

	private SIPFactory sip = new SIPFactory();
	private String exitState = null;

	/**
	 * 
	 */
	public SipBuilderRunner() {
		// TODO Auto-generated constructor stub
	}

	private SIPFactory configureSip(){
		SIPFactory sipC = new SIPFactory();
		
		
		return sipC;
	}
	
	public String getExitState() {
		// TODO Auto-generated method stub
		return exitState;
	}

	public void execute(Properties paramProp, ArrayList<String> localAssemblage){
		
		// TODO Auto-generated method stub
		sip = configureSip(); 
		
	}

}
