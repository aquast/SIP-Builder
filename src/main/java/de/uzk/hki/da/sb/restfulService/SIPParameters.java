/**
 * PdfAPilotParameters.java - This file is part of the DiPP Project by hbz
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

import java.util.Properties;

import org.apache.log4j.Logger;

import de.hbz_nrw.www.pdfaconverter.ServerImpl.Configuration;
import de.hbz_nrw.www.pdfaconverter.types.CompliancyLevelType;
import de.hbz_nrw.www.pdfaconverter.types.ConvertFromUrl;
import de.hbz_nrw.www.pdfaconverter.types.HtmlOptionType;
import de.hbz_nrw.www.pdfaconverter.types.ParameterType;
import de.hbz_nrw.www.pdfaconverter.types.ReportFormatType;
import de.hbz_nrw.www.pdfaconverter.types.ReportLangType;
import de.hbz_nrw.www.pdfaconverter.types.ReportTriggerType;

/**
 * Class PdfAPilotParameters
 * 
 * <p><em>Title: </em></p>
 * <p>Description: </p>
 * 
 * @author aquast, email
 * creation date: 22.01.2010
 *
 */
public class SIPParameters {

	// Initiate Logger for PdfAPilotParameters
	private static Logger log = Logger.getLogger(SIPParameters.class);
	private static ParameterType defaultParam = new ParameterType();
	private static Properties paramProp = new Properties();

		
	public static ParameterType createParamType(Properties prop){
		
		paramProp = prop;
		ParameterType param = new ParameterType();
		
		// define Processing Params
		if(paramProp.containsKey("returnOnlyValidPdfA")){
			param.setReturnOnlyValidPDFA(paramProp.getProperty("returnOnlyValidPdfA").matches("true"));
		}
		if(paramProp.containsKey("quickProcessing")){
			param.setQuickProcessing(paramProp.getProperty("quickProcessing").matches("true"));
		}
		if(paramProp.containsKey("analyseOnly")){
			param.setAnalyseOnly(paramProp.getProperty("analyseOnly").matches("true"));
		}
		if(paramProp.containsKey("createEpub")){
			param.setCreateEpub(paramProp.getProperty("createEpub").matches("true"));
		}
		if(paramProp.containsKey("forceConversionReconvert")){
			param.setForceConversionReconvert(paramProp.getProperty("forceConversionReconvert").matches("true"));
		}
		if(paramProp.containsKey("forceConversionPagesToImages")){
			param.setForceConversionPagesToImages(paramProp.getProperty("forceConversionPagesToImages").matches("true"));
		}
		if(paramProp.containsKey("forceConversionDocToImages")){
			param.setForceConversionDocToImages(paramProp.getProperty("forceConversionDocToImages").matches("true"));
		}
		
		if(paramProp.containsKey("compliancyLevel")){
			if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value1.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value1);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value2.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value2);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value3.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value3);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value4.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value4);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value5.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value5);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value6.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value6);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value7.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value7);
			}
			else if(paramProp.getProperty("compliancyLevel").equals(CompliancyLevelType.value8.getValue())){
				param.setCompliancyLevel(CompliancyLevelType.value8);
			}
		}

		// define Report Params
		if(paramProp.containsKey("htmlReport")){
			if(paramProp.getProperty("htmlReport").matches("true")){
				param.setReportFormat(new ReportFormatType[]{ReportFormatType.HTML});
			}
		}
		if(paramProp.containsKey("xmlReport")){
			if(paramProp.getProperty("xmlReport").matches("true")){
				param.setReportFormat(new ReportFormatType[]{ReportFormatType.XML});
			}
		}
		if(paramProp.containsKey("mhtReport")){
			if(paramProp.getProperty("mhtReport").matches("true")){
				param.setReportFormat(new ReportFormatType[]{ReportFormatType.MHT});
			}
		}
		
		if(paramProp.containsKey("reportLang")){
			if(paramProp.getProperty("reportLang").equals(ReportLangType.DE.getValue())){
				param.setReportLang(ReportLangType.DE);
			}
			else if(paramProp.getProperty("reportLang").equals(ReportLangType.EN.getValue())){
				param.setReportLang(ReportLangType.EN);
			}
			else if(paramProp.getProperty("reportLang").equals(ReportLangType.FR.getValue())){
				param.setReportLang(ReportLangType.FR);
			}
			else if(paramProp.getProperty("reportLang").equals(ReportLangType.ES.getValue())){
				param.setReportLang(ReportLangType.ES);
			}
			else if(paramProp.getProperty("reportLang").equals(ReportLangType.IT.getValue())){
				param.setReportLang(ReportLangType.IT);
			}
			else if(paramProp.getProperty("reportLang").equals(ReportLangType.JA.getValue())){
				param.setReportLang(ReportLangType.JA);
			}			
		}
		
		if(paramProp.containsKey("reportTrigger")){

			if(paramProp.getProperty("reportTrigger").equals(ReportTriggerType.ALWAYS.getValue())){
				param.setReportTrigger(ReportTriggerType.ALWAYS);
			}
			else if(paramProp.getProperty("reportTrigger").equals(ReportTriggerType.NEVER.getValue())){
				param.setReportTrigger(ReportTriggerType.NEVER);
			}
			else if(paramProp.getProperty("reportTrigger").equals(ReportTriggerType.IFPDFA.getValue())){
				param.setReportTrigger(ReportTriggerType.IFPDFA);
			}
			else if(paramProp.getProperty("reportTrigger").equals(ReportTriggerType.IFNOPDFA.getValue())){
				param.setReportTrigger(ReportTriggerType.IFNOPDFA);
			}
			
		}

		if(paramProp.containsKey("htmlOpenResult")){
			if(paramProp.getProperty("htmlOpenResult").matches("true")){
				param.setHtmlReportOptions(new HtmlOptionType[]{HtmlOptionType.OPENRESULT});
			}
		}

		if(paramProp.containsKey("htmlNoIcons")){
			if(paramProp.getProperty("htmlNoIcons").matches("true")){
				param.setHtmlReportOptions(new HtmlOptionType[]{HtmlOptionType.NOICONS});
			}
		}
		
		if(paramProp.containsKey("htmlNoDetails")){
			if(paramProp.getProperty("htmlNoDetails").matches("true")){
				param.setHtmlReportOptions(new HtmlOptionType[]{HtmlOptionType.NODETAILS});
			}
		}

		if(paramProp.containsKey("htmlNoCorrection")){
			if(paramProp.getProperty("htmlNoCorrection").matches("true")){
				param.setHtmlReportOptions(new HtmlOptionType[]{HtmlOptionType.NOCORRECTION});
			}
		}
		

		return param;

	}

	
	private static void createDefaultProperties(){
		
		// set Processing Params
		paramProp.setProperty("returnOnlyValidPdfA", "true");
		paramProp.setProperty("quickProcessing", "true");
		paramProp.setProperty("analyseOnly", "false");
		paramProp.setProperty("createEpub", "false");
		
		paramProp.setProperty("compliancyLevel", "1b");
		
		
		//set Reporting Params
		paramProp.setProperty("reportTrigger", "ALWAYS");
		paramProp.setProperty("reportLang", "DE");
		paramProp.setProperty("htmlReport", "true");
		paramProp.setProperty("htmlOpenResult", "true");
		paramProp.setProperty("htmlNoIcons", "false");
		paramProp.setProperty("xmlReport", "false");
		paramProp.setProperty("mhtReport", "false");
		
		
		
	}
	

	/**
	 * <p><em>Title: Parse the ParameterType Object</em></p>
	 * <p>Description: method generates an String output from all Callas Parameters given 
	 * as Elements from ParameterType by WebService Operation calls.</p>
	 * 
	 * @param paramType <code>parameterType</code> a complex Data Type that provides all needed parameters for 
	 * the Callas PDF A Tool 
	 * @param fileIdent <code>String</code> fileIdentis required to give reportFile an unique file name 
	 * @return <code>String</code> with all given parameters parsed from xml
	 */
	public static String createParameterString(String fileIdent, ParameterType paramType){
		StringBuffer paramBuffer = new StringBuffer();
		
		// set Parameter Strings
		if(paramType.getReturnOnlyValidPDFA() && paramType.getReturnOnlyValidPDFA() == true){
			paramBuffer.append(" --onlypdfa");
		}
		
		if(paramType.getAnalyseOnly() && paramType.getAnalyseOnly() == true){
			paramBuffer.append(" --analyse");
		}
		
		if(paramType.getCreateEpub() && paramType.getCreateEpub() == true){
			paramBuffer.append(" --createepub " + fileIdent);
		}

		if(paramType.getQuickProcessing() && paramType.getQuickProcessing() == true){
			paramBuffer.append(" --quick");
		}

		if(paramType.getCompliancyLevel()!=null){
			paramBuffer.append(" --level=" + paramType.getCompliancyLevel());
		}
		
		if(paramType.getForceConversionReconvert() && paramType.getForceConversionReconvert() == true){
			paramBuffer.append(" --forceconversion_reconvert");
		}

		if(paramType.getForceConversionPagesToImages() && paramType.getForceConversionPagesToImages() == true){
			paramBuffer.append(" --forceconversion_pagestoimages");
		}

		if(paramType.getForceConversionDocToImages() && paramType.getForceConversionDocToImages() == true){
			paramBuffer.append(" --forceconversion_doctoimages");
		}

		if(paramType.getReportLang()!=null){
			paramBuffer.append(" --language=" + paramType.getReportLang());
		}


		if(paramType.getReportFormat() != null){
			for(int i=0; i < paramType.getReportFormat().length; i++){
				paramBuffer.append(" --report=" + paramType.getReportFormat()[i]);
				if(paramType.getReportTrigger()!=null){
					paramBuffer.append("," + paramType.getReportTrigger());
				}
				if(paramType.getReportFormat()[i].equals(ReportFormatType.HTML) && paramType.getHtmlReportOptions() != null){
					for(int j=0; j < paramType.getHtmlReportOptions().length; j++){
						paramBuffer.append("," + paramType.getHtmlReportOptions()[j]);
					}
				}
				paramBuffer.append(",PATH="+ Configuration.getResultDirPath()+ fileIdent.replace(".pdf", "." + paramType.getReportFormat()[i].toString().toLowerCase()));
			}
		}
		


		return paramBuffer.toString();
	}
	
	public static String createParameterString(String fileIdent, ConvertFromUrl convFromUrl){

		StringBuffer paramBuffer = new StringBuffer();

		if(convFromUrl.getCompliancyLevel() != null){
			String param = "--level=" + convFromUrl.getCompliancyLevel();
			paramBuffer.append(param);
		}

		if(convFromUrl.getReportType() != null){
			String param = " --report=" + convFromUrl.getReportType();
			paramBuffer.append(param);
		}
		return paramBuffer.toString();
				
	}


	/**
	 * @param defaultParam the defaultParam to set
	 */
	public static void setDefaultParam(ParameterType defaultParam) {
		SIPParameters.defaultParam = defaultParam;
	}

	/**
	 * @param defaultParam the defaultParam to set
	 */
	public static void setDefaultProperties(Properties ParamProp) {
		paramProp = ParamProp;
	}

	/**
	 * @return the defaultParam
	 */
	public static Properties getDefaultProperties() {
		createDefaultProperties();
		return paramProp;
	}

}
