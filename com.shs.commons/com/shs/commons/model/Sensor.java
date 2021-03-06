package com.shs.commons.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sensor {
	private int id;
	private String sensor_name;
	private String ip_address;
	private String mac_address;
	private Date date_setup;
	private Boolean status;
	private Boolean installed;
	private Wing_Room fk_position;
	private Float price;
	private Room fk_room;
	private Type_Sensor fk_type_sensor;
	private Integer scope_sensor;
	private String date_setup_formatted;
	
	public Sensor() {
		// TODO Auto-generated constructor stub
	}
	
	public Sensor(int id, String sensor_name, String ip_address, String mac_address, Date date_setup, Boolean status,
			Boolean installed, Wing_Room fk_position, Float price, Room fk_room, Type_Sensor fk_type_sensor,
			Integer scope_sensor) {
		super();
		this.id = id;
		this.sensor_name = sensor_name;
		this.ip_address = ip_address;
		this.mac_address = mac_address;
		this.date_setup = date_setup;
		this.status = status;
		this.installed = installed;
		this.fk_position = fk_position;
		this.price = price;
		this.fk_room = fk_room;
		this.fk_type_sensor = fk_type_sensor;
		this.scope_sensor = scope_sensor;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public Date getDate_setup() {
		return date_setup;
	}
	public void setDate_setup(Date date_setup) {
		this.date_setup = date_setup;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Boolean getInstalled() {
		return installed;
	}
	public void setInstalled(Boolean installed) {
		this.installed = installed;
	}
	public Wing_Room getFk_position() {
		return fk_position;
	}
	public void setFk_position(Wing_Room fk_position) {
		this.fk_position = fk_position;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Room getFk_room() {
		return fk_room;
	}
	public void setFk_room(Room fk_room) {
		this.fk_room = fk_room;
	}
	public Type_Sensor getFk_type_sensor() {
		return fk_type_sensor;
	}
	public void setFk_type_sensor(Type_Sensor fk_type_sensor) {
		this.fk_type_sensor = fk_type_sensor;
	}

	public Integer getScope_sensor() {
		return scope_sensor;
	}

	public void setScope_sensor(Integer scope_sensor) {
		this.scope_sensor = scope_sensor;
	}
	
	public String getDate_setup_formatted() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date_setup);
	}
	@Override
	public String toString() {
		
		return "Sensor [id=" + id + ", sensor_name=" + sensor_name + ", ip_address=" + ip_address + ", mac_address="
				+ mac_address + ", date_setup=" + getDate_setup_formatted() + ", status=" + status + ", installed=" + installed
				+ ", fk_position=" + fk_position + ", price=" + price + ", fk_room=" + fk_room + ", fk_type_sensor="
				+ fk_type_sensor + ", scope_sensor=" + scope_sensor + "]";
	}
	
	public Object[] getAsArray() {
		Object[] array = {id, sensor_name, ip_address, mac_address, date_setup, status, installed, fk_position, price, fk_room, fk_type_sensor, scope_sensor};
		return array;
	}
	
	
}
