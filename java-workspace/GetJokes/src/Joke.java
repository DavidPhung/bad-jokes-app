
public class Joke {
	
	private String text;
	private String source;
	private String sourceInfo;
	private boolean isQA;
	private String question;
	private String answer;
	
	public Joke(String text, String source, String sourceInfo) {
		this.text = text;
		this.source = source;
		this.sourceInfo = sourceInfo;
		process();
	}
	
	private void process() {
		int indexOfQuestionMark = text.indexOf('?');
		if (indexOfQuestionMark < 0) {
			isQA = false;
			return;
		}
		isQA = true;
		question = text.substring(0, indexOfQuestionMark + 1).trim();
		answer = text.substring(indexOfQuestionMark + 1).trim();
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
	
	@Override
	public String toString() {
		return text + "|" + source + "|" + sourceInfo + "|" + isQA + "|" + question + "|" + answer;
	}
}
