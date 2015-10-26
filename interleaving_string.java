public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2) return false;
        boolean[][] map = new boolean[len1+1][len2+2];
        map[0][0] = true;
        for (int i = 1; i <= s1.length(); i++){
            if (s1.charAt(i-1) == s3.charAt(i-1)) map[i][0] = true;
            else break;
        }
        for (int j = 1; j <= s2.length(); j++){
            if (s2.charAt(j-1) == s3.charAt(j-1)) map[0][j] = true;
            else break;
        }
        for (int i = 1; i <= s1.length(); i++){
            char ch1 = s1.charAt(i-1);
            for (int j = 1; j <= s2.length(); j++){
                int k = i + j;
                char ch2 = s2.charAt(j-1);
                char ch3 = s3.charAt(k-1);
                if (ch1 == ch3){
                    map[i][j] |= map[i-1][j];
                }
                if (ch2 == ch3){
                    map[i][j] |= map[i][j-1];
                }
            }
        }
        return map[len1][len2];
    }
    
    
}