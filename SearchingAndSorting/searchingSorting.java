import java.util.*;

import javax.lang.model.util.ElementScanner14;
package SearchingAndSorting;

public class searchingSorting {

    // https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1
    // You are provided with marks of N students in Physics, Chemistry and Maths.
    // Perform the following 3 operations:

    // Sort the students in Ascending order of their Physics marks.
    // Once this is done, sort the students having same marks in Physics in the
    // descending order of their Chemistry marks.
    // Once this is also done, sort the students having same marks in Physics and
    // Chemistry in the ascending order of their Maths marks.

    class Marks implements Comparable<Marks> {
        int phy, chem, math;

        Marks(int phy, int chem, int math) {
            this.phy = phy;
            this.chem = chem;
            this.math = math;
        }

        public int compareTo(Marks other) {
            if (this.phy != other.phy)
                return this.phy - other.phy;
            else if (this.chem != other.chem)
                return this.chem - other.chem;
            else
                return this.math - other.math;
        }
    }

    public void customSort(int phy[], int chem[], int math[], int N) {
        int n = phy.length;
        Marks[] arr = new Marks[N];
        for (int i = 0; i < n; i++) {
            arr[i] = new Marks(phy[i], chem[i], math[i]);
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            phy[i] = arr[i].phy;
            chem[i] = arr[i].chem;
            math[i] = arr[i].math;
        }
    }

