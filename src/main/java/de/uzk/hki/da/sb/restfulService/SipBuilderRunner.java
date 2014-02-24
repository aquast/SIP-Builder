/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import de.uzk.hki.da.sb.RestMessageWriter;
import de.uzk.hki.da.sb.RestProgressManager;
import de.uzk.hki.da.sb.SIPFactory;

import java.util.Enumeration;
import java.util.Properties;

/**
 * @author aquast
 *
 */
public class SipBuilderRunner {

	private SIPFactory sip = new SIPFactory();
	private int exitState = -1;

	/**
	 * 
	 */
	public SipBuilderRunner() {
		// TODO Auto-generated constructor stub
	}

	private SIPFactory configureSip(SipBuilderParam sbParam){
		SIPFactory sipC = new SIPFactory();
		ParamMapper.setParam(sbParam, sipC);
		
		return sipC;
	}
	
	public int getExitState() {
		return exitState;
	}

	public void execute(Properties paramProp){
		
		SipBuilderParam sbParam = new SipBuilderParam();
		sbParam.setProperties(paramProp);
		
		RestProgressManager progressManager = new RestProgressManager(); 
		sip = configureSip(sbParam);
		RestMessageWriter rMessageWriter = new RestMessageWriter();
		sip.setMessageWriter(rMessageWriter);
		sip.setProgressManager(progressManager);
		
		sip.startSIPBuilding();
		
    	do { 
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
    	} while (sip.isWorking());
    	
    	exitState = sip.getReturnCodeInt();
    	rMessageWriter.showMessage("Finished SIP Building", exitState);

		
	}

}
