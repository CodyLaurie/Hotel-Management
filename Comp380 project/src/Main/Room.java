package Main;

import java.util.ArrayList;
import java.util.List;

public class Room{
	private boolean reserved;
	private boolean service;
	private int total = 0;
	private List<String> items = new ArrayList<String>();
	
	public Room(boolean resstatus,boolean setservice,int settotal,List<String> setitems){
		this.reserved = resstatus;
		this.service = setservice;
		this.total = settotal;
		this.items = setitems;
	}
	
	public void setreserved(boolean status){
		this.reserved = status;
	}
	
	public void setservice(boolean status){
		this.service = status;
	}

	public void addtotal(int amount){
		this.total = total + amount;
	}
	
	public void additems(String item){
		this.items.add(item);
		this.service = true;
		HotelManagement.save();
	}
	
	public boolean getreserved(){
		return this.reserved;
	}
	
	public boolean getservice(){
		return this.service;
	}

	public int gettotal(){
		return this.total;
	}
	
	public List<String> getfood(){
		return this.items;
	}
	
	public void removefood(String food){
		this.items.remove(food);
	}
	
	
}