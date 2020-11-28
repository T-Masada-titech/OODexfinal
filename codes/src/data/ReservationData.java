package data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ReservationData {
	private ArrayList<Reservation> data;
	private int maxSize; //予約の最大数
	public ReservationData(int size) {
		data = new ArrayList<Reservation>();
		maxSize = size;
	}

	public boolean addReservation(int id, String name, String callNumber,String date, Contents contents) {
		if(id == -1) {
			return false;
		}
		data.add(new Reservation(id, name, callNumber,date, contents));
		return true;
	}

	public boolean removeReservation(int id) {
		boolean isRemoved = false;
		for(Reservation s: data) {
			if(s.getID() == id) {
				data.remove(s);
				isRemoved = true;
				break;
			}
		}
		return isRemoved;
	}

	public Reservation getReservation(int id) {
		for(Reservation r : data) {
			if(r.getID() == id) {
				return r;
			}
		}
		return null;
	}

	public boolean isFull() {
		if(data.size() == maxSize) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		if(data.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	public int createReservationID() {
		if(isFull()) {
			return -1; // error : reservation is full
		}
		int id = -1;
		boolean isCreated;
		for(int i = 0; i < maxSize; i++) {
			isCreated = true;
			for(Reservation r: data) {
				if(r.getID() == i) {
					isCreated = false;
					break;
				}
			}
			if(isCreated) {
				id = i;
				break;
			}
		}
		return id;
	}

	public int size() {
		return data.size();
	}

	public void printAllReservation() {
		Collections.sort(data);
		System.out.println("予約一覧");
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}

	public Contents getReservationContentsFromDate(String date) {
		Contents contents = new Contents();
		for(Reservation r : data) {
			if(r.getDate().equals(date)) {
				for(Map.Entry<String, Integer> e : r.getContents().entrySet()) {
					if(contents.containsKey(e.getKey())) {
						contents.put(e.getKey(), e.getValue() + contents.get(e.getKey()));
					} else {
						contents.put(e.getKey(), e.getValue());
					}
				}
			}
		}
		return contents;
	}
}
