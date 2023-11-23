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

        int turn = 1;
        System.out.printf("turn %d\n",turn);

        int[] puzzleNumber = new int[16];
        for(int i = 0; i < 16 ; i++) {
            puzzleNumber[i] = (int) (Math.random() * 16);
            for (int j = 0; j < i; j++) {
                if (puzzleNumber[i] == puzzleNumber[j]) {
                    --i;
                }
            }
        }
        int[][] fifteenPuzzle = new int[4][4];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fifteenPuzzle[i][j] = puzzleNumber[count];
                count++;
            }
        }
        printNumber(fifteenPuzzle);
        printNumber(scanNumber(fifteenPuzzle));
    }
    static void printNumber(int [][] fifteenPuzzle) {

      for (int i = 0; i < fifteenPuzzle.length; i++) {
          for (int j = 0; j < fifteenPuzzle[i].length; j++) {
              System.out.printf("[%d]",fifteenPuzzle[i][j]);
          }
          System.out.println();
      }
    }

    static int[][] scanNumber(int [][]fifteenPuzzle){
        int changeNum = 0;

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("숫자 입력 > "); //입력 받은 숫자 유효성 검사
            changeNum = scanner.nextInt();
            if(changeNum < 1 || changeNum > 15) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.\n");
            }
        }while(changeNum < 1 || changeNum > 15);

        int empty = 0;

        int idx_1 = 0, idx_2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                if (empty == fifteenPuzzle[i][j]){
                    idx_1 = i;
                    idx_2 = j;
                }
            }
        }
        int idx_3 =0, idx_4 =0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (changeNum == fifteenPuzzle[i][j]) {
                    idx_3 = i;
                    idx_4 = j;
                }
            }
        }

        int tmp;
        tmp = fifteenPuzzle[idx_1][idx_2];
        fifteenPuzzle[idx_1][idx_2] = fifteenPuzzle[idx_3][idx_4];
        fifteenPuzzle[idx_3][idx_4] = tmp;

        return fifteenPuzzle;
    }

}