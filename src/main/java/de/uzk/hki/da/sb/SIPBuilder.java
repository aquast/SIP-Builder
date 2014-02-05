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

import java.awt.Color;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Main class; starts GUI or CLI mode
 * 
 * @author Thomas Kleinke
 */
class SIPBuilder {
	
	private static Logger logger = Logger.getRootLogger();	

	
    public static void main(String[] args) {
    	
        logger.setLevel(Level.ERROR);
        
        try {
        	if (SystemUtils.IS_OS_WINDOWS)
        		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "CP850"));
        	else
        		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return;
		}
    
	    String mainFolderPath = SIPBuilder.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    String confFolderPath, dataFolderPath;
	    try {
	    	mainFolderPath = URLDecoder.decode(mainFolderPath, "UTF-8");
			confFolderPath = new File(mainFolderPath).getParent() + File.separator + "conf";
			dataFolderPath = new File(mainFolderPath).getParent() + File.separator + "data";
		} catch (UnsupportedEncodingException e) {
			confFolderPath = "conf";
			dataFolderPath = "data";
		}
	    
	    if (args.length == 0)
	    	startGUIMode(confFolderPath, dataFolderPath);
	    else
		    startCLIMode(confFolderPath, dataFolderPath, args);
    }
    
    /**
     * Starts the SIP-Builder in GUI mode
     * 
     * @param confFolderPath Path to conf folder
     * @param dataFolderPath Path to data folder
     */
    private static void startGUIMode(String confFolderPath, String dataFolderPath) {
    	
    	try {
    		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	} catch (Exception e) {
    		return;
    	}

    	UIManager.put("Label.disabledForeground", Color.LIGHT_GRAY);
    	UIManager.put("ComboBox.disabledForeground", Color.LIGHT_GRAY);
    	UIManager.put("CheckBox.disabledText", Color.LIGHT_GRAY);

    	Gui gui = new Gui(confFolderPath, dataFolderPath);
    	gui.setBounds(100, 100, 750, 520);
    	gui.setResizable(false);
    	gui.setVisible(true);
    	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gui.setTitle("DA NRW SIP-Builder");
    }
    
    /**
     * Starts the SIP-Builder in CLI mode
     * 
     * @param confFolderPath Path to conf folder
     * @param dataFolderPath Path to data folder
     * @param args The CLI arguments passed over by the user
     */
    private static void startCLIMode(String confFolderPath, String dataFolderPath, String[] args) {
    
    	Cli cli = new Cli(confFolderPath, dataFolderPath, args);
    	int exitCode = cli.start();
    	
    	if (exitCode < 0)
    		exitCode = 0;
    	
    	System.exit(exitCode);
    }

}


