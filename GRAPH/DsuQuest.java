import java.util.*;
public class DsuQuest {

    static int[] par, size;
    public static int findPar(int u) {
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        par = new int[n * m];
        size = new int[n * m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }

        int maxArea = 0;
        int[][] dir = {{1, 0}, {0, 1}};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    int p1 = findPar(i * m + j);
                    for(int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        
                        if(r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar( r * m + c);
                            if(p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, size[p1]);
                }
            }
        }
        return maxArea;
    }

    //Laxicographically smallest equivalen String
    //Input: A = "parker", B = "morris", S = "parser"
    //Output: "makkek"

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {

        par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (int i = 0; i < s1.length(); i++) {
            int p1 = findPar(s1.charAt(i) - 'a');
            int p2 = findPar(s2.charAt(i) - 'a');

            par[p1] = Math.min(p1, p2);
            par[p2] = Math.min(p1, p2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = (char) (findPar(baseStr.charAt(i) - 'a') + 'a');
            sb.append(ch);
        }

        return sb.toString();
    }

    // Satisfiablity of equality equation 
    //Input: equations = ["a==b","b==c","a==c"]
   //Output: true

    public static boolean equationsPossible(String[] equations) {
        par = new int[26];
        for(int i = 0; i < par.length; i++) {
            par[i] = i;
        }

        for(String s : equations) {
            char ch1 = s.charAt(0);
            char ch2 = s.charAt(1);
            char ch3 = s.charAt(3);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch3 - 'a');
            if(ch2 == '=' && p1 != p2) {
                par[p2] = p1;
            }
        }
        
        for(String s : equations) {
            char ch1 = s.charAt(0);
            char ch2 = s.charAt(1);
            char ch3 = s.charAt(3);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch3 - 'a');
            if(ch2 == '!' && p1 == p2) {
                return false;
            }
        } 

        return true;
    }

    //leetcode 839 : Similar String Groups

    public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        par = new int[n];
        int group = n;

        for(int i = 0; i < n; i++) {
            par[i] = i;
        }

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    if(p1 != p2) {
                        par[p2] = p1;
                        group--;
                    }
                }
            }
        }

        return n - group;
    }

    public static boolean isSimilar(String str1, String str2) {
        int n = str1.length();
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(str1.charAt(i) != str2.charAt(i) && ++count > 2) {
                return false;
            }
        }
        return false;
    }

    
    public static void main(String[] args) {

    }
}
