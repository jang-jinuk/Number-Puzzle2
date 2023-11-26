public class InputIndexValidator {

    public int number;
    public int[][] fifteenPuzzle;
    public int index_1, index_2;
    public int[] indexNumber = new int[2];

    InputIndexValidator(int number, int[][]fifteenPuzzle) {
        this.number = number;
        this.fifteenPuzzle = fifteenPuzzle;
        this.index_1 = index_1;
        this.index_2 = index_2;
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
