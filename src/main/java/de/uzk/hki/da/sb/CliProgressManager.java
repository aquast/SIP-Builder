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

class CliProgressManager extends ProgressManager {
	
	private long copiedFilesFromListCount = 0; 
	
	@Override
	public void abort() {
		System.out.println("\nSIP-Erstellungsvorgang abgebrochen");
	}

	@Override
	public void createStartMessage() {
		System.out.println("\n\nSIP-Erstellung läuft...");
	}
	
	@Override
	public void startJob(int id) {
		
		SIPCreationJob job = jobMap.get(id);
		job.initialTotalProgress = totalProgress;

		updateProgressBar();
	}
	
	@Override
	public void copyProgress(int id, long processedData) {
		
		super.copyProgress(id, processedData);
		updateProgressBar();	
	}
	
	@Override
	public void premisProgress(int id, double progress) {
		
		super.premisProgress(id, progress);
		updateProgressBar();
	}
	
	@Override
	public void bagitProgress(int id, double progress) {
		
		super.bagitProgress(id, progress);
		updateProgressBar();
	}
	
	@Override
	public void archiveProgress(int id, long archivedData) {
		
		super.archiveProgress(id, archivedData);
		updateProgressBar();	
	}
	
	public void copyFilesFromListProgress() {
		
		copiedFilesFromListCount++;
		totalProgress = ((double) copiedFilesFromListCount / (double) totalSize) * 100;
		updateProgressBar();
	}
	
	@Override
	public void skipJob(int id) {
		super.skipJob(id);
		updateProgressBar();
	}
	
	@Override
	public void deleteTempProgress(int id, double progress) {
		
		super.deleteTempProgress(id, progress);
		updateProgressBar();
	}
	
	@Override
	public void createSuccessMessage(boolean skippedFiles) {
		
		super.createSuccessMessage(skippedFiles);
		updateProgressBar();
		System.out.println("\n\nDie SIP-Erstellung wurde erfolgreich abgeschlossen.");
		if (skippedFiles)
			System.out.println("Bereits existierende SIPs wurden nicht neu erstellt. " +
							   "Starten Sie den SIP-Builder mit der Option -alwaysOverwrite, um " + 
							   "sämtliche SIPs neu zu generieren und existierende SIPs gleichen Namens " +
							   "im Zielordner zu überschreiben.");
	}
	
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
	
	public void setTotalSize(long size) {
		totalSize = size;
	}
}
