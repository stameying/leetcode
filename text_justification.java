public class Solution {
    
    public String adjustLine(String line, int count, int wordCount, int maxWidth){
        if (wordCount == 1){ // only one word in a line, left align
            while (count < maxWidth){
                line+=" ";
                count++;
            }
            return line;
        }else{ // even spaces
            int remainSpace = maxWidth-count;
            int spot = wordCount-1;
            if (remainSpace % spot == 0){ // can even spaces
                int space_num = remainSpace/spot;
                String[] array = line.split(" ");
                String newLine = "";
                for (int i = 0; i < array.length; i++){
                    newLine+=array[i];
                    if (i != array.length-1){
                        for (int j = 0; j <= space_num; j++){
                            newLine+=" ";
                        }
                    }
                }
                return newLine;
            }else{ // can't even spaces, asign one more space on the left
                int space_num = remainSpace/spot;
                int space_remain = remainSpace%spot;
                String[] array = line.split(" ");
                String newLine = "";
                for (int i = 0; i < array.length; i++){
                    newLine+=array[i];
                    if (i != array.length-1){
                        for (int j = 0; j <= space_num; j++){
                            newLine+=" ";
                        }
                        if (space_remain > 0){
                            newLine+=" ";
                            space_remain--;
                        }
                    }
                }
                return newLine;
            }
        }
    }
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        String line = "";
        if (words.length == 1 && words[0].length() == 0 ){
            for (int i = 0; i < maxWidth; i++){
                line+=" ";
            }
            res.add(line);
            return res;
        }
        if (maxWidth == 0){
            res.add(line);
            return res;
        }
        int count = 0;
        int wordCount = 0;
        int linewordCount = 0;
        while (wordCount < words.length){
            String cur = words[wordCount];
            if (linewordCount!= 0 && 1+count+cur.length() > maxWidth){
                String adjustLine = adjustLine(line,count,linewordCount,maxWidth);
                res.add(adjustLine);
                count=0;
                linewordCount=0;
                line="";
            }else{ // linewordCount == 0 || enough space to get a space and next word
                if (linewordCount != 0){
                    count++;
                    line+=" ";
                }
                count+=cur.length();
                line+=cur;
                wordCount++;
                linewordCount++;
            }
        }
        if (line.length() > 0){
            String adjustLine = adjustLine(line,count,1,maxWidth);
            res.add(adjustLine);
        }
        return res;
    }
}