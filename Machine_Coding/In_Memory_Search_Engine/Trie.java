package In_Memory_Search_Engine;
import java.util.*;

class TrieNode{
    Map<Character,TrieNode> node;
    boolean endOfString;
    TrieNode(){
        node = new HashMap<>();
        endOfString = false;
    }
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
        System.out.println("Trie Created !");
    }
    public void insert(String str){
        TrieNode current  = root;
        for(int i = 0; i < str.length() ; i++){
            char ch = str.charAt(i);
            TrieNode new_node = current.node.get(ch);
            System.out.println("Inserting"+" "+ch +" "+new_node);
            if(new_node == null){
                new_node = new TrieNode();
                current.node.put(ch,new_node);
                current.node.put('.',new_node);
            }
            current = new_node;
        }
        current.endOfString = true;
        System.out.println("String "+ str +" Inserted in Trie");
    }
    public boolean search(String str){
        TrieNode start = root;
        TrieNode current = start;
        int i = 0;
        while(i < str.length()){
            char  ch = str.charAt(i);
            if(ch == '*'){
                if(i + 1 < str.length()){
                    char nextchar = str.charAt(i+1);
                    
                    TrieNode temp = start.node.get('.');
                    boolean present = temp.node.containsKey(nextchar);
                    while(temp != null || present){
                        temp = start.node.get('.');
                        System.out.println("Stuck");
                        if(temp.node.containsKey(nextchar)){
                            start = temp;
                            ch = nextchar;
                            i += 1;
                            break;
                        }
                        
                        start = temp;
                    }
                }
                else{
                    return true;
                }        
            }
            // System.out.println(start.node +" " + ch);
            
            current = start.node.get(ch);
            if(current == null) return false;
            start = current;
            i++;
        }
        return start.endOfString;
    }
    public static void main(String... args) throws Exception{
        Trie trie = new Trie();
        trie.insert("Tostrouk");
        trie.insert("Tokoahma");
        trie.insert("API");
        System.out.println(trie.search("T.s*"));
        System.out.println(trie.search("APIS"));
        System.out.println(trie.search("Tokoahma"));
        System.out.println(trie.search(".PIS"));
    }    
} 