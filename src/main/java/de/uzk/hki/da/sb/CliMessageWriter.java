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

class CliMessageWriter extends MessageWriter{

	UserInput standardAnswer = UserInput.NO;
	
	@Override
	public void showMessage(String message) {
		showMessage(message, 0);
	}
	
	@Override
	public void showMessage(String message, int type) {
		System.out.println(message);		
	}

	@Override
	public UserInput showOverwriteDialog(String message) {
		
		return standardAnswer;
	}
	
	@Override
	public UserInput showYesNoDialog(String message) {
		
		if (standardAnswer == UserInput.NO) {
			System.out.println("\nIm Zielverzeichnis existiert bereits eine Lieferung des gewählten Namens."); 
			System.out.println("Bitte löschen Sie die bestehende Lieferung oder wählen Sie einen anderen Namen.");
		}
		
		return standardAnswer;
	}
	
	public void showZeroByteFileMessage() {
		
		String message = "";
		if (zeroByteFiles.size() == 1) {
			message = "\nWARNUNG: Während der SIP-Erstellung wurde eine Datei der Größe 0 Byte gefunden:\n" 
					+ zeroByteFiles.get(0) + "\n" +					
					"Diese Datei wurde bei der SIP-Erstellung nicht berücksichtigt.";		
		}
		else {
			message = "\n\nWARNUNG: Während der SIP-Erstellung wurden mehrere Dateien der Größe 0 Byte gefunden:\n\n";
			for (String s : zeroByteFiles) {
				message += s;
				message += "\n";
			}				
			message += "\nDiese Dateien wurden bei der SIP-Erstellung nicht berücksichtigt.";		
		}
		System.out.println(message);
	}

	public void setStandardAnswer(UserInput standardAnswer) {
		this.standardAnswer = standardAnswer;
	}
}
