public class InputInValidator {
    public int number;
    public int[][] fifteenPuzzle;
    public int idx_1, idx_2;
    public int[] indexNumber = new int[2];
    
    InputInValidator(int number, int[][]fifteenPuzzle) {
        this.number = number;
        this.fifteenPuzzle = fifteenPuzzle;
        this.idx_1 = idx_1;
        this.idx_2 = idx_2;
        this.indexNumber = indexNumber;
    }
    int[] scanIndex() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (number == fifteenPuzzle[i][j]) {
                    indexNumber[0] = i;
                    indexNumber[1] = j;
                }
            }
        }

        return indexNumber;
    }
}
