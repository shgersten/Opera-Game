package LoopMusicJavaUpdated;
import java.io.File;

public class Main {    
	public static void main(String[] args)
	{
		/*
		   String filepath = "claudio.wav";

		   musicStuff musicObject = new musicStuff();

		   musicObject.playMusic(filepath);
		   */
		String relativeFilePath = "claudio.wav";

		// Create a File object for the relative path
		File file = new File(relativeFilePath);

		// Get the absolute path of the file
		String absoluteFilePath = file.getAbsolutePath();

		// Print the absolute path (optional)
		//System.out.println("Absolute Path: " + absoluteFilePath);

		// Create an instance of musicStuff
		musicStuff musicObject = new musicStuff();

		// Play the music using the absolute file path
		musicObject.playMusic(absoluteFilePath);


	}
}
