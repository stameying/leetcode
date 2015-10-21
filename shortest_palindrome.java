public class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder builder = new StringBuilder(s);
        builder.reverse();
        String adjustStr = s + "#" + builder.toString();
        int[] table = buildPMT(adjustStr);
        int palinLen = table[table.length-1];
        builder = new StringBuilder(s.substring(palinLen,s.length()));
        builder.reverse();
        return builder.append(s).toString();
    }
    
    public int[] buildPMT(String s){
        char[] charArray = s.toCharArray();
        int[] table = new int[s.length()];
        int j = 0, i = 1;
        while (i < s.length()){
           if (charArray[i] == charArray[j]){
                table[i] = j+1;
                i++;
                j++;
            }else{
                if (j == 0){
                    table[i] = 0;
                    i++;
                }else{
                    j = table[j-1];
                }
            } 
        }
        return table;
    }
}