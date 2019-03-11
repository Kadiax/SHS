package com.blueone.controller;



import java.sql.SQLException;
import java.util.List;

import com.blueone.model.User;
import com.blueone.model.UserModel;
import com.blueone.view.SHSView;
import connectionPool.DataSource;

// Fais le lien entre la vue(print) et le model(getteur)

public class DBConnectController {
	 private UserModel userModel;
	 private SHSView shsView;
	 
	 public DBConnectController(SHSView v) throws SQLException, ClassNotFoundException {
		 userModel = new UserModel();
		 shsView = v;
	 
	 }
	 
	 public void start() throws SQLException  {
		
		try { 
		 shsView.printScreen("Size of the init pool: "+DataSource.getSize());
		 System.out.println("######get All Users###########");
		 List<User> users = UserModel.getUsers();
		 shsView.printScreen("Size of the pool: "+DataSource.getSize());
		 
		 for(int i=0; i<users.size(); i++) {
			 shsView.printScreen(users.get(i).toString());
		 }
		 
		}finally {
		 DataSource.shutdown();	
		 shsView.printScreen("Size of the pool after shutdown: "+DataSource.getSize());
		}
	 }
 
}