    // 2.
    // https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
    // Input:
    // n = 5, arr1[] = {1, 2, 3, 4, 5}
    // m = 3, arr2 [] = {1, 2, 3}
    // Output: 1 2 3 4 5
    // Explanation: Distinct elements including
    // both the arrays are: 1 2 3 4 5.

    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        if (arr1[0] < arr2[0])
            ans.add(arr1[0]);
        else
            ans.add(arr2[0]);

        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                if (ans.get(ans.size() - 1) != arr1[i])
                    ans.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                if (ans.get(ans.size() - 1) != arr1[i])
                    ans.add(arr1[i]);
                i++;
            } else {
                if (ans.get(ans.size() - 1) != arr2[j])
                    ans.add(arr2[j]);
                j++;
            }
        }

        while (i < n) {
            if (ans.get(ans.size() - 1) != arr1[i])
                ans.add(arr1[i]);
            i++;
        }
        while (j < m) {
            if (ans.get(ans.size() - 1) != arr2[j])
                ans.add(arr1[j]);
            j++;
        }
        return ans;
    }

    // 3.
    // Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    // Output: true
    public static int findEffectiveRow(int[][] arr, int target) {
        int cols = arr[0].length - 1;
        int low = 0;
        int heigh = arr.length - 1;
        while (low <= heigh) {
            int mid = low + (heigh - low) / 2;
            if (arr[mid][0] <= target && arr[mid][cols] >= target) {
                return mid;
            } else if (arr[mid][0] < target) {
                low = mid + 1;
            } else {
                heigh = mid - 1;
            }
        }
        return -1;
    }

    public static boolean binarySearch(int[][] arr, int tar, int row) {
        int cols = arr[0].length;
        int low = 0;
        int heigh = cols - 1;
        while (low <= heigh) {
            int mid = low + (heigh - low) / 2;
            if (arr[row][mid] == tar) {
                return true;
            } else if (arr[row][mid] < tar) {
                low = mid + 1;
            } else {
                heigh = mid - 1;
            }
        }
        return false;
    }

    public static boolean search(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix.length;
        int row = findEffectiveRow(matrix, target);
        if (row == -1)
            return false;
        return binarySearch(matrix, target, row);
    }

    // 4.
    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    //5. 
    //https://leetcode.com/problems/find-pivot-index/
    // Input: nums = [1,7,3,6,5,6]
    // Output: 3
    // Explanation:
    // The pivot index is 3.
    // Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
    // Right sum = nums[4] + nums[5] = 5 + 6 = 11
    public int pivotIndex(int[] nums) {
        int sum = 0;
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            sum = sum + nums[i];
        }
        int lsum = 0, rsum = sum;

        for(int idx = 0; idx < len; idx++) {
            rsum = rsum - nums[idx];
            if(lsum == rsum) return idx;
            lsum = lsum + nums[idx];
        }

        return -1;
    }

    // 6.
    //658. Find K Closest Elements
    //https://leetcode.com/problems/find-k-closest-elements/
    // Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
    // An integer a is closer to x than an integer b if:
    // |a - x| < |b - x|, or
    // |a - x| == |b - x| and a < b

    public class Pair implements Comparable<Pair> {
        int val, gap;
        Pair(int val, int gap) {
            this.val = val;
            this.gap = gap;
        }

        public int compareTo(Pair other) {
            if(this.gap == other.gap) {
                return this.val - other.val;
            } else {
                return this.gap - other.gap;
            }
        }
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(Collections.reverseOrder());
        int n = arr.length;
        for(int idx = 0; idx < n; idx++) {
            if(idx < k) {
                pq.add(new Pair(arr[idx], Math.abs(x - arr[idx])));
            } else {
                if(pq.peek().gap > (Math.abs(x - arr[idx]))) {
                    pq.remove();
                    pq.add(new Pair(arr[idx], Math.abs(x - arr[idx])));
                }
            }
        }

        while(pq.size() > 0) {
            result.add(pq.remove().val);
        }
        Collections.sort(result);
        return result;
    }
    

    //7. Roof Top 
    //https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1
    // Input:
    // N = 4
    // A[] = {1,2,3,4}
    // Output: 3
    // Explanation: 1 to 2 to 3 to 4 is the jump of
    // length 3 to have maximum number of 
    // buildings with increasing heights.

    static int maxStep(int arr[], int n) {
        int maxStep = 0;
        int idx = 0;
        while(idx < n - 1) {
            int count = 0;
            while(idx < n - 1 && arr[idx] < arr[idx + 1]) {
                count++;
                idx++;
            }
            maxStep = Math.max(maxStep, count);
        }
        return maxStep;
    }
    

    // 8. Maximize sum(arr[i]*i) of an Array 
    // https://practice.geeksforgeeks.org/problems/maximize-arrii-of-an-array0026/1#
    // Input : Arr[] = {5, 3, 2, 4, 1}
    // Output : 40
    // Explanation:
    // If we arrange the array as 1 2 3 4 5 then 
    // we can see that the minimum index will multiply
    // with minimum number and maximum index will 
    // multiply with maximum number. 
    // So 1*0+2*1+3*2+4*3+5*4=0+2+6+12+20 = 40 mod(109+7) = 40
    int Maximize(int arr[], int n) {   
        long mod = 1000000007;
        Arrays.sort(arr);
        long sum = 0;
        for(int i = 0; i < n; i++) {
            long mult = ((arr[i] % mod) * (i % mod)) % mod;
            sum = ((sum % mod) + (mult % mod)) % mod;
        }
        return (int) (sum % mod);
    } 
    
    //9. 
    //First and last occurrences of x 
    // Input:
    // n=9, x=5
    // arr[] = { 1, 3, 5, 5, 5, 5, 67, 123, 125 }
    // Output:  2 5
    // Explanation: First occurrence of 5 is at index 2 and last
    //             occurrence of 5 is at index 5. 
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] firstLast = new int[2];
        firstLast[0] = -1;
        firstLast[1] = -1;
        if(nums.length == 0) {
            return firstLast;
        }
        
        //First Binary search for finding first position 
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                firstLast[0] = mid;
                right = mid - 1;
            } else if(target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Second binary search for finding last position
        left = 0;
        right = nums.length - 1;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                firstLast[1] = mid;
                left = mid + 1;
            } else if(target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return firstLast;
    }
    
    //10.
    //https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    //Input:
    // N = 4
    // A[] = {8,3,1,2}
    // Output: 29
    // Explanation: Above the configuration
    // possible by rotating elements are
    // 3 1 2 8 here sum is 3*0+1*1+2*2+8*3 = 29
    // 1 2 8 3 here sum is 1*0+2*1+8*2+3*3 = 27
    // 2 8 3 1 here sum is 2*0+8*1+3*2+1*3 = 17
    // 8 3 1 2 here sum is 8*0+3*1+1*2+2*3 = 11
    // Here the max sum is 29 

    int max_sum(int arr[], int n) {
        int s0 = 0;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            s0 += arr[i] * i;
            sum += arr[i];
        }
        int si = s0;
        int maxSum = s0;
        for(int i = 0; i < n - 1; i++) {
            int siOne = si + sum - (n * arr[n - i - 1]);
            maxSum = Math.max(maxSum, siOne);
            si = siOne;
        }
        return maxSum;
    }

    //11
    //Searching in rotated sorted array
    //https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int low = 0;
        int heigh = nums.length - 1;
        while(low <= heigh) {
            int mid = low + (heigh - low) / 2;
            if(target == mid) {
                return mid;
            } else if(nums[low] <= nums[mid]) {
                //left part is sorted;
                if(target >= nums[low] && target <= nums[mid]) 
                    heigh = mid - 1;
                else
                    low = mid + 1;    
                
            } else if(nums[mid] <= nums[heigh]) {
                if(target >= nums[mid] && target <= nums[heigh]) {
                    low = mid + 1;
                } else {
                    heigh = mid - 1;
                }
            }
        }
        return -1;
    }

    //12
    //Find Minimum in Rotated Sorted Array
    // Input: nums = [3,4,5,1,2]
    // Output: 1
    // Explanation: The original array was [1,2,3,4,5] rotated 3 times.
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        if(nums[lo] <= nums[hi]) return nums[0];
        int res = 0;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(nums[mid] > nums[mid + 1]) {
                res =  nums[mid + 1];
                break;
            } else if(nums[mid] < nums[mid - 1]) {
                //if this line will written first then it will give error
                res = nums[mid];
                break;
            //Search only unsorted part of the array        
            } else if(nums[lo] <= nums[mid]) {
                lo = mid + 1;
            } else if(nums[mid] <= nums[hi]) {
                hi = mid - 1;
            }
        }
        return res;
    }

    //12
    //find rotation count gfg
    // Input:
    // N = 5
    // Arr[] = {5, 1, 2, 3, 4}
    // Output: 1
    // Explanation: The given array is 5 1 2 3 4. 
    // The original sorted array is 1 2 3 4 5. 
    // We can see that the array was rotated 
    // 1 times to the right.
    int findKRotation(int nums[], int n) {
        int lo = 0;
        int hi = nums.length - 1;
        if(nums[lo] <= nums[hi]) return 0;
        int res = 0;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(nums[mid] > nums[mid + 1]) {
                res =  mid + 1;
                break;
            } else if(nums[mid] < nums[mid - 1]) {
                //if this line will written first then it will give error
                res = mid;
                break;
            } else if(nums[lo] <= nums[mid]) {
                lo = mid + 1;
            } else if(nums[mid] <= nums[hi]) {
                hi = mid - 1;
            }
        }
        return res;
	}


    //12
    //https://leetcode.com/problems/median-of-two-sorted-arrays/
    // Input: nums1 = [1,2], nums2 = [3,4]
    // Output: 2.50000
    // Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
    public void mergeArray(int[] arr1, int[] arr2, int[] merged) {
        int i = 0, j = 0, k = 0;
        int arr1Len = arr1.length;
        int arr2Len = arr2.length;
        while(i < arr1Len && j < arr2Len) {
                if(arr1[i] < arr2[j]) {
                    merged[k] = arr1[i];
                    i++;
                } else {
                    merged[k] = arr2[j];
                    i++;
                }
                k++;
        }
        while(i < arr1Len) {
            merged[k++] = arr1[i++];
        }
        while(j < arr2Len) {
            merged[k++] = arr2[j++];
        }

    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        int len = nums1Len + nums2Len;
        int[] merged = new int[len]; 
        mergeArray(nums1, nums2, merged);
        double median = 0.0; 
        int mid = len / 2;
        if(len % 2 == 1) {
            median = merged[mid] * 1.0;
        } else {
            median = ( (merged[mid] + merged[mid - 1]) * 1.0 ) / 2;
        }
        return median;
    }


    //13 
    // Koko eat banana 
    // Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
    // Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
    // Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
    // Return the minimum integer k such that she can eat all the bananas within h hours.
    // Example 1:
    // Input: piles = [3,6,7,11], h = 8
    // Output: 4
    public boolean isPossibleToEat(int[] piles, int speed, int hours) {
        int totalHours = 0;
        for(int bananas : piles) {
            totalHours += (int)Math.ceil((bananas * 1.0) / speed);
        }
        return hours >= totalHours;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int len = piles.length;
        int max = Integer.MIN_VALUE;
        for(int num : piles)
            max = Math.max(num, max);
        if(len == h) return max;    
        int lowSpeed = 0, highSpeed = max, speed = max; 
        while(lowSpeed <= highSpeed) {
            int midSpeed = lowSpeed + (highSpeed - lowSpeed) / 2;
            if(isPossibleToEat(piles, midSpeed, h)) {
                speed = midSpeed;
                highSpeed = midSpeed - 1;
            } else {
                lowSpeed = midSpeed + 1;
            }
        }   
        return speed;
    }

    //14. chocalate distribution
    public static int find(int[]arr, int n, int m) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        
        for(int i = 0; i < n - m; i++) {
            int minChoclate = arr[i];
            int maxChoclate = arr[i + m - 1];
            int diff = maxChoclate - minChoclate;
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    //15
    //Count the triplets 
    //https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1
    // Input:
    // N = 4
    // arr[] = {1, 5, 3, 2}
    // Output: 2
    // Explanation: There are 2 triplets: 
    // 1 + 2 = 3 and 3 +2 = 5 
    public int isSumPresent(int[] arr, int tar, int left, int right) {
        int count = 0;
        while(left < right) {
            int sum = arr[left] + arr[right];
            if(sum == tar) {
                count++;
                left++;
                right--;
            } else if(sum < tar) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public int countTriplet(int arr[], int n) {
        Arrays.sort(arr);
	    int count = 0;
        for(int i = n - 1; i >= 2; i--) {
            int tar = arr[i];
            int right = i - 1;
            int left = 0;
            count += isSumPresent(arr, tar, left, right);
        }
        return count;
	}
    

    //15
    //Count the number of possible triangles
    //https://practice.geeksforgeeks.org/problems/count-possible-triangles-1587115620/1
    // Input: 
    // n = 3
    // arr[] = {3, 5, 4}
    // Output: 
    // 1
    // Explanation: 
    // A triangle is possible 
    // with all the elements 5, 3 and 4.
    public static int findTriangles(int[] arr, int tar, int left, int right) {
        int count = 0;
        while(left < right) {
            int sum = arr[left] + arr[right];
            if(sum > tar) {
                count += (right - left);
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
    static int findNumberOfTriangles(int arr[], int n) {
        Arrays.sort(arr);
        int triangles = 0;
        for(int i = n - 1; i >= 2; i--) {
            int tar = arr[i];
            int left = 0;
            int right = i - 1;
            triangles += findTriangles(arr, tar, left, right);
        }
        return triangles;
    }

    //16
    //https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
    //Minimize Maximum Pair Sum in Array
    // Input: nums = [3,5,2,3]
    // Output: 7
    // Explanation: The elements can be paired up into pairs (3,3) and (5,2).
    // The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int maxPairSum = Integer.MIN_VALUE; 
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum > maxPairSum) {
                maxPairSum = sum;
            }
            left++;
            right--;
        }
        return maxPairSum;
    }


    //17
    //https://practice.geeksforgeeks.org/problems/counting-elements-in-two-arrays/1
    // Input:
    // m = 6, n = 6
    // arr1[] = {1,2,3,4,7,9}
    // arr2[] = {0,1,2,1,1,4}
    // Output: 4 5 5 6 6 6
    // Explanation: Number of elements less than
    // or equal to 1, 2, 3, 4, 7, and 9 in the
    // second array are respectively 4,5,5,6,6,6
    public static int findLessNums(int[] arr, int tar) {
        int left = 0;
        int right = arr.length - 1;
        int lessNums = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] <= tar) {
                lessNums += (mid - left) + 1;
                left = mid + 1; 
            } else {
                right = mid - 1;
            }
        }
        return lessNums;
    }
    public static ArrayList<Integer> countEleLessThanOrEqual(int arr1[], int arr2[], int m, int n) {
       Arrays.sort(arr2);
       ArrayList<Integer> ans = new ArrayList<Integer>();
       for(int i = 0; i < m; i++) {
           ans.add(findLessNums(arr2, arr1[i]));
       }
       return ans;
    }

    //18
    //https://practice.geeksforgeeks.org/problems/counts-zeros-xor-pairs0349/1
    //Input : arr[ ] = {1, 3, 4, 1, 4}
    // Output : 2
    // Explanation:
    // Index( 0, 3 ) and (2 , 4 ) are only pairs 
    // whose xors is zero so count is 2.
    public static long calculate (int arr[], int n) {
       int max = Integer.MIN_VALUE;
       for(int i = 0; i < n; i++) {
           max = Math.max(max, arr[i]);
       }
       int[] freqArr = new int[max + 1];
       for(int i = 0; i < n; i++) {
           freqArr[i]++;
       }
       int count =  0;
       for(int i = 0; i < max + 1; i++) {
            int num = freqArr[i];
            count += ((num - 1) * num) / 2;
       }
       return count;
    }

    //19
    //https://practice.geeksforgeeks.org/problems/row-with-max-1s0023/1#
    // Input: 
    // N = 4 , M = 4
    // Arr[][] = {{0, 1, 1, 1},
    //           {0, 0, 1, 1},
    //           {1, 1, 1, 1},
    //           {0, 0, 0, 0}}
    // Output: 2
    // Explanation: Row 2 contains 4 1's (0-based
    // indexing).
    int rowWithMax1s(int arr[][], int n, int m) {
        int row = 0;
        int cols = m - 1;
        int max1 = 0;
        int ans = -1;
        while(row < n && cols >= 0) {
            if(arr[row][cols] == 1) {
                //(m - cols) is number of 1
                if(m - cols > max1) {
                    ans = row;
                }
                cols--;
            } else {
                row++;
            }
            if(cols < 0) {
                ans = row;
            }
        }
        return ans;
    }
    

   //20
   //https://practice.geeksforgeeks.org/
   //problems/inversion-of-array-1587115620/1
    // Input: N = 5, arr[] = {2, 4, 1, 3, 5}
    // Output: 3
    // Explanation: The sequence 2, 4, 1, 3, 5 
    // has three inversions (2, 1), (4, 1), (4, 3).
    static long count = 0;
    static long[] mergeTwo(long[] left, long[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        long[] mergeArray = new long[leftLength + rightLength];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < leftLength && j < rightLength) {
            if(left[i] < right[j]) {
                mergeArray[k] = left[i];
                i++;
                k++;
            } else {
                count += (leftLength - i);
                mergeArray[k] = right[j];
                k++;
                j++;
            }
        }
        while(i < leftLength) {
            mergeArray[k++] = left[i++];
        }
        while(j < rightLength) {
            mergeArray[k++] = right[j++];
        }
        return mergeArray;
    }
    static long[] mergeSort(long[] arr, int left, int right) {
        if(left == right) {
            long[] baseArray = new long[1];
            baseArray[0] = arr[left];
            return baseArray;
        }
        int mid = left + (right - left) / 2;
        long[] leftArray = mergeSort(arr, left, mid);
        long[] rightArray = mergeSort(arr, mid + 1, right);
        long[] merge = mergeTwo(leftArray, rightArray);
        return merge;
    }
    static long inversionCount(long arr[], long N) {
        int left = 0;
        int right = arr.length - 1;
        mergeSort(arr, left, right);
        return count;
    }

    //https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
    //Pairs of Songs With Total Durations Divisible by 60
    // Input: time = [30,20,150,100,40]
    // Output: 3
    // Explanation: Three pairs have a total duration divisible by 60:
    // (time[0] = 30, time[2] = 150): total duration 180
    // (time[1] = 20, time[3] = 100): total duration 120
    // (time[1] = 20, time[4] = 40): total duration 60
    public int countPair(int[] left, int[] right) {
        int leftLen = left.length;
        int rightLen = right.length;
        for(int i = 0; i < leftLen; i++) {
            for(int j = 0; j < rightLen; j++) {
                if((left[i] + right[j]) % 60 == 0) {
                    count++;
                }
            }
        }
    }
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        mergeSort(time, 0, n - 1);
    }
    public static void main(String[] args) {
        System.out.println("manish");
    }

}
