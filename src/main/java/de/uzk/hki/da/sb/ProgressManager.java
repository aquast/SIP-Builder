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

import java.util.HashMap;
import java.util.Map;

abstract class ProgressManager {

	Map<Integer, SIPCreationJob> jobMap = new HashMap<Integer, SIPCreationJob>();
	long totalSize = 0;
	double totalProgress = 0.0;
	
	final double copyPercentage = 0.2;
	final double premisPercentage = 0.05;
	final double bagitPercentage = 0.2;
	final double archivePercentage = 0.5;
	final double deleteTempPercentage = 0.05;
	
	public void addJob(int id, String packageName, long folderSize) {
		
		SIPCreationJob job = new SIPCreationJob(id, packageName, folderSize);
		if (folderSize == 0)
			job.copyProgress = 100.0;
		
		jobMap.put(id, job);
		
		totalSize += folderSize;
	}
	
	public void reset() {
		jobMap.clear();
		totalProgress = 0.0;
	}
	
	abstract public void abort();
	
	public void calculateProgressParts(boolean createCollection) {

		for (SIPCreationJob job : jobMap.values()) {
			
			if (job.id == -1)
				job.progressPart = 5.0;
			else if (job.folderSize == 0) {
				if (jobMap.values().size() == 1)
					job.progressPart = 100.0;
				else
					job.progressPart = 0.0;
			}
			else {
				if (createCollection)
					job.progressPart = (((double) job.folderSize / (double) totalSize) * 95.0);
				else
					job.progressPart = (((double) job.folderSize / (double) totalSize) * 100.0);
			}
		}
	}
	
	abstract public void createStartMessage();
	
	abstract public void startJob(int id);
	
	public void copyProgress(int id, long processedData) {
		
		SIPCreationJob job = jobMap.get(id);
		
		job.processedData += processedData;
		if (job.folderSize == 0)
			job.copyProgress = 0;
		else
			job.copyProgress = (job.processedData / (double) job.folderSize) * (job.progressPart * copyPercentage);
		
		totalProgress = job.getProgress();
	}
	
	public void premisProgress(int id, double progress) {
		
		SIPCreationJob job = jobMap.get(id);
		
		job.premisProgress += progress * (job.progressPart * premisPercentage) / 100;

		totalProgress = job.getProgress();
	}
	
	public void bagitProgress(int id, double progress) {
			
		SIPCreationJob job = jobMap.get(id);
		
		if (id == -1)
			job.bagitProgress += progress * job.progressPart / 100;
		else
			job.bagitProgress += progress * (job.progressPart * bagitPercentage) / 100;
				
		totalProgress = job.getProgress();
	}
	
	public void archiveProgress(int id, long archivedData) {
				
		SIPCreationJob job = jobMap.get(id);
		
		job.archivedData += archivedData;
		if (job.folderSize == 0)
			job.archiveProgress = 0;
		else
			job.archiveProgress = ((double) job.archivedData / (double) job.folderSize) * (job.progressPart * archivePercentage);
		
		totalProgress = job.getProgress();
	}
	
	public void deleteTempProgress(int id, double progress) {
		
		SIPCreationJob job = jobMap.get(id);
		
		job.deleteTempProgress += progress * (job.progressPart * deleteTempPercentage) / 100;
				
		totalProgress = job.getProgress();
	}
	
	public void skipJob(int id) {
		
		SIPCreationJob job = jobMap.get(id);
		
		totalProgress = job.initialTotalProgress + job.progressPart; 
	}
	
	public void createSuccessMessage(boolean skippedFiles) {
		totalProgress = 100.0;
	}
	
	public void setJobFolderSize(int id, long folderSize) {
		
		jobMap.get(id).folderSize = folderSize;		
	}
	
	class SIPCreationJob {
		
		int id;
		String packageName;
		long folderSize;
		double copyProgress, premisProgress, bagitProgress, archiveProgress, deleteTempProgress;
		double progressPart;
		double initialTotalProgress;
		long processedData, archivedData;

		
		public SIPCreationJob(int id, String packageName, long folderSize) {
			
			this.id = id;
			this.packageName = packageName;
			this.folderSize = folderSize;
		}
		
		public double getProgress() {
			
			return initialTotalProgress +
				   copyProgress + 
				   premisProgress + 
				   bagitProgress + 
				   archiveProgress + 
				   deleteTempProgress;
		}
	}
	
}
