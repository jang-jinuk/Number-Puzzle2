//1. 타이틀 출력 및 현재턴 표시
//2. 공백칸을 보함한 1 - 15까지의 숫자를 섞어서 배치(공백을 포함한 숫자 배열을 만들고 이중 배열에 랜덤으로 배치)
//3. 입력 프롬프트를 구현(입력 유효범위를 1 - 15로 설정, 입력 오류 시 다시 입력받음)
//4. 정상적으로 입력 시 공백칸 위로 밀어내기(배열 인덱스 값을 확인해 공백칸 배열 인덱스 옆에 위치하는 값이 확인하고 교환)
//5. 숫자가 올바르게 정렬되면 축하메세지와 함께 게임종료
//6. 먼저 0-15까지 숫자를 1차원 배열에 랜덤으로 받고(숫자는 겹치지 않게) 그리고 2차원 배열에 순서대로 저장한다.
//7. 숫자 0은 공백으로 교체하여 출력


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("재미있는 15퍼즐!\n");
        startGame();
    }
    static int[][] selectNumber(int[] puzzleNumber, int[][] fifteenPuzzle) {
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

    static void printNumber(int[][] fifteenPuzzle) {

        for (int i = 0; i < fifteenPuzzle.length; i++) {
            for (int j = 0; j < fifteenPuzzle[i].length; j++) {
                System.out.printf("[%2d]", fifteenPuzzle[i][j]);
            }
            System.out.println();
        }

    }

    static int[][] scanNumber(int[][] fifteenPuzzle) {
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

    static int inspectNumber(int changeNum, int[][] fifteenPuzzle) {

        int idx_1, idx_2, idx_3, idx_4;
        int[] indexNumber = new int[4];
        indexNumber = indexBox(changeNum, fifteenPuzzle);

        idx_1 = indexNumber[0];
        idx_2 = indexNumber[1];
        idx_3 = indexNumber[2];
        idx_4 = indexNumber[3];

        int changeButton;
        changeButton = locationNumber(idx_1,idx_2,idx_3,idx_4);
        return changeButton;
    }
    static int[][] changePuzzle(int[][] fifteenPuzzle,int changeNum) {
        int idx_1, idx_2, idx_3, idx_4;
        int[] indexNumber = new int[4];
        indexNumber = indexBox(changeNum, fifteenPuzzle);

        idx_1 = indexNumber[0];
        idx_2 = indexNumber[1];
        idx_3 = indexNumber[2];
        idx_4 = indexNumber[3];

        int tmp;
        tmp = fifteenPuzzle[idx_1][idx_2];
        fifteenPuzzle[idx_1][idx_2] = fifteenPuzzle[idx_3][idx_4];
        fifteenPuzzle[idx_3][idx_4] = tmp;

        return fifteenPuzzle;
    }
    static int[] indexBox(int changeNum, int[][] fifteenPuzzle) {
        int empty = 0;
        int[] emptyIdx = new int[2];
        int[] changeIdx = new int[2];
        int[] idxBox = new int[4];

        Inspect inspectNum1 = new Inspect(empty,fifteenPuzzle);
        Inspect inspectNum2 = new Inspect(changeNum,fifteenPuzzle);

        emptyIdx = inspectNum1.scanIdex();
        changeIdx = inspectNum2.scanIdex();

        idxBox[0] = emptyIdx[0];
        idxBox[1] = emptyIdx[1];
        idxBox[2] = changeIdx[0];
        idxBox[3] = changeIdx[1];

        return idxBox;
    }
    static int locationNumber(int idx_1,int idx_2,int idx_3,int idx_4) {
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
    static int [][] rightNumber(int[][] rightAnswer) {
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rightAnswer[i][j] = cnt;
                cnt++;
            }
        }
        rightAnswer[4][4] = 0;
        return rightAnswer;
    }
    static void startGame() {
        int [][] rightAnswer = new int[4][4];
        rightAnswer = rightNumber(rightAnswer);
        
        int[] puzzleNumber = new int[16];
        int[][] fifteenPuzzle = new int[4][4];
        fifteenPuzzle= selectNumber(puzzleNumber,fifteenPuzzle);
        int turn = 1;
        while(true) {
            System.out.printf("turn %d\n", turn);
            turn++;

            printNumber(fifteenPuzzle);
            fifteenPuzzle = scanNumber(fifteenPuzzle);
            System.out.println();

            if (fifteenPuzzle.equals(rightAnswer)) {
                System.out.printf("축하합니다! %d턴만에 퍼즐을 완성했습니다!", turn);
                break;
            }
        }
    }
}