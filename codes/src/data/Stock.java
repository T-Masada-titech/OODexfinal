package data;

public class Stock implements Comparable<Stock>{
	private String kind;
	public Stock(String kind) {
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}

	public int compareTo(Stock s) {
		return this.kind.compareTo(s.getKind());
	}
}
