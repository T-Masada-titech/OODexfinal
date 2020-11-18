package stock;
import java.util.ArrayList;

public class StockData {
	private ArrayList<Stock> data;
	public StockData() {
		data = new ArrayList<Stock>();
	}
	private final int MAX_STOCK = 1000; //在庫の最大数

	public boolean addStock(String kind, int num) {
		if(num + data.size() > MAX_STOCK) {
			return false;
		}
		for(int n = 0; n < num; n++) {
			data.add(new Stock(kind));
		}
		return true;
	}

	public boolean removeStock(String kind, int num) {
		if(sizeOfKind(kind) - num < 0) {
			return false;
		}
		for(int n = 0; n < num; n++) {
			for(int i = 0; !data.get(i).kind().equals(kind); i++) {
				data.remove(i);
			}
		}
		return true;
	}

	public int size() {
		return data.size();
	}

	private int sizeOfKind(String kind) {
		int size = 0;
		for(Stock s: data) {
			if(s.kind().equals(kind)) size++;
		}
		return size;
	}
}
