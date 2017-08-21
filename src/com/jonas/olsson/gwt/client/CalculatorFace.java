package com.jonas.olsson.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class CalculatorFace extends Composite implements HasText {

	private static CalculatorFaceUiBinder uiBinder = GWT.create(CalculatorFaceUiBinder.class);

	interface CalculatorFaceUiBinder extends UiBinder<Widget, CalculatorFace> {
	}

	public CalculatorFace() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	

	public CalculatorFace(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
