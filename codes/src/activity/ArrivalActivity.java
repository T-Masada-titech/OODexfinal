package activity;

import java.util.Map;
import java.util.Scanner;

import data.Contents;
import data.ReservationData;
import data.StockData;

public class ArrivalActivity extends Activity {
	public ArrivalActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd  = sd;
		this.rd = rd;
	}
	public void doActivity() {
		Contents reservationContents = getAndPrintReservationContentsNextDay();
		Contents arrivalContents = getArrivalContents();
		Contents addStockContents = getAddStockContents(arrivalContents, reservationContents);
		if(arrivalContents.isEmpty()) {
			System.out.println("入荷処理を中断します\n");
			return;
		}
		System.out.println("入荷内容"
				+ arrivalContents);
		System.out.println("入荷処理を実行しますか？  実行する：y, 実行しない：else");
		if(!scan.next().equals("y")) {
			System.out.println("入荷処理を中断します\n");
			return;
		}
		//在庫データを増やす
		for(Map.Entry<String, Integer> e : addStockContents.entrySet()) {
			sd.addStock(e.getKey(), e.getValue());
			checkNewAndSetPrice(e.getKey());
		}
		for(Map.Entry<String, Integer> e : reservationContents.entrySet()) {
			sd.addDataForReservation(e.getKey(), e.getValue());
		}
		sd.printAllStock();
		sd.printAllReservationStock();

		System.out.println("入荷処理が完了しました\n");
	}

	private Contents getAndPrintReservationContentsNextDay() {
		System.out.println("次営業日の日にちを入力してください。");
		String date = scan.next();
		System.out.println(date + "分の予約の注文内容の総計は");
		Contents contents = rd.getReservationContentsFromDate(date);
		System.out.println(contents);
		return contents;
	}

	private Contents getArrivalContents() {
		Contents contents = new Contents();
		Scanner scan =  new Scanner(System.in);
		while(true) {
			while(checkContinue(scan)) {
				System.out.println("入荷内容を入力してください");
				sd.printAllStock();
				System.out.println("種類はなんですか？");
				String sake = scan.next();
				sake = sake.toLowerCase();
				System.out.println("数を入力してください");
				int num;
				while((num = scan.nextInt()) <= 0) {
					System.out.println("正しい値を入力してください");
				}
				contents.put(sake, num);
				int sum = 0;
				for(Map.Entry<String, Integer> e : contents.entrySet()) {
					sum += e.getValue();
				}
				if(!sd.checkAbleAdd(sum)) {
					System.out.println("在庫がいっぱいのため、在庫を追加できません");
				}
			}
			int sum = 0;
			for(Map.Entry<String, Integer> e : contents.entrySet()) {
				sum += e.getValue();
			}
			if(!sd.checkAbleAdd(sum)) {
				System.out.println("在庫がいっぱいのため、在庫を追加できません\n"
									+ "在庫総計 : " + sd.size());
			} else {
				break;
			}
		}
		return contents;
	}



	private boolean checkContinue(Scanner scan) {
		boolean isContinue = false;
		System.out.println("入荷するものはまだありますか？  ある：y, ない：else");
		switch(scan.next()) {
			case "y":
				isContinue = true;
				break;
			default:
				isContinue = false;

		}
		return isContinue;
	}

	private Contents getAddStockContents(Contents arrival, Contents reservation) {
		Contents contents = new Contents();
		for(Map.Entry<String, Integer> ea : arrival.entrySet()) {
			String kind = ea.getKey();
			int sum = ea.getValue();
			for(Map.Entry<String, Integer> er : reservation.entrySet()) {
				if(kind.equals(er.getKey())) {
					if(sum > er.getValue()) {
						kind = ea.getKey();
						sum = ea.getValue() - er.getValue();
					} else {
						sum = 0;
					}
				}
			}
			if(sum != 0)
				contents.put(kind, sum);
		}
		return contents;
	}

	private void checkNewAndSetPrice(String kind) { //新商品の値段を設定する
		if(sd.isNewProduct(kind)) {
			System.out.println("新商品の料金を入力してください");
			int price = scan.nextInt();
			sd.setPrice(kind, price);
		}
	}


}
