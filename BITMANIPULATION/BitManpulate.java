package BITMANIPULATION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BitManpulate {

  // Some operator on bits

  public static void operatorsOnBits() {
    int n = 20;
    System.out.println(Integer.toBinaryString(n)); // 20 -> 10100

    // (10100) | (101) => 00101
    System.out.print("OR" + "->");
    int a = 1;
    System.out.print(Integer.toBinaryString(n | 5) + " "); // 00101
    System.out.print((a | 0) + " "); // (a | 0) -> a
    System.out.println(a | 1); // (a | 1) -> 1

    // (10100) & (101) => 00100
    System.out.print("AND" + " ");
    System.out.print(Integer.toBinaryString(n & 5) + " "); // 100
    System.out.print((a & 0) + " "); // (a & 0) -> 0;
    System.out.println((a & 1) + " "); // (a & 1) -> a;

    // XOR(^) operation
    // (10100) ^ (101) => 10001
    System.out.print("XOR" + " ");
    System.out.print(Integer.toBinaryString(n ^ 5) + " "); // 10001
    System.out.print((a ^ 0) + " "); // (a ^ 0) -> a;
    System.out.println((a ^ 1) + " "); // (a ^ 1) -> ~a(0)

    // left Shift (<<)
    n = 43; // 00101011
    System.out.println(n + " left Shigt");
    System.out.println(Integer.toBinaryString((n << 2)) + " "); // 10101100

    // right Shift(>>)
    System.out.println(n + " right Shift");
    System.out.println(Integer.toBinaryString(n >> 2)); // 00101011 -> 1010

    // toggle Bit
    System.out.println(n + " Toggle Bit");
    // 43 -> for leading zero(111111111111111111111111) 11010100
    System.out.print(Integer.toBinaryString(~n) + " ");
    System.out.println(Integer.toBinaryString(-n));
    System.out.println(~n);

    System.out.println(Integer.toBinaryString(1 << 2));
  }

  // 1.
  // Basics Of Bit Manipulation
  // Easy Prev Next
  // 1. You are given a number n.
  // 2. Print the number produced on setting its i-th bit.
  // 3. Print the number produced on unsetting its j-th bit.
  // 4. Print the number produced on toggling its k-th bit.
  // 5. Also, Check if its m-th bit is on or off. Print 'true'
  // if it is on, otherwise print 'false'.
  public static void bitBasic(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int i = scn.nextInt();
    int j = scn.nextInt();
    int k = scn.nextInt();
    int m = scn.nextInt();

    int onMask = (1 << i);
    int offMask = ~(1 << j);
    int toggleMask = (1 << k);
    int checkMask = (1 << m);
    System.out.println(n | onMask);
    System.out.println(n & offMask);
    System.out.println(n ^ toggleMask);
    System.out.println((n & checkMask) > 0 ? true : false);
  }

  // 2
  //
  public String rightMostSetBit(int n) {
    int plusOneMask = (~n) + 1; // also work [(-n) + 1]
    System.out.println(n & plusOneMask);
    return Integer.toBinaryString(n & plusOneMask);
  }

  // 3.
  // https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
  //
  static int countSetBits(int n) {
    int count = 0;
    System.out.println(Integer.toBinaryString(12 >> 2));
    while (n > 0) {
      System.out.println(Integer.toBinaryString(n));
      count += n & 1;
      n = n >>> 1;
    }
    return count;
  }

  // Brain kernighan's algorithm
  // for counting set bit in binary digit
  static int kernighanAlgo(int n) {
    int count = 0;
    while (n != 0) {
      n = n & (n - 1);
      count++;
    }
    return count;
  }

  // 4.
  // josephus-special-official
  // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/
  // bit-manipulation/josephus-special-official/ojquestion#!
  public static int powerOfTwo(int n) {
    int i = 1;
    while ((i << 1) <= n) {
      i = (i << 1);
    }
    return i;
  }

  public static int solution(int n) {
    int powerOfTwoMult = powerOfTwo(n);
    int l = n - powerOfTwoMult;
    return (l << 1) + 1;
  }

  // 6.
  // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/
  // bit-manipulation/gray-code/ojquestion#!
  public static List<Integer> grayCode(int n) {
    if (n == 0 || n == 1) {
      List<Integer> baseAns = new ArrayList<Integer>();
      baseAns.add(0);
      if (n == 0)
        return baseAns;
      baseAns.add(1);
      return baseAns;
    }
    helper(n);
    return ans;
  }

  static List<Integer> ans = new ArrayList<Integer>();

  public static List<String> helper(int n) {
    if (n == 1) {
      List<String> baseAns = new ArrayList<String>();
      baseAns.add("0");
      baseAns.add("1");
      return baseAns;
    }

    List<String> recAns = helper(n - 1);

    ans = new ArrayList<Integer>();
    List<String> myAns = new ArrayList<String>();

    for (int i = 0; i < recAns.size(); i++) {
      String str = recAns.get(i);
      String binaryString = "0" + str;
      myAns.add(binaryString);
      ans.add(Integer.parseInt(binaryString, 2));
    }

    for (int i = recAns.size() - 1; i >= 0; i--) {
      String str = recAns.get(i);
      String binaryString = "1" + str;
      myAns.add(binaryString);
      ans.add(Integer.parseInt(binaryString, 2));
    }
    return myAns;
  }

  // 7 : Minimum Number Of Software Developer
  // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/
  // bit-manipulation/minimum-number-of-software-developers-official/ojquestion#!
  static ArrayList<Integer> sol = new ArrayList<>();

  public static void solution(int[] people, int nskills, int cp, ArrayList<Integer> onesol, int skillMask) {
    if (cp == people.length) {
      if (((1 << nskills) - 1) == skillMask) {
        if (sol.size() == 0 || onesol.size() <= sol.size()) {
          sol = new ArrayList<>(onesol);
        }
      }
      return;
    }

    solution(people, nskills, cp + 1, onesol, skillMask); // NO

    onesol.add(cp);
    solution(people, nskills, cp + 1, onesol, (people[cp] | skillMask));
    onesol.remove(onesol.size() - 1);

  }

  public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

    HashMap<String, Integer> smap = new HashMap<>();
    int n = req_skills.length;
    for (int i = 0; i < n; i++) {
      smap.put(req_skills[i], i);
    }

    int np = people.size();
    int[] pep = new int[np];
    for (int i = 0; i < np; i++) {
      int personSkills = people.get(i).size();
      for (int j = 0; j < personSkills; j++) {
        String skill = people.get(i).get(j);
        int snum = smap.get(skill);
        pep[i] = pep[i] | (1 << snum);
      }
    }

    solution(pep, n, 0, new ArrayList<>(), 0);
    int[] ans = new int[sol.size()];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = sol.get(i);
    }
    return ans;
  }

  //8.All repeating except one
  //https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup
  ///bit-manipulation/all-repeating-except-one-official/ojquestion#!
  public int findSingeNum(int[] arr) {
    int ans = 0;
    // when we xor to same value then result become 0
    for(int val : arr) {
        ans = ans ^ val; 
    }
    return ans;
  }

  //9. All repeating expect two
  //https://leetcode.com/problems/single-number-iii/
  // Example 1:
  // Input: nums = [1,2,1,3,2,5]
  // Output: [3,5]
  // Explanation:  [5, 3] is also a valid answer.

  public int[] singleNumber(int[] nums) {
    int xorOfElem = 0;
    for(int val : nums) {
      xorOfElem = xorOfElem ^ val;
    }
    
    int mask = xorOfElem & - xorOfElem;
    int x = 0;
    int y = 0;
    for(int val : nums) {
      if((val & mask) == 0) {
        x = x ^ val;
      } else {
        y = y ^ val;
      }
    }
    int[] ans = new int[2];
    ans[0] = x;
    ans[1] = y;
    return ans;
  }
  
  // 10.
  //https://practice.geeksforgeeks.org/problems/find-missing-and-repeating2512/1
  // Input:
  // N = 3
  // Arr[] = {1, 3, 3}
  // Output: 3 2
  // Explanation: Repeating number is 3 and 
  // smallest positive missing number is 2.

  int[] findTwoElement(int nums[], int n) {
    int xorOfElem = 0;
    for(int val : nums) {
      xorOfElem = xorOfElem ^ val;
    }
    for(int i = 1; i < n; i++) {
      xorOfElem = xorOfElem ^ i;
    }

    int mask = xorOfElem & - xorOfElem;
    int x = 0;
    int y = 0;
    for(int val : nums) {
      if((val & mask) == 0) {
        x = x ^ val;
      } else {
        y = y ^ val;
      }
    }
    for(int i = 1; i < n; i++) {
      if((i & mask) == 0) {
        x = x ^ i;
      } else {
        y = y ^ i;
      }
    }
    int[] ans = new int[2];
    for(int val : nums) {
      if(val == x) {
        ans[0] = x;
        ans[0] = y;
        break;
      } else if(val == y) {
        ans[0] = y;
        ans[0] = x;
        break;
      }
    }
    return ans;
  }

  //11. UTF-8 validation
  //https://leetcode.com/problems/utf-8-validation/
  // Char. number range  |        UTF-8 octet sequence
  // (hexadecimal)    |              (binary)
  // --------------------+---------------------------------------------
  // 0000 0000-0000 007F | 0xxxxxxx
  // 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
  // 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
  // 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
  public boolean validUtf8(int[] data) {
        int remainingBit = 0;
        for(int num : data) {
          if(remainingBit == 0) {
            if(num << 7 == 0b0) {
              remainingBit = 0;
            } else if(num << 5 == 0b110) {
              remainingBit = 1;
            } else if(num << 4 == 0b1110) {
              remainingBit = 2;
            } else if(num << 3 == 0b11110) {
              remainingBit = 3;
            }
          } else {
            if(num >> 2 == 0b10)
              remainingBit--;
            else
              return false;  
          }
        }
        return true;
  }


  public static void main(String[] args) {
    // System.out.println("manish");
    // operatorsOnBits();
    // System.out.println(countSetBits(9));
    System.out.println(kernighanAlgo(52));
  }
}
