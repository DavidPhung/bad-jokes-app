import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, SQLException {
		ConfFileReader databaseConf = new ConfFileReader("database.conf");
		String url = databaseConf.get("url");
		String user = databaseConf.get("user");
		String password = databaseConf.get("password");
		
		List<Joke> jokes = ICanHazDadJoke.getNewJokes();
		for (Joke joke : jokes) {
			DatabaseHelper.insert(url, user, password, joke);
		}
	}
}
