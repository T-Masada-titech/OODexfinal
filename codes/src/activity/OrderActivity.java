package activity;
import java.util.Scanner;
public class OrderActivity extends Activity {
	public void doActivity() {
		Scanner scan = new Scanner(System.in);
		System.out.println("注文内容を入力してください\n"
				+ "種類はなんですか？\n"
				+ "日本酒：j, ワイン：w");
		String sake = scan.next();
		System.out.println("数を入力してください");
		int num = scan.nextInt();


		scan.close();
	}
}
