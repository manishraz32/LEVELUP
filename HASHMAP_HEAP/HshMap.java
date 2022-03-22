import java.lang.reflect.Array;
import java.util.*;

public class HshMap {

    // Implementatation of HashMap in java
    public static class HashMap<K, V> {
        private class HMNode {
            K key;
            V value;

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size; // n
        private LinkedList<HMNode>[] buckets; // N = buckets.length

        public HashMap() {
            initbuckets(4);
            size = 0;
        }

        public void put(K key, V value) throws Exception {
            int bi = hashFunction(key);
            int di = findInBucket(bi, key);

            if (di == -1) {
                HMNode node = new HMNode(key, value);
                buckets[bi].addLast(node);
                size++;
            } else {
                HMNode node = buckets[bi].get(di);
                node.value = value;
            }

            double lambda = size * 1.0 / buckets.length;
            if (lambda > 2.0) {
                rehash();
            }
        }

        public V get(K key) throws Exception {
            int bi = hashFunction(key);
            int di = findInBucket(bi, key);

            if (di == -1) {
                return null;
            } else {
                HMNode node = buckets[bi].get(di);
                return node.value;
            }
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = findInBucket(bi, key);

            if (di == -1) {
                return false;
            } else {
                return true;
            }
        }

        public V remove(K key) throws Exception {
            int bi = hashFunction(key);
            int di = findInBucket(bi, key);

            if (di == -1) {
                return null;
            } else {
                HMNode node = buckets[bi].remove(di);
                size--;
                return node.value;
            }
        }

        public ArrayList<K> keyset() throws Exception {
            ArrayList<K> set = new ArrayList<>();

            for (int bi = 0; bi < buckets.length; bi++) {
                for (HMNode node : buckets[bi]) {
                    set.add(node.key);
                }
            }

            return set;
        }

        public int size() {
            return size;
        }

        public void display() {
            System.out.println("Display Begins");
            for (int bi = 0; bi < buckets.length; bi++) {
                System.out.print("Bucket" + bi + " ");
                for (HMNode node : buckets[bi]) {
                    System.out.print(node.key + "@" + node.value + " ");
                }
                System.out.println(".");
            }
            System.out.println("Display Ends");
        }

        // returns bucket index for a key
        private int hashFunction(K key) {
            int hc = key.hashCode();
            int bi = Math.abs(hc) % buckets.length;
            return bi;
        }

        // return data index for a bucket and key
        private int findInBucket(int bi, K key) {
            int di = 0;
            for (HMNode node : buckets[bi]) {
                if (node.key.equals(key)) {
                    return di;
                }
                di++;
            }

            return -1;
        }

        // when lambda crosses a threshold
        private void rehash() throws Exception {
            LinkedList<HMNode>[] oba = buckets;
            initbuckets(2 * oba.length);
            size = 0;

            for (int bi = 0; bi < oba.length; bi++) {
                for (HMNode onode : oba[bi]) {
                    put(onode.key, onode.value);
                }
            }
        }

        private void initbuckets(int N) {
            buckets = new LinkedList[N];
            for (int bi = 0; bi < buckets.length; bi++) {
                buckets[bi] = new LinkedList<>();
            }
        }
    }

    //https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/
    //hashmap-and-heaps/find-itinerary-from-tickets-official/ojquestion#!
    // 4
    // Chennai Banglore 
    // Bombay Delhi 
    // Goa Chennai 
    // Delhi Goa 

    public static void findItinerary() {
		Scanner scn = new Scanner(System.in);
		int noofpairs_src_des = scn.nextInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < noofpairs_src_des; i++) {
			String s1 = scn.next();
			String s2 = scn.next();
			map.put(s1, s2);	
		}
        // adding all potential src int potentialSrc map
        //so that we can find the original src
        HashMap<String, Boolean> potentialSrc = new HashMap<>();
        for(String src : map.keySet()) {
            String dest = map.get(src);
            potentialSrc.put(dest, false);
            if(potentialSrc.containsKey(src) == false) {
                potentialSrc.put(src, true);
            }
        }
        
        //find original src from the potential src 
        String src = "";
        for(String key : potentialSrc.keySet()) {
            if(potentialSrc.get(key) == true) {
                src = key;
            }
        }
        while(true) {
            if(map.get(src) == true) {
                System.out.println(src + "->");
            } else {
                break;
            }
        }
    }

    
    //Quest: 2
    //https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
    // Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
    // Output: true
    // Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
    public boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int num : arr) {
            int rem = num % k;
            int freq = hmap.getOrDefault(rem, 0);
            hmap.put(rem, freq + 1);
        }
        for(int num : arr) {
            int rem= num % k;
            if(rem == 0) {
                int freq = hmap.get(rem);
                if(freq % 2 == 1) {
                    return false;
                }
            } else if(rem * 2 == k) {
                int freq = hmap.get(rem);
                if(freq % 2 == 1) {
                    return false;
                }
            } else {
                int freq = hmap.get(rem); // freq of x
                int otherFreq = hmap.get(k - rem); // frequency of k - x
                if(freq != otherFreq) {
                    return false;
                }
            }
        }
        return true;
    }


    //https://practice.geeksforgeeks.org/problems/
    //count-distinct-elements-in-every-window/1/
    // N = 3, K = 2
    // A[] = {4,1,1}
    // Output: 2 1
    ArrayList<Integer> countDistinct(int arr[], int n, int k) {
        int len = arr.length;
        int i = 0;
        int j = -1;
        ArrayList<Integer> ans = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        while(i < k - 1) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        while(i < len) {
            // adding the element into the hashmap
            i++;
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            ans.add(map.size());
            //deletion of elements from the HashMap
            j++;
            int freq = map.get(arr[j]);
            if(freq == 1) {
                map.remove(arr[j]);
            } else {
                map.put(arr[i], map.get(arr[i]) - 1);
            }

        }
        return ans;
    }

    

    //https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/
    // Input:
    // N = 8
    // A[] = {15,-2,2,-8,1,7,10,23}
    // Output: 5
    // Explanation: The largest subarray with
    // sum 0 will be -2 2 -8 1 7.

    //follow up question ot this question is
    //count all subarray with 0 sum  
    int maxLen(int arr[], int n) {
        int i = -1;
        int sum = 0;
        int ans = 0;
        HashMap<Integer, Integer> hmap = new HashMap<>();
        int count = 0;
        hmap.put(sum, i);
        while(i < n - 1) {
            i++;
            sum += arr[i];
            if(hmap.containsKey(sum)) {
                int prvIndexOfSum = hmap.get(sum);
                int len = i - prvIndexOfSum;
                if(len > ans) {
                    ans = len;
                }
                count++;
            } else {
                hmap.put(sum, i);
            }
        }
        return ans;
    }















    public static void main(String[] args) {
        System.out.println("manish");
    }
}
