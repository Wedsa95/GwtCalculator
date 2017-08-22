package com.jonas.olsson.gwt.client;


import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class Calculator implements EntryPoint {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel numberOneButtonPanel = new VerticalPanel();
	private VerticalPanel operatorButtonPanel = new VerticalPanel();
	private VerticalPanel numberTwoButtonPanel = new VerticalPanel();
	
	private FlexTable calcTable = new FlexTable();
	private FlexTable numberOneButtonTable = new FlexTable();
	private FlexTable operatorButtonTable = new FlexTable();
	private FlexTable numberTwoButtonTable = new FlexTable();
	
	private Button calcButton = new Button("Calculate");
	private ArrayList<Button> numberButtons = new ArrayList<>();
	private ArrayList<Button> operatorButtons = new ArrayList<>();
	
	private TextBox numberOne = new TextBox();
	private TextBox numberTwo = new TextBox();
	private TextBox operatorBox = new TextBox();
	
	private String[] buttonOptionArray = {"1","2","3","4","5","6","7","8","9","0","."};
	private String[] operatorOptionArray = {"+","-","/","%","*"};
	private ArrayList<Calculation> calcultions = new ArrayList<>();
	
	private EventHandler handler = new EventHandler();


	@Override
	public void onModuleLoad() {
		/*
		numberOne.addStyleName("textBox");
		numberTwo.addStyleName("textBox");
		*/
		operatorBox.addStyleName("operator-style");
		
		//TO DO lägga till klik bara knappar
		//Dom kommer nog ta texten på kanppen sen räkna ut det
		
		
		
		//TO DO KOLLA IN FLOW PANEL PÅ 
		addNumberButtons();
		addOperatorButtons();
		
		numberOneButtonPanel.add(numberOne);
		numberOneButtonPanel.add(numberOneButtonTable);
		hPanel.add(numberOneButtonPanel);
		
		operatorButtonPanel.add(operatorBox);
		operatorButtonPanel.add(operatorButtonTable);
		hPanel.add(operatorButtonPanel);
		
		numberTwoButtonPanel.add(numberTwo);
		numberTwoButtonPanel.add(numberTwoButtonTable);
		hPanel.add(numberTwoButtonPanel);
		
		hPanel.add(calcButton);
		vPanel.add(calcTable);
		
		calcTable.setText(0, 0, "equation");
		calcTable.setText(0, 1, "awnser");
		
		numberOne.addKeyUpHandler(handler);
		numberOne.addFocusHandler(handler);
		numberTwo.addKeyUpHandler(handler);
		numberTwo.addFocusHandler(handler);
		operatorBox.addKeyUpHandler(handler);
		operatorBox.addFocusHandler(handler);
		calcButton.addClickHandler(handler);
		
		RootPanel.get().add(hPanel);
		RootPanel.get().add(vPanel);
		
				
	}
	
	public void addNumberButtons() {
		int k = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				numberButtons.add(new Button(buttonOptionArray[k],handler));
				numberOneButtonTable.setWidget(i, j, numberButtons.get(k));
				k++;
			}
		}
		numberButtons.add(new Button(buttonOptionArray[k],handler));
		numberOneButtonTable.setWidget(3,0, numberButtons.get(k));
		k++;
		numberButtons.add(new Button(buttonOptionArray[k],handler));
		numberOneButtonTable.setWidget(3,1, numberButtons.get(k));
		numberOneButtonTable.setVisible(false);
	}
	
	public void addOperatorButtons() {
		for(int i = 0; i < operatorOptionArray.length; i++) {
			operatorButtons.add(new Button(operatorOptionArray[i],handler));
			operatorButtons.get(i).addClickHandler(handler);
			operatorButtonTable.setWidget(0, i, new Button(operatorOptionArray[i]));
		}
		operatorButtonTable.setVisible(false);
	}
	
	public void calculate() {
		
		double firstNumber = Double.parseDouble(numberOne.getText().trim());
		double secondNumber = Double.parseDouble(numberTwo.getText().trim());
		String operator = operatorBox.getText().trim();
		double awnser = 0;
		
		if(operator.equals("/")) {
			awnser = firstNumber/secondNumber;
		}else if(operator.equals("*")) {
			awnser = firstNumber*secondNumber;
		}else if(operator.equals("+")) {
			awnser = firstNumber+secondNumber;
		}else if(operator.equals("-")) {
			awnser = firstNumber-secondNumber;
		}else if(operator.equals("%")) {
			awnser = firstNumber%secondNumber;
		}else {
			return;
		}
		
		calcultions.add(new Calculation(firstNumber,secondNumber, operator, awnser));
		
		//Calculation[] calulations =  new Calculation[];
		
		calcTable.setText(0, 0, "equation");
		calcTable.setText(0, 1, "awnser");
		uppdateCalcTable();
		
		numberOne.setText("");
		numberTwo.setText("");
		operatorBox.setText("");
	}
	public void uppdateCalcTable() {
		
		for(int i = 0; i < calcultions.size(); i++) {
			calcTable.setText(i, 0, calcultions.get(i).getQuestion());
			calcTable.setText(i, 1, String.valueOf(calcultions.get(i).getAwnser()));
		}
		
	}
	
	public boolean isNumber(String arg) {
		try
		   {
		      Double.parseDouble(arg);
		      return true;
		   }
		   catch(NumberFormatException e)
		   {
		      return false;
		   }
	}
	public boolean isOperator(String arg) {
		
		if(arg == "+"|| arg == "/"||arg == "*" ||
				arg == "%"|| arg == "-") {
			return true;
		}else {
			return false;
		}

	}

	public class EventHandler implements ClickHandler, KeyUpHandler, FocusHandler{
		//TO DO Skapa en handler åt all Nummer kanppar
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			// TODO att den inte ger focus till nummer knapparna 
			if(event.getNativeKeyCode() == KeyCodes.KEY_TAB) {
				
			}
			if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			
				if(!isOperator(operatorBox.getValue().trim()) || !isNumber(numberOne.getText().trim()) || !isNumber(numberTwo.getText().trim())) {
					Window.alert("This calculator can only calculate Numbers and [ . + / * % -] operators");
					return;
				}
					calculate();
			}
		}
		
		@Override
		public void onClick(ClickEvent event) {
			Widget sender = (Widget) event.getSource();
			for(int i = 0; i< operatorButtons.size(); i++) {
				if(event.getSource() == operatorButtons.get(i)) {
					operatorBox.setText("" + operatorOptionArray[i]); 
				}
			}
			
			
			if(sender.equals(calcButton)) {
				if(!isOperator(operatorBox.getValue().trim()) || !isNumber(numberOne.getText().trim()) || !isNumber(numberTwo.getText().trim())) {
					Window.alert("This calculator can only calculate Numbers and [ + / * % -] operators");
					return;
				}
					calculate();
			}
				
		}

		@Override
		public void onFocus(FocusEvent event) {
			// TODO Auto-generated method stub
			
			// TODO Denna ska ta fram valen till att klicka 
			// siffrorna 
			
			if(event.getSource() == numberOne){
				numberOneButtonTable.setVisible(true);
				operatorButtonTable.setVisible(false);
				numberTwoButtonTable.setVisible(false);
			}
			if(event.getSource() == numberTwo) {
				numberOneButtonTable.setVisible(false);
				operatorButtonTable.setVisible(false);
				numberTwoButtonTable.setVisible(true);
			}
			if(event.getSource() == operatorBox) {
				numberOneButtonTable.setVisible(false);
				operatorButtonTable.setVisible(true);
				numberTwoButtonTable.setVisible(false);
			}
		}

		
			
			
	}

	
}



