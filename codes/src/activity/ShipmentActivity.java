package activity;

import java.util.Scanner;

import data.Contents;
import data.ReservationData;
import data.StockData;

public class ShipmentActivity extends Activity {
	private Contents order;

	public ShipmentActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}
	public void doActivity() {
		System.out.println("出荷処理が完了しました\n");
	}

	public void doActivityFromOrder(Contents order) {
		this.order = order;
	}
}
