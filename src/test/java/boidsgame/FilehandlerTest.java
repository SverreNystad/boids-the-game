package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FilehandlerTest {

	@Test
	@DisplayName("This test shall check if storing to settings and reading works.")
	public void testSettingsfile() {
		Filehandler.storeSettingsInFile("Hoid", 0, 0, "on", 0, 0, 0, 0, 0, 0, 0, 0);
		try {
			System.out.println( Filehandler.readFromSettingsfile().toString());
			assertEquals("[Gamemode, startBoidsAmount, startPoidProsent, wraparound, poidViewRangeSliderValue, killRadiusSliderValue, poidSeperationCoefficientValue, AttractionToHoidsCoefficientValue, hoidViewRangeSliderValue,  CohesionCoefficientValue,  AlignmentCoefficientValue,  hoidSeperationCoefficientValue, Hoid, 0, 0, on, 0, 0, 0.0, 0.0, 0, 0.0, 0.0, 0.0]",Filehandler.readFromSettingsfile().toString());

		} catch (FileNotFoundException e) {
			assertTrue(false, "Could not find file");
			e.printStackTrace();
		}
		// Bad inputs
		Filehandler.storeSettingsInFile("Hoid", 0, 200, "on", 0, 0, 0, 0, 0, 0, 0, 0);		
		Filehandler.storeSettingsInFile("Hoid", -10, 0, "on", 0, 0, 0, 0, 0, 0, 0, 0);
		Filehandler.storeSettingsInFile("Hoid", 0, 0, "badInput", 0, 0, 0, 0, 0, 0, 0, 0);
		Filehandler.storeSettingsInFile("Hoid", 0, -10, "on", 0, 0, 0, 0, 0, 0, 0, 0);
		// Should not update since it is bad input
		try {
			assertEquals("[Gamemode, startBoidsAmount, startPoidProsent, wraparound, poidViewRangeSliderValue, killRadiusSliderValue, poidSeperationCoefficientValue, AttractionToHoidsCoefficientValue, hoidViewRangeSliderValue,  CohesionCoefficientValue,  AlignmentCoefficientValue,  hoidSeperationCoefficientValue, Hoid, 0, 0, on, 0, 0, 0.0, 0.0, 0, 0.0, 0.0, 0.0]",Filehandler.readFromSettingsfile().toString(), "The input got changed. Bad input passed into file");

		} catch (FileNotFoundException e) {
			assertTrue(false, "Could not find file");
			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("This test shall check if")
	public void testStoreHighscoresInFile() {
		PlayerBoid testPoidBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, false, null, "Poid");
		PlayerBoid testHoidBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, false, null, "Hoid");

		try {
			// File fil = new File("highscores.txt");
			(new Filehandler()).storeToFile("highscores.txt", "", "", true);
			Filehandler.storeHighscoresInFile(testPoidBoid);
			Filehandler.storeHighscoresInFile(testPoidBoid);
			Filehandler.storeHighscoresInFile(testHoidBoid);

		} catch (IOException e) {
			System.out.println("Saving failed!");
			System.out.println(e.getMessage());
		}

		try {
			assertTrue(Filehandler.formatScores(Filehandler.sortHighscoreByGamemodeValue(new Filehandler().readFromHighscoreFile()).get(0)).equals("Boid, Kills, Lifetime\n Poid, 0, 0.0\n Poid, 0, 0.0"));
			System.out.println(Filehandler.formatScores(Filehandler.sortHighscoreByGamemodeValue(new Filehandler().readFromHighscoreFile()).get(1)).toString());
			assertTrue(Filehandler.formatScores(Filehandler.sortHighscoreByGamemodeValue(new Filehandler().readFromHighscoreFile()).get(1)).equals("Boid, Kills, Lifetime\n Hoid, 0, 0.0"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
