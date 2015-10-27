/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/
public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) return s.length();
        int window_left_index = 0, maxLen = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int cur_pos = 0; cur_pos < s.length(); cur_pos++){
            char cur_char = s.charAt(cur_pos);
            if (!map.containsKey(cur_char)){
                map.put(cur_char,cur_pos);
            }else{
                int last_dup_position = map.get(cur_char);
                for (int delete_pos = window_left_index; delete_pos <= last_dup_position; delete_pos++){
                    map.remove(s.charAt(delete_pos));
                }
                maxLen = Math.max(maxLen, cur_pos-window_left_index);
                map.put(cur_char,cur_pos);
                window_left_index = last_dup_position+1;
            }
        }
        maxLen = Math.max(maxLen, s.length()-window_left_index);
        return maxLen;
}