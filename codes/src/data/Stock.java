package data;

public class Stock implements Comparable<Stock>{
	private String kind;
	public Stock(String kind) {
		this.kind = kind;
	}

	public String kind() {
		return kind;
	}

	public int compareTo(Stock s) {
		return this.kind.compareTo(s.kind());
	}
}
