package com.shs.commons.model;

public class Room implements Comparable<Room>{
	private Integer id;
	private String type_room;
	private Integer floor;
	private Integer room_number;
	
	public Room(Integer id, String type_room, Integer floor, Integer room_number) {
		super();
		this.id = id;
		this.type_room = type_room;
		this.floor = floor;
		this.room_number=room_number;
	}
	
	public Room() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_room() {
		return type_room;
	}
	public void setType_room(String type_room) {
		this.type_room = type_room;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	

	public Integer getRoom_number() {
		return room_number;
	}

	public void setRoom_number(Integer room_number) {
		this.room_number = room_number;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", type_room=" + type_room + ", floor=" + floor + ", room_number=" + room_number
				+ "]";
	}

	@Override
	public int compareTo(Room o) {
		if(this.getId()<o.getId())
			return -1;
		else if(this.getId()>o.getId())
		return 1;
		else
		return 0;
	}
	
}