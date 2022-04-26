package boidsgame;

import java.io.IOException;
import java.util.List;

public interface FilehandlerInterface {
	/**
	 * A general storing method that will create a file if no file exists, appends or overwrites it.
	 * @param filename
	 * @param headLine
	 * @param data
	 * @param overwrite
	 * @throws IOException
	 */
	public void storeToFile(String filename, String headLine, String data, Boolean overwrite) throws IOException;
	// /**
	//  * A general reading method to read 
	//  * @param filename
	//  * @param file
	//  * @return
	//  */
	// public List<String> readFromFileByLogic(String filename, String file);
	
}
