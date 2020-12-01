
import java.util.Scanner;

import activity.Activity;
import data.ReservationData;
import data.StockData;

public class Main {
	public static final int MAX_STOCK_SIZE = 100;
	public static final int MAX_RESERVATION_SIZE = 10;
	public static ReservationData reservationData = new ReservationData(MAX_RESERVATION_SIZE);
	public static StockData stockData = new StockData(MAX_STOCK_SIZE);

	public static void main(String args[]) {
		stockData.addStock("nihonshu", 10);
		stockData.addStock("wine", 10);
		stockData.setPrice("nihonshu", 2500);
		stockData.setPrice("wine", 2000);
		Scanner scan = new Scanner(System.in);
		Activity activity;
		boolean doNext = true;
		String act;
		while(doNext) {
			System.out.println("実行したい処理を選択してください\n"
							+ "入荷処理：" + Activity.STR_ARRIVE + ", 出荷処理：" + Activity.STR_SHIPMENT
							+ ", 注文処理：" + Activity.STR_ORDER + "\n"
							+ "予約作成：" + Activity.STR_RESERVATION + ", 予約キャンセル：" + Activity.STR_CANCEL
							+ ", 値段変更処理：" + Activity.STR_PRICECHANGE + ", 終了：e\n");
			act = scan.next();
			if(act.equals("e")) {
				doNext = false;
			} else {
				activity = Activity.createActivity(act, scan, stockData, reservationData);
				activity.doActivity();
			}
		}
		scan.close();
		System.out.println("システムを終了しました");
	}
}
