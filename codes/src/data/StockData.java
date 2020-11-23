package data;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StockData {
	private ArrayList<Stock> data;  //在庫データのうち、予約でないもの
	private ArrayList<Stock> dataForReservation;  //在庫データのうち、予約分
	private TreeMap<String, Integer> priceList = new TreeMap<>();
	private int maxSize; //在庫の最大数
	public StockData(int size) {
		data = new ArrayList<Stock>();
		dataForReservation = new ArrayList<Stock>();
		maxSize = size;
	}

	public boolean checkAbleAdd(int num) {
		return num + size() <= maxSize;
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
		return data.size() + dataForReservation.size();
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
			if(stocks.containsKey(s.kind())) {
				Integer sum = stocks.get(s.kind());
				stocks.replace(s.kind(), sum + 1);
			} else {
				stocks.put(s.kind(), 1);
			}
		}
		System.out.println("在庫一覧");
		while(!stocks.isEmpty()) {
			String kind = stocks.firstKey();
			Integer sum = stocks.get(kind);
			stocks.remove(kind);
			System.out.println(kind + " : " + sum);
		}
	}

	public void setPrice(String kind, int price) {
		if(priceList.containsKey(kind)) {
			priceList.replace(kind, price);
		} else {
			priceList.put(kind,  price);
		}
	}

	public int getPrice(String kind) {
		return priceList.get(kind);
	}

	public boolean removeDataForReservation(Contents order) {
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			int sum = 0;
			for(Stock s : dataForReservation) {
				if(s.kind().equals(e.getKey())) {
					sum ++;
				}
			}
			if(sum < e.getValue()) {
				return false;
			}
		}
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			for(int i = 0; i < e.getValue(); i++) {
				for(Stock s : dataForReservation) {
					if(s.kind().equals(e.getKey())) {
						dataForReservation.remove(s);
					}
				}
			}
		}
		return true;
	}

	public boolean removeStockFromOrder(Contents order) {
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			if(!checkAbleRemove(e.getKey(), e.getValue())) {
				return false;
			}
		}
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			removeStock(e.getKey(), e.getValue());
		}
		return true;
	}
/*
	public boolean moveToReservationData(Contents order) {
		for(Map.Entry<String, Integer> e: order.entrySet()) {
			if(!checkAbleRemove(e.getKey(), e.getValue())) {
				return false;
			}
		}
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			removeStock(e.getKey(), e.getValue());
			dataForReservation.add(new Stock(e.getKey()));
		}
		return true;
	}
	*/
}
