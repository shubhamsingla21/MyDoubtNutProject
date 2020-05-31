package com.doubtnut.generatePDF.entity;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionsEntity {
	List<String> questions;
	LocalDateTime timeAtCreation;
	String path;
	
	String mailId;
	

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDateTime getTimeAtCreation() {
		return timeAtCreation;
	}

	public void setTimeAtCreation(LocalDateTime timeAtCreation) {
		this.timeAtCreation = timeAtCreation;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionsEntity [questions=").append(questions).append("]");
		return builder.toString();
	}
	

}
