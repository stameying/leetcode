public int minDistance(String word1, String word2) {
        int aPos = 0, bPos = 0; 
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        
        for (int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // first choice, insert a character in words1
                    int insert = dp[i-1][j] + 1;
                    // second choice, delete a character from words2
                    int delete = dp[i][j-1] + 1;
                    // third choice, replace a character
                    int replace = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(insert,delete);
                    dp[i][j] = Math.min(dp[i][j],replace);
                }
            }
        }
        return dp[len1][len2];
    }