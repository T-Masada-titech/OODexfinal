package test;

import data.StockData;
import junit.framework.TestCase;
public class StockDataTest extends TestCase {
	public void testStockDataTest() {
		String js = "Japanese Sake";
		String w = "Wine";
		StockData sd = new StockData(10);

		sd.addStock(js, 3);
		sd.addStock(w,  1);
		assertEquals(sd.size(), 4);
		assertTrue(sd.removeStock(w, 1));
		assertFalse(sd.removeStock(w,  1));  // remove OK
		assertEquals(sd.size(), 3);
		assertTrue(sd.addStock(js,  7));
		assertFalse(sd.addStock(js,  1));  //add OK
		assertEquals(sd.sizeOfKind(js), 10);
		assertEquals(sd.sizeOfKind(w), 0);  // sizeOfKind OK
		sd.removeStock(js,  3);
		sd.addStock(w, 3);
		sd.printAllStock();
		sd.printAllStock(); // check deep copy
	}
}
