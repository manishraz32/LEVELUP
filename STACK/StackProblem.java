import java.util.*;

Scanner scn=new Scanner(System.in);

public class StackProblem {

    // https://leetcode.com/problems/next-greater-element-i/
    // 496. Next Greater Element I

    public int[] nextGreaterElement(int[] quary, int[] nums2) {
        int[] greaterElements = fillNextGreater(nums2);
        HashMap<Integer, Integer> hmap = new HashMap<>();
        int queryLen = quary.length;
        int numsLen = nums2.length;
        for (int i = 0; i > numsLen; i++) {
            hmap.put(nums2[i], greaterElements[i]);
        }
        int[] res = new int[queryLen];
        for (int i = 0; i < queryLen; i++) {
            res[i] = hmap.get(quary[i]);
        }
        return res;
    }

    public int[] fillNextGreater(int[] arr) {
        Stack<Integer> stk = new Stack<Integer>();
        int len = arr.length;
        int[] ans = new int[len];
        for (int idx = len - 1; idx >= 0; idx++) {
            while (stk.size() > 0 && stk.peek() < arr[idx])
                stk.pop();
            ans[idx] = stk.size() > 0 ? stk.peek() : -1;
            stk.push(arr[idx]);
        }
        return ans;
    }

    // https://leetcode.com/problems/next-greater-element-ii/
    // 503. Next Greater Element II
    // Input: nums = [1,2,1]
    // Output: [2,-1,2]
    // Explanation: The first 1's next greater number is 2;
    // The number 2 can't find next greater number.
    // The second 1's next greater number needs to search circularly, which is also
    // 2.

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Stack<Integer> stk = new Stack<>();
        for (int i = len - 2; i >= 0; i--) {
            while (stk.size() > 0 && stk.peek() <= nums[i]) {
                stk.pop();
            }
            stk.push(nums[i]);
        }
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (stk.size() > 0 && stk.peek() <= nums[i]) {
                stk.pop();
            }
            ans[i] = stk.size() > 0 ? stk.peek() : -1;
            stk.push(nums[i]);
        }
        return ans;
    }

    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // 84. Largest Rectangle in Histogram
    // Input: heights = [2,1,5,6,2,3]
    // Output: 10
    // Explanation: The above is a histogram where width of each bar is 1.
    // The largest rectangle is shown in the red area, which has an area = 10 units.

    public int[] rightSmallest(int[] arr) {
        Stack<Integer> stk = new Stack<Integer>();
        int len = arr.length;
        int[] ans = new int[len];
        for (int idx = len - 1; idx >= 0; idx--) {
            while (stk.size() > 0 && arr[stk.peek()] > arr[idx])
                stk.pop();
            ans[idx] = stk.size() > 0 ? stk.peek() : len;
            stk.push(idx);
        }
        return ans;
    }

    public int[] leftSmallest(int[] arr) {
        Stack<Integer> stk = new Stack<Integer>();
        int len = arr.length;
        int[] ans = new int[len];
        for (int idx = 0; idx < len; idx++) {
            while (stk.size() > 0 && arr[stk.peek()] > arr[idx])
                stk.pop();
            ans[idx] = stk.size() > 0 ? stk.peek() : -1;
            stk.push(idx);
        }
        return ans;
    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] firstLeftSmallest = leftSmallest(heights);
        int[] firstRightSmallest = rightSmallest(heights);
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int width = firstRightSmallest[i] - firstLeftSmallest[i] - 1;
            maxArea = Math.max(maxArea, (heights[i] * width));
        }
        return maxArea;
    }

    // https://leetcode.com/problems/maximal-rectangle/
    // 85. Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix.length;
        int[] newArray = new int[m];
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cellVal = matrix[i][j];
                if (cellVal == '1') {
                    newArray[j]++;
                } else {
                    newArray[j] = 0;
                }
            }
            int area = largestRectangleArea(newArray);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    // https://leetcode.com/problems/validate-stack-sequences/
    // 946. Validate Stack Sequences
    // Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
    // Output: true
    // Explanation: We might do the following sequence:
    // push(1), push(2), push(3), push(4),
    // pop() -> 4,
    // push(5),
    // pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stk = new Stack<>();
        int idx = 0;
        for (int num : pushed) {
            stk.push(num);
            while (stk.size() > 0 && stk.peek() == popped[idx]) {
                stk.pop();
                idx++;
            }
        }
        return popped.length == idx;
    }

    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    // 921. Minimum Add to Make Parentheses Valid
    public int minAddToMakeValid(String str) {
        Stack<Character> stk = new Stack<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char parentheses = str.charAt(i);
            if (parentheses == '(') {
                stk.push(parentheses);
            } else {
                if (stk.size() > 0 && stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(parentheses);
                }
            }
        }
        return stk.size();
    }

    // https://leetcode.com/problems/remove-outermost-parentheses/
    // 1021. Remove Outermost Parentheses
    // Input: s = "(()())(())"
    // Output: "()()()"
    // Explanation:
    // The input string is "(()())(())", with primitive decomposition "(()())" +
    // "(())".
    // After removing outer parentheses of each part, this is "()()" + "()" =
    // "()()()"
    public String removeOuterParentheses(String s) {
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int sLen = s.length();
        for (int i = 0; i < sLen; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                if (stk.size() > 0) {
                    sb.append(ch);
                }
                stk.push(ch);
            } else {
                stk.pop();
                if (stk.size() > 0) {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/score-of-parentheses/
    // 856. Score of Parentheses
    public int scoreOfParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        int slen = s.length();
        for (int i = 0; i < slen; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stk.push(-1);
            } else {
                if (stk.peek() == -1) {
                    stk.pop();
                    stk.push(1);
                } else {
                    int val = 0;
                    while (stk.peek() != -1) {
                        val += stk.peek();
                    }
                    stk.pop();
                    stk.push(val);
                }
            }
        }
        int val = 0;
        while (stk.size() > 0) {
            val += stk.pop();
        }
        return val;
    }

    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
    // 1190. Reverse Substrings Between Each Pair of Parentheses
    public String reverseParentheses(String s) {
        char[] charArr = s.toCharArray();
        Stack<Character> stk = new Stack<>();
        int len = charArr.length;
        for (int i = 0; i < len; i++) {
            char ch = charArr[i];
            if (ch == ')') {
                LinkedList<Character> que = new LinkedList<>();
                while (stk.peek() != '(') {
                    que.addLast(stk.pop());
                }
                stk.pop();
                while (que.size() > 0) {
                    stk.push(que.removeFirst());
                }
            } else {
                stk.push(ch);
            }
        }
        int stkLen = stk.size();
        char[] arr = new char[stkLen];
        int idx = stk.size() - 1;
        while (stk.size() > 0) {
            arr[idx--] = stk.pop();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stkLen; i++) {
            // System.out.println(arr[i]);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    // Input: s = "lee(t(c)o)de)"
    // Output: "lee(t(c)o)de"
    // Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Integer> stk = new Stack<>();
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            char ch = arr[i];
            if (stk.size() == 0 && (ch == '(' || ch == ')')) {
                stk.push(i);
            } else if (ch == ')') {
                if (arr[stk.peek()] == '(') {
                    stk.pop();
                } else {
                    stk.push(i);
                }
            } else if (ch == '(') {
                stk.push(i);
            }
        }
        while (stk.size() > 0) {
            // System.out.println(stk.peek());
            int idx = stk.pop();
            // idx = idx > 0 ? idx : (-1 * idx);
            arr[idx] = '#';
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            // System.out.println(arr[i]);
            if (arr[i] != '#') {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/online-stock-span/
    // Input
    // ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
    // [[], [100], [80], [60], [70], [60], [75], [85]]
    // Output
    // [null, 1, 1, 1, 2, 1, 4, 6]
    public class Pair {
        int price;
        int index;
    }

    Stack<Pair> stk = new Stack<>();
    int time = -1;

public StockSpanner() {
    Pair p = new Pair();
    p.price = Integer.MAX_VALUE;
    p.index = time;
    time++;
    stk.push(p); 
}

    public int next(int price) {
        Pair p = new Pair();
        p.price = price;
        p.index = time;
        time++;
        while (stk.peek().price <= p.price) {
            stk.pop();
        }
        int leftMostIndex = stk.peek().index;
        int ans = p.index - leftMostIndex;
        stk.push(p);
        return ans;
    }

    // https://leetcode.com/problems/exclusive-time-of-functions/
    // Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    // Output: [3,4]
    // Explanation:
    // Function 0 starts at the beginning of time 0, then it executes 2 for units of
    // time and reaches the end of time 1.
    // Function 1 starts at the beginning of time 2, executes for 4 units of time,
    // and ends at the end of time 5.
    // Function 0 resumes execution at the beginning of time 6 and executes for 1
    // unit of time.
    // So function 0 spends 2 + 1 = 3 units of total time executing, and function 1
    // spends 4 units of total time executing.
    // Example 2:

    // Input: n = 1, logs =
    // ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
    // Output: [8]
    // Explanation:
    // Function 0 starts at the beginning of time 0, executes for 2 units of time,
    // and recursively calls itself.
    // Function 0 (recursive call) starts at the beginning of time 2 and executes
    // for 4 units of time.
    // Function 0 (initial call) resumes execution then immediately calls itself
    // again.
    // Function 0 (2nd recursive call) starts at the beginning of time 6 and
    // executes for 1 unit of time.
    // Function 0 (initial call) resumes execution at the beginning of time 7 and
    // executes for 1 unit of time.
    // So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
    public class FunPair {
        int funId;
        int startTime;
        int childTime;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        int length = logs.size();
        Stack<FunPair> stk = new Stack<>();
        for (String str : logs) {
            String[] log = str.split(":");
            if (log[1].equals("start")) {
                FunPair p = new FunPair();
                p.funId = Integer.parseInt(log[0]);
                p.startTime = Integer.parseInt(log[1]);
                p.childTime = 0;
                stk.push(p);
            } else {
                FunPair p = stk.pop();
                int endTime = Integer.parseInt(log[2]);
                int startTime = p.startTime;
                int interval = endTime - startTime;
                int time = interval - p.childTime;
                ans[p.funId] += time;
                if (stk.size() > 0) {
                    stk.peek().childTime += interval;
                }
            }

        }
        return ans;
    }

    // https://leetcode.com/problems/asteroid-collision/
    // Input: asteroids = [5,10,-5]
    // Output: [5,10]
    // Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never
    // collide.
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stk = new Stack<>();
        int len = asteroids.length;
        for (int i = 0; i < len; i++) {
            int size = asteroids[i];
            if (stk.size() == 0) {
                stk.push(size);
            } else if (size > 0) {
                stk.push(size);
            } else if (size < 0) {
                if (stk.size() > 0 && stk.peek() < 0) {
                    stk.push(size);
                    // System.out.println("running");
                    continue;
                }
                size = -1 * size;
                if (stk.size() > 0 && size >= stk.peek()) {
                    while (stk.size() > 0 && stk.peek() > 0 && stk.peek() < size) {
                        stk.pop();
                    }
                    if (stk.size() > 0 && stk.peek() > 0 && stk.peek() == size) {
                        stk.pop();
                    } else if (stk.size() > 0 && stk.peek() > size) {
                        // do nothing
                    } else {
                        stk.push(-size);
                    }
                }
            }
        }
        int[] ans = new int[stk.size()];
        int idx = ans.length - 1;
        while (stk.size() > 0) {
            ans[idx--] = stk.pop();
        }
        return ans;
    }

    // https://leetcode.com/problems/remove-k-digits/
    // Input: num = "1432219", k = 3
    // Output: "1219"
    // Explanation: Remove the three digits 4, 3, and 2 to form the new
    // number 1219 which is the smallest.

    public String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char val = num.charAt(i);
            while (stk.size() > 0 && k > 0 && stk.peek() > val) {
                stk.pop();
                k--;
            }
            stk.push(val);
        }

        while (k > 0) {
            stk.pop();
        }

        char[] ansArr = new char[stk.size()];
        int arrLength = ansArr.length;
        int idx = ansArr.length;
        while (stk.size() > 0) {
            ansArr[idx--] = stk.pop();
        }
        int index = 0;
        while (index < arrLength && ansArr[idx] == 0) {
            idx++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < arrLength; i++) {
            sb.append(ansArr[idx]);
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/remove-duplicate-letters/
    // Remve Duplicate Letter
    // Input: s = "bcabc"
    // Output: "abc"
    public static String removeDuplicateLetters(String s) {
        int count[] = new int[26];
        boolean added[] = new boolean[26];

        for (int i = s.length() - 1; i >= 0; i--)
            count[s.charAt(i) - 'a']++;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (added[c - 'a']) {
                count[c - 'a']--;
                continue;
            }
            while (st.size() > 0 && st.peek() > c && count[st.peek() - 'a'] > 0) {
                added[st.pop() - 'a'] = false;
            }

            st.add(c);
            added[c - 'a'] = true;
            count[c - 'a']--;
        }

        char ch[] = new char[st.size()];
        int i = ch.length - 1;
        while (i >= 0)
            ch[i--] = st.pop();

        return new String(ch);
    }

    // https://leetcode.com/problems/basic-calculator/
    // Basic Calulator
    // Input: s = "(1+(4+5+2)-3)+(6+8)"
    // Output: 23
    public int calculate(String s) {

        int sum = 0;
        int sign = 1;

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                int val = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                val = val * sign;
                sign = 1;
                sum += val;
            } else if (ch == '(') {
                st.push(sum);
                st.push(sign);
                sum = 0;
                sign = +1;
            } else if (ch == ')') {
                sum *= st.pop();
                sum += st.pop();
            } else if (ch == '-') {
                sign *= -1;
            }
        }

        return sum;
    }

    // https://leetcode.com/problems/basic-calculator-ii/
    // Input: s = " 3+5 / 2 "
    // Output: 5
    public int calculate(String s) {
        int len = s.length();
        char sign = '+';
        // int val = 0;
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                int val = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                if (sign == '+') {
                    stk.push(val);
                } else if (sign == '-') {
                    stk.push(-val);
                } else if (sign == '*') {
                    val = stk.pop() * val;
                    stk.push(val);
                } else if (sign == '/') {
                    val = stk.pop() / val;
                    stk.push(val);
                }
            } else if (ch != ' ') {
                sign = ch;
            }
        }

        int ans = 0;
        while (stk.size() > 0) {
            ans += stk.pop();
        }
        return ans;
    }

    // Design a Hit Couter
    // https://pepcoding.com/resources/
    // https://pepcoding.com/data-structures-and-algorithms-in-java-levelup/stacks/design-hit-counter-official/ojquestion#!
    static class HitCounter {

        /** Initialize your data structure here. */
        ArrayDeque<Integer> que;

        public HitCounter() {
            que = new ArrayDeque<Integer>();
        }

        /**
         * Record a hit.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            que.add(timestamp);
            int start = timestamp - 300 + 1;
            while (que.size() > 0 && que.peek() < start) {
                que.remove();
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         * 
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            int start = timestamp - 300 + 1;
            while (que.size() > 0 && que.peek() < start) {
                que.pop();
            }
            return que.size();
        }
    }

    // https://leetcode.com/problems/number-of-recent-calls/
    // Input
    // ["RecentCounter", "ping", "ping", "ping", "ping"]
    // [[], [1], [100], [3001], [3002]]
    // Output
    // [null, 1, 2, 3, 3]

    // Explanation
    // RecentCounter recentCounter = new RecentCounter();
    // recentCounter.ping(1); // requests = [1], range is [-2999,1], return 1
    // recentCounter.ping(100); // requests = [1, 100], range is [-2900,100], return
    // 2
    // recentCounter.ping(3001); // requests = [1, 100, 3001], range is [1,3001],
    // return 3
    // recentCounter.ping(3002); // requests = [1, 100, 3001, 3002], range is
    // [2,3002], return 3


    class RecentCounter {
        Queue<Integer> que;
        public RecentCounter() {
            que = new LinkedList<Integer>();
        }
        
        public int ping(int t) {
            que.add(t);
            int start = t - 3000;
            while(que.size() > 0 && que.peek() < start) {
                que.remove();
            }
            return que.size();
        }
    }


    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup
    //stacks/moving-average-from-data-stream-official/ojquestion
    public static class MovingAverage {
        public MovingAverage(int size) {
    
        }
    
        public double next(int val) {
    
        }
      }


      //https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
    //   Input: s = "aabcbc"
    //   Output: true
    //   Explanation:
    //   "" -> "abc" -> "aabcbc"
    //   Thus, "aabcbc" is valid.
    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stk = new Stack<Character>();
        for(int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if(ch == 'c') {
                if(stk.size() >= 2 && stk.pop() == 'b' && stk.pop() == 'a') {
                    //paired
                } else {
                    return false;
                }
            } else {
                //System.out.println(ch);
                stk.push(ch);
            }
        }
        return stk.size() == 0;
    }

    public static void main(String[] args) {
        System.out.println("manish");
    }
}
