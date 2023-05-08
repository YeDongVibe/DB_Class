package subject;

import java.util.ArrayList;
import java.util.Scanner;

class QueryExe {
	int num;
	String text;

	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}

	int getNum() {
		return num;
	}

	String getText() {
		return text;
	}
}

public class test01 {

	public static void exe01() {

	}

	public static void main(String[] args) {

		ArrayList<QueryExe> list = new ArrayList<>();

		list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라"));

		Scanner sc = new Scanner(System.in);
		while (true) {

			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}

			System.out.println("선택 <0 : EXIT> : ");
			int sel = sc.nextInt();
			if (sel == 0)
				break;

			for (QueryExe qe : list) {
				if (qe.getNum() == sel) {

				}
			}
		}
		sc.close();
	}

}
