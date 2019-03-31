package com.shs.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.shs.client.view.SHSView;

public class MenuController implements ActionListener{
	SHSView view;
	public MenuController(SHSView v) {
		this.view = v;
		view.addMenuListner(this);
		view.addLogoListner(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton ){
			String choix = e.getActionCommand();
			switch (choix) {
			case "index":
				view.setCardAppView("indexView");
				break;
				
			case "Supervised Rooms":
				view.setCardAppView("supRoomView");
				break;

			default:
				break;
			}
		}
		
	}
}