/**
  DA-NRW Software Suite | SIP-Builder
  Copyright (C) 2013 Historisch-Kulturwissenschaftliche Informationsverarbeitung
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

class CopyUtility {

	private ProgressManager progressManager = null;
	private int jobId;
	private List<String> forbiddenFileExtensions = null;
	private SIPFactory.SipBuildingProcess sipBuildingProcess;
	
	public boolean copyDirectory(File source, File destination, List<String> forbiddenFileExtensions) throws IOException {
		
		this.forbiddenFileExtensions = forbiddenFileExtensions;
		return copy(source, destination);
	}	
	
	public boolean copyDirectory(File source, File destination) throws IOException, IllegalArgumentException {
		
		return copy(source, destination);
	}	
	
	private boolean copy(File source, File destination) throws IOException, IllegalArgumentException {

		if (sipBuildingProcess.isAborted())
			return false;
		
		if(source.isDirectory()) {
			
			if(!(source.getName().equals("thumbs")) && !(source.getName().equals("thumbnails"))) {

				if(!destination.exists())
					destination.mkdir();

				String files[] = source.list();

				for (String file : files) {
					File srcFile = new File(source, file);
					File destFile = new File(destination, file);

					if (!copy(srcFile,destFile))
						return false;
				}
			}

		}else{

			if (!source.getName().equals(".DS_Store") &&
				(source.length() != 0) &&
				checkFileExtension(source.getName())) {
			
				InputStream input = new FileInputStream(source);
				OutputStream output = new FileOutputStream(destination); 

				byte[] buffer = new byte[2048];
				int length;
				
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
			
				input.close();
				output.close();
			}
			
			if (progressManager != null)
				progressManager.copyProgress(jobId, FileUtils.sizeOf(source));
		}
		
		return true;
	}

	private boolean checkFileExtension(String fileName) {

		if (forbiddenFileExtensions != null) {
			for (String extension : forbiddenFileExtensions) { 
				if (fileName.endsWith(extension))
					return false;
			}
		}

		return true;
	}
	
	public void setProgressManager(ProgressManager progressManager) {
		this.progressManager = progressManager;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public void setSipBuildingProcess(SIPFactory.SipBuildingProcess sipBuildingProcess) {
		this.sipBuildingProcess = sipBuildingProcess;
	}
	
}
