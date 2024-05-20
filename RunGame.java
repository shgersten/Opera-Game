import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;



public class RunGame {
	public static void main(String[] args) throws IOException, InterruptedException {
		Game g = new Game();

		// Find the absolute path of claudio.wav
		String filename = "claudio.wav";

		Path filePath = findFile(filename, Paths.get(System.getProperty("user.home")));
		if (filePath == null) {
			System.out.println("File not found: " + filename);
			return;
		}
		String absoluteFilePath = filePath.toAbsolutePath().toString();
		Thread musicThread = new Thread() {
			public void run() {
				Clip clip;
				try {
					AudioInputStream input = AudioSystem.getAudioInputStream(new File(absoluteFilePath));
					clip = AudioSystem.getClip();
					clip.open(input);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		musicThread.start();
		g.play();
	}
public static Path findFile(String filename, Path startDir) throws IOException {
	try (Stream<Path> stream = java.nio.file.Files.walk(startDir)) {
		return stream
			.filter(path -> path.getFileName().toString().equals(filename))
			.findFirst()
			.orElse(null);
	}
}
}

