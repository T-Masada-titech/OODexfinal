package activity;
import java.util.Scanner;

import data.ReservationData;
import data.StockData;

public class Activity {
	protected Scanner scan;
	protected StockData sd;
	protected ReservationData rd;
	public static final String STR_ARRIVE = "a";
	public static final String STR_ORDER = "o";
	public static final String STR_SHIPMENT = "s";
	public static final String STR_RESERVATION = "r";
	public static final String STR_CANCEL = "c";
	public static final String STR_PRICECHANGE = "p";

	public static Activity createActivity(String act, Scanner scan, StockData sd, ReservationData rd) {
		switch(act) {
		case STR_ARRIVE:
			System.out.println("入荷処理を開始します");
			return new ArrivalActivity(scan, sd, rd);
		case STR_ORDER:
			System.out.println("注文処理を開始します");
			return new OrderActivity(scan, sd, rd);
		case STR_SHIPMENT:
			System.out.println("出荷処理を開始します");
			return new ShipmentActivity(scan, sd, rd);
		case STR_RESERVATION:
			System.out.println("予約作成処理を開始します");
			return new ReservationActivity(scan, sd, rd);
		case STR_CANCEL:
			System.out.println("予約キャンセル処理を開始します");
			return new CancelActivity(scan, sd, rd);
		case STR_PRICECHANGE:
			System.out.println("値段変更処理を開始します");
			return new PriceChangeActivity(scan, sd, rd);
		default:
			return new DefaultActivity();
		}
	}
	public void doActivity() {
	};
}
