package STRING;

import java.util.*;

public class StringProb {

    /********** STRING BASIC QUESTION ************/
    // 1.
    // Algorithm of sliding window for string
    // pattern matching

    public static boolean isMatch(int l, int r, char[] text, char[] pattern) {
        int plen = pattern.length;
        for (int i = 0; i < plen; i++) {
            if (text[l + i] != pattern[i])
                return false;
        }
        return true;
    }

    public static void searchPattern() {
        char[] text = { 'c', 'o', 'd', 'e', 'n', 'c', 'o', 'd', 'e' };
        char[] pattern = { 'c', 'o', 'd' };
        int tlen = text.length;
        int plen = pattern.length;

        for (int l = 0, r = plen - 1; r < tlen; l++, r++) {
            if (isMatch(l, r, text, pattern)) {
                System.out.println(l);
            }
        }
    }

    // 2.Anagram
    // https://leetcode.com/problems/valid-anagram/submissions/
    // Example 1:
    // Input: s = "anagram", t = "nagaram"
    // Output: true
    public static void fillFreq(int[] arr, String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            arr[ch - 'a']++;
        }
    }

    public boolean isAnagram(String s, String t) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        fillFreq(arr1, s);
        fillFreq(arr2, t);

        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    // 3.find all anagrams in a string
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // Input: s = "cbaebabacd", p = "abc"
    // Output: [0,6]
    // Explanation:
    // The substring with start index = 0 is "cba", which is an anagram of "abc".
    // The substring with start index = 6 is "bac", which is an anagram of "abc".
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        List<Integer> res = new ArrayList<>();
        for (int l = 0, r = plen - 1; r < slen; l++, r++) {
            String str1 = s.substring(l, r + 1);
            if (isAnagram(str1, p)) {
                res.add(l);
            }
        }
        return res;
    }

    // 4. Sort the string in ascending order
    // https://practice.geeksforgeeks.org/problems/sort-the-string-in-descending-order3542/1
    String ReverseSort(String str) {
        int n = str.length();
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        // manually reverse
        StringBuilder sb = new StringBuilder();
        sb.append(arr);
        return sb.toString();
    }

    // 5.Alternatively merge two String
    // https://practice.geeksforgeeks.org/problems/merge-two-strings2736/1#
    // Input:
    // S1 = "Hello" S2 = "Bye"
    // Output: HBeylelo
    public String merge(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n || i < m; i++) {
            if (i < n)
                sb.append(S1.charAt(i));
            if (i < m)
                sb.append(S2.charAt(i));
        }
        return sb.toString();
    }

    // 6.
    // https://practice.geeksforgeeks.org/problems/save-ironman0227/1
    // Input : S = "I am :IronnorI Ma, i"
    // Output : YES
    public static boolean saveIronman(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            // check it is character or anything else
            if (Character.isLetter(ch1) == false && Character.isDigit(ch1) == false) {
                i++;
                continue;
            }
            if (Character.isLetter(ch2) == false && Character.isDigit(ch2) == false) {
                j--;
                continue;
            }
            // int diff1 = (ch1 >= 'A' && ch1 <= 'Z') ? ch1 - 'A' : ch1 - 'a';
            // int diff2 = (ch2 >= 'A' && ch2 >= 'Z') ? ch2 - 'A' : ch2 - 'a';
            int diff = Math.abs(ch1 - ch2);

            if (diff != 0 && diff != 32) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 7.
    // https://practice.geeksforgeeks.org/problems/extract-maximum2943/1
    // S = 100klh564abc365bg
    // Output: 564
    static int extractMaximum(String str) {
        int num = 0, res = 0;
        boolean flag = false;
        // Start traversing the given string
        for (int i = 0; i < str.length(); i++) {
            // If a numeric value comes, start converting
            // it into an integer till there are consecutive
            // numeric digits
            if (Character.isDigit(str.charAt(i)))
                num = num * 10 + (str.charAt(i) - '0');

            // Update maximum value
            else {
                res = Math.max(res, num);

                // Reset the number
                num = 0;
            }
        }
        if (num == 0 && res == 0 && flag == false)
            return -1;
        return Math.max(res, num);
    }

    // 8.
    // https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string5459/1
    // S = pqr.mno
    // Output: mno.pqr
    public static String reverseWords(String str) {
        int n = str.length();
        StringBuilder res = new StringBuilder();
        Stack<Character> stk = new Stack();
        for (int i = 0; i < n; i++) {
            stk.push(str.charAt(i));
        }
        while (stk.size() > 0) {
            Stack newStk = new Stack<>();
            while (stk.size() > 0 && stk.peek() != '.') {
                newStk.push(stk.pop());
            }
            while (newStk.size() > 0) {
                res.append(newStk.pop());
            }
            if (stk.size() > 0)
                res.append(stk.pop());
        }
        return res.toString();
    }

    // 9.
    // https://practice.geeksforgeeks.org/problems/implement-strstr/1
    public static boolean isMatch_(int l, int r, char[] t, char[] p) {
        int n = t.length;
        int m = p.length;
        for (int i = 0; i < m; i++) {
            if (t[l + i] != p[i])
                return false;
        }
        return true;
    }

    int strstr(string s, string p) {
        int slen = s.length();
        int plen = p.length();
        char[] sarr = s.toCharArray();
        char[] parr = p.toCharArray();

        for (int l = 0, r = plen - 1; l < slen - plen; l++, r++) {
            if (sarr[l] == parr[0]) {
                if (isMatch_(l, r, sarr, parr))
                    return l;
            }
        }
        return -1;
    }

    // 10
    // https://practice.geeksforgeeks.org/problems/check-for-subsequence4930/1
    boolean isSubSequence(String A, String B) {
        int alen = A.length();
        int blen = B.length();
        if (alen > blen)
            return false;
        int i = 0; // point String A
        int j = 0; // point String B
        // when any string finish then terminate loop
        while (i < alen && j < blen) {
            if (A.charAt(i) == B.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i == alen)
            return true;
        return false;
    }

    // 11
    // https://practice.geeksforgeeks.org/problems/
    // check-if-strings-are-rotations-of-each-other-or-not-1587115620/1
    // s1 = abcd s2 = bcda // true;
    public static boolean areRotations(String s1, String s2) {
        s1 = s1 + s1;
        char[] t = s1.toCharArray();
        char[] p = s2.toCharAttay();
        int tlen = t.length;
        int plen = p.length;
        for (int l = 0; l <= tlen - plen; i++) {
            if (t[l] == p[0]) {
                if (isMatch_(l, 0, t, p))
                    return true;
            }
        }
        return false;
    }

    // 12.
    // https://practice.geeksforgeeks.org/
    // problems/check-if-two-strings-are-k-anagrams-or-not/1
    // Input:
    // str1 = "fodr", str2="gork"
    // k = 2
    // Output:
    // 1
    // Explanation: Can change fd to gk
    boolean areKAnagrams(String s1, String s2, int k) {
        int s1len = s1.length();
        int s2len = s2.length();
        if (s1len != s2len)
            return false;
        HashMap<Character, Integer> hm = new HashMap<>();
        // counting freqency of string
        for (int i = 0; i < s1len; i++) {
            char ch = s1.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s1len; i++) {
            char ch = s2.charAt(i);
            // hm.get(ch) > 0
            // because if frequency become 0 then dont decrese by 1
            if (hm.containsKey(ch) && hm.get(ch) > 0)
                hm.put(ch, hm.get(ch) - 1);
        }

        int count = 0;
        for (Character ch : hm.keySet()) {
            count += hm.get(ch);
        }
        return count <= k;
    }

    // 13
    // Non repeating character
    // https://practice.geeksforgeeks.org/problems/non-repeating-character-1587115620/1#
    static char nonrepeatingCharacter(String S) {
        int len = S.length();
        LinkedHashMap<Character, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        for (Character key : hm.keySet()) {
            if (hm.get(key) == 1) {
                return key;
            }
        }
        return '$';
    }

    // 14
    // Longest Distinct characters in string
    // https://practice.geeksforgeeks.org/problems/
    // longest-distinct-characters-in-string5848/1
    // Input:
    // S = "geeksforgeeks"
    // Output: 7
    // Explanation: "eksforg" is the longest
    // substring with all distinct characters.
    int longestSubstrDistinctChars(String str) {
        int i = 0;
        int j = 0;
        int len = str.length();
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (j < len) {
            if (!set.contains(str.charAt(j))) {
                set.add(str.charAt(j));
                max = Math.max(set.size(), max);
                j++;
            } else {
                set.remove(str.charAt(i++));
            }
        }
        return max;
    }

    // 15
    // https://practice.geeksforgeeks.org/problems/find-k-th-character-in-string3841/1
    public String findBinary(int m) {
        if (m == 0)
            return "" + 0;
        StringBuilder sb = new StringBuilder();
        while (m != 0) {
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
        for (int i = 1; i <= n; i++) {
            int blen = binary.length();
            for (int j = 0; j < blen; j++) {
                char ch = binary.charAt(j);
                sb.append(ch);
                if (ch == '0') {
                    sb.append(one);
                } else {
                    sb.append(zero);
                }
            }
            binary = sb.toString();
            sb = new StringBuilder();
        }
        return binary.charAt(k - 1);
    }

    // 16
    // https://practice.geeksforgeeks.org/problems/nearest-multiple-of-102437/1
    // Input : N = 29
    // Output : 30
    String roundToNearest(String num) {
        int len = num.length();
        int ln = num.charAt(len - 1) - '0';
        int sln = num.charAt(len - 2) - '0';
        char[] numArr = num.toCharArray();
        if (ln <= 5) {
            ln = 0;
        } else {
            ln = 0;
            sln = sln + 1;
        }
        numArr[len - 1] = ln;
        numArr[len - 2] = sln;
        StringBuilder sb = new StringBuilder();
        for (char ch : numArr) {
            sb.append(ch);
        }
        return sb.toCharArray();
    }

    // ******Must Do interview Question *******/
    // 17. Longest Common Prefix in an Array
    // https://practice.geeksforgeeks.org/problems/
    // longest-common-prefix-in-an-array5129/1/?track=md-string&batchId=144

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
        for (int i = 0; i < n && i < m; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            } else {
                sb.append(str1.charAt(i));
            }
        }
        return sb.toString();
    }

    String longestCommonPrefix(String arr[], int n) {
        String str = arr[0];
        for (int i = 1; i < n; i++) {
            str = findCommonString(str, arr[i]);
        }
        return str.length() > 0 ? str : "-1";
    }

    // 18->https://practice.geeksforgeeks.org/problems
    /// roman-number-to-integer3201/1/?track=md-string&batchId=144
    // Roman number to Integer
    public int romanToDecimal(String str) {
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);

        int res = 0;
        int n = str.length();
        for (int i = 0; i < n - 1; i++) {
            int numricVal = hm.get(str.charAT(i));
            int nextNumVal = hm.get(str.charAt(i + 1));
            if (numricVal < nextNumVal) {
                res -= numricVal;
            } else {
                res += numricVal;
            }
        }
        res = res + hm.get(n - 1);
        return res;
    }

    // 20
    // Equal point in a string of brackets
    // Input: str = "(())))("
    // Output: 4
    // Explanation:
    // After index 4, string splits into (())
    // and ))(. Number of opening brackets in the
    // first part is equal to number of closing
    // brackets in the second part.

    public long countSub(String str) {
        char[] charArr = str.toCharArray();
        int len = charArr.length;

        int[] rightClosing = new int[len];
        int[] leftOpening = new int[len];

        int rightClose = 0;
        for (int i = len - 1; i >= 0; i--) {
            char ch = charArr[i];
            if (ch == ')') {
                rightClose += 1;
                rightClosing[i] = rightClose;
            } else {
                rightClosing[i] = rightClose;
            }
        }

        int leftOpen = 0;
        for (int i = 0; i < len; i++) {
            char ch = charArr[i];
            if (ch == '(') {
                leftOpen += 1;
                leftOpening[i] = leftOpen;
            } else {
                leftOpening[i] = leftOpen;
            }
        }

        if (rightClosing[0] == len)
            return len;
        if (leftOpening[len - 1] == len)
            return 0;
        long ans = 0;
        for (int i = 0; i < len; i++) {
            int left = (i - 1 < 0) ? 0 : leftOpening[i - 1];
            int right = (i >= len) ? 0 : rightClosing[i];

            if (left == right) {
                ans = (long) i;
            }
        }

        return ans;
    }

    // 21
    // Pangram Checking
    // Input:
    // S = Bawds jog, flick quartz, vex nymph
    // Output: 1
    // Explantion: In the given input, there
    // are all the letters of the English
    // alphabet. Hence, the output is 1.
    public static boolean checkPangram(String str) {
        int len = str.length();
        int[] hash = new int[26];

        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch) || Character.isLowerCase(ch)) {
                if (Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                }
                hash[ch - 'a']++;
            }
        }

        for (int num : hash) {
            if (num == 0)
                return false;
        }

        return true;
    }

    // 22
    // Smallest window in a string containing all the characters of another string
    // Input:
    // S = "timetopractice"
    // P = "toc"
    // Output:
    // toprac
    // Explanation: "toprac" is the smallest
    // substring in which "toc" can be found.
    public static String smallestWindow(String s1, String s2) {
        if (s2.length() == 1) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(0)) {
                    return s2;
                }
            }
            return "";
        }

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        int ans = Integer.MAX_VALUE;
        int matchcount = 0;
        int[] fans = new int[2];
        for (int i = 0, j = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (map2.containsKey(c)) {
                map1.put(c, map1.getOrDefault(c, 0) + 1);
                if (map1.get(c) <= map2.get(c)) {
                    matchcount++;
                }
                while (matchcount == s2.length()) {
                    char chj = s1.charAt(j);
                    if (!map1.containsKey(chj)) {
                        // nothing
                    } else if (map1.get(chj) > map2.getOrDefault(chj, 0)) {
                        map1.put(chj, map1.get(chj) - 1);
                    } else {
                        if (i - j + 1 < ans) {
                            ans = i - j + 1;
                            fans[0] = i;
                            fans[1] = j;
                        }
                        map1.put(chj, map1.get(chj) - 1);
                        if (map1.get(chj) < 0) {
                            map1.remove(chj);
                        }
                        matchcount--;
                    }
                    j++;
                }

            }

        }
        if (fans[0] == fans[1] && s2.length() != 1) {
            return "-1";
        } else
            return (s1.substring(fans[1], fans[0] + 1));
    }

    // 23
    // Input:
    // N = 5
    // words[] = {act,god,cat,dog,tac}
    // Output:
    // act cat tac
    // god dog
    // Explanation:
    // There are 2 groups of
    // anagrams "god", "dog" make group 1.
    // "act", "cat", "tac" make group 2.

    public List<List<String>> Anagrams(String[] stringList) {
        HashMap<String, List<String>> map = new HashMap<>();
        int len = stringList.length;
        List<List<String>> ans = new ArrayList<>();
        if (len == 0)
            return ans;

        for (int i = 0; i < len; i++) {
            // System.out.println(i);
            String str = stringList[i];
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String newStr = new String(strArr);
            if (map.containsKey(newStr)) {
                List<String> list = map.get(newStr);
                list.add(str);
                map.put(newStr, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            }
        }

        // List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            ans.add(list);
        }

        return ans;
    }

    // 24
    // Search Pattern (Rabin-Karp Algorithm)
    int d = 256;

    ArrayList<Integer> search(String pat, String txt) {
        int q = 101;
        ArrayList<Integer> ans = new ArrayList<>();
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters one by one
            if (p == t) {
                /* Check for characters one by one */
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                    ans.add(i + 1);
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;

                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                    t = (t + q);
            }
        }
        if (ans.size() == 0)
            ans.add(-1);
        return ans;
    }


    //25
    //Sum of two large numbers
    String findSum(String num1, String num2) {
        int i = num1.length() -1;
        int j = num2.length() -1;
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        
        while(i >= 0 || j >= 0 || carry > 0){
            int n1 = 0;
            int n2 = 0;
            
            if(i >= 0){
                n1 = num1.charAt(i) - '0';
                i--;
            }
            if(j >= 0){
                n2 = num2.charAt(j) - '0';
                j--;
            }
            
            int num = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) >= 10 ? 1 : 0;
            sb.append(num);
           
        }

        // Removing leading zero
        sb.reverse();
        int len = sb.length();
        boolean flag = false;
        StringBuilder ans = new StringBuilder();
        int idx = 0;
        
        while(idx < len - 1 && sb.charAt(idx) == '0') {
            idx++;
        }
        
        while(idx < len) {
            ans.append(sb.charAt(idx++));
        }
        
        return ans.toString();
    }



    //26
    //Add Binary Strings
    // Input:
    // A = "1101", B = "111"
    // Output: 10100
    // Explanation:
    // 1101
    // + 111
    // 10100
    
    String addBinary(String A, String B) {
        
        StringBuilder sb = new StringBuilder();
        int i = A.length() - 1;
        int j = B.length() - 1;
        int c = 0;
        
        while(i >= 0 || j >= 0 || c > 0) {
            int num1 = 0;
            int num2 = 0;
            
            if(i >= 0) {
                num1 = A.charAt(i--) == '1' ? 1 : 0;
            }
            
            if(j >= 0) {
                num2 = B.charAt(j--) == '1' ? 1 : 0;
            }
            
            int sum = num1 + num2 + c;
            
            if(sum == 0) {
                sb.append('0');
                c = 0;
            } else if(sum == 1) {
                sb.append('1');
                c = 0;
            } else if(sum == 2) {
                sb.append('0');
                c = 1;
            } else {
                sb.append('1');
                c = 1;
            }
        }
        
        sb.reverse();
        int len = sb.length();
        StringBuilder ans = new StringBuilder();
        int idx = 0;
        
        while(idx < len - 1 && sb.charAt(idx) == '0') {
            idx++;
        }
        
        while(idx < len) {
            ans.append(sb.charAt(idx++));
        }
        
        return ans.toString();
    }

    //27
    //Equal 0, 1 and 2 
    // Input: str = “0102010”
    // Output: 2
    // Explanation: Substring str[2, 4] = “102” and
    // substring str[4, 6] = “201” has equal number
    // of 0, 1 and 2 


    long getSubstringWithEqual012(String str) { 
        char[] arr = str.toCharArray();
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;
        String key = 0 + "#" + 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(key, 1);
        int len = arr.length;
        long ans = 0;
        
        for(int i = 0; i < len; i++) {
            
            if(arr[i] == '0') {
                zeroCount++;
            } else if(arr[i] == '1') {
                oneCount++;
            } else {
                twoCount++;
            }
            
            key = (oneCount - zeroCount) + "#" + ( twoCount - oneCount);
            
            if(map.containsKey(key)) {
                ans += map.get(key);
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        
        return ans;
    }

    //28
    //Encrypt the string - 2
    // Input:
    // S = "aaaaaaaaaaa"
    // Output:
    // ba 
    // Explanation: 
    // aaaaaaaaaaa
    // Step1: a11 (a occurs 11 times)
    // Step2: a11 is ab [since 11 is b in
    // hexadecimal]
    // Step3: ba [After reversing]
    static String encryptString(String S) {
        int count = 1;
        int len = S.length();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < len; i++) {
            if(S.charAt(i) != S.charAt(i - 1)) {
                sb.insert(0, S.charAt(i - 1));
                sb.insert(0, Integer.toHexString(count));
                count = 1;
            } else {
                count++;
            }
            
            if(i == len - 1) {
               sb.insert(0, S.charAt(i));
               sb.insert(0, Integer.toHexString(count));
            }
        }
        
        return sb.toString();
    }

    //29
    // Input:
    // IPv4 address = 222.111.111.111
    // Output: 1
    // Explanation: Here, the IPv4 address is as
    // per the criteria mentioned and also all
    // four decimal numbers lies in the mentioned
    // range.

    public int ston(String str) {
        int num = 0;
        int len = str.length();
        
        for(int i = 0; i < len; i++) {
            num = num * 10 + (str.charAt(i) - '0');
        }
        return num;
    }
    
    public boolean isValid(String s) {
        int n = s.length();
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '.') count++;
        }
        if(count != 3) return false;
        String[] strArr = s.split("\\.");
        int strLen = strArr.length;
        if(strLen != 4) return false;
        int sum = 0;
        for(int i = 0; i < strLen; i++) {
            String str = strArr[i];
            if(str.length() == 0 || (str.length() > 1 && str.charAt(0) == '0')) return false;
            if(Character.isLetter(str.charAt(0))) return false;
            int num = ston(str);
            //System.out.println(num);
            if(num < 0 || num > 255) {
                return false;
            }
            sum += num;
        }
        //if(sum == 0) return true;
        return true;
    }

    //30
    //Implement Atoi
    // Input:
    // str = 123
    // Output: 123
    public int ston(String str) {
        int sign = 1;
        int num = 0;
        int len = str.length();
        
        int i = 0;
        if(str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        
        while(i < len) {
            char ch = str.charAt(i++);
            if(!Character.isDigit(ch)) return -1;
            num = num * 10 + (ch - '0');
        }
        return num * sign;
    }
    
    int atoi(String str) {
	    return ston(str);
    }

    //31. longest prefix suffix
    // Input: s = "abab"
    // Output: 2
    // Explanation: "ab" is the longest proper 
    // prefix and suffix. 
    
    int lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        lps[0] = 0;
        int i = 0, j = 1;
        
        while(j < n) {
            if(s.charAt(i) == s.charAt(j)) {
                lps[j] = i + 1;
                i++;
                j++;
            } else {
                if(i != 0) {
                    i = lps[i - 1];
                } else {
                    lps[j] = 0;
                    j++;
                }
            }
        }
        int ans = lps[n - 1];
        return ans;
    }

    //32 Search Pattern (KMP-Algorithm) 
    // Input:
    // txt = "batmanandrobinarebat", pat = "bat"
    // Output: 1 18
    // Explanation: The string "bat" occurs twice
    // in txt, one starts are index 1 and the other
    // at index 18. 

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0
 
        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
 
                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    
    ArrayList<Integer> searchPattern(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        ArrayList<Integer> ans = new ArrayList<>();
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]
 
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);
 
        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                ans.add(i - j + 1);
                j = lps[j - 1];
            }
 
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // searchPattern();
        // System.out.println(extractMaximum("100klh10000000abc385bg"));
        System.out.println(reverseWords("pqr.mno"));
    }

}