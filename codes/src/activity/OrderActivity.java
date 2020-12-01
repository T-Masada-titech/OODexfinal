package activity;
import java.util.Map;
import java.util.Scanner;

import data.Contents;
import data.ReservationData;
import data.StockData;
public class OrderActivity extends Activity {
	public OrderActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}
	public void doActivity() {
		Contents order = takeOrder();
		if(order.isEmpty()) {
			System.out.println("注文処理を終了します");
			return;
		}
		if(checkAbleShipment(order)) {
			checkAndDoShipmentProcess(order);
		} else {
			checkAndDoReservationProcess(order);
		}
	}

	private Contents takeOrder() {
		Contents order = new Contents();
		Scanner scan =  new Scanner(System.in);
		while(checkContinue(scan)) {
			System.out.println("注文内容を入力してください");
			sd.printAllStock();
			System.out.println("種類はなんですか？");
			String sake = scan.next();
			sake = sake.toLowerCase();
			if(!sd.isExist(sake)) {
				System.out.println("正しい種類を入力してください");
				continue;
			}
			System.out.println("数を入力してください");
			int num;
			while((num = scan.nextInt()) <= 0) {
				System.out.println("正しい値を入力してください");
			}
			order.put(sake, num);
		}
		return order;
	}

	private boolean checkContinue(Scanner scan) {
		boolean isContinue = false;
		System.out.println("注文はまだありますか？  ある：y, ない：else");
		switch(scan.next()) {
			case "y":
				isContinue = true;
				break;
			default:
				isContinue = false;

		}
		return isContinue;
	}

	private boolean checkAbleShipment(Contents order) {
		boolean isAble = true;
		for(Map.Entry<String, Integer> e : order.entrySet()) {
			isAble = sd.checkAbleRemove(e.getKey(), e.getValue().intValue());
			if(!isAble) {
				break;
			}
		}

		if(isAble) {
			System.out.println("販売可能です");
			return true;
		} else {
			System.out.println("在庫がありません");
			return false;
		}
	}

	private void checkAndDoShipmentProcess(Contents order) {
		System.out.println("出荷処理を実行しますか？  実行する：y, 実行しない：else");
		if(scan.next().equals("y")) {
			System.out.println("出荷処理に移行します");
			ShipmentActivity sa = new ShipmentActivity(scan, sd, rd);
			sa.doActivityFromOrder(order);
		} else {
			System.out.println("出荷処理を中断しました");
		}
	}

	private void checkAndDoReservationProcess(Contents order) {
		System.out.println("予約処理を実行しますか？  実行する：y, 実行しない：else");
		if(scan.next().equals("y")) {
			System.out.println("予約処理に移行します");
			ReservationActivity ra = new ReservationActivity(scan, sd, rd);
			ra.doActivityFromOrder(order);
		} else {
			System.out.println("予約処理を中断しました");
		}
	}
}
