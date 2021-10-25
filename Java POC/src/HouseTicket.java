import java.util.Scanner;

public class HouseTicket {
	public static int ticket[][] = new int[3][9];

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public void displayGeneratedTicket() {
		System.out.println("Output :- ");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (ticket[i][j] == 0)
					System.out.print("  , ");
				else {
					if (ticket[i][j] <= 9)
						System.out.print("0" + ticket[i][j] + ", ");
					else
						System.out.print(ticket[i][j] + ", ");
				}
			}
			if (ticket[i][8] == 0)
				System.out.println("  ");
			else
				System.out.println(ticket[i][8]);
		}
	}

	public static int elementNumber(int i, int j) {
		int number;
		if (j == 0)
			number = getRandomNumber(1, 10);
		else if (j == 1)
			number = getRandomNumber(11, 20);
		else if (j == 2)
			number = getRandomNumber(21, 30);
		else if (j == 3)
			number = getRandomNumber(31, 40);
		else if (j == 4)
			number = getRandomNumber(41, 50);
		else if (j == 5)
			number = getRandomNumber(51, 60);
		else if (j == 6)
			number = getRandomNumber(61, 70);
		else if (j == 7)
			number = getRandomNumber(71, 80);
		else
			number = getRandomNumber(81, 90);

		return number;
	}

	public void generateTicket(int row, int col) {
		int count = 0;
		int countColumnElement[] = new int[9];
		// 1st row
		while (count < 5) {
			int j = getRandomNumber(0, 9);
			if (ticket[0][j] == 0) {
				ticket[0][j] = elementNumber(0, j);
				countColumnElement[j]++;
				count++;
			}
		}

		// For 2nd row
		count = 0;
		while (count < 5) {
			int j = getRandomNumber(0, 9);
			if (ticket[1][j] == 0) {
				int value = elementNumber(1, j);
				if (value != ticket[0][j]) {
					ticket[1][j] = value;
					countColumnElement[j]++;
					count++;
				}
			}
		}

		// For 3rd row
		count = 0;
		while (count < 5) {
			int j = getRandomNumber(0, 9);
			if (countColumnElement[j] < 2) {
				if (ticket[2][j] == 0) {
					int value = elementNumber(2, j);
					if (value != ticket[0][j]) {
						ticket[2][j] = value;
						countColumnElement[j]++;
						count++;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Rows:");
		int row = sc.nextInt();
		System.out.println("Enter Columns:");
		int col = sc.nextInt();

		HouseTicket ht = new HouseTicket();
		ht.generateTicket(row, col);
		ht.displayGeneratedTicket();
	}

}
