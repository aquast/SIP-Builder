/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Andres Quast
 *
 */
@XmlRootElement
public class SIPBuilderResult {

	private String inputFileUrl = null;
	private String resultFileUrl = null;
	private String exitState = null;
	private String reportFileUrl = null;
	private String compliancyLevel = null;
	
}
