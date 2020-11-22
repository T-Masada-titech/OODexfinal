package stock;
import java.util.ArrayList;

public class StockData {
	private ArrayList<Stock> data;
	private int maxSize; //在庫の最大数
	public StockData(int size) {
		data = new ArrayList<Stock>();
		maxSize = size;
	}


	public boolean addStock(String kind, int num) {
		if(num + data.size() > maxSize) {
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
			int i;
			for(i = 0; !data.get(i).kind().equals(kind); i++);
			data.remove(i);
		}
		return true;
	}

	public int size() {
		return data.size();
	}

	public int sizeOfKind(String kind) {
		int size = 0;
		for(Stock s: data) {
			if(s.kind().equals(kind)) size++;
		}
		return size;
	}
}
