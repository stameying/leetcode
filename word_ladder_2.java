/*
* Word Ladder 2
*/
public class Solution {
    class wordNode{
        String word;
        wordNode parent;
        int steps;
        public wordNode(String word, wordNode parent, int steps){
            this.word = word;
            this.parent = parent;
            this.steps = steps;
        }
    }
    
    public void createMap(Map<String,Set<String>> map, String word, Set<String> wordList){
        char[] array =  word.toCharArray();
        for (int i = 0; i < word.length(); i++){
            for (char ch = 'a'; ch <= 'z'; ch++){
                char old_char = array[i];
                if (ch != old_char){
                    array[i] = ch;
                    String next = new String(array);
                    if (wordList.contains(next)){
                        if (map.containsKey(word)){
                            Set<String> set = map.get(word);
                            set.add(next);
                        }else{
                            Set<String> set = new HashSet<String>();
                            set.add(next);
                            map.put(word,set);
                        }
                    }
                }
                array[i] = old_char;
            }
        }    
    }
    
    public void findPath(wordNode node, List<List<String>> res){
        List<String> sol = new ArrayList<String>();
        while (node != null){
            sol.add(0,node.word);
            node = node.parent;
        }
        res.add(sol);
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String,Set<String>> map = new HashMap<String,Set<String>>();
        wordList.add(beginWord);
        wordList.add(endWord);
        for (String word: wordList){
            createMap(map,word,wordList);
        }
        int resLevel = 0;
        HashMap<String,Integer> visited_nodes = new HashMap<String,Integer>();
        Queue<wordNode> queue = new LinkedList<wordNode>();
        queue.offer(new wordNode(beginWord,null,1));
        
        //ready
        while (!queue.isEmpty()){
            wordNode cur = queue.poll();
            String cur_word = cur.word;
            int cur_step = cur.steps;
            
            /*
            * find a path
            */
            if (cur_word.equals(endWord)){
                /*
                * resLevel is 0 if we find the first path
                * cur_step == resLevel if we find a new path with same depth
                */
                if (resLevel == 0 || cur_step == resLevel){ 
                    resLevel = cur_step;
                    findPath(cur,res);
                }else{
                    /*
                    * Not valid path since it is longer 
                    */
                    break;
                }
            }else{
                Set<String> neighbors = map.get(cur_word);
                if (neighbors == null || neighbors.isEmpty()) continue;
                List<String> removeList = new ArrayList<String>();
                for (String neighbor: neighbors){
                    /*
                    * if neighbor already visited, means there is a shorter way to meet neighbor,
                    * so no need to visit neighbor from current node
                    */
                    if (visited_nodes.containsKey(neighbor)){
                        int preVisited_level = visited_nodes.get(neighbor);
                        if (preVisited_level < cur_step + 1){
                            map.get(neighbor).remove(cur_word);
                            removeList.add(neighbor);
                            continue;
                        }
                    }
                    /*
                    * if neighbor not visited nor visited in a less level, 
                    * meet neighbor, put it into the queue,
                    * remove current node from neighbor's neighbor list
                    */
                    visited_nodes.put(neighbor,cur_step+1);
                    queue.offer(new wordNode(neighbor,cur,cur_step+1));
                    if (map.containsKey(neighbor)){
                        map.get(neighbor).remove(cur_word);
                    }
                    
                }
                /*
                * remove pre-visited neighbor from current node's neighbor lists
                */
                for (String neighbor: removeList){
                    neighbors.remove(neighbor);
                }
            }
        }
        return res;
    }
}