package com.shs.client.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.shs.commons.model.Room;
import com.shs.commons.model.User;
import com.shs.server.connection.pool.DBAccess;

public class RoomServerHandler {
	private Socket server;
	private JsonReader reader;
	private JsonWriter writer;
	private Gson gson;
	//WITHOUT VM
	private int port = 6533;
	private InetAddress adress =InetAddress.getLocalHost();
	//WITH VM
	private int portServer = DBAccess.getPORT_SERVER();
	private String adressServer = DBAccess.getSERVER();
	
	public RoomServerHandler() throws UnknownHostException, IOException {
		gson = new Gson();
	}
	
	public void getFlux() throws IOException { 
		try {
			this.server = new Socket(adress,port);		
			reader = new JsonReader(new InputStreamReader(server.getInputStream(), "UTF-8"));
			writer = new JsonWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"));
		}catch(IOException e) {
			throw new IOException("Error connection to server ");
		}
	}
	
	public void stopFlux() throws IOException {
        try{
        	reader.close();
	        writer.close();
	        server.close();}
        catch(IOException e) {
        	throw new IOException("Error closed flux "+e);
        }
    }
	
	public String insertRoomToServer(Room room)throws IOException  {
		//connections
     	getFlux();
		try {//Type class
			String request = "insert-Room";
			
			//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value("insert-Room");
		    writer.name("object").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object"+gson.toJson(room));
		    //response
		    reader.beginObject();
		    String response = "Server "+reader.nextName()+": "+reader.nextString();
		    reader.endObject();
		    return response;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	} 
	
	
	public String updateRoomToServer(Room room) throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request = "update-Room";
			
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object: "+gson.toJson(room));
		    //response
		    reader.beginObject();reader.nextName();
		    String response = reader.nextString();
		    reader.endObject();System.out.println("Server response: "+response);
		    return response;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}
	
	public String deleteRoom(Room room) throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request = "delete-Room";
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object: "+gson.toJson(room));
		    //response
		    reader.beginObject();reader.nextName();
		    String response = reader.nextString();
		    reader.endObject();System.out.println("Server response: "+response);
		    return response;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}

	public String deleteAllRoom() throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request = "deleteAll-Room";
			
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request);
		    //response
		    reader.beginObject();reader.nextName();
		    String response = reader.nextString();
		    reader.endObject();System.out.println("Server response: "+response);
		    return response;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}
	
	public List<Room> searchRoomToServer(Room room) throws IOException {
		//connections
     	getFlux();
		List<Room> list=new ArrayList<>(); 
		try {
			//Type class
			String request="select-Room";
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n"+gson.toJson(room));
		    //response
		    String name=null;
		    
		    reader.beginObject();
		    while(reader.hasNext()) {
		    	 name = reader.nextName();
			    if(name.equals("room")) {
			    	String objectJson=reader.nextString();
			    	Room receivRoom=new Gson().fromJson(objectJson, Room.class);
			    	list.add(receivRoom);System.out.println(list); 	
			    }
			    else if(name.equals("null")) {
			    	System.out.println(reader.nextString());
			    }
		    }
		    reader.endObject();
		 
		    return list;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
		
	}
		

	public List<Room> searchAllRoom() throws IOException {
		List<Room> list = new ArrayList<>();
		//connections
     	getFlux();
		try {
			String request="selectAll-Room";
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request);
		    //response
		    reader.beginObject();
		    while (reader.hasNext()) {
			    String name= reader.nextName();
			    if(name.equals("null")) {
			    	System.out.println(reader.nextString());
			    }else {
				   String objectJson=reader.nextString();
				   	list.add(new Gson().fromJson(objectJson, Room.class));
				 }
		    }
		    reader.endObject();
		    return list;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}

	
}
