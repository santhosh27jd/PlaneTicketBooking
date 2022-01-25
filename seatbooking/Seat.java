package com.seatbooking;


public class Seat {
	
	int windowSeat;
	int asileSeat;
	int middleSeat;

	public int getWindowSeat() {
		return windowSeat;
	}

	public void setWindowSeat(int windowSeat) {
		this.windowSeat = windowSeat;
	}

	public int getAsileSeat() {
		return asileSeat;
	}

	public void setAsileSeat(int asileSeat) {
		this.asileSeat = asileSeat;
	}

	public int getMiddleSeat() {
		return middleSeat;
	}

	public void setMiddleSeat(int middleSeat) {
		this.middleSeat = middleSeat;
	}

	public Seat(int windowSeat, int asileSeat, int middleSeat) {
		this.windowSeat = windowSeat;
		this.asileSeat = asileSeat;
		this.middleSeat = middleSeat;
	}
}
