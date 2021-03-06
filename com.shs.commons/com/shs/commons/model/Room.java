package com.shs.commons.model;

public class Room implements Comparable<Room>{

	private Integer id;
	private Integer floor;
	private Integer room_number;
	private Integer m2;
	private Type_Room type_room;
	private Wing_Room wing_room;
	private Integer nb_doors;
	private Integer nb_windows;


	public Room(Integer id, Integer floor, Integer room_number, Integer m2, Type_Room type_room, Wing_Room wing_room,
			int nb_doors, int nb_windows) {
		super();
		this.id = id;
		this.floor = floor;
		this.room_number = room_number;
		this.m2 = m2;
		this.type_room = type_room;
		this.wing_room = wing_room;
		this.nb_doors = nb_doors;
		this.nb_windows = nb_windows;
	}



	public Room() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public Type_Room getType_room() {
		return type_room;
	}

	public void setType_room(Type_Room type_room) {
		this.type_room = type_room;
	}

	public Wing_Room getWing_room() {
		return wing_room;
	}

	public void setWing_room(Wing_Room wing_room) {
		this.wing_room = wing_room;
	}


	public Integer getNb_doors() {
		return nb_doors;
	}



	public void setNb_doors(int nb_doors) {
		this.nb_doors = nb_doors;
	}



	public Integer getNb_windows() {
		return nb_windows;
	}



	public void setNb_windows(int nb_windows) {
		this.nb_windows = nb_windows;
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



	@Override
	public String toString() {
		return "Room [id=" + id + ", floor=" + floor + ", room_number=" + room_number + ", m2=" + m2 + ", type_room="
				+ type_room + ", wing_room=" + wing_room + ", nb_doors=" + nb_doors + ", nb_windows=" + nb_windows
				+ "]";
	}




}