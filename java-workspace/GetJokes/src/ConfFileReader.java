import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class ConfFileReader {

	private Hashtable<String, String> confTable;

	public ConfFileReader(String path) throws FileNotFoundException {
		confTable = new Hashtable<String, String>();
		try (Scanner sc = new Scanner(new File(path))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] ss = line.split("=", 2);
				confTable.put(ss[0].trim(), ss[1].trim());
			}
		}
	}
	
	public String get(String key) {
		return confTable.get(key);
	}

}
