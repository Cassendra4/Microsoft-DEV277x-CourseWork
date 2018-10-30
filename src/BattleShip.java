import java.util.Random;
import java.util.Scanner;


/**
 * @battleShip
 * @Hasangi Thathsarani
 */

public class BattleShip {

    public void battleShip() {
        int[][] arr = new int[10][10];
        int[][] player = new int[10][10];
        int[][] com = new int[10][10];
        int[] count = new int[2];
        System.out.println("**** Welcome to the Battle Ships game ****");
        System.out.println("Right now the sea is empty" + "\n\n");
        int[][] a = {{1, 2}, {1, 2, 3}};
        int[][] b = {{1, 2, 3}, {1, 2}};
        printBattleShip(arr, count, player);
        getShipLocations(arr);
        printBattleShip(arr, count, player);
        generateComputeShips(arr);

        do {
            playersTurn(player, arr);
            printBattleShip(arr, count, player);
            computerTurn(com, arr);
            printBattleShip(arr, count, player);
        } while (count[0] >= 1 && count[1] >= 1);
    }

    private static void printBattleShip(int[][] arr, int[] count, int[][] play) {
        int player = 0, com = 0;
        System.out.print("   ");
        for (int j = 0; j < arr[0].length; j++) {
            System.out.print(j);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    if (play[i][j] == 1) {
                        System.out.print("-");
                    } else {
                        System.out.print(" ");
                    }
                } else if (arr[i][j] == 1) {
                    player++;
                    System.out.print("@");
                } else if (arr[i][j] == 3) {
                    System.out.print("!");
                } else if (arr[i][j] == 4) {
                    System.out.print("x");
                } else if (arr[i][j] == 2) {
                    com++;
                    System.out.print(" ");
                }
            }
            System.out.println("| " + i);
        }
        System.out.print("   ");
        for (int j = 0; j < arr[0].length; j++) {
            System.out.print(j);
        }
        System.out.println("\n");
        System.out.printf("Your ships : %d | Computer ships : %d ", player, com);
        System.out.println("\n");
        if (player == 0) {
            System.out.println("Opss! You lost the battle");
        } else if (com == 0) {
            System.out.println("Hooray! You win the battle");
        }
        count[0] = player;
        count[1] = com;
    }


    private static void getShipLocations(int[][] shipArr) {
        // int[][] shipArr = new  int[10][10];
        int x, y = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Deploy your ships:");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("Enter X coordinate for your ship %d : ", i);
            x = input.nextInt();
            System.out.printf("Enter Y coordinate for your ship %d: ", i);
            y = input.nextInt();
            if (x < shipArr.length && y < shipArr[0].length) {
                if (shipArr[x][y] == 0) {
                    shipArr[x][y] = 1;
                } else {
                    i = i - 1;
                }
            } else {
                i = i - 1;
            }
        }
        System.out.println("\n\n\n");
    }

    private static void generateComputeShips(int[][] shipArr) {
        Random rand = new Random();
        int x, y = 0;
        System.out.println("Computer is deploying ships ");
        for (int i = 1; i <= 5; i++) {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            if (x < shipArr.length && y < shipArr[0].length) {
                if (shipArr[x][y] == 0) {
                    shipArr[x][y] = 2;
                    System.out.printf("Deployed %d ship \n", i);
                } else {
                    i = i - 1;
                }
            } else {
                i = i - 1;
            }
        }
        System.out.println("\n\n\n");

    }

    private static void playersTurn(int[][] player, int[][] shipArr) {
        int x, y = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("It's your turn");
        boolean con = false;
        do {
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();
            if (x < shipArr.length && y < shipArr[0].length && player[x][y] != 1) {
                con = true;
                if (shipArr[x][y] == 0) {
                    System.out.println("You just missed it!");
                    player[x][y] = 1;
                } else if (shipArr[x][y] == 1) {
                    System.out.println("You just sunk one of your own ships!");
                    shipArr[x][y] = 4;
                } else if (shipArr[x][y] == 2) {
                    System.out.println("Boom! You sunk the ship!");
                    shipArr[x][y] = 3;
                }
            } else {
                con = false;
            }
        } while (!con);
    }

    private static void computerTurn(int[][] comp, int[][] shipArr) {
        int x, y = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("It's computer turn");
        boolean con = false;
        Random rand = new Random();
        do {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            if (x < shipArr.length && y < shipArr[0].length && comp[x][y] != 1) {
                con = true;
                if (shipArr[x][y] == 0) {
                    System.out.println("Computer missed!");
                    comp[x][y] = 1;
                } else if (shipArr[x][y] == 1) {
                    System.out.println("The Computer sunk one of your ships!");
                    shipArr[x][y] = 4;
                } else if (shipArr[x][y] == 2) {
                    System.out.println("The Computer sunk one of its own ships!");
                    shipArr[x][y] = 3;
                }
                System.out.println("\n");
            } else {
                con = false;
            }
        } while (!con);
    }
}
