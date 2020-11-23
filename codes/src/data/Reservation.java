package data;

public class Reservation implements Comparable<Reservation>{
	private int id;
	private String name;
	private String callNumber;
	private Contents contents;
	public Reservation(int id, String name, String callNumber, Contents contents) {
		this.id = id;
		this.name = name;
		this.callNumber = callNumber;
		this.contents = contents;
	}

	int getID() {
		return this.id;
	}

	public Contents getContents() {
		return contents;
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
}
