package RockPaperScissors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            System.out.print("Board için Satır sayısını girin: ");
            int n = sc.nextInt();
            System.out.print("Board için Sütun sayısını girin: ");
            int k = sc.nextInt();

        MineSweeper oyun = new MineSweeper(n, k);
        oyun.run();
    }


}
