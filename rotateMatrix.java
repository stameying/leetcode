public class Solution {
    public void rotate(int[][] matrix) {
        /*
        *  1  2  3               7  8  9       \             7  4  1
        *  4  5  6   => ----- => 4  5  6   =>    \      =>   8  5  2
        *  7  8  9               1  2  3           \         9  6  3
        *
        */
        for (int i = 0; i < matrix.length/2; i++){
            for (int j = 0; j < matrix[0].length; j++){
                int temp = matrix[matrix.length-1-i][j];
                matrix[matrix.length-1-i][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        
        for (int i = 0; i < matrix.length; i++){
            for (int j = i; j < matrix[0].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}