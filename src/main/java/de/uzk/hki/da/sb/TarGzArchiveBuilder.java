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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

class TarGzArchiveBuilder {
	
	private ProgressManager progressManager;
	private int jobId;
	private SIPFactory.SipBuildingProcess sipBuildingProcess;

	public boolean archiveFolder(File srcFolder, File destFile, boolean includeFolder)
			throws Exception {

		FileOutputStream fOut = null;
		BufferedOutputStream bOut = null;
		GzipCompressorOutputStream gzOut = null;
		TarArchiveOutputStream tOut = null;

		try {
			fOut = new FileOutputStream(destFile);
			bOut = new BufferedOutputStream(fOut);
			gzOut = new GzipCompressorOutputStream(fOut);
			tOut = new TarArchiveOutputStream(gzOut, "UTF-8");

			tOut.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			tOut.setBigNumberMode(2);

			if (includeFolder) {
				if (!addFileToTarGZ(tOut,srcFolder,""))
					return false;
			}
			else
			{
				File children[] = srcFolder.listFiles();

				for (int i = 0; i < children.length; i++) {
					if (!addFileToTarGZ(tOut,children[i],""))
						return false;
				}
			}
		}
		finally
		{
			tOut.finish();

			tOut.close();
			gzOut.close();
			bOut.close();
			fOut.close();
		}

		return true;
	}

	/**
	 * @param tOut
	 * @param the actual file that should be added
	 * @param base
	 * @throws IOException
	 */
	private boolean addFileToTarGZ(TarArchiveOutputStream tOut, File file, String base) throws IOException{

		if (sipBuildingProcess.isAborted())
			return false;
		
		String entryName = base + file.getName();

		TarArchiveEntry entry = (TarArchiveEntry) tOut.createArchiveEntry(file, entryName);
		tOut.putArchiveEntry(entry);

		if (file.isFile()){
			FileInputStream fis = new FileInputStream(file);
			IOUtils.copy(fis, tOut);
			tOut.closeArchiveEntry();
			fis.close();
			
			progressManager.archiveProgress(jobId, FileUtils.sizeOf(file));
		}

		if (file.isDirectory()){
			tOut.closeArchiveEntry();

			File children[] = file.listFiles();
			if (children == null)
				return true;

			for (int i = 0; i < children.length; i++) {

				if (!addFileToTarGZ(tOut, children[i], entryName + "/"))
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
