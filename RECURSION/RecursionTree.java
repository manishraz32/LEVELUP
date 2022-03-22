import java.util.*;
public class RecursionTree {

   //Permutation with infinite coin
   // arr[] = [2, 3, 5, 7] , tar = 10
    public static int permutationWithInfi(int[] arr, int tar, String ans, int k) {
        if(k == 0) {
            if(tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(tar - arr[i] >= 0) {
                count += permutationWithInfi(arr, tar - arr[i], ans + arr[i], k - 1);
            }    
        }
        return count;
    }
    
    // Premutation with infinite through sub Sequence method
    public static int permutationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += permutationWithInfi_subSeq(arr, tar - arr[idx], 0, ans + arr[idx]);
        count += permutationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }
    //permutation with Single coin
    public static int permutationWithSingleCoin(int[] arr, boolean[] vis, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += permutationWithSingleCoin(arr, vis, tar - arr[i], ans + arr[i]);
                vis[i] = false;
            }
        }

        return count;
    }

    // permtutation by single coin by using of sub sequence 
    public static int permutationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingleCoin_subSeq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);
        return count;
    }

    //Combination with infinite coin
    public static int combinationWithInfinite(int[] arr, int idx, int tar, String ans, int k) {
        if(k == 0) {
            if(tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++) {
            if(tar - arr[i] >= 0) {
                count += combinationWithInfinite(arr, i, tar - arr[i], ans + arr[i], k-1);
            }
           
        }
        return count;

    }

    //Combination with single coin
     public static int combinationWithSingle(int[] arr, int idx, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++) {
            if(tar - arr[i] >= 0) {
                count += combinationWithSingle(arr, i + 1, tar - arr[i], ans + arr[i] );
            }
           
        }
        return count;
    }
    //combination with infinite with sub Sequence method
    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfi_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }
   
    // 1D_Queen_Set=================================================================================

    // tboxes = total Bpxes, tqn = total queen, qpsf = queen placed so far, bn =
    // box_no,
    public static int queenCombination(int tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe; i++) {
            count += queenCombination(tboxe, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe.length; i++) {
            if (!tboxe[i]) {
                tboxe[i] = true;
                count += queenPermutation(tboxe, tqn, qpsf + 1, 0, ans + "b" + i + "q" + qpsf + " ");
                tboxe[i] = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        // System.out.println("answer is " + permutationWithInfi(arr, 10, ""));
        // System.out.println("answer is " + combinationWithInfinite(arr,0, 10, ""));
        // System.out.println("answer is " + combinationWithSingle(arr,0, 10, ""));
        System.out.println(combinationWithInfinite(arr, 8,0,"", 4));

    }
}
