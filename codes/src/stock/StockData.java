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
			for(Stock s: data) {
				if(s.kind().equals(kind)) {
					data.remove(s);
					break;
				}
			}
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

	public void printAllStock() {
		ArrayList<String> stock = new ArrayList<String>();
		ArrayList<Integer> sum = new ArrayList<Integer>();
		for(Stock s:data) {
			if(stock.contains(s.kind())) {
				int i;
				for(i = 0; i < stock.size(); i++) {
					if(stock.get(i).equals(s.kind())) {
						break;
					}
				}
				sum.set(i, sum.get(i) + 1);

			} else {
				stock.add(s.kind());
				sum.add(1);
			}
		}
		System.out.println("在庫一覧");
		for(int i = 0; i < stock.size(); i++) {
			System.out.println(stock.get(i) + " : " + sum.get(i));
		}
	}
}
