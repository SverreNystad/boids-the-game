package boidsgame;

import java.io.FileNotFoundException;
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
	/**
	 * A general reading method to get the text in file.
	 * @param filename filename with zufix.
	 * @param splitter any string.
	 * @return A list where each line is an array with the elements between the splitter is elements
	 * @throws FileNotFoundException If filename is not correct or does not exist throw.
	 */
	public List<String> readFromFileAndSplitBy(String filename, String splitter) throws FileNotFoundException;
	
}
