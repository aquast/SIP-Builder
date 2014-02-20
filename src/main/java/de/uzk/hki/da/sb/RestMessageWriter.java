/**
 * 
 */
package de.uzk.hki.da.sb;

import org.apache.log4j.Logger;

import de.uzk.hki.da.sb.MessageWriter;

/**
 * @author aquast
 *
 */
public class RestMessageWriter extends MessageWriter {

	/**
	 * 
	 */
	public RestMessageWriter() {
		// TODO Auto-generated constructor stub
	}
	
	// Initiate Logger for TestRestClient
	private static Logger log = Logger.getLogger(RestMessageWriter.class);



	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.MessageWriter#showMessage(java.lang.String)
	 */
	@Override
	public void showMessage(String message) {
		log.info(message); 
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.MessageWriter#showMessage(java.lang.String, int)
	 */
	@Override
	public void showMessage(String message, int type) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.MessageWriter#showOverwriteDialog(java.lang.String)
	 */
	@Override
	public UserInput showOverwriteDialog(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.MessageWriter#showCollectionOverwriteDialog(java.lang.String)
	 */
	@Override
	public UserInput showCollectionOverwriteDialog(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.uzk.hki.da.sb.MessageWriter#showZeroByteFileMessage()
	 */
	@Override
	public void showZeroByteFileMessage() {
		// TODO Auto-generated method stub

	}

}
