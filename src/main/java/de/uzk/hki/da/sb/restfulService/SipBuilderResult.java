/**
 * SipBuilderResult.java - This file is part of the DiPP Project by hbz
 * Library Service Center North Rhine Westfalia, Cologne 
 *
 * -----------------------------------------------------------------------------
 *
 * <p><b>License and Copyright: </b>The contents of this file are subject to the
 * D-FSL License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at <a href="http://www.dipp.nrw.de/dfsl/">http://www.dipp.nrw.de/dfsl/.</a></p>
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.</p>
 *
 * <p>Portions created for the Fedora Repository System are Copyright &copy; 2002-2005
 * by The Rector and Visitors of the University of Virginia and Cornell
 * University. All rights reserved."</p>
 *
 * -----------------------------------------------------------------------------
 *
 */
package de.uzk.hki.da.sb.restfulService;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class SipBuilderResult
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 13.02.2014
 *
 */
@XmlRootElement
public class SipBuilderResult {

	
	private String inputFileUrl = null;
	private String resultFileUrl = null;
	private int exitStateInt = 0;
	private String exitState = null;

	
	
	/**
	 * 
	 */
	public SipBuilderResult() {
		// TODO Auto-generated constructor stub
	}
	
	
}
