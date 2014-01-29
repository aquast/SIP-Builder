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

import java.util.ArrayList;
import java.util.List;

abstract class MessageWriter {
	
	enum UserInput { YES, NO, ALWAYS_OVERWRITE };
	
	List<String> zeroByteFiles = new ArrayList<String>();

	abstract public void showMessage(String message);
	abstract public void showMessage(String message, int type);
	
	abstract public UserInput showOverwriteDialog(String message);
	abstract public UserInput showYesNoDialog(String message); 
	
	abstract public void showZeroByteFileMessage();
	
	public void addZeroByteFile(String zeroByteFile) {
		zeroByteFiles.add(zeroByteFile);
	}
	
	public void resetZeroByteFiles() {
		zeroByteFiles.clear();
	}
	
	public List<String> getZeroByteFiles() {
		return zeroByteFiles;
	}
}
