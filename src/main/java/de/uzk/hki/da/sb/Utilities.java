/*
  DA-NRW Software Suite | SIP-Builder
  Copyright (C) 2014 Historisch-Kulturwissenschaftliche Informationsverarbeitung
  Universität zu Köln

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package de.uzk.hki.da.sb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import de.uzk.hki.da.sb.ContractRights.ConversionCondition;
import de.uzk.hki.da.sb.PublicationRights.Law;
import de.uzk.hki.da.sb.PublicationRights.TextType;
import de.uzk.hki.da.sb.SIPFactory.KindOfSIPBuilding;


class Utilities {
	
	private static final String sipBuilderVersion = "0.6.4";
	
	public static KindOfSIPBuilding translateKindOfSIPBuilding(String kindofSIPBuildingName) {

		if (kindofSIPBuildingName.equals("Mehrere SIPs aus Unterordnern des Quellverzeichnisses erstellen"))
			return KindOfSIPBuilding.MULTIPLE_FOLDERS;
		else if (kindofSIPBuildingName.equals("Einzelnes SIP aus dem Quellverzeichnis erstellen"))
			return KindOfSIPBuilding.SINGLE_FOLDER;
		
		return null;
	}
	
	public static ConversionCondition translateConversionCondition(String conversionConditionName) {

		if (conversionConditionName.equals("Keine"))
			return ConversionCondition.NONE;
		else if (conversionConditionName.equals("Über Migration informieren"))
			return ConversionCondition.NOTIFY;
		else if (conversionConditionName.equals("Zustimmung für Migration einholen"))
			return ConversionCondition.CONFIRM;
		
		return null;
	}
	
	public static String translateConversionCondition(ConversionCondition conversionConditionEnum) {

		if (conversionConditionEnum == ConversionCondition.NONE)
			return "Keine";
		else if (conversionConditionEnum == ConversionCondition.NOTIFY)
			return "Über Migration informieren";
		else if (conversionConditionEnum == ConversionCondition.CONFIRM)
			return "Zustimmung für Migration einholen";
		
		return null;
	}
	
	public static TextType translateTextType(String textType) {

		if (textType.equals("Fußzeile"))
			return TextType.footer;
		else if (textType.equals("Wasserzeichen (oben)"))
			return TextType.north;
		else if (textType.equals("Wasserzeichen (mittig)"))
			return TextType.center;
		else if (textType.equals("Wasserzeichen (unten)"))
			return TextType.south;
		
		return null;
	}
	
	public static String translateTextType(TextType textType) {

		if (textType == TextType.footer)
			return "Fußzeile";
		else if (textType == TextType.north)
			return "Wasserzeichen (oben)";
		else if (textType == TextType.center)
			return "Wasserzeichen (mittig)";
		else if (textType == TextType.south)
			return "Wasserzeichen (unten)";
		
		return null;
	}
	
	public static String translateTextTypePosition(TextType textType) {

		if (textType == TextType.footer)
			return "Fußzeile";
		else if (textType == TextType.north)
			return "oben";
		else if (textType == TextType.center)
			return "mittig";
		else if (textType == TextType.south)
			return "unten";
		
		return null;
	}	
	
	public static Law translateLaw(String lawName) {

		if (lawName.equals("ePflicht"))
			return Law.EPFLICHT;
		else if (lawName.equals("UrhG DE"))
			return Law.URHG_DE;
		
		return null;
	}
	
	public static String translateLaw(Law lawEnum) {

		if (lawEnum == Law.EPFLICHT)
			return "ePflicht";
		else if (lawEnum == Law.URHG_DE)
			return "UrhG DE";
		
		return null;
	}
	
	/** 
	  * Checks for zero byte files
	  * @return true if zero byte files exist inside a given folder, false otherwise
	  */
	public static boolean checkForZeroByteFiles(File folder, String sipName, MessageWriter messageWriter) {
		
		Collection<File> files = FileUtils.listFiles(folder, null, true);
		
		for (File file : files) {
			if (file.length() == 0) {
				String zeroByteFileEntry = file.getName() + " (" + sipName + ")";
				messageWriter.addZeroByteFile(zeroByteFileEntry);
			}				
		}
		
		if (messageWriter.getZeroByteFiles().size() > 0)
			return true;
		else
			return false;		
	}
	
	public static String readFile(File file) throws Exception {

		Reader reader;
		try {
			reader = new FileReader(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			throw new Exception("Couldn't create reader", e);
		}
		String modsText = "";

		try {
			for (int c; (c = reader.read() ) != -1; )
				modsText += (char) c;

			reader.close();								

		} catch (IOException e) {
			throw new Exception("Couldn't read file " + file.getAbsolutePath(), e);
		}

		return modsText;		
	}

	public static void writeFile(File outputFile, String text) throws Exception {

		Writer writer;
		try {
			writer = new FileWriter(outputFile);
		} catch (IOException e) {
			throw new Exception("Couldn't create writer", e);
		}

		try {
			writer.write(text);
			writer.close();

		} catch (IOException e) {
			throw new Exception("Couldn't write to file " + outputFile.getAbsolutePath(), e);
		}		
	}
	
	public static String getSipBuilderVersion() {
		return sipBuilderVersion;
	}
}
