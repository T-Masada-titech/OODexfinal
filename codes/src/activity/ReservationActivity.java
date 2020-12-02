package activity;

import java.util.Scanner;

import data.Contents;
import data.MyCalendar;
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
		order = takeOrder();
		if(order.isEmpty()) {
			System.out.println("予約作成処理を中断します\n");
			return;
		}
		doActivityFromOrder(order);
	}
	public void doActivityFromOrder(Contents order) {
		this.order = order;
		System.out.println(order);
		System.out.println("この内容で予約しますか？    はい：y, いいえ：else");
		if(!scan.next().equals("y")) {
			System.out.println("予約処理を終了します");
			return;
		}
		if(rd.isFull()) {
			System.out.println("予約がいっぱいです\n");
			return;
		}
		System.out.println("お客様の名前を入力してください");
		String name = scan.next();
		System.out.println("お客様の電話番号を入力してください");
		String call = scan.next();
		System.out.println("予約日を入力してください");
		System.out.println("年:");
		int year = scan.nextInt();
		System.out.println("月:");
		int month = scan.nextInt();
		System.out.println("日:");
		int date = scan.nextInt();
		MyCalendar cal = new MyCalendar(year, month, date);
		int id = rd.createReservationID();
		rd.addReservation(id, name, call, cal, this.order);
		System.out.println("予約IDは " + id + " です");
		System.out.println("予約作成処理が完了しました\n");
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
}
