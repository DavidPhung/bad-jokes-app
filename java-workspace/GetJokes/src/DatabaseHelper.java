import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

	public static int insert(String url, String user, String password, String text, String source, String sourceInfo, boolean isQA, String question, String answer) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, user, password)){
			String sql = "INSERT INTO jokes(text, source, source_info, is_qa, question, answer)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, text);
			st.setString(2, source);
			st.setString(3, sourceInfo);
			st.setBoolean(4, isQA);
			st.setString(5, question);
			st.setString(6, answer);
			
			return st.executeUpdate();
		}
	}
	
	public static int insert(String url, String user, String password, Joke joke) throws SQLException {
		return insert(url, user, password, joke.getText(), joke.getSource(), joke.getSourceInfo(), joke.isQA(), joke.getQuestion(), joke.getAnswer());
	}
}
