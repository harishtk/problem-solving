package leetcode;

public class TriePrefixTree {
    public static void main(String[] args) {
        
    }

    static class Trie {
        TrieNode root;
    
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            } 
            curr.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.children[ch - 'a'] == null) return false;
                curr = curr.children[ch - 'a'];
            }
            return curr != null && curr.isEndOfWord;
        }
        
        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (curr.children[ch - 'a'] == null) return false;
                curr = curr.children[ch - 'a'];
            }
            return true;
        }
    
        static class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;
            TrieNode() {
                children = new TrieNode[26];
                isEndOfWord = false;
            }
        }

        static class TrieNode2 {
            TrieNode2[] node;
            boolean isEnd;
            public TrieNode2(){
                node = new TrieNode2[26];
            }
        
            public void insert(String str, int index){
                if (index>=str.length()){return;}
                int i = str.charAt(index)-'a';
                if (node[i]==null){
                    node[i] = new TrieNode2();
                }
                if (index==str.length()-1){
                    node[i].isEnd = true;
                }
                node[i].insert(str, index+1);
            }
        
            public boolean search(String str, int index){
                if (index>=str.length()){return false;}
                int i = str.charAt(index)-'a';
                if (node[i]==null){
                    return false;
                }
                if (index==str.length()-1 && node[i].isEnd){
                    return true;
                }else{
                    return node[i].search(str, index+1);
                }
            }
        
            public boolean startsWith(String prefix, int index){
                if (index>=prefix.length()){return true;}
                int i = prefix.charAt(index)-'a';
                if (node[i]==null){
                    return false;
                }
                return node[i].startsWith(prefix, index+1);
            }
        }
    }
}
