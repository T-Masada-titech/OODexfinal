package activity;

import java.util.Map;
import java.util.Scanner;

import data.Contents;
import data.Reservation;
import data.ReservationData;
import data.StockData;

public class ShipmentActivity extends Activity {
	private Contents order = null;

	public ShipmentActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}
	public void doActivity() { //予約からの出荷処理
		Reservation r;
		System.out.println("予約IDを入力してください");
		int ID = scan.nextInt();
		if((r = rd.getReservation(ID)) == null) {
			System.out.println("予約が存在しません");
			rd.printAllReservation();
			return;
		} else {
			order = r.getContents();
		}
		if(!printOrderAndCheckContinue()) {
			System.out.println("出荷処理を中断します\n");
			return;
		}
		if(!sd.removeDataForReservation(order)) {
			System.out.println("予約用在庫がまだ足りないため、出荷できません");
			System.out.println("出荷処理を中断します\n");
			return;
		}
		rd.removeReservation(ID);
		System.out.println("料金は" + calcPrice() + "円です");

		System.out.println("出荷処理が完了しました\n");
	}

	public void doActivityFromOrder(Contents order) {//注文からの出荷処理
		this.order = order;
		if(!printOrderAndCheckContinue()) {
			System.out.println("出荷処理を中断します\n");
			return;
		}
		sd.removeStockFromOrder(order);
		System.out.println("料金は" + calcPrice() + "円です");
		System.out.println("出荷処理が完了しました\n");
	}

	public int calcPrice() {
		int sum = 0;
		for(Map.Entry<String, Integer> e: order.entrySet()) {
			sum += sd.getPrice(e.getKey()) * e.getValue();
		}
		return sum;
	}

	private boolean printOrderAndCheckContinue() {
		System.out.println("注文内容\n" + order);
		System.out.println("出荷処理を実行しますか?  実行する：y, 実行しない：else");
		if(scan.next().equals("y")) {
			return true;
		}
		return false;
	}
}
