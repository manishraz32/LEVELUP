package STRING;

import java.util.*;
public class StringProb {

    /**********STRING BASIC QUESTION************/
    //1.
    // Algorithm of  sliding window for string
    //pattern matching  
    
    public static boolean isMatch(int l, int r, char[] text, char[] pattern) {
        int plen = pattern.length;
        for(int i = 0; i < plen; i++) {
            if(text[l+i] != pattern[i])
                return false;
        }
        return true;
    }
    public static void searchPattern() {
        char[] text = {'c','o','d','e','n','c','o','d','e'};
        char[] pattern = {'c','o','d'};
        int tlen = text.length;
        int plen = pattern.length;

        for(int l = 0, r = plen - 1; r < tlen; l++, r++) {
            if(isMatch(l, r, text, pattern)) {
                System.out.println(l);
            }
        }
    }


    //2.Anagram
    //https://leetcode.com/problems/valid-anagram/submissions/
    // Example 1:
    // Input: s = "anagram", t = "nagaram"
    // Output: true
    public static void fillFreq(int[] arr, String str) {
        int len = str.length();
        for(int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            arr[ch - 'a']++;
        }
    }
    public boolean isAnagram(String s, String t) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        fillFreq(arr1, s);
        fillFreq(arr2, t);
        
        for(int i = 0; i < 26; i++) {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    //3.find all anagrams in a string
    //https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // Input: s = "cbaebabacd", p = "abc"
    // Output: [0,6]
    // Explanation:
    // The substring with start index = 0 is "cba", which is an anagram of "abc".
    // The substring with start index = 6 is "bac", which is an anagram of "abc".
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        List<Integer> res = new ArrayList<>();
        for(int l = 0, r = plen - 1; r < slen; l++, r++) {
            String str1 = s.substring(l, r + 1);
            if(isAnagram(str1, p)) {
                res.add(l);
            }
        }
        return res;
    }


    //4. Sort the string in ascending order
    //https://practice.geeksforgeeks.org/problems/sort-the-string-in-descending-order3542/1
    String ReverseSort(String str) { 
        int n = str.length();
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        // manually reverse
        StringBuilder sb = new StringBuilder();
        sb.append(arr);
        return sb.toString();
    }

