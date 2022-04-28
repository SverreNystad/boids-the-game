package boidsgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
// import javafx.print.Collation;

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
		
		// List<String> readResult = new ArrayList<>();
		// File readFile = new File(filename);
		// if (readFile.exists()){
		// 	Scanner readFileReader = new Scanner(filename);

		// 	while(readFileReader.hasNextLine()) {
		// 		String line = readFileReader.nextLine();
		// 		for (String dataFromFile : line.split(splitter)){
		// 			readResult.add(dataFromFile);
		// 		}
		// 	}
		// 	readFileReader.close();
		// }
		// else throw new FileNotFoundException("The " + filename + " file could not be found.");
		// return readResult;
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
	private static void valideStoreSettingsInFileArguments(String gameMode, int startBoidsAmountSliderValue, int startPoidProsentSliderValue, String wraparound) throws IllegalArgumentException { // TODO TEST MER
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
		// Filehandler temp = new Filehandler();
		// return temp.readFromFileAndSplitBy("currentGameSettings.txt", ", ");
	}

	public static void storeHighscoresInFile(PlayerBoid player) throws IOException{
		Filehandler temp = new Filehandler();
		// writes to file
		temp.storeToFile("highscores.txt", "Gamemode, Kills, Lifetime", player.getGameMode() + ", " + String.valueOf(player.getKillScore()) + ", " + String.valueOf(player.getLifeTime()) + "\n", false);
	}

	public static List<List<List<String>>> readFromHighscoreFile() throws FileNotFoundException {
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
				highscoreResult.add(innerList);
			}
			settingsFileReader.close();
		}
		else throw new FileNotFoundException("The highscores file could not be found.");
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
		sortedHighscore.add(poidList);
		sortedHighscore.add(hoidList);
		return sortedHighscore;
	}
	
	public static String formatScores(List<List<String>> sortedHighscore){
		return "Boid, Kills, Lifetime\n" + sortedHighscore.toString().replace('[', ' ').replace("]]", "").replace("],", "\n");
	}

	
	//  // Ønsker å sortere liseter på et element i en av kolonnene.
	// public  List<List<List<String>>> sortHighscoreByGamemodeValue(List<List<String>> highscoreResult){
	// 	List<List<List<String>>> sortedHighscore = new ArrayList<>();
	// 	List<List<String>> poidList = new ArrayList<>();
	// 	List<List<String>> hoidList = new ArrayList<>();
	// 	for (List<String> stringList : highscoreResult) {
	// 		if (stringList.get(0).equals("Hoid")) {
	// 			hoidList.add(stringList);
	// 		}
	// 		else {
	// 			poidList.add(stringList);
	// 		}
	// 	}
		
	// 	// Collections.sort(poidList, Comparator.comparing(a -> a[1]));
	// 	Arrays.sort(poidList, Comparator.comparingDouble(o -> o[0][1]));
	// 	Arrays.sort(hoidList, Comparator.comparingDouble(o -> o[0][2]));


	// 	Collections.sort(poidList, (Comparator.<List<List<String>>>
    //                     comparingDouble(poidScore1 -> Double.valueOf(poidScore1.get(0).get(1)))
    //                     .thenComparingDouble(poidScore2 -> Double.valueOf(poidScore2.get(0).get(1)))));
	// 	sortedHighscore.add(poidList);
	// 	sortedHighscore.add(hoidList);

	// 	return sortedHighscore;
	// }
}