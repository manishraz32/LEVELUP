package ARRAY;

import java.util.*;

public class Arraays {

    //u
    public boolean isLongPressedName(String name, String typed) {
        if (name.length() > typed.length())
            return false;
        int i = 0; // for name
        int j = 0; // for typed

        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if ((i - 1) > 0 && name.charAt(i - 1) == typed.charAt(j)) {
                    j++;
                }
            }
        }
        while (j < typed.length()) {
            if (name.charAt(i - 1) != typed.charAt(j)) {
                return false;
            } else {
                j++;
            }
        }
        while (i < name.length()) {
            if (name.charAt(i) == name.charAt(i - 1)) {
                i++;
            } else {
                return false;
            }
        }

        return true;
    }

    // https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            maxArea = Math.max(maxArea, area);

            //if Hight of left wall is greater then move from right to left
            //ele move left to right
            if (height[i] < height[j]) {
                i++;
            } else {
                j++;
            }
        }
        return maxArea;
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int k = j;
        while (i <= j) {
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];
            if (val1 > val2) {
                ans[k--] = val1;
                i++;
            } else {
                ans[k--] = val2;
                j--;
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        int val = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (val == nums[i]) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    val = nums[i];
                    count = 1;
                }
            }
        }
        return val;
    }

    // https://leetcode.com/problems/majority-element-ii/
    public boolean isMajority(int val, int[] arr) {
        int count = 0;
        for (int ele : arr) {
            if (ele == val)
                count++;
        }
        return count > arr.length / 3;
    }

    public List<Integer> majorityElement2(int[] nums) {
        int val1 = nums[0];
        int count1 = 1;
        int val2 = nums[0];
        int count2 = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == val1) {
                count1++;
            } else if (nums[i] == val2) {
                count2++;
            } else {
                if (count1 == 0) {
                    val1 = nums[i];
                    count1 = 1;
                } else if (count2 == 0) {
                    val2 = nums[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (isMajority(val1, nums)) {
            ans.add(val1);
        }
        if (val2 != val1 && isMajority(val2, nums)) {
            ans.add(val2);
        }
        return ans;
    }

    // https://leetcode.com/problems/maximum-product-of-three-numbers/
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    // https://leetcode.com/problems/max-chunks-to-make-sorted/
    // elements are from 0 to n - 1;
    public int maxChunksToSorted_(int[] arr) {
        int max = 0;
        int chunks = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (i == max) {
                chunks++;
            }
        }
        return chunks;
    }

    // https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] rightMin = new int[n];
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }

        int chunk = 1;
        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            leftMax = Math.max(arr[i], leftMax);
            if (leftMax <= rightMin[i + 1]) {
                chunk++;
            }
        }
        return chunk;
    }

    // https://leetcode.com/problems/reverse-vowels-of-a-string/
    public boolean isVowels(char ch) {
        String vowel = "AEIOUaeiou";
        return vowel.contains(ch + "");
    }

    public void swap_(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (left < right && !isVowels(arr[left]))
                left++;
            while (right > left && !isVowels(arr[right]))
                right--;
            swap_(arr, left, right);
            left++;
            right--;
        }

        String ans = "";
        for (char ch : arr) {
            ans = ans + ch;
        }
        return ans;
    }
    //next greater element
    public static int findDipIndex(char[] arr) {
        int index = -1;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                index = i - 1;
                break;
            }

        }
        return index;
    }

    public static int findCielIndex(char[] arr, int dipIndex) {
        int idx = arr.length - 1;
        while (arr[dipIndex] >= arr[idx]) {
            idx--;
        }
        return idx;
    }

    public static void swap(char[] arr, int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static String nextGreaterElement(String str) {
        char[] arr = str.toCharArray();
        int dipIndex = findDipIndex(arr);
        int ceilIndex = findCielIndex(arr, dipIndex);
        swap(arr, dipIndex, ceilIndex);
        reverse(arr, dipIndex + 1, arr.length - 1);

        return String.valueOf(arr);
    }

    // https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
    // Input: nums = [2,1,4,3], left = 2, right = 3
    // Output: 3
    // Explanation: There are three subarrays that meet the requirements: [2], [2,
    // 1], [3]

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int si = 0;
        int ei = 0;

        int prevCount = 0;
        int count = 0;
        while (ei < n) {
            if (nums[ei] >= left && nums[ei] <= right) {
                prevCount = (ei - si) + 1; // for one number
                count += prevCount;
            } else if (nums[ei] < left) {
                count += prevCount;
            } else {
                si = ei + 1;
                prevCount = 0;
            }
        }
        return count;
    }

    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup
    // /arrays-and-strings/wiggle-sort-1/ojquestion#!
    // 1. Given an unsorted array 'arr'.
    // 2. Reorder it in-place such that : arr[0] <= arr[1] >= arr[2] <= arr[3] 
    // 3. Please sort the array in place and do not define additional arrays.
    // 4. Allowed Time Complexity : O(n)

    public static void swapWiggle(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void wiggleSort(int[] arr) {

        for (int idx = 0; idx < arr.length - 1; idx++) {
            if (idx % 2 == 0) {
                if (arr[idx + 1] < arr[idx]) {
                    swapWiggle(arr, idx, idx + 1);
                }
            } else {
                if (arr[idx + 1] > arr[idx]) {
                    swapWiggle(arr, idx, idx + 1);
                }
            }
        }
    }

    // https://leetcode.com/problems/wiggle-sort-ii/
    // nums[0] < nums[1] > nums[2] < nums[3]....
    // Input: nums = [1,5,1,1,6,4]
    // Output: [1,6,1,5,1,4]
    // Explanation: [1,4,1,5,1,6] is also accepted.

    public void wiggleSort_Two(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] res = new int[n];
        int i = 1;
        int j = n - 1;
        while (i < n) {
            res[i] = nums[j];
            i += 2;
            j--;
        }
        i = 0;
        while (i < n) {
            res[i] = nums[j];
            i += 2;
            j--;
        }
        for (int idx = 0; idx < n; idx++) {
            nums[idx] = res[idx];
        }

    }

    // https://www.pepcoding.com/resources/
    // data-structures-and-algorithms-in-java-levelup/arrays-and-strings/range_addition/ojquestion#!
    // 1. Assume you have an array of length 'n' initialized with all 0's and are
    // given 'q' queries to update.
    // 2. Each query is represented as a triplet: [startIndex, endIndex, inc] which
    // increments each element of subarray A[startIndex ... endIndex] (startIndex
    // and endIndex inclusive) with inc.
    // 3. Return the modified array after all 'q' queries were executed.
    // Input Format
    // length = 5,
    // updates =
    // {
    // {1, 3, 2},
    // {2, 4, 3},
    // {0, 2, -2}
    // }

    public static int[] getModifiedArray(int length, int[][] queries) {
        int[] ans = new int[length];
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            int si = queries[i][0];
            int ei = queries[i][1];
            int inc = queries[i][2];

            ans[si] += inc;
            if (ei + 1 < length) {
                ans[ei + 1] -= inc;
            }
        }
        
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += ans[i];
            ans[i] = sum;
        }
        return ans;
    }

    // https://leetcode.com/problems/product-of-array-except-self/
    // Input: nums = [1,2,3,4]
    // Output: [24,12,8,6]
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int res[] = new int[n];

        int[] rightMul = new int[n];
        rightMul[n - 1] = nums[n - 1];
        for (int i = n - 2; n >= 0; i--) {
            rightMul[i] = rightMul[i - 1] * nums[i];
        }
        int lmult = 1;
        for (int i = 0; i < n - 1; i++) {
            res[i] = lmult * rightMul[i + 1];
            lmult = lmult * nums[i];
        }
        res[n - 1] = lmult;
        return res;
    }

    // https://leetcode.com/problems/maximize-distance-to-closest-person/
    // Input: seats = [1,0,0,0,1,0,1]
    // Output: 2
    // Explanation:
    // If Alex sits in the second open seat (i.e. seats[2]), then the closest person
    // has distance 2.
    // If Alex sits in any other open seat, the closest person has distance 1.
    // Thus, the maximum distance to the closest person is 2.
    public int maxDistToClosest(int[] seats) {
        int idx = Integer.MAX_VALUE;
        int person = 1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == person) {
                seats[i] = -person;
                idx = i;
            } else {
                seats[i] = Math.abs(idx - i);
            }
        }
        idx = Integer.MAX_VALUE;
        person = -1;
        int max = 0;
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == person) {
                idx = i;
            } else {
                seats[i] = Math.abs(idx - i);
                max = Math.max(max, seats[i]);
            }
        }
        return max;
    }

    // https://leetcode.com/problems/first-missing-positive/
    // Input: nums = [7,8,9,11,12]
    // Output: 1
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean isOnePresent = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
            if (nums[i] == 1) {
                isOnePresent = true;
            }
        }

        if (!isOnePresent) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            int idx = nums[i] - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // https://leetcode.com/problems/maximum-swap/
    // Example 1:
    // Input: num = 2736
    // Output: 7236
    // Explanation: Swap the number 2 and the number 7.
    public int maximumSwap(int a) {
        String str1 = Integer.toString(a);
        char[] nums = str1.toCharArray();
        int[] lastIndex = new int[10];
        for (int i = 0; i < nums.length; i++) {
            lastIndex[nums[i] - '0'] = i;
        }

        for (int i = 0; i < nums.length; i++) {
            int digit = nums[i] - '0';
            boolean flag = true;
            for (int j = 9; j > digit; j--) {
                if (lastIndex[j] > i) {
                    swap(nums, i, lastIndex[j]);
                    flag = false;
                    break;
                }
            }
            if (flag == false) {
                break;
            }
        }

        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num = num * 10;
            num = num + nums[i];
        }
        return num;
    }

    // https://www.pepcoding.com/resources/
    // data-structures-and-algorithms-in-java-levelup/arrays-and-strings/
    // 2-sum-target-sum-unique-pairs/ojquestion#!
    // Sample Input
    // 6
    // 10 10 30 40 50 20
    // 60
    // Sample Output
    // 10 50
    // 20 40
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                if (left != 0 && arr[left] == arr[left - 1]) {
                    left++;
                    continue;
                }
                List<Integer> subAns = new ArrayList<Integer>();
                subAns.add(arr[left]);
                subAns.add(arr[right]);
                ans.add(subAns);
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/3sum/
    // Example 1:
    // Input: nums = [-1,0,1,2,-1,-4]
    // Output: [[-1,-1,2],[-1,0,1]]
    private static List<List<Integer>> twoSum_(int[] arr, int target, int st) {
        int left = st;
        int right = arr.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        while (left < right) {
            // how to prevent repitition
            if (left != st && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> subres = new ArrayList<>();
                subres.add(arr[left]);
                subres.add(arr[right]);
                res.add(subres);
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums, int targ) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i <= n - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int val1 = nums[i];
            List<List<Integer>> subres = twoSum_(nums, targ - val1, i + 1);
            for (List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    // https://leetcode.com/problems/4sum/
    // Input: nums = [1,0,-1,0,-2,2], target = 0
    // Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    private static List<List<Integer>> TwoSum_(int[] arr, int target, int st) {
        int left = st;
        int right = arr.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        while (left < right) {
            // how to prevent repitition
            if (left != st && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> subres = new ArrayList<>();
                subres.add(arr[left]);
                subres.add(arr[right]);
                res.add(subres);
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum_(int[] nums, int targ, int sidx) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = sidx; i <= n - 3; i++) {
            if (i != sidx && nums[i] == nums[i - 1]) {
                continue;
            }

            int val1 = nums[i];
            List<List<Integer>> subres = TwoSum_(nums, targ - val1, i + 1);
            for (List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i <= n - 4; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int val1 = nums[i];
            List<List<Integer>> subList = threeSum_(nums, target - val1, i + 1);
            for (List<Integer> list : subList) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    // https://leetcode.com/problems/complex-number-multiplication/
    // Example 1:
    // Input: num1 = "1+1i", num2 = "1+1i"
    // Output: "0+2i"
    // Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it
    // to the form of 0+2i.
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf('+')));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf('+') + 1, num1.length() - 1));

        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf('+')));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf('+') + 1, num2.length() - 1));

        int a = a1 * a2 - b1 * b2;
        int b = a1 * b2 + a2 * b1;
        return "" + a + "+" + b + "i";

    }

    // https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
    // Example 1:

    // Input: n = 6
    // arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
    // dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
    // Output: 3
    // Explanation:
    // Minimum 3 platforms are required to
    // safely arrive and depart all trains.

    static int findPlatform(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int trains = 0;
        int minPlatform = 0;
        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                trains++;
                i++;
            } else {
                trains--;
                j++;
            }
            minPlatform = Math.max(minPlatform, trains);
        }
        return minPlatform;
    }

    // https://practice.geeksforgeeks.org/problems/sieve-of-eratosthenes5242/1
    // Input:
    // N = 10
    // Output:
    // 2 3 5 7
    // Explanation:
    // Prime numbers less than equal to N
    // are 2 3 5 and 7.
    static ArrayList<Integer> sieveOfEratosthenes(int N) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true); // this line is optional

        for (int i = 2; i * i < N; i++) {
            if (isPrime[i] == false)
                continue;
            for (int j = i + i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        // which has true value then that is prime number
        for (int i = 2; i < N; i++) {
            if (isPrime[i])
                ans.add(i);
        }

        return ans;
    }

    // c
    // Input:
    // L = 6, N = 78
    // arr[] = {5, 20, 3, 2, 5, 80}
    // Output: 1
    // Explanation: (2, 80) have difference of 78.

    public boolean findPair(int arr[], int size, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = 1;
        boolean isPair = false;
        while (right < size || left == right) {
            int diff = arr[right] - arr[left];
            if (diff == target) {
                isPair = true;
                break;
            } else if (diff < target) {
                right++;
            } else {
                left--;
            }
        }

        return isPair;
    }

    // https://leetcode.com/problems/boats-to-save-people/
    // Input: people = [3,2,2,1], limit = 3
    // Output: 3
    // Explanation: 3 boats (1, 2), (2) and (3)

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int right = people.length;
        int left = 0;
        int boats = 0;
        while (left <= right) {
            if (left + right <= limit) {
                boats++;
                left++;
                right--;
            } else {
                boats++;
                right--;
            }
        }

        return boats;
    }

    // https://leetcode.com/problems/reach-a-number/
    // Input: target = 2
    // Output: 3
    // Explanation:
    // On the 1st move, we step from 0 to 1 (1 step).
    // On the 2nd move, we step from 1 to -1 (2 steps).
    // On the 3rd move, we step from -1 to 2 (3 steps).

    public int reachNumber(int target) {
        target = Math.abs(target);
        int sum = 0;
        int jump = 0;

        while (sum < target) {
            jump++;
            sum += jump;
        }

        if (sum == target) {
            return jump;
        } else if ((sum - target) % 2 == 0) {
            return jump;
        } else if ((sum + jump + 1) - target % 2 == 0) {
            return jump + 1;
        } else {
            return jump + 2;
        }
    }

    // https://leetcode.com/problems/partition-labels/
    // Input: s = "ababcbacadefegdehijhklij"
    // Output: [9,7,8]
    // Explanation:
    // The partition is "ababcbaca", "defegde", "hijhklij".
    // This is a partition so that each letter appears in at most one part.
    // A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it
    // splits s into less parts.

    public List<Integer> partitionLabels(String s) {
        int len = s.length();
        HashMap<Character, Integer> hm = new HashMap<>();

        // adding last impact of character in index
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            hm.put(ch, i);
        }

        List<Integer> res = new ArrayList<>();
        int prev = -1;
        int max = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            max = Math.max(max, hm.get(ch));
            if (i == max) { // here a partition can be done
                res.add(max - prev);
                prev = max;
            }
        }
        return res;
    }

    // https://leetcode.com/problems/transpose-matrix/
    // transpose of matrix
    // Input: matrix = [[1,2,3],
    // [4,5,6],
    // [7,8,9]]

    // Output: [[1,4,7],
    // [2,5,8],
    // [3,6,9]]

    public int[][] transpose(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] ans = new int[c][r];

        for (int i = c - 1; i >= 0; i--) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = matrix[j][i];
            }
        }

        return ans;
    }

    // https://leetcode.com/problems/push-dominoes/
    private static void solveConfiguration(char[] arr, int i, int j) {
        if (arr[i] == 'L' && arr[j] == 'L') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'L';
        } else if (arr[i] == 'R' && arr[j] == 'R') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'R';
        } else if (arr[i] == 'L' && arr[j] == 'R') {
            // nothing to do
        } else {
            int diff = j - i;
            if (diff % 2 == 0) {
                int mid = (i + j) / 2;
                for (int k = i + 1; k < mid; k++)
                    arr[k] = 'R';

                for (int k = mid + 1; k < j; k++)
                    arr[k] = 'L';

            } else {
                int mid = (i + j) / 2;
                for (int k = i + 1; k <= mid; k++)
                    arr[k] = 'R';

                for (int k = mid + 1; k < j; k++)
                    arr[k] = 'L';

            }
        }
    }

    public String pushDominoes(String str) {
        char[] arr = new char[str.length() + 2];
        arr[0] = 'L';
        arr[arr.length - 1] = 'R';
        for (int i = 1; i < arr.length - 1; i++) {
            arr[i] = str.charAt(i - 1);
        }
        int j = 0, k = 1;
        while (k < arr.length) {
            while (arr[k] == '.') {
                k++;
            }
            if (k - j > 1)
                solveConfiguration(arr, j, k);
            j = k;
            k++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < arr.length - 1; i++) {
            res.append(arr[i]);
        }
        return res.toString();
    }

    // https://leetcode.com/problems/consecutive-numbers-sum/
    // Input: n = 15
    // Output: 4
    // Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        for (int k = 1; 2 * n > k * (k - 1); k++) {
            int numerator = (n - (k * (k - 1)) / 2);
            if (numerator % k == 0)
                count++;
        }
        return count;
    }

    // https://leetcode.com/problems/add-strings/
    // Example 1:
    // Input: num1 = "11", num2 = "123"
    // Output: "134"

    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int n1 = 0;
            int n2 = 0;

            if (i >= 0) {
                n1 = num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                n2 = num2.charAt(j) - '0';
                j--;
            }

            int num = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) >= 10 ? 1 : 0;
            sb.append(num);

        }

        return sb.reverse().toString();

    }

    // https://leetcode.com/problems/multiply-strings/
    // Input: num1 = "123", num2 = "456"
    // Output: "56088"
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "" + 0;

        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1 + l2];
        int i = l2 - 1;
        int pf = 0; // power facter

        while (i >= 0) {
            int ival = num2.charAt(i) - '0';
            i--; //

            int j = l1 - 1;
            int k = res.length - 1 - pf;
            int c = 0;
            while (j >= 0 || c != 0) {
                int jval = j >= 0 ? num1.charAt(j) - '0' : 0;
                j--;
                int prod = ival * jval + c + res[k];
                res[k] = prod % 10;
                c = prod / 10;
                k--;
            }
            pf++;
        }
        StringBuilder str = new StringBuilder();
        boolean flag = false;
        for (int val : res) {
            if (val == 0 && flag == false) {
                continue;
            } else {
                flag = true;
                str.append(val);
            }
        }

        return res.toString();
    }

    // https://leetcode.com/problems/trapping-rain-water/[not submitted]
    public static void nextGreaterElement(int[] arr, int[] nge) {
        int n = arr.length;
        Stack<Integer> stk = new Stack<>();
        stk.push(arr.length);
        nge[n - 1] = n;

        for (int i = n - 2; i >= 0; i++) {
            while (stk.size() > 0 && arr[i] > arr[stk.peek()]) {
                stk.pop();
            }

            if (stk.size() == 0) {
                nge[i] = n;
            } else {
                nge[i] = stk.peek();
            }
            stk.push(i);
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] nge = new int[n];
        nextGreaterElement(nums, nge);

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int j = i;
            if (nge[j] < i + k) {
                j = nge[j];
            }
            ans[i] = nums[j];
        }
        return ans;
    }

    // https://www.lintcode.com/problem/920/
    // Meeting room
    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (Interval a, Interval b) -> {
            return a.start - b.start;
        });

        int end = intervals.get(0).end;
        for(int i = 0; i < intervals.size(); i++) {
            if(end > intervals.get(i).start) {
                return false;
            } else {
                end = intervals.get(i).end;
            }
        }
        return true;
    }

    //https://leetcode.com/problems/merge-intervals/
    // Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    // Output: [[1,6],[8,10],[15,18]]
    // Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
    public int[][] merge(int[][] intervals) {
        int size = intervals.length;
        Arrays.sort(intervals,(val1, val2) -> Integer.compare(val1[0], val2[0]));
        ArrayList<int[]> res = new ArrayList<>();
        int lsp = intervals[0][0];
        int lep = intervals[0][1];

        for(int i = 1; i < size; i++) {
            int sp = intervals[i][0];
            int ep = intervals[i][1];

            if(sp > lep) {
                int[] interval = {lsp, lep};
                res.add(interval);
            } else if(sp < lep) {
                lep = ep;
            } else {
                //fully overlapped so do nothing
            }
        }
        int[] interval = {lsp, lep};
        res.add(interval);

        return res.toArray(new int[res.size()][]);
    }

    //https://leetcode.com/problems/interval-list-intersections/
    // Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
    // Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0; //point firstList
        int j = 0; //point SecondList
        int n = firstList.length;
        int m = firstList.length;

        while(i < n && j < n) {
            int sp = Math.max(firstList[i][0], secondList[j][0]);
            int ep = Math.min(firstList[i][1], secondList[j][1]);

            if(sp <= ep) {
                int[] interval = {sp, ep};
                list.add(interval);
            }

            if(firstList[i][1] < firstList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }


    public static void main(String[] args) {

    }
}
