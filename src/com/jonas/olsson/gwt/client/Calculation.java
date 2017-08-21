package com.jonas.olsson.gwt.client;

public class Calculation {
	
	private double firstNumber;
	private String operator; 
	private double secondNumber;
	private double awnser; 
	private String question;
	
	public Calculation() {
	}
	
	public Calculation(double firstNumber, double secondNumber, String operator, double awnser) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.operator = operator;
		this.awnser = awnser;
		
		setQuestion(firstNumber, operator, secondNumber);
		
	}

	public double getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(double firstNumber) {
		this.firstNumber = firstNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public double getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(double secondNumber) {
		this.secondNumber = secondNumber;
	}

	public String getAwnserToString() {
		return String.valueOf(awnser);
	}
	
	public double getAwnser() {
		return awnser;
	}
	public void setAwnser(double awnser) {
		this.awnser = awnser;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(double firstNumber, String operator, double secondNumber) {
		this.question = firstNumber + " " + operator + " " +  secondNumber+ " = ";
	}
	
}
