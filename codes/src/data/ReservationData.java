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

	public boolean addReservation(int id, String name, String callNumber, Contents contents) {
		if(id == -1) {
			return false;
		}
		data.add(new Reservation(id, name, callNumber, contents));
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

	public int createReservationID() {
		if(data.size() == maxSize) {
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
			Reservation r = data.get(i);
			System.out.println(r.getID() + " | " + r.getClientName()
			           + " | " + r.getCallNumber());
			Contents c = r.getContents();
			for(Map.Entry<Stock, Integer> e : c.entrySet()) {
				String kind = e.getKey().kind();
				Integer sum = e.getValue();
				System.out.println(kind + " : " + sum);
			}
		}
	}
}
