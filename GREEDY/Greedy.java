public class Greedy {

    // 1. N meeting in one room
    // Input:
    // N = 6
    // start[] = {1,3,0,5,8,5}
    // end[] = {2,4,6,7,9,9}
    // Output:
    // 4
    // Explanation:
    // Maximum four meetings can be held with
    // given start and end timings.
    // The meetings are - (1, 2),(3, 4), (5,7) and (8,9)

    public static class Pair implements Comparable<Pair> {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Pair o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static int maxMeetings(int start[], int end[], int n) {
        Pair[] interval = new Pair[n];

        for (int i = 0; i < n; i++) {
            Pair pair = new Pair(start[i], end[i]);
            // pair.start = start[i];
            // pair.end = end[i];
            interval[i] = pair;
        }
        Arrays.sort(interval);
        int count = 1;
        int i = 0;
        int j = 1;

        while (j < n) {
            if (interval[i].end < interval[j].start) {
                count++;
                i = j;
                j++;
            } else {
                j++;
            }
        }

        return count;
    }

    // 2.
    // Input:
    // N = 5
    // Jobs = {(1,2,100),(2,1,19),(3,2,27),
    // (4,1,25),(5,1,15)}
    // Output:
    // 2 127
    // Explanation:
    // 2 jobs can be done with
    // maximum profit of 127 (100+27).

    class JobComparator implements Comparator<Job> {
        public int compare(Job job1, Job job2) {
            return job2.profit - job1.profit;
        }
    }

    int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, new JobComparator());
        // for(Job job : arr) {
        // System.out.println(job.profit + " ");
        // }
        boolean[] isDone = new boolean[n];
        int jobDone = 0, profit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(arr[i].deadline, n); j > 0; j--) {
                if (isDone[j - 1] == false) {
                    isDone[j - 1] = true;
                    jobDone++;
                    profit += arr[i].profit;
                    break;
                }
            }
        }
        int[] ans = { jobDone, profit };
        return ans;
    }

    // 3. Convert

    // 4. Choose and Swap

    // Input: str = "ccad"
    // Output: "aacd"
    // Explanation: In ccad, we choose ‘a’ and ‘c’
    // and after doing the replacement operation
    // once we get, aacd and this is the
    // lexicographically smallest string possible.

    public String LexicographicallyMinimum(String str1) {
        char[] str = str1.toCharArray();
        int n = str.length;
        int[] fIdx = new int[26];

        // place -1 at every index
        for (int i = 0; i < 26; i++) {
            fIdx[i] = -1;
        }

        // storing first index of every character of string
        for (int i = 0; i < n; i++) {
            if (fIdx[str[i] - 'a'] == -1) {
                fIdx[str[i] - 'a'] = i;
            }
        }
        // System.out.println(str[0] + " " + fIdx[str[0] - 'a']);
        int idx = 0;
        int j = 0;

        for (idx = 0; idx < n; idx++) {
            boolean flag = false;
            char ch = str[idx];
            // System.out.println(ch);

            for (j = 0; j < ch - 'a'; j++) {
                if (fIdx[j] > fIdx[ch - 'a']) {
                    // System.out.println(fIdx[ch - 'a']);
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        // if ch is not at last index then only we swap
        if (idx < n - 1) {
            char ch1 = str[idx];
            char ch2 = (char) (j + 'a');
            for (int i = 0; i < n; i++) {
                // System.out.println("run");
                if (str[i] == ch1)
                    str[i] = ch2;
                else if (str[i] == ch2)
                    str[i] = ch1;
            }
        }
        // for(int i = 0; i < n; i++) System.out.println(str[i]);
        return String.valueOf(str);
    }

    // 5. Buy Maximum Stocks if i stocks can be bought on i-th day
    // Input:
    // price[] = { 10, 7, 19 }
    // k = 45
    // Output: 4
    // Explanation: A customer purchases 1 stock on day 1,
    // 2 stocks on day 2 and 1 stock on day 3 for
    // 10, 7 * 2 = 14 and 19 respectively. Hence,
    // total amount is 10 + 14 + 19 = 43 and number
    // of stocks purchased is 4.

    public static class Pair implements Comparable<Pair> {
        int price;
        int maxStock;

        public Pair(int price, int maxStock) {
            this.price = price;
            this.maxStock = maxStock;
        }

        public int compareTo(Pair o) {
            // if(this.price != o.price)
            return this.price - o.price;
            // return o.maxStock - this.maxStock;
        }
    }

    public static int buyMaximumProducts(int n, int k, int[] price) {
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            Pair pair = new Pair(price[i], i + 1);
            pairs[i] = pair;
        }
        Arrays.sort(pairs);
        int remMoney = k;
        int stocks = 0;
        for (int i = 0; i < n; i++) {
            int stockPrice = pairs[i].price;
            int maxStock = pairs[i].maxStock;

            if (stockPrice * maxStock <= remMoney) {
                stocks += maxStock;
                remMoney = remMoney - (stockPrice * maxStock);
            } else {
                stocks += remMoney / stockPrice;
                remMoney = remMoney - (stockPrice * (remMoney / stockPrice));
            }
        }

        return stocks;
    }

    // 6
    // Shop in Candy Store
    // min and max price to buy all Candy
    // Input:
    // N = 4
    // K = 2
    // candies[] = {3 2 1 4}

    // Output:
    // 3 7

    public static void reverse(int[] array) {
        // System.out.println("Array = " + Arrays.toString(array));
        int maxIndex = array.length - 1;
        int halfLength = array.length / 2;
        for (int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
        // System.out.println("Reversed array = " + Arrays.toString(array));
    }

    static ArrayList<Integer> candyStore(int candies[], int N, int k) {
        Arrays.sort(candies);
        int minPrice = 0;
        int maxPrice = 0;

        int i = 0;
        int j = N - 1;

        while (i <= j) {
            minPrice += candies[i];
            j = j - k;
            i++;
        }

        reverse(candies);

        i = 0;
        j = N - 1;

        while (i <= j) {
            maxPrice += candies[i];
            j = j - k;
            i++;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(minPrice);
        list.add(maxPrice);
        return list;
    }

    // 7
    // Check if it is possible to survive on Island
    // Input: S = 10, N = 16, M = 2
    // Output: 2
    // Explaination: One possible solution is to
    // buy a box on the first day (Monday),
    // it’s sufficient to eat from this box up to
    // 8th day (Monday) inclusive. Now, on the 9th
    // day (Tuesday), you buy another box and use
    // the chocolates in it to survive the 9th and
    // 10th day.

    static int minimumDays(int S, int N, int M) {
        int buyableDay = S - (S / 7);
        int totalUnitNeed = S * M;
        int daysToBuy = totalUnitNeed / N;
        if (totalUnitNeed % N != 0)
            daysToBuy++;

        return daysToBuy <= buyableDay ? daysToBuy : -1;
    }

    // 8
    // Input:
    // A[] = {-1, -1, -2, 4, 3}
    // Output: 24
    // Explanation: Maximum product will be ( -2 * -1 * 4 * 3 ) = 24

    // tip - take max product as long and then type cast to int

    public static int findMaxProduct(int n, int[] arr) {
        int mod = 1000000007;
        if (n == 1)
            return arr[0];
        long max = 1;
        int max_negative = Integer.MIN_VALUE;
        int count_neg = 0;
        int count_zero = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                count_zero++;
                continue;
            } else if (arr[i] < 0) {
                max_negative = Math.max(max_negative, arr[i]);
                count_neg++;
            }
            max = (max * arr[i]) % mod;
        }
        if (count_zero == n)
            return 0;
        if (count_neg == 1 && count_zero + count_neg == n)
            return 0;
        if (count_neg % 2 == 1)
            max = max / max_negative;
        return (int) max;
    }

    // 9 ->
    // Maximize sum after K negations
    // Input:
    // N = 5, K = 1
    // arr[] = {1, 2, -3, 4, 5}
    // Output:
    // 15
    // Explanation:
    // We have k=1 so we can change -3 to 3 and
    // sum all the elements to produce 15 as output.

    public static long maximizeSum(long a[], int n, int k) {
        Arrays.sort(a);

        for (int i = 0; i < n; i++) {
            if (a[i] < 0 && k > 0) {
                a[i] = -1 * a[i];
                k--;
            }
        }

        long sum = 0;
        for (long num : a)
            sum += num;

        long minNum = Integer.MAX_VALUE;
        for (long num : a) {
            minNum = Math.min(num, minNum);
        }

        if (k % 2 == 1) {
            sum = sum - (2 * minNum);
        }

        return sum;
    }

    // 10
    // Smallest Subset with Greater Sum
    // Input:
    // N = 5
    // Arr[] = {4,3,1,0,2}
    // Output:
    // 2
    // Explanation:
    // If we just select 4 and 3 from the array,
    // the sum will be (4+3) = 7 and the sum of
    // the rest of the elements will be (2+0+1)=3.
    // So, the answer will be 2. We can also
    // select 4 and 2 as well.

    int minSubset(int[] Arr, int N) {
        Arrays.sort(Arr);
        reverse(Arr);
        long sum = 0;

        for (int num : Arr) {
            sum += num;
        }

        int count = 0;
        long lsum = 0;

        for (int i = 0; i < N; i++) {
            lsum = lsum + Arr[i];
            sum = sum - Arr[i];
            count++;
            if (lsum > sum) {
                break;
            }
        }

        return count;
    }

    // 11->
    // The task is to find the smallest number with given sum of digits
    // as S and number of digits as D.
    // Input:
    // S = 9
    // D = 2
    // Output:
    // 18
    // Explanation:
    // 18 is the smallest number
    // possible with sum = 9
    // and total digits = 2.

    static String smallestNumber(int s, int d) {
        if (s > 9 * d)
            return "-1";
        String ans = "";

        for (int i = d - 1; i >= 0; i--) {
            if (s > 9) {
                ans = "9" + ans;
                s = s - 9;
            } else {
                if (i == 0) {
                    ans = s + ans;
                } else {
                    ans = (s - 1) + ans;
                    i--;
                    while (i > 0) {
                        ans = "0" + ans;
                        i--;
                    }
                    ans = "1" + ans;
                    break;
                }
            }
        }
        return ans;
    }

    // 12
    // Input:
    // N1 = 3, N2 = 4, N3 = 2
    // S1 = {4,2,3}
    // S2 = {1,1,2,3}
    // S3= {1,4}
    // Output:
    // 5
    // Explanation:
    // We can pop 1 element from the 1st stack, and 2
    // elements from the 2nd stack. Now remaining elements
    // yield the equal sum of the three stacks, that is 5.

    public static int maxEqualSum(int N1, int N2, int N3, int[] S1, int[] S2, int[] S3) {

        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int num : S1)
            sum1 += num;
        for (int num : S2)
            sum2 += num;
        for (int num : S3)
            sum3 += num;

        int top1 = 0, top2 = 0, top3 = 0;
        int ans = 0;
        while (true) {
            if (top1 == N1 || top2 == N2 || top3 == N3) {
                ans = 0;
                break;
            } else if (sum1 == sum2 && sum2 == sum3) {
                ans = sum1;
                break;
            } else if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= S1[top1++];
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= S2[top2++];
            } else {
                sum3 -= S3[top3++];
            }
        }
        return ans;
    }

    // 13
    // Minimum number of jumps to reach end of an array
    // Input:
    // N1 = 3, N2 = 4, N3 = 2
    // S1 = {4,2,3}
    // S2 = {1,1,2,3}
    // S3= {1,4}
    // Output:
    // 5
    // Explanation:
    // We can pop 1 element from the 1st stack, and 2
    // elements from the 2nd stack. Now remaining elements
    // yield the equal sum of the three stacks, that is 5.

    public static int maxEqualSum(int N1, int N2, int N3, int[] S1, int[] S2, int[] S3) {

        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int num : S1)
            sum1 += num;
        for (int num : S2)
            sum2 += num;
        for (int num : S3)
            sum3 += num;

        int top1 = 0, top2 = 0, top3 = 0;
        int ans = 0;
        while (true) {
            if (top1 == N1 || top2 == N2 || top3 == N3) {
                ans = 0;
                break;
            } else if (sum1 == sum2 && sum2 == sum3) {
                ans = sum1;
                break;
            } else if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= S1[top1++];
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= S2[top2++];
            } else {
                sum3 -= S3[top3++];
            }
        }
        return ans;
    }

    // 14
    // Minimum Cost to cut a board into squares
    // Initial Value : Total_cost = 0
    // Total_cost = Total_cost + edge_cost * total_pieces

    public static int minimumCostOfBreaking(int[] ver, int[] hor, int M, int N) {

        Arrays.sort(ver);
        reverse(ver);
        Arrays.sort(hor);
        reverse(hor);

        int i = 0;
        int j = 0;
        int n = ver.length;
        int m = hor.length;
        int verCount = 1;
        int horCount = 1;
        int cost = 0;

        while (i < n && j < m) {
            if (ver[i] > hor[j]) {
                cost += ver[i] * verCount;
                i++;
                horCount++;
            } else {
                cost += hor[j] * horCount;
                j++;
                verCount++;
            }
        }
        while (i < n) {
            cost += ver[i] * verCount;
            i++;
            // horCount++;
        }
        while (j < m) {
            cost += hor[j] * horCount;
            j++;
            // verCount++;
        }
        return cost;
    }

    public static void main(String[] args) {

    }
}