    //5.Alternatively merge two String
    //https://practice.geeksforgeeks.org/problems/merge-two-strings2736/1#
    // Input:
    // S1 = "Hello" S2 = "Bye"
    // Output: HBeylelo
    public String merge(String S1, String S2)
    { 
        int n = S1.length();
        int m = S2.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n || i < m; i++) {
            if(i < n) sb.append(S1.charAt(i));
            if(i < m) sb.append(S2.charAt(i));
        }
        return sb.toString();
    }

    //6.
    //https://practice.geeksforgeeks.org/problems/save-ironman0227/1
    // Input : S = "I am :IronnorI Ma, i"
    // Output : YES
    public static boolean saveIronman (String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while(i <= j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            //check it is character or anything else
            if(Character.isLetter(ch1) == false && Character.isDigit(ch1) == false) {
                i++;
                continue;
            }
            if(Character.isLetter(ch2) == false && Character.isDigit(ch2) == false) {
                j--;
                continue;
            }
            // int diff1 = (ch1 >= 'A' && ch1 <= 'Z') ? ch1 - 'A' : ch1 - 'a';
            // int diff2 = (ch2 >= 'A' && ch2 >= 'Z') ? ch2 - 'A' : ch2 - 'a';
            int diff = Math.abs(ch1 - ch2);
    
            if(diff != 0 && diff != 32) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //7.
    //https://practice.geeksforgeeks.org/problems/extract-maximum2943/1
    //S = 100klh564abc365bg
    //Output: 564
    static int extractMaximum(String str)
    {
        int num = 0, res = 0;
        boolean flag = false;
        // Start traversing the given string
        for (int i = 0; i<str.length(); i++)
        {
            // If a numeric value comes, start converting
            // it into an integer till there are consecutive
            // numeric digits
            if (Character.isDigit(str.charAt(i)))
                num = num * 10 + (str.charAt(i)-'0');
       
            // Update maximum value
            else
            {
                res = Math.max(res, num);
       
                // Reset the number
                num = 0;
            }
        }
        if(num == 0 && res == 0 && flag == false) return - 1;
        return Math.max(res, num);
    }  
    
    //8. 
    //https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string5459/1
    // S = pqr.mno
    // Output: mno.pqr
    public static String reverseWords(String str)
    {   
        int n = str.length();
        StringBuilder res = new StringBuilder();
        Stack<Character> stk = new Stack();
        for(int i = 0; i < n; i++) {
            stk.push(str.charAt(i));
        }
        while(stk.size() > 0) {
            Stack newStk = new Stack<>();
            while(stk.size() > 0 && stk.peek() != '.') {
                newStk.push(stk.pop());
            }
            while(newStk.size() > 0) {
                res.append(newStk.pop());
            }
            if(stk.size() > 0) 
                res.append(stk.pop());
        }
        return res.toString();
    }

    //9.
    //https://practice.geeksforgeeks.org/problems/implement-strstr/1
    public static boolean isMatch_(int l , int r, char[] t, char[] p) {
        int n = t.length;
        int m = p.length;
        for(int i = 0; i < m; i++) {
            if(t[l + i] != p[i])
                return false;
        }
        return true;
    }
    int strstr(string s, string p) {
        int slen = s.length();
        int plen = p.length();
        char[] sarr = s.toCharArray();
        char[] parr = p.toCharArray();

        for(int l = 0, r = plen - 1; l < slen - plen; l++, r++) {
            if(sarr[l] == parr[0]) {
                if(isMatch_(l, r, sarr, parr))
                    return l;
            }
        }
        return -1;
    }


    //10
    //https://practice.geeksforgeeks.org/problems/check-for-subsequence4930/1
    boolean isSubSequence(String A, String B) {
        int alen = A.length();
        int blen = B.length();
        if(alen > blen) return false;
        int i = 0; // point String A
        int j = 0; // point String B
        // when any string finish then terminate loop
        while(i < alen && j < blen) {
            if(A.charAt(i) == B.charAt(j)) {
                i++;
                j++;
            } else{
                j++;
            }
        }
        if(i == alen) return true;
        return false;
    }
    //11
    //https://practice.geeksforgeeks.org/problems/
    //check-if-strings-are-rotations-of-each-other-or-not-1587115620/1
    // s1 = abcd s2 = bcda // true;
    public static boolean areRotations(String s1, String s2 )
    {
        s1 = s1 + s1;
        char[] t = s1.toCharArray();
        char[] p = s2.toCharAttay();
        int tlen = t.length;
        int plen = p.length;
        for(int l = 0; l <= tlen - plen; i++) {
            if(t[l] == p[0]) {
                if(isMatch_(l, 0, t, p))
                    return true;
            }
        }
        return false;
    }

    //12.
    //https://practice.geeksforgeeks.org/
    //problems/check-if-two-strings-are-k-anagrams-or-not/1
    // Input:
    // str1 = "fodr", str2="gork"
    // k = 2
    // Output:
    // 1
    // Explanation: Can change fd to gk
    boolean areKAnagrams(String s1, String s2, int k) {
        int s1len = s1.length();
        int s2len = s2.length();
        if(s1len != s2len) return false;
        HashMap<Character, Integer> hm = new HashMap<>();
        // counting freqency of string
        for(int i = 0; i < s1len; i++) {
            char ch = s1.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < s1len; i++) {
            char ch = s2.charAt(i);
            //hm.get(ch) > 0
            //because if frequency become 0 then dont decrese by 1
            if(hm.containsKey(ch) && hm.get(ch) > 0)
            hm.put(ch, hm.get(ch) - 1);
        }
        
        int count = 0;
        for(Character ch : hm.keySet()) {
            count += hm.get(ch);
        }
        return count <= k;
    }
    
    //13
    //Non repeating character
    //https://practice.geeksforgeeks.org/problems/non-repeating-character-1587115620/1#
    static char nonrepeatingCharacter(String S)
    {
        int len = S.length();
        LinkedHashMap<Character, Integer> hm = new LinkedHashMap<>();
        for(int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        for(Character key : hm.keySet()) {
            if(hm.get(key) == 1) {
                return key;
            }
        }
        return '$';
    }

    //14
    //Longest Distinct characters in string
    //https://practice.geeksforgeeks.org/problems/
    //longest-distinct-characters-in-string5848/1
    // Input:
    // S = "geeksforgeeks"
    // Output: 7
    // Explanation: "eksforg" is the longest 
    // substring with all distinct characters.
    int longestSubstrDistinctChars (String str) {
        int i = 0;
        int j = 0;
        int len = str.length();
        Set<Character> set = new HashSet<>();
        int max = 0;
        while(j < len) {
            if(!set.contains(str.charAt(j))) {
                set.add(str.charAt(j));
                max = Math.max(set.size(), max);
                j++;
            } else {
                set.remove(str.charAt(i++));
            }
        }
        return max;
    }
    
    //15
    //https://practice.geeksforgeeks.org/problems/find-k-th-character-in-string3841/1
    public String findBinary(int m) {
        if(m == 0) return "" + 0;
        StringBuilder sb = new StringBuilder();
        while(m != 0) {
            int rem = m % 2;
            String str = "" + rem;
            sb.insert(0, str);
            m = m / 2;
        }
        return sb.toString();
    }
    char kthCharacter(int m, int n, int k) {
        String binary = findBinary(m);
        StringBuilder sb = new StringBuilder();
        String one = "1", zero = "0";
        for(int i = 1; i <= n; i++) {
            int blen = binary.length();
            for(int j = 0; j < blen; j++) {
                char ch = binary.charAt(j);
                sb.append(ch);
                if(ch == '0') {
                    sb.append(one);
                } else {
                    sb.append(zero);
                }
            }
            binary = sb.toString();
            sb = new StringBuilder();
        }
        return binary.charAt(k-1);
    }

    //16
    //https://practice.geeksforgeeks.org/problems/nearest-multiple-of-102437/1
    // Input : N = 29 
    // Output : 30
    String roundToNearest(String num) {
        int len = num.length();
        int ln = num.charAt(len - 1) - '0';
        int sln = num.charAt(len - 2) - '0';
        char[] numArr = num.toCharArray();
        if(ln <= 5) {
            ln = 0;
        } else {
            ln = 0;
            sln = sln + 1;
        }
        numArr[len - 1] = ln;
        numArr[len - 2] =  sln;
        StringBuilder sb = new StringBuilder();
        for(char ch : numArr) {
            sb.append(ch);
        }
        return sb.toCharArray();
    }

    //******Must Do interview Question *******/
    //2. Longest Common Prefix in an Array
    //https://practice.geeksforgeeks.org/problems/
    //longest-common-prefix-in-an-array5129/1/?track=md-string&batchId=144

    // Input:
    // N = 4
    // arr[] = {geeksforgeeks, geeks, geek,geezer}
    // Output: gee
    // Explanation: "gee" is the longest common
    // prefix in all the given strings.
    String findCommonString(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int n = str1.length();
        int m = str2.length();
        for(int i = 0; i < n && i < m; i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                break;
            } else {
                sb.append(str1.charAt(i));
            }
        }
        return sb.toString();
    }
    String longestCommonPrefix(String arr[], int n){
        String str = arr[0];
        for(int i = 1; i < n; i++) {
            str = findCommonString(str, arr[i]);
        }
        return str.length() > 0 ? str : "-1";
    }


    //https://practice.geeksforgeeks.org/problems
    ///roman-number-to-integer3201/1/?track=md-string&batchId=144
    //Roman number to Integer 
    public int romanToDecimal(String str) {
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('I',1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        
        int res = 0;
        int n = str.length();
        for(int i = 0; i < n - 1; i++) {
            int numricVal = hm.get(str.charAT(i));
            int nextNumVal = hm.get(str.charAt(i + 1));
            if(numricVal < nextNumVal) {
                res -= numricVal;
            } else {
                res += numricVal;
            }
        }
        res = res + hm.get(n - 1);
        return res;
    }






    public static void main(String[] args) {
        //searchPattern();
        // System.out.println(extractMaximum("100klh10000000abc385bg"));
        System.out.println(reverseWords("pqr.mno"));
    }
    
}