package data;
import java.util.ArrayList;

public class StockData {
	private ArrayList<Stock> data;
	private int maxSize; //在庫の最大数
	public StockData(int size) {
		data = new ArrayList<Stock>();
		maxSize = size;
	}

	public boolean checkAbleAdd(int num) {
		return num + data.size() <= maxSize;
	}

	public boolean checkAbleRemove(String kind, int num) {
		return sizeOfKind(kind) - num >= 0;
	}

	public boolean addStock(String kind, int num) {
		if(!checkAbleAdd(num)) {
			return false;     //check they can add stocks
		}
		for(int n = 0; n < num; n++) {
			data.add(new Stock(kind));
		}
		return true;
	}

	public boolean removeStock(String kind, int num) {
		if(!checkAbleRemove(kind, num)) {
			return false;  // check they can remove stocks
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
		Contents stocks = new Contents();
		for(Stock s:data) {
			if(stocks.containsKey(s)) {
				Integer sum = stocks.get(s);
				stocks.replace(s, sum + 1);
			} else {
				stocks.put(s, 1);
			}
		}
		System.out.println("在庫一覧");
		while(!stocks.isEmpty()) {
			Stock stock = stocks.firstKey();
			String kind = stock.kind();
			Integer sum = stocks.get(stock);
			stocks.remove(stock);
			System.out.println(kind + " : " + sum);
		}
	}
}
