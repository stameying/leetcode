public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] last = new int[matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            int[] h = generateLine(matrix,i,last);
            max = Math.max(max,getMaxArea(h));
            last = h;
        }
        return max;
    }
    
    public int[] generateLine(char[][] matrix, int row, int[] lastline){
        int[] h = new int[matrix[0].length];
        if (row == 0){
            for (int j = 0; j < matrix[0].length; j++){
                h[j] = matrix[row][j]=='1'?1:0;
            }
        }else{
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[row][j] == '0'){
                    h[j] = 0;
                }else{
                    h[j] = lastline[j]+1;
                }
            }
        }
        return h;
    }
    
    public int getMaxArea(int[] height){
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length+1];
        h = Arrays.copyOf(height, height.length + 1);
        while ( i < h.length){
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) stack.push(i++);
            else{
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t]*(stack.isEmpty()? i:i-stack.peek()-1));
            }
        }
        return maxArea;
    }
}