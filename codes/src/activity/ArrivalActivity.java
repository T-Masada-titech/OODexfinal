package activity;

import java.util.Scanner;

import data.ReservationData;
import data.StockData;

public class ArrivalActivity extends Activity {
	public ArrivalActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd  = sd;
		this.rd = rd;
	}
	public void doActivity() {
		/* 処理 */
		System.out.println("入荷処理が完了しました\n");
	}
}
