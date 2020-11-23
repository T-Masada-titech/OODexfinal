package test;

import data.Contents;
import data.ReservationData;
import data.Stock;
import junit.framework.TestCase;

public class ReservationDataTest extends TestCase {
	public void testReservationData() {
		int id1;
		String name;
		String callNum;
		Contents contents = new Contents();
		ReservationData rd = new ReservationData(10);
		id1 = rd.createReservationID();
		name = "s";
		callNum = "080";
		contents.put(new Stock("Wine"), 3);
		contents.put(new Stock("Japanese Sake"), 2);
		assertTrue(rd.addReservation(id1, name, callNum, contents)); // check add
		int id2 = rd.createReservationID();
		assertFalse(id2 == -1); // check create ID
		name = "t";
		rd.addReservation(id2,  name, callNum, contents);
		rd.printAllReservation();   // check printAllReservation
		for(int i = 1; i <= 8; i++) {
			rd.addReservation(id2 + i, name, callNum, contents);
		}
		id2 = rd.createReservationID();
		assertFalse(rd.addReservation(id2, name, callNum, contents)); // check don't add if it is full
		assertTrue(rd.removeReservation(id1));  // check remove
		for(int i = 1; i <= 9; i++) {
			assertTrue(rd.removeReservation(i));
		}
		rd.printAllReservation(); //  They aren't any reservations
	}
}
