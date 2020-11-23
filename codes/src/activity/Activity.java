package activity;
import data.ReservationData;
import data.StockData;

public class Activity {
	public static Activity createActivity(String act, StockData sd, ReservationData rd) {
		switch(act) {
		case "a":
			System.out.println("入荷処理を開始します");
			return new ArrivalActivity();
		case "o":
			System.out.println("注文処理を開始します");
			return new OrderActivity();
		case "s":
			System.out.println("出荷処理を開始します");
			return new ShipmentActivity();
		case "r":
			System.out.println("予約作成処理を開始します");
			return new ReservationActivity();
		case "c":
			System.out.println("予約キャンセル処理を開始します");
			return new CancelActivity();
		default:
			return new DefaultActivity();
		}
	}
	public void doActivity() {
	};
}
