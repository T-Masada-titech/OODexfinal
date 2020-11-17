
import java.util.Scanner;

import activity.Activity;

public class Main {
	public static void main(String args[]) {
		Activity activity;
		Scanner scan = new Scanner(System.in);
		boolean doNext = true;
		String act;
		while(doNext) {
			System.out.println("実行したい処理を選択してください\n"
							+ "入荷処理：a, 出荷処理：s, 注文処理：o\n"
							+ "予約作成：r, 予約キャンセル：c, 終了：e\n");
			act = scan.nextLine();
			if(act.equals("e")) {
				doNext = false;
			} else {
				activity = Activity.createActivity(act);
				activity.doActivity();
			}
		}
		scan.close();
		System.out.println("システムを終了しました");
	}
}
