/**
 * 
 */
package de.uzk.hki.da.sb.restfulService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @author aquast
 *
 */
public class FileUtilTests {

	// Initiate Logger for Class
	private static Logger log = Logger.getLogger(FileUtilTests.class);

	/**
	 * 
	 */
	public FileUtilTests() {
		// TODO Auto-generated constructor stub
	}

	@Test public void convertFileList(){
		
		InputStream testFile = getClass().getResourceAsStream("/RestfulServiceTests/InListFile.txt");
		
		System.out.println("Stream: " + testFile.toString());
		
		String filePath = System.getProperty("user.dir") + "/src/test/resources/RestfulServiceTests/InListFile.txt";
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			list = FileUtil.readFromFile(filePath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//log.error(e);
			e.printStackTrace();
		}

		int j = 0;

		Iterator<String[]> it = list.iterator();
		while(it.hasNext()){
			j++;
			String[] line = it.next();
			for (int i = 0; i < line.length ;i++){
				log.info("Zeile " + j + ": item " + i + " = " + line[i]);
				
			}
			log.info("Ende der Zeile\n");
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtilTests fuTest = new FileUtilTests();
		fuTest.convertFileList();

	}

}
