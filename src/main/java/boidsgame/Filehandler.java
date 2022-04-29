package boidsgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandler implements FilehandlerInterface {
	
	@Override
	public List<String> readFromFileAndSplitBy(String filename, String splitter) throws FileNotFoundException {		
		List<String> settingsResult = new ArrayList<>();
		
		File settingsFile = new File(filename);
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

	@Override
	public void storeToFile(String filename, String headLine, String data, Boolean shallOverwrite) throws IOException{
		File currentFile = new File(filename);
		FileWriter currentWriter = new FileWriter(currentFile, !shallOverwrite);
		if (shallOverwrite){
			currentWriter.write(headLine + "\n" + data);
		}
		else {
			if (!currentFile.exists()){
				currentWriter.write(headLine);
			}
			currentWriter.append(data);
		}
		currentWriter.close();
	}

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

	public static void storeHighscoresInFile(PlayerBoid player) throws IOException{
		Filehandler temp = new Filehandler();
		// writes to file
		temp.storeToFile("highscores.txt", "Gamemode, Kills, Lifetime", player.getGameMode() + ", " + String.valueOf(player.getKillScore()) + ", " + String.valueOf(player.getLifeTime()) + "\n", false);
	}

	public List<List<String>> readFromHighscoreFile() throws FileNotFoundException {
		List<List<String>> highscoreResult = new ArrayList<>();
		File settingsFile = new File("highscores.txt");
		if (settingsFile.exists()){
			Scanner settingsFileReader = new Scanner(settingsFile);

			while(settingsFileReader.hasNextLine()) {
				String line = settingsFileReader.nextLine();
				List<String> innerList = new ArrayList<>();

				for (String dataFromFile : line.split(", ")){
					innerList.add(dataFromFile);
				}
				if (innerList.size() < 3) continue;
				highscoreResult.add(innerList);
			}
			settingsFileReader.close();
		}
		else throw new FileNotFoundException("The highscores file could not be found.");
		return highscoreResult;
	}
	
	 // Ønsker å sortere liseter på et element i en av kolonnene.
	public static List<List<List<String>>> sortHighscoreByGamemodeValue(List<List<String>> highscoreResult){
		List<List<List<String>>> sortedHighscore = new ArrayList<>();
		List<List<String>> poidList = new ArrayList<>();
		List<List<String>> hoidList = new ArrayList<>();
		for (List<String> stringList : highscoreResult) {
			if (stringList.get(0).equals("Hoid")) {
				hoidList.add(stringList);
			}
			else {
				poidList.add(stringList);
			}
		}
		poidList.sort((List<String> score1, List<String> score2 ) -> Double.valueOf(score2.get(1)).compareTo(Double.valueOf(score1.get(1))));
		hoidList.sort((List<String> score1, List<String> score2 ) -> Double.valueOf(score2.get(2)).compareTo(Double.valueOf(score1.get(2))));
		sortedHighscore.add(poidList);
		sortedHighscore.add(hoidList);

		return sortedHighscore;
	}
	public static String formatScores(List<List<String>> sortedHighscore){
		return "Boid, Kills, Lifetime\n " + sortedHighscore.toString().replace("[", "").replace("]]", "").replace("],", "\n");
	}

}