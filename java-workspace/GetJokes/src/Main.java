import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, SQLException {
		ConfFileReader databaseConf = new ConfFileReader("database.conf");
		String url = databaseConf.get("url");
		String user = databaseConf.get("user");
		String password = databaseConf.get("password");
		
		HashSet<String> existingJokes = DatabaseUtils.getAllJokes(url, user, password);
		System.out.println("Loaded " + existingJokes.size() + " from database");
		
		System.out.println("Checking icanhazdadjoke.com");
		List<Joke> jokes = ICanHazDadJoke.getNewJokes();
		int count = 0;
		for (Joke joke : jokes) {
			if (existingJokes.contains(joke.getText())) continue;
			
			DatabaseUtils.insert(url, user, password, joke);
			count++;
		}
		System.out.println("Found " + count + " new jokes from icanhazdadjoke.com");
	}
}
