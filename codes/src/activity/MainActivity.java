package activity;
import java.util.Scanner;

public class MainActivity {
	public static void main(String args[]) {
		ArrivalActivity arrival;
		CancelActivity cancel;
		OrderActivity order;
		ReservationActivity reservation;
		ShipmentActivity shipment;
		Scanner scan = new Scanner(System.in);
		boolean doNext = true;
		while(doNext) {
			System.out.println("実行したい処理を選択してください\n"
							+ "入荷処理：a, 出荷処理：s, 注文処理：o\n"
							+ "予約作成：r, 予約キャンセル：c, 終了：e\n");
			switch(scan.nextLine()) {
				case "a":
					arrival = new ArrivalActivity();
					arrival.doActivity();
					System.out.println();
					break;
				case "c":
					cancel = new CancelActivity();
					cancel.doActivity();
					System.out.println();
					break;
				case "o":
					order = new OrderActivity();
					order.doActivity();
					System.out.println();
					break;
				case "r":
					reservation = new ReservationActivity();
					reservation.doActivity();
					System.out.println();
					break;
				case "s":
					shipment = new ShipmentActivity();
					shipment.doActivity();
					System.out.println();
					break;
				case "e":
					doNext = false;
					break;
				default :
					System.out.println("正しい処理を選択してください\n");
			}
		}
		scan.close();
		System.out.println("システムを終了しました");
	}
}
