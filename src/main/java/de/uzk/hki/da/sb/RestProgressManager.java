/**
 * 
 */
package de.uzk.hki.da.sb;

import org.apache.log4j.Logger;

import de.uzk.hki.da.sb.ProgressManager.SIPCreationJob;
import de.uzk.hki.da.sb.restfulService.ServiceTests;

/**
 * @author aquast
 *
 */
public class RestProgressManager extends ProgressManager {

	// Initiate Logger for TestRestClient
	private static Logger log = Logger.getLogger(RestProgressManager.class);

	/**
	 * 
	 */
	public RestProgressManager() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.ProgressManager#abort()
	 */
	@Override
	public void abort() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.ProgressManager#createStartMessage()
	 */
	@Override
	public void createStartMessage() {
		log.info("starting SIP Processing");

	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.ProgressManager#startJob(int)
	 */
	@Override
	public void startJob(int id) {

		SIPCreationJob job = jobMap.get(id);
		job.initialTotalProgress = totalProgress;

		updateProgressBar();
	}
	
	/**
	 * Updates the CLI progress bar according to the current totalProgress value
	 */
	private void updateProgressBar() {
		
		System.out.print("\r\r[");

		for (int i = 0; i < 25; i++) {
			if (i < (int) (totalProgress / 4))
				System.out.print("=");
			else
				System.out.print(" ");
		}
		
		System.out.print("] " + Math.round(totalProgress * 10) / 10.0 + "%");
	}


}
