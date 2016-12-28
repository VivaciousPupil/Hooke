
package practice_01;

import java.util.Scanner;

public class Huk {

	public static int[] getRandom(int[] num, int holes, int knives) {
		int[] arr = new int[knives];
		// 刀子的個數
		int n;
		for (int i = 0; i < arr.length; i++) {
			n = (int) (Math.random() * (holes - i));
			arr[i] = num[n];
			for (int j = n; j < num.length - 1; j++) {
				num[j] = num[j + 1];
			}
		}
		return arr;
	}
	public static void game(Player p, int holes, int knives) {
		// Player p = new Player();
		p.chance = holes;
		int[] bucket = new int[holes + 1];
		int[] arr = new int[holes];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i ;
			 System.out.print(arr[i]+" ");
		}
		// System.out.print("\n隨機不重複產生10個亂數: ");
		int[] Array;
		Array = getRandom(arr, holes, knives);
		for (int i = 0; i < (knives - 1); i++) {
			// System.out.println(Array[i] + " ");
			bucket[Array[i]] = -1;
		}

		Scanner sc = new Scanner(System.in);
		while (p.hp > 0 && p.chance > 0) {
			System.out.println("請輸入想刺入的數字洞口(從１～" + (holes - 1) + ")：");
			int choice = Integer.parseInt(sc.nextLine())+1;
			System.out.println(choice-1);
			// System.out.println(bucket[choice]);
			if (bucket[choice] == 0) {
				System.out.println("你成功撐過 1 次了！");
				bucket[choice] = 1;// 把已選到的數字改成不能再輸入了
				p.chance--;
			} else if (bucket[choice] == -1) {
				bucket[choice] = 1;
				p.hp -= 20;
				p.chance--;
				System.out.println("你抽到刀子了！");
			} else if (bucket[choice] == 1) {
				p.chance--;
				System.out.println("你已經輸入過了，你這小壞壞！請再輸入一次！");
				p.chance++;
			}
			if (p.hp <= 0) {
				System.out.println("你還剩" + 0 + "局!");
			} else {
				if(p.chance-1<0){
					break;
				}else{
				System.out.println("你還剩" + (p.chance-1) + "局!");
				}
			}
			System.out.println("hp:" + p.hp);
			System.out.println();
		}
		if (p.hp <= 0 || (p.hp <= 0 && (p.chance-1) <= 0)) {
			System.out.println("很不幸的，你死了！");
		}
		if ((p.chance-1) <= 0 && p.hp > 0) {
			System.out.println("很高興告訴你！你獲勝了！");
		}
	}
	
	public static void main(String[] args) {
		// System.out.println("請輸入玩家人數：");
		// int people =
		// while()
		Player p = new Player();
		System.out.println("請輸入你的名字：");
		Scanner sc = new Scanner(System.in);
		p.name = sc.nextLine();
		System.out.println("Hi!" + p.name);
		boolean repeat = true;
		boolean five_holes = true;
		while (repeat) {
			System.out.println("你想要有幾個洞口(大於5個洞口)？：");
			int holes = sc.nextInt() + 1;
			while (five_holes) {
				if ((holes-1) < 5) {
					System.out.println("題目有說，你都沒在聽膩！");
					System.out.println("你想要有幾個洞口(大於5個洞口)？：");
					 holes = sc.nextInt() + 1;
					continue;
				} else {
					five_holes = false;
				}
			}
			System.out.println("你想要有幾把刀？：");
			int knives = sc.nextInt();
			if (knives >= (holes - 1)) {
				System.out.println("多出來的插你腦袋喔！");
			} else {
				System.out.println("開始遊戲!");
				game(p, holes, knives);
				repeat = false;
			}
		}
	}

}
