package BL;

public class Flights{
	
	String origin, destination, time, date, username; 

	public Flights(String origin, String destination, String time, String date, String username) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.time = time;
		this.date = date;
		this.username = username; 
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		
		this.origin = origin;
		
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

