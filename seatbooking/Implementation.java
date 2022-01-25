package com.seatbooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Implementation {

	private List<int[][]> seatList = null;
	private int noOfPassenger = 0;
	int all = 0;
	Map<Integer, ArrayList<String>> output = new LinkedHashMap<Integer, ArrayList<String>>();

	public Implementation(List<int[][]> seatList, int noOfPass) {
		// TODO Auto-generated constructor stub
		this.noOfPassenger = noOfPass;
		this.seatList = seatList;
	}

	/**
	 * doProcess
	 */
	public void doProcess() {
		Map<String, String> seatDetails = new LinkedHashMap<>();
		Map<Integer, Seat> seatLocation = new LinkedHashMap<>();
		Map<Integer, ArrayList<String>> detail = new LinkedHashMap<>();

		if (seatList == null || seatList.size() == 0) {
			return;
		}
		int rowNumber = 0;
		int windowSeat = 0;
		int middelSeat = 0;
		int asileSeat = 0;

		// Find seats
		for (int i = 0; i < seatList.size(); i++) {
			if (i == 0) {
				// first element
				int[][] seatArr = seatList.get(i);
				int row = seatArr.length;
				int column = seatArr[0].length;
				for (int j = 0; j < row; j++) {
					ArrayList<String> loc = new ArrayList<>();
					for (int k = 0; k < column; k++) {
						if (column == 2) {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "w"); // W- Window seat
								windowSeat++;
								loc.add("W" + windowSeat);
							} else {
								seatDetails.put(i + "-" + j + "-" + k, "A"); // A- Asile seat
								asileSeat++;
								loc.add("A" + asileSeat);
							}
						} else {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "w");
								windowSeat++;
								loc.add("W" + windowSeat);
							} else if (k == column - 1) {
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								loc.add("A" + asileSeat);
							} else {
								seatDetails.put(i + "-" + j + "-" + k, "M"); // M - Middle seat
								middelSeat++;
								loc.add("M" + middelSeat);
							}
						}

					}
					rowNumber++;
					seatLocation.put(rowNumber, new Seat(windowSeat, asileSeat, middelSeat));
					windowSeat = 0;
					middelSeat = 0;
					asileSeat = 0;
					detail.put(rowNumber, loc);
				}

			} else if (i == seatList.size() - 1) {
				rowNumber = 0;
				// last element
				int[][] seatArr = seatList.get(i);
				int row = seatArr.length;
				int column = seatArr[0].length;
				for (int j = 0; j < row; j++) {
					rowNumber++;
					ArrayList<String> loc = new ArrayList<>();
					if (detail.containsKey(rowNumber)) {
						loc = detail.get(rowNumber);
					}
					for (int k = 0; k < column; k++) {
						if (column == 2) {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}

							} else {
								seatDetails.put(i + "-" + j + "-" + k, "W");
								windowSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("W"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("W" + sum);
								} else {
									loc.add("W" + windowSeat);
								}
							}
						} else {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}
							} else if (k == column - 1) {
								seatDetails.put(i + "-" + j + "-" + k, "W");
								windowSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("W"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("W" + sum);
									;
								} else {
									loc.add("W" + windowSeat);
								}
							} else {
								seatDetails.put(i + "-" + j + "-" + k, "M");
								middelSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("M"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("M" + sum);
								} else {
									loc.add("M" + middelSeat);
								}
							}
						}

					}
					// rowNumber++;
					if (seatLocation.containsKey(rowNumber)) {
						Seat seat = seatLocation.get(rowNumber);
						seat.setAsileSeat(seat.getAsileSeat() + asileSeat);
						seat.setMiddleSeat(seat.getMiddleSeat() + middelSeat);
						seat.setWindowSeat(seat.getWindowSeat() + windowSeat);
						seatLocation.put(rowNumber, seat);
					} else {
						seatLocation.put(rowNumber, new Seat(windowSeat, asileSeat, middelSeat));
					}
					windowSeat = 0;
					middelSeat = 0;
					asileSeat = 0;
					detail.put(rowNumber, loc);

				}
			} else {
				// Middle element
				rowNumber = 0;
				int[][] seatArr = seatList.get(i);
				int row = seatArr.length;
				int column = seatArr[0].length;
				for (int j = 0; j < row; j++) {
					rowNumber++;
					ArrayList<String> loc = new ArrayList<>();
					if (detail.containsKey(rowNumber)) {
						loc = detail.get(rowNumber);
					}

					for (int k = 0; k < column; k++) {
						if (column == 2) {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}
							} else {
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}
							}
						} else {
							if (k == 0) { // window seat
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}

							} else if (k == column - 1) {
								seatDetails.put(i + "-" + j + "-" + k, "A");
								asileSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("A"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));
									int sum = val + 1;
									loc.add("A" + sum);
								} else {
									loc.add("A" + asileSeat);
								}
							} else {
								seatDetails.put(i + "-" + j + "-" + k, "M");
								middelSeat++;
								Optional<String> opt = loc.stream().filter(el -> el.contains("M"))
										.sorted(Collections.reverseOrder()).findFirst();
								if (opt.isPresent()) {
									String ls = opt.get();
									int val = Integer.parseInt(ls.substring(1, 2));

									int sum = val + 1;
									loc.add("M" + sum);
								} else {
									loc.add("M" + middelSeat);
								}
							}
						}

					}
					// rowNumber++;
					if (seatLocation.containsKey(rowNumber)) {
						Seat seat = seatLocation.get(rowNumber);
						seat.setAsileSeat(seat.getAsileSeat() + asileSeat);
						seat.setMiddleSeat(seat.getMiddleSeat() + middelSeat);
						seat.setWindowSeat(seat.getWindowSeat() + windowSeat);
						seatLocation.put(rowNumber, seat);
					} else {
						seatLocation.put(rowNumber, new Seat(windowSeat, asileSeat, middelSeat));
					}
					windowSeat = 0;
					middelSeat = 0;
					asileSeat = 0;
					detail.put(rowNumber, loc);
				}
			}
		}

		System.out.println("SEAT IDENTIFICATION WITH ROW  ----" + detail);
		fillSeats(detail, noOfPassenger);
		System.out.println("SEAT ALLOCATION BASED ON ROW --- " + output);
	}

	/**
	 * 
	 * @param seatLocation
	 * @param noOfPass
	 */
	private void fillSeats(Map<Integer, ArrayList<String>> seatLocation, int noOfPass) {
		// Allocating seats

		Iterator<Entry<Integer, ArrayList<String>>> it = seatLocation.entrySet().iterator();
		if (noOfPass == 0 || noOfPass < 0) {
			return;
		}

		while (it.hasNext() && noOfPass != 0) {
			Entry<Integer, ArrayList<String>> entry = it.next();
			ArrayList<String> list = entry.getValue();
			ArrayList<String> seatNumber = new ArrayList<String>();
			if (output.containsKey(entry.getKey())) {
				seatNumber = output.get(entry.getKey());
			}

			if (list.stream().filter(el -> el.contains("A")).findAny().isEmpty()) {
				if (list.stream().filter(el -> el.contains("W")).findAny().isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						if (noOfPass == 0 || noOfPass < 0) {
							output.put(entry.getKey(), seatNumber);
							return;
						}
						String pos = list.get(i);
						if (pos.contains("" + "M")) {
							all = all + 1;
							seatNumber.remove(i);
							seatNumber.add(i, all + "");
							noOfPass--;
							list.remove(i);
							list.add(i, "X");
						}
					}
					output.put(entry.getKey(), seatNumber);
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (noOfPass == 0 || noOfPass < 0) {
							output.put(entry.getKey(), seatNumber);
							return;
						}
						String pos = list.get(i);
						if (pos.contains("" + "W")) {
							all = all + 1;
							seatNumber.remove(i);
							seatNumber.add(i, all + "");
							noOfPass--;
							list.remove(i);
							list.add(i, "X");
						}
					}
					output.put(entry.getKey(), seatNumber);
				}

			} else {
				for (int i = 0; i < list.size(); i++) {
					if (noOfPass == 0 || noOfPass < 0) {
						output.put(entry.getKey(), seatNumber);
						return;
					}
					String pos = list.get(i);
					if (pos.contains("A")) {
						all = all + 1;
						seatNumber.add(i, all + "");
						noOfPass--;
						list.remove(i);
						list.add(i, "X");
					} else {
						seatNumber.add(i, 0 + "");
					}
				}
				output.put(entry.getKey(), seatNumber);
			}
		}
		if (noOfPass != 0) {
			fillSeats(seatLocation, noOfPass);
		}

	}

}
