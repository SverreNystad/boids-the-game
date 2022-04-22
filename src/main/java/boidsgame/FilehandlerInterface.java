package boidsgame;

import java.io.IOException;
import java.util.List;

public interface FilehandlerInterface {
	
	public void storeToFile(String filename, String headLine, String data, Boolean overwrite) throws IOException;
	public List<String> readFromFileByLogic(String filename, String file);
	
}
