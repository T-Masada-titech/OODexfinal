package activity;

import java.util.Scanner;

import data.ReservationData;
import data.StockData;

public class PriceChangeActivity extends Activity{
	public PriceChangeActivity(Scanner scan, StockData sd, ReservationData rd) {
		this.scan = scan;
		this.sd = sd;
		this.rd = rd;
	}

	public void doActivity() {
		String kind;
		while(true) {
			sd.printAllStock();
			System.out.println("値段を変更したい商品の商品名を入力してください");
			kind = scan.next();
			if(!sd.isNewProduct(kind)) {
				break;
			}
			System.out.println("正しい種類を入力してください");
		}
		System.out.println("新しい値段を入力してください");
		int price = scan.nextInt();
		System.out.println("値段変更処理を実行しますか？   実行する：y, 実行しない：else");
		if(!scan.next().equals("y")) {
			System.out.println("値段変更処理を中断します\n");
			return;
		}
		sd.setPrice(kind, price);
		System.out.println("値段変更処理が完了しました\n");
	}
}
