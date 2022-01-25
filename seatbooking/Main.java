package com.seatbooking;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("------- Program starts ----- ");
		System.out.println("------- Input Args ----- ");
		System.out.println("  Input : List of integer array");
		System.out.println("  No of passenger ");
		
		List<int [][]> seatList = new ArrayList<>();
		int[][] arr = new int[2][3]; // Row by Column
		int[][] arr1 = new int[3][4];
		int[][] arr2 = new int[3][2];
		int[][] arr3 = new int[4][3];
		seatList.add(arr);
		seatList.add(arr1);
		seatList.add(arr2);
		seatList.add(arr3);
		int noOfpass = 30; // No of passenger
		Implementation implemtation = new Implementation(seatList, noOfpass);
		System.out.println("------ OUTPUT  ---- ");
		implemtation.doProcess();
		System.out.println("------ Program Ends ---- ");
	}

}
