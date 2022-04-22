package boidsgame;

import java.util.List;

public interface FilehandlerInterface {
	
	public void storeToFile(String filename, String file);
	public List<String> readFromFileByLogic(String filename, String file);
	
}
