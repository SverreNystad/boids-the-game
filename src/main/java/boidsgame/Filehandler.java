package boidsgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandler implements FilehandlerInterface {
	
	// @Override
	// public List<String> readFromFileByLogic(String filename, String file) {
	// 	// TODO Auto-generated method stub
		
	// }
	@Override
	public void storeToFile(String filename, String headLine, String data, Boolean overwrite) throws IOException{
		File currentFile = new File(filename);
		FileWriter currentWriter = new FileWriter(currentFile);
		if (overwrite){
			currentWriter.write(headLine + "\n" + data);
		}
		else {
			if (!currentFile.exists()){
				currentWriter.write(headLine);
			}
			currentWriter.append(data); // TODO APPEND DOES NOT WORK
		}
		currentWriter.close();
		
	}

	// public static void storeSettingsInFile(String gameMode, int startBoidsAmountSliderValue, int startPoidProsentSliderValue, String wraparound) {
	public static void storeSettingsInFile(
		String gameMode, int startBoidsAmountSliderValue, int startPoidProsentSliderValue, String wraparound, 
		int poidViewRangeSliderValue, int killRadiusSliderValue, double poidSeperationCoefficientValue, double attractionToHoidsCoefficientValue, 
		int hoidViewRangeSliderValue, double cohesionCoefficientValue, double alignmentCoefficientValue, double hoidSeperationCoefficientValue) {

		try{
			valideStoreSettingsInFileArguments(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound);
			Filehandler temp = new Filehandler();
			// writes to file
			temp.storeToFile("currentGameSettings.txt", 
			"Gamemode, startBoidsAmount, startPoidProsent, wraparound, poidViewRangeSliderValue, killRadiusSliderValue, poidSeperationCoefficientValue, AttractionToHoidsCoefficientValue, hoidViewRangeSliderValue,  CohesionCoefficientValue,  AlignmentCoefficientValue,  hoidSeperationCoefficientValue"
			, gameMode + ", " + startBoidsAmountSliderValue + ", " + startPoidProsentSliderValue + ", " + wraparound + ", " + poidViewRangeSliderValue + ", " + killRadiusSliderValue + ", " + poidSeperationCoefficientValue + ", " + attractionToHoidsCoefficientValue + ", " + hoidViewRangeSliderValue + ", " + cohesionCoefficientValue + ", " + alignmentCoefficientValue + ", " + hoidSeperationCoefficientValue
			, true);
		}
		catch (IOException e){
			System.out.println("An error has occured. In the storing of gamesettings.");
		}
		catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	private static void valideStoreSettingsInFileArguments(String gameMode, int startBoidsAmountSliderValue, int startPoidProsentSliderValue, String wraparound) throws IllegalArgumentException {
		if (startBoidsAmountSliderValue < 0) {
			throw new IllegalArgumentException("Cannot start with no boids.");
		}
		if (startPoidProsentSliderValue < 0) {
			throw new IllegalArgumentException("Cannot start with negative difficulty.");
		}
		if (startPoidProsentSliderValue > 100) {
			throw new IllegalArgumentException("Cannot start with difficulty  larger then 100%.");
		}
		if (!wraparound.equals("on") && !wraparound.equals("off")){
			throw new IllegalArgumentException("Boids need to know if the world wrap around.");
		}
	}

	public static List<String> readFromSettingsfile() throws FileNotFoundException{
		List<String> settingsResult = new ArrayList<>();
		
		File settingsFile = new File("currentGameSettings.txt");
		if (settingsFile.exists()){
			Scanner settingsFileReader = new Scanner(settingsFile);

			while(settingsFileReader.hasNextLine()) {
				String line = settingsFileReader.nextLine();
				for (String dataFromFile : line.split(", ")){
					settingsResult.add(dataFromFile);
				}
			}
			settingsFileReader.close();
		}
		else throw new FileNotFoundException("The settings file could not be found.");
		return settingsResult;
	}

	// TODO: Gets scores from playerBoid.
	public static void storeHighscoresInFile(PlayerBoid player) throws IOException{
		Filehandler temp = new Filehandler();
		// writes to file
		temp.storeToFile("highscores.txt", "Kills, Lifetime", String.valueOf(player.getKillScore()) + ", " + String.valueOf(player.getLifeTime()) + "\n", false);
	}
}

