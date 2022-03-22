import java.util.*;
public class Trie {

    public class Node {
        Node[] children;
        boolean isEnd;

        Node() {
            this.children = new Node[26];
        }
    }
    public Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node curr = root;
        int n = word.length();
        for(int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            Node chAddress = curr.children[ch - 'a'];
            if(chAddress == null) {
                chAddress = new Node();
            }
            curr = chAddress;
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        Node curr = root;
        int n = word.length();
        
        for(int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            Node chAddress = curr.children[ch - 'a'];
            if(chAddress == null) return false;
            curr = chAddress;
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;
        int n = prefix.length();
        
        for(int i = 0; i < n; i++) {
            char ch = prefix.charAt(i);
            Node chAddress = curr.children[ch - 'a'];
            if(chAddress == null) return false;
            curr = chAddress;
        }
        return true;
    }
    

    //https://leetcode.com/problems/word-search-ii/

    // in this program we have to crete node that will contain node[] and string
    // insert method will take care of how many childe every root hava
    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for(String str : words) {
            insert(str);
        }
        
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dfs(board, i, j, root, ans, vis);
            }
        }
        return ans;
    }

    public void dfs(char[][] board, int i, int j, Node root, List<String> ans, boolean[][] vis) {
        int n = board.length;
        int m = board[0].length;
        if(i < 0 || j < 0 || i > n || j > m || vis[i][j] == true || root.count == 0) return;
        char ch = board[i][j]; 
        Node child = root.children[ch - 'a'];
        if(child == null) return;
        if(child.str != null) {
            ans.add(child.str);
            child.str = null;
        }
        
        vis[i][j] = true;
        dfs(board, i + 1, j, child, ans, vis);
        dfs(board, i - 1, j, child, ans, vis);
        dfs(board, i, j + 1, child, ans, vis);
        dfs(board, i, j - 1, child, ans, vis);
        vis[i][j] = false;

        if(child.count == 0) {
            root.count--;
        }

    }

    //https://leetcode.com/problems/replace-words/
    // Input: dictionary = ["cat","bat","rat"], 
    // sentence = "the cattle was rattled by the battery"
    // Output: "the cat was rat by the bat"

    public String replaceWords(List<String> dictionary, String sentence) {
        int dictSize = dictionary.size();
        Node root = new Node();

        for(int i = 0; i < dictSize; i++) {
            String str = dictionary.get(i);
            insert(root, str);
        }

        StringTokenizer st = new StringTokenizer(sentence);
        StringBuilder sb = new StringBuilder(); //
        while(st.hasMoreTokens()) {
            String word = st.nextToken();
            String isSuffix = search(root, word);
            if(isSuffix != null) {
                sb.append(isSuffix);
            } else {
                sb.append(word);
            }
            sb.append(" ");
        }
        sb.delete(sb.length() - 1);
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println("manish");
    }
}
