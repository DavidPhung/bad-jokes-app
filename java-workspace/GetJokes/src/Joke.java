
public class Joke {

	private String text;
	private String source;
	private String sourceInfo;
	private boolean isQA;
	private String question;
	private String answer;
	private String author;

	public Joke(String text, String source, String sourceInfo) {
		this.text = text;
		this.source = source;
		this.sourceInfo = sourceInfo;
		process();
	}

	private void process() {
		int indexOfLastDash = text.lastIndexOf('-');
		if (indexOfLastDash >= 0) {
			author = text.substring(indexOfLastDash + 1).trim();
			text = text.substring(0, indexOfLastDash + 1).trim();
		}

		int indexOfQuestionMark = text.indexOf('?');
		if (indexOfQuestionMark < 0) {
			isQA = false;
		} else {
			isQA = true;
			question = text.substring(0, indexOfQuestionMark + 1).trim();
			answer = text.substring(indexOfQuestionMark + 1).trim();
		}
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}

	public String getSourceInfo() {
		return sourceInfo;
	}

	public boolean isQA() {
		return isQA;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return text + "|" + source + "|" + sourceInfo + "|" + author + "|" + isQA + "|" + question + "|" + answer;
	}
}
