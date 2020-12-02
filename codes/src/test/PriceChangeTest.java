package test;

import java.util.Scanner;

import activity.Activity;
import data.ReservationData;
import data.StockData;
import junit.framework.TestCase;

public class PriceChangeTest extends TestCase {
	public void testPriceChange() {
		final int MAX_STOCK_SIZE = 100;
		final int MAX_RESERVATION_SIZE = 10;
		final ReservationData rd = new ReservationData(MAX_RESERVATION_SIZE);
		final StockData sd = new StockData(MAX_STOCK_SIZE);
		sd.addStock("nihonshu", 10);
		sd.addStock("wine", 10);
		sd.setPrice("nihonshu", 2500);
		sd.setPrice("wine", 2000);
		Scanner scan = new Scanner(System.in);
		Activity activity = Activity.createActivity(Activity.STR_PRICECHANGE, scan, sd, rd);
		activity.doActivity();
		scan.close();
	}
}
