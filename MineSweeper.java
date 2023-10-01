package RockPaperScissors;

import java.util.Scanner;

public class MineSweeper {
    private String[][] matris;
    private String[][] board;
    // Oyun tahtası için iki matris oluşturuluyor: biri kullanıcının göreceği matris, diğeri mayınları saklayan matris.
    private int n;
    private int k;
    private int mayinSayisi;

    public MineSweeper(int n, int k) {
        this.n = n;
        this.k = k;
        this.matris = new String[n][k];
        this.board = new String[n][k];
        this.mayinSayisi = (n * k) / 4;
        // Mayın sayısını hesapla (Boardın 1/4'ü kadar mayın olacak).
        initializeBoard();  // Matrisleri başlangıç değerleriyle doldur
        placeMines(); // Mayınları tahtaya yerleştir.
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int satir, sutun;
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(matris);    // Kullanıcının göreceği matrisi ekrana bas
            System.out.println();
            // Satır ve sütun girişini doğrulama
            do {
                System.out.print("Satır sayısını girin: ");
                satir = sc.nextInt();
                System.out.print("Sütun sayısını girin: ");
                sutun = sc.nextInt();
            } while (!checkInput(satir, sutun));

            if (board[satir][sutun].equals("*")) {
                // Eğer kullanıcı mayına bastıysa, oyunu sonlandır.
                System.out.println("Game Over");
                gameOver = true;
            } else {
                int sayac = countAdjacentMines(satir, sutun);
                matris[satir][sutun] = String.valueOf(sayac);
                board[satir][sutun]=String.valueOf(sayac);
            }

            if (checkWin()) {
                System.out.println("Tebrikler! Oyunu kazandınız!");
                printBoard(board);
                gameOver = true;
            }
            System.out.println("===========================");
            System.out.println();
        }
    }
    public boolean checkInput(int satir, int sutun) {
        // Seçilen satır ve sütun sınırlar içerisinde mi?
        return satir >= 0 && satir < n && sutun >= 0 && sutun < k;
    }

    private void initializeBoard() {
        // Matrisleri başlangıç değerleriyle doldur: "-"
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                matris[i][j] = "-";
                board[i][j] = "-";
            }
        }
    }

    private void placeMines() {
        // Mayınları rastgele tahtaya yerleştir.
        for (int i = 0; i < mayinSayisi; i++) {
            int satir, sutun;
            do {
                satir = (int) (Math.random() * n);
                sutun = (int) (Math.random() * k);
            } while (board[satir][sutun].equals("*"));
            board[satir][sutun] = "*";
        }
    }

    private void printBoard(String[][] matris) {
        // Kullanıcının göreceği matrisi ekrana bas.
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.print(matris[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int countAdjacentMines(int satir, int sutun) {
        // Kullanıcının seçtiği hücre etrafındaki mayın sayısını hesapla.
        int sayac = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + j;
                if (yeniSatir >= 0 && yeniSatir < n && yeniSutun >= 0 && yeniSutun < k && board[yeniSatir][yeniSutun].equals("*")) {
                    sayac++;
                }
            }
        }
        return sayac;
    }

    private boolean checkWin() {
        int acilanHucresayisi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (!matris[i][j].equals("-")) {
                    acilanHucresayisi++;
                }
            }
        }
        int toplamHucresayisi = n * k;
        int mayinHucresayisi = toplamHucresayisi - acilanHucresayisi;

        return mayinHucresayisi == mayinSayisi;
    }
}