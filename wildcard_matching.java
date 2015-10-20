
public class wildcard_matching {

	public static boolean isMatch(String s, String p) {
		int i = 0, j = 0, star = -1, mark = -1;
		while (i < s.length()){
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))){
				/*
				 * Matching one by one
				 * */
				i++;
				j++;
			}else if(j < p.length() && p.charAt(j) == '*'){
				/*
				 * Recording star's position, assume it does not need to match 
				 * any character in s first and keep matching the after part of
				 * p and s 
				 * */
				star = j;
				j++;
				mark = i;
			}else if (star != -1){
				/*
				 * 1. p reaches end or 2. char(s) != char(p),  
				 * need to go back to the start position, and increase the match
				 * of * -> char by 1 each time to see if now it matches the rest
				 * part
				 * */
				j = star+1;
				mark += 1;
				i = mark;
			}else{
				/*
				 * if star can't help matching, then it is not matching
				 * */
				return false;
			}
		}
		/*
		 * now s is all matched and if there is still * in the end of p, can be eliminated
		 * */
		while (j < p.length() && p.charAt(j) == '*') j++;
		
		/*
		 * if there is still pattern remained in p, means they are not matching
		 * */
		if (j == p.length()) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "hello there";
		String p = "h*e";
		String res = isMatch(s,p)? "is match":"is not match";
		System.out.println(s +" and " + p + " " + res);
		
		String s1 = "hello there";
		String p1 = "*?ee";
		String res1 = isMatch(s1,p1)? "is match":"is not match";
		System.out.println(s1 +" and " + p1 + " " + res1);
		
		String s2 = "hello there";
		String p2 = "*?e";
		String res2 = isMatch(s2,p2)? "is match":"is not match";
		System.out.println(s2 +" and " + p2 + " " + res2);
	}

}
