import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ICanHazDadJoke {
	private static final String url = "https://icanhazdadjoke.com";

	public static List<Joke> getNewJokes() {
		List<Joke> jokes = new ArrayList<Joke>();
		String responseStr = "";
		int totalNbrPages = -1;
		int pageNbr = 1;
		do {
			responseStr = RESTUtils.get(url + "/search?limit=30&page=" + pageNbr, "wwwproxy.se.axis.com", 3128, RESTUtils.MediaType.JSON);
			JsonObject jsonObj = JsonParser.parseString(responseStr).getAsJsonObject();
			
			if (totalNbrPages == -1) totalNbrPages = jsonObj.get("total_pages").getAsInt();
			
			JsonArray results = jsonObj.get("results").getAsJsonArray();
			for (JsonElement jsonElement : results) {
				JsonObject jo = jsonElement.getAsJsonObject();
				Joke joke = new Joke(jo.get("joke").getAsString(), url, "/j/" + jo.get("id").getAsString());
				jokes.add(joke);
			}
			
			System.out.println("Page " + pageNbr + " of " + totalNbrPages);
			pageNbr++;
		} while (pageNbr <= totalNbrPages);

		return jokes;
	}
}
