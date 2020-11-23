package activity;

import java.util.Scanner;

import data.Contents;
import data.ReservationData;
import data.StockData;

public class ReservationActivity extends Activity {
	private Contents order;

	public ReservationActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}
	public void doActivity() {
		System.out.println("予約作成処理が完了しました\n");
	}
	public void doActivityFromOrder(Contents order) {
		this.order = order;
		System.out.println("お客様の名前を入力してください");
		String name = scan.next();
		System.out.println("お客様の電話番号を入力してください");
		String call = scan.next();
		System.out.println("予約日を入力してください");
		String date = scan.next();
		int id = rd.createReservationID();
		if(rd.addReservation(id, name, call, date, this.order)) {
			System.out.println("予約IDは " + id + " です");
		} else {
			System.out.println("予約がいっぱいです");
		}
		System.out.println("予約作成処理が完了しました\n");
	}
}
