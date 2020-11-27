package data;

import java.util.Map;

public class Reservation implements Comparable<Reservation>{
	private int id;
	private String name;
	private String callNumber;
	private String date;
	private Contents contents;
	public Reservation(int id, String name, String callNumber,String date, Contents contents) {
		this.id = id;
		this.name = name;
		this.callNumber = callNumber;
		this.date = date;
		this.contents = contents;
	}

	int getID() {
		return this.id;
	}

	public Contents getContents() {
		return contents;
	}

	public String getDate() {
		return this.date;
	}

	public String getClientName() {
		return this.name;
	}

	public String getCallNumber() {
		return this.callNumber;
	}

	public int compareTo(Reservation r) {
		return this.id - r.getID();
	}

	public String toString() {
		String out =  "\n" + getID() + " | " + getClientName() + " | "
				+ getCallNumber() + " | " + getDate() + "\n";
		for(Map.Entry<String, Integer> e : getContents().entrySet()) {
			out = out + e.getKey() + " : " + e.getValue() + "\n";
		}
		return out;
	}
}
