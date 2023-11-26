import java.util.Scanner;

public class Game {
    private boolean isLoop = true;
    int startGame() {
        int [][] rightAnswer = new int[4][4];
        rightAnswer = rightNumber(rightAnswer);

        int[] puzzleNumber = new int[16];
        int[][] fifteenPuzzle = new int[4][4];
        fifteenPuzzle= selectNumber(puzzleNumber,fifteenPuzzle);
        int turn = 1;

        while(isLoop) {
            System.out.printf("turn %d\n", turn);
            turn++;

            printNumber(fifteenPuzzle);
            fifteenPuzzle = scanNumber(fifteenPuzzle);
            System.out.println();

            if (fifteenPuzzle.equals(rightAnswer)) {
                break;
            }
        }
        return turn;
    }
    int [][] rightNumber(int[][] rightAnswer) {
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rightAnswer[i][j] = cnt;
                cnt++;
            }
        }
        rightAnswer[3][3] = 0;
        return rightAnswer;
    }
    int[][] selectNumber(int[] puzzleNumber, int[][] fifteenPuzzle) {
        for (int i = 0; i < 16; i++) {
            puzzleNumber[i] = (int) (Math.random() * 16);
            for (int j = 0; j < i; j++) {
                if (puzzleNumber[i] == puzzleNumber[j]) {
                    --i;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fifteenPuzzle[i][j] = puzzleNumber[count];
                count++;
            }
        }
        return fifteenPuzzle;
    }

    void printNumber(int[][] fifteenPuzzle) {

        for (int i = 0; i < fifteenPuzzle.length; i++) {
            for (int j = 0; j < fifteenPuzzle[i].length; j++) {
                if (fifteenPuzzle[i][j] == 0) {
                    System.out.print("[  ]");
                }
                else {
                    System.out.printf("[%2d]", fifteenPuzzle[i][j]);
                }
            }
            System.out.println();
        }

    }
    int[][] scanNumber(int[][] fifteenPuzzle) {
        int changeNum = 0;
        int changeButton = 0;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("숫자 입력 > ");
            changeNum = scanner.nextInt();
            changeButton = inspectNumber(changeNum,fifteenPuzzle);
            if (changeNum < 1 || changeNum > 15 || changeButton == 0) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.\n");
            }
        } while (changeNum < 1 || changeNum > 15 || changeButton == 0);

        fifteenPuzzle = changePuzzle(fifteenPuzzle,changeNum);

        return fifteenPuzzle;
    }

    int inspectNumber(int changeNum, int[][] fifteenPuzzle) {

        int index_1, index_2, index_3, index_4;
        int[] indexNumber = new int[4];
        indexNumber = indexBox(changeNum, fifteenPuzzle);

        index_1 = indexNumber[0];
        index_2 = indexNumber[1];
        index_3 = indexNumber[2];
        index_4 = indexNumber[3];

        int changeButton;
        changeButton = locationNumber(index_1,index_2,index_3,index_4);
        return changeButton;
    }
    int[][] changePuzzle(int[][] fifteenPuzzle,int changeNum) {
        int index_1, index_2, index_3, index_4;
        int[] indexNumber = new int[4];
        indexNumber = indexBox(changeNum, fifteenPuzzle);

        index_1 = indexNumber[0];
        index_2 = indexNumber[1];
        index_3 = indexNumber[2];
        index_4 = indexNumber[3];

        int tmp;
        tmp = fifteenPuzzle[index_1][index_2];
        fifteenPuzzle[index_1][index_2] = fifteenPuzzle[index_3][index_4];
        fifteenPuzzle[index_3][index_4] = tmp;

        return fifteenPuzzle;
    }
    int[] indexBox(int changeNum, int[][] fifteenPuzzle) {
        int empty = 0;
        int[] emptyIndex = new int[2];
        int[] changeIndex = new int[2];
        int[] indexBox = new int[4];

        InputIndexValidator inspectNum1 = new InputIndexValidator(empty,fifteenPuzzle);
        InputIndexValidator inspectNum2 = new InputIndexValidator(changeNum,fifteenPuzzle);

        emptyIndex = inspectNum1.scanIndex();
        changeIndex = inspectNum2.scanIndex();

        indexBox[0] = emptyIndex[0];
        indexBox[1] = emptyIndex[1];
        indexBox[2] = changeIndex[0];
        indexBox[3] = changeIndex[1];

        return indexBox;
    }
    int locationNumber(int idx_1,int idx_2,int idx_3,int idx_4) {
        int changeButton = 0;
        if (idx_1 == idx_3 && (idx_2 - 1) == idx_4) {
            changeButton = 1;
        } else if (idx_1 == idx_3 && (idx_2 + 1) == idx_4) {
            changeButton = 1;
        } else if (idx_2 == idx_4 && (idx_1 - 1) == idx_3) {
            changeButton = 1;
        } else if (idx_2 == idx_4 && (idx_1 + 1) == idx_3) {
            changeButton = 1;
        }
        return changeButton;
    }
}
