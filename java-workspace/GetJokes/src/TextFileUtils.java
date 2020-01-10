import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileUtils {

	public static List<Joke> getJokesFromFiles(String path, String source, String sourceInfo) throws IOException {
		List<Joke> jokes = new ArrayList<Joke>();
		for (String line : Files.readAllLines(Paths.get(path))) {
			jokes.add(new Joke(line, source, sourceInfo));
		}
		return jokes;
	}

}
