package model;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.View;

public class BTLModel {
	private View buttonView;
	private int interaceButton;

	public BTLModel() {
		this.interaceButton = 1;
	}
	
	public BTLModel(int interaceButton) {
		this.interaceButton = interaceButton;
	}

	public int getInteraceButton() {
		return interaceButton;
	}

	public void setInteraceButton(int interaceButton) {
		this.interaceButton = interaceButton;
	}
	
	public void setInterface_1() {
		this.setInteraceButton(1);
	}
	
	public void setInterface_2() {
		this.setInteraceButton(2);
	}
	
	public void setInterface_3() {
		this.setInteraceButton(3);
	}
	
	public void setInterface_4() {
		this.setInteraceButton(4);
	}
	
	public void setInterface_5() {
		this.setInteraceButton(5);
	}
	
}
