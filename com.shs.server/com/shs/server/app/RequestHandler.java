package com.shs.server.app;

import com.shs.commons.model.Room;
import com.shs.server.model.RoomManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public class RequestHandler implements Runnable {
	private static int cpt=0;
	private int num;
	private Socket client= null;
	private Connection connDB;
	private JsonReader reader;
	private JsonWriter writer;

	
	public RequestHandler(Socket client, Connection connDB) {
		num=cpt++;
		this.client = client;
		this.connDB = connDB;
		
			
		try {
			reader = new JsonReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		    writer = new JsonWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
		} catch (IOException e) {}
	}

	@Override
	public void run(){
		//Communication Json
		try {
			System.out.println("Thread:"+num+" "+readMessage(reader));
		} catch (IOException e) {
	    	System.out.println("Error communication to client "+e);
		} catch (SQLException e) {
			System.out.println("Error DB "+e);
		}
        finally{
			try {
				stopConnection();
			} catch (IOException e) {}
		}
		
	}
	
	public String readMessage(JsonReader reader) throws IOException, SQLException {
		String request=null;
		Object object=null;
		String className=null;
		
		reader.beginObject();
	     while (reader.hasNext()) {
	       String name = reader.nextName();
	       if (name.equals("request")) {
	    	   request = reader.nextString();
	    	   String[] res=request.split("-");
	    	   className=res[1];
	       }
	       else if (name.equals("object")) {
	    	   String objectJson = reader.nextString();
	    	   if(className.equals("Room"))
	    		   object = new Gson().fromJson(objectJson, Room.class);
	       }else {
	         reader.skipValue();
	       }
	     }
	    reader.endObject();
	    requestManager(request, object);
	    return request+":"+object;
	}
	
	private void requestManager(String request, Object object) throws SQLException, IOException {
		RoomManager roomManager= new RoomManager(connDB);	
		boolean response = false;
		String message=null, error=null;
		switch (request) {
			case "insert-Room":
				try{
					response=RoomManager.create((Room) object);
					}
		        catch(SQLException e) {
		        	error="Error insertion "+e;
		        }
				
				break;
			case "update-Room":
				try{
					response=RoomManager.update((Room) object);
					}
		        catch(SQLException e) {
		        	error="Error updating "+e;
		        }
				break;
			default:
				break;
			}
			
		if(response)
			message=request+"-succusful";
		else
			message=request+"-failed: "+error;
		//Creation response Json
		writer.beginObject();
		writer.name("response").value(message);
		writer.endObject();
		writer.flush();
		System.out.println("Thread:"+num+" send response :Room inserted");
	}

	public void stopConnection() throws IOException {
        reader.close();
        writer.close();
        client.close();
    }


}
