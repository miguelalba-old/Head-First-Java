package ch14;

import java.io.Serializable;

public class QuizCard implements Serializable {
	private String uniqueID;
	private String category;
	private String question;
	private String answer;
	private String hint;

	public QuizCard(String q, String a) {
		question = q;
		answer = a;
	}
	
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getHint() {
		return hint;
	}
}