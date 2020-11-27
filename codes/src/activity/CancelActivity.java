package activity;

import java.util.Scanner;

import data.ReservationData;
import data.StockData;

public class CancelActivity extends Activity {
	public CancelActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}
	public void doActivity() {
		if(rd.isEmpty()) {
			System.out.println("予約は存在しません\n");
			return;
		}
		System.out.println("予約IDを入力してください");
		int id = scan.nextInt();
		if((rd.getReservation(id)) == null) {
			System.out.println("該当の予約が存在しません");
			rd.printAllReservation();
			System.out.printf("\n");
			return;
		}
		System.out.println(rd.getReservation(id));
		System.out.println("本当に予約をキャンセルしますか？ する：y, しない：else");
		if(scan.next().equals("y")) {
			removeReservation(id);
			System.out.println("予約をキャンセルしました");
		}
		System.out.println("予約キャンセル処理を終了します");
	}

	private  void removeReservation(int id) {
		rd.removeReservation(id);
	}
}
