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
import com.shs.server.connection.pool.DBAccess;

public class ServerHandler {
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
	
	public ServerHandler() throws UnknownHostException, IOException {
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
	
	public String insertObjectToServer(Object object)throws IOException  {
		//connections
     	getFlux();
		try {//Type class
			String request=null, type=null;
			if(object.getClass() == Room.class)
				type="Room";
			request = "insert-"+type;
			
			
			//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(object));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object"+gson.toJson(object));
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
	
	
	public String updateObjectToServer(Object object) throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request=null, type=null;
			if(object.getClass() == Room.class)
				type="Room";
			request = "update-"+type;
			
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(object));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object"+gson.toJson(object));
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
	
	public String delete(Object object) throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request=null, type=null;
			if(object.getClass() == Room.class)
				type="Room";
			request = "delete-"+type;
			
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value(request);
		    writer.name("object").value(gson.toJson(object));
		    writer.endObject();
		    writer.flush();
		    System.out.println("request:"+request+"\n object"+gson.toJson(object));
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

	public String deleteAll(String type) throws IOException {
		//connections
     	getFlux();
		try {
			//Type class
			String request=null;
			if(type.equals("Room"))
			request = "deleteAll-"+type;
			
			
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
	
	public List<Room> searchObjectToServer(Room room) throws IOException {
		//connections
     	getFlux();
		try {
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value("search-room");
		    writer.name("room").value(gson.toJson(room));
		    writer.endObject();
		    writer.flush();
		    System.out.println("send room to server for research");
		    //response
		    reader.beginObject();
		    String response = "Server "+reader.nextName()+": "+reader.nextString();
		    reader.endObject();
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
		return readRooms(reader);
	}
	
	public List<Room> readRooms(JsonReader reader) throws IOException {
		List<Room> rooms = new ArrayList<Room>();
		reader.beginObject();
	     reader.beginArray();
	     while (reader.hasNext()) {
	    	String roomJson = reader.nextString();
	       rooms.add(new Gson().fromJson(roomJson, Room.class));
	     }
	     reader.endArray();
	     reader.endObject();
	     return rooms;
	}	

	public List<Room> SearchAll() throws IOException {
		List<Room> rooms = new ArrayList<>();
		//connections
     	getFlux();
		try {
	     	//Creation request Json
		    writer.setIndent("	");
		    writer.beginObject();
		    writer.name("request").value("searchAll-Room");
		    writer.endObject();
		    writer.flush();
		    System.out.println("send request to server for search all rooms ");
		    //response
		    reader.beginArray();
		     while (reader.hasNext()) {
		      // rooms.add(reader.nextDouble());
		     }
		     reader.endArray();
		    reader.endObject();
		    return rooms;
	      } 
	    catch (IOException ioe) { 
	    	throw new IOException("Error communication to server ");
		}
	    finally {
	    	stopFlux();
	    }
	}
	
}
