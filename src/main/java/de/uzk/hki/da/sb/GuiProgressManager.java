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

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

class GuiProgressManager extends ProgressManager {
	
	private JProgressBar progressBar;
	private JLabel progressDisplay;
	private JLabel stepDisplay;
	
	public GuiProgressManager(JProgressBar progressBar, JLabel progressDisplay, JLabel stepDisplay) {
		
		this.progressBar = progressBar;
		this.progressDisplay = progressDisplay;
		this.stepDisplay = stepDisplay;
	}

	@Override
	public void reset() {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setEnabled(true);
				progressBar.setIndeterminate(false);
				progressBar.setValue(0);
				stepDisplay.setText("");
			}});

		super.reset();
	}

	@Override
	public void abort() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setEnabled(false);
				progressBar.setIndeterminate(false);
				if (progressBar.getValue() == 100) {
					progressBar.setValue(progressBar.getValue() - 1);
					progressBar.setValue(progressBar.getValue() + 1);			
				} else {		
					progressBar.setValue(progressBar.getValue() + 1);
					progressBar.setValue(progressBar.getValue() - 1);
				}
				progressDisplay.setText("SIP-Erstellungsvorgang abgebrochen");
				stepDisplay.setText("");
			}});
	}
	
	@Override
	public void createStartMessage() {
	}

	@Override
	public void startJob(int id) {
		SIPCreationJob job = jobMap.get(id);
		job.initialTotalProgress = totalProgress;
		
		final int jobId = id;
		final String packageName = job.packageName;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				if (jobId == -1)
					progressDisplay.setText("Erstelle Lieferung " + "\"" + packageName + "\"");
				else
					progressDisplay.setText("Erstelle SIP aus Ordner " + "\"" + packageName + "\"");
			}});
	}

	@Override
	public void copyProgress(int id, long processedData) {
		
		super.copyProgress(id, processedData);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
				stepDisplay.setText("Kopiere Daten...");
			}});
	}
	
	@Override
	public void premisProgress(int id, double progress) {
		
		super.premisProgress(id, progress);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
				stepDisplay.setText("Erzeuge Premis-Datei...");
			}});
	}
	
	@Override
	public void bagitProgress(int id, double progress) {
		
		super.bagitProgress(id, progress);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(true);
				stepDisplay.setText("Erstelle Bagit...");
			}});
	}
	
	@Override
	public void archiveProgress(int id, long archivedData) {
		
		super.archiveProgress(id, archivedData);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
				stepDisplay.setText("Erstelle Archivdatei...");
			}});
	}
	
	@Override
	public void skipJob(int id) {
		super.skipJob(id);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
			}});
	}
	
	@Override
	public void deleteTempProgress(int id, double progress) {
		
		super.deleteTempProgress(id, progress);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
				stepDisplay.setText("Lösche temporäre Daten...");
			}});
	}
	
	@Override
	public void createSuccessMessage(boolean skippedFiles) {

		super.createSuccessMessage(skippedFiles);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue((int) totalProgress);
				progressBar.setIndeterminate(false);
				stepDisplay.setText("");

				progressDisplay.setText("Die SIP-Erstellung wurde erfolgreich abgeschlossen.");
			}});
	}
}
