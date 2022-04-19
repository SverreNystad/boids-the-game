package boidsgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandler {
	
	public static void storeSettingsInFile(String gameMode, int startBoidsAmountSliderValue, int startPoidProsentSliderValue, String wraparound) throws IOException{
		try{
			valideStoreSettingsInFileArguments(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound);
			File currentGameSettings = new File("currentGameSettings.txt");
			FileWriter currentWritter = new FileWriter(currentGameSettings);
			// FileWriter currentWritter = new FileWriter("currentGameSettings.txt");
			currentWritter.write("Gamemode, startBoidsAmount, startPoidProsent, wraparound\n");
			currentWritter.append(gameMode + ", " + String.valueOf(startBoidsAmountSliderValue) + ", " + String.valueOf(startPoidProsentSliderValue) + ", " + wraparound);
			currentWritter.close();
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
	public void storeHighscoresInFile(PlayerBoid player) throws IOException{
		File highScoreFile = new File("highscores.txt");
		FileWriter currentWriter = new FileWriter(highScoreFile);
		if (!highScoreFile.exists()){
			currentWriter.write("Kills, Lifetime\n");
		}
		currentWriter.append(String.valueOf(player.getKillScore()) + ", " + String.valueOf(player.getLifeTime()) + "\n");
		currentWriter.close();
	}

	public static void main(String[] args) {
		Filehandler temp = new Filehandler();
		try {
			System.out.println(temp.readFromSettingsfile());
			
		} catch (FileNotFoundException e) {
			//TODO: handle exception
			System.out.println("Could not find file");
		}
	}
}

