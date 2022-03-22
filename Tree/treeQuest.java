import java.util.*;
import java.io.*;

public class treeQuest {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rtp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    top.node.left = new Node(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }

                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }

                top.state++;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // 1. size of binary tree -> no of nodes in binary tree
    public static int size(Node node) {
        if (node == null)
            return 0;
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return leftSize + rightSize + 1;
    }

    // 2. Sum of all nodes of binary tree
    public static int sum(Node node) {
        if (node == null)
            return 0;
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);
        return leftSum + rightSum + node.data;
    }

    // 3. Maximum node value in binary tree
    public static int max(Node node) {
        if (node == null)
            return 0;
        int lmax = max(node.left);
        int rmax = max(node.right);
        return Math.max(Math.max(lmax, rmax), node.data);
    }

    // 4. Hight of binary tree
    // on the basis of node return 0 from base case
    // on the basis of edge return -1 from base case
    public static int height(Node node) {
        if (node == null)
            return 0;
        int lheight = height(node.left);
        int rheight = height(node.right);
        return Math.max(lheight, rheight) + 1;
    }

    // 5. BFS in tree
    // Level order traversal in Binary Treee
    public static void levelOrder(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() > 0) {
            int size = que.size();

            while (size-- > 0) {
                node = que.removeFirst();
                System.out.print(node.data + " ");

                if (node.left != null) {
                    que.addLast(node.left);
                }

                if (node.right != null) {
                    que.addLast(node.right);
                }
            }

            System.out.println();
        }
    }

    // 6. Find a node in binary tree
    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;
        if (find(node.left, data))
            return true;
        if (find(node.right, data))
            return true;
        return false;
    }

    // 7. Find root to node path
    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        if (node == null)
            return new ArrayList<Integer>();

        if (node.data == data) {
            ArrayList<Integer> base = new ArrayList<Integer>();
            base.add(node.data);
            return base;
        }
        ArrayList<Integer> isInLeft = nodeToRootPath(node.left, data);
        if (isInLeft.size() > 0) {
            isInLeft.add(node.data);
            return isInLeft;
        }
        ArrayList<Integer> isInRight = nodeToRootPath(node.right, data);
        if (isInRight.size() > 0) {
            isInRight.add(node.data);
            return isInLeft;
        }

        return new ArrayList<Integer>();
    }

    // 8. Print K level down
    public static void printKLevelsDown(Node node, int k) {
        if (node == null)
            return;
        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        printKLevelsDown(node.left, k - 1);
        printKLevelsDown(node.right, k - 1);
    }

    // 9. print k away distance

    public static ArrayList<Node> nodeToRootPath_(Node node, int data) {
        if (node == null) {
            return new ArrayList<>();
        }

        if (node.data == data) {
            ArrayList<Node> list = new ArrayList<>();
            list.add(node);
            return list;
        }

        ArrayList<Node> llist = nodeToRootPath_(node.left, data);
        if (llist.size() > 0) {
            llist.add(node);
            return llist;
        }

        ArrayList<Node> rlist = nodeToRootPath_(node.right, data);
        if (rlist.size() > 0) {
            rlist.add(node);
            return rlist;
        }

        return new ArrayList<>();
    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = nodeToRootPath_(node, data);
        // printKLevelsDown(path.get(0), k);
        for (int i = 0; i < path.size(); i++) {
            Node newNode = path.get(i);
            printKLevelsDown(newNode, k, i == 0 ? null : path.get(i - 1));
            k--;
        }
    }

    public static void printKLevelsDown(Node node, int k, Node blocker) {
        if (node == null || k < 0 || node == blocker) {
            return;
        }

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown(node.left, k - 1, blocker);
        printKLevelsDown(node.right, k - 1, blocker);
    }

    // 10. Root to leaf path sum
    // https://practice.geeksforgeeks.org/problems/root-to-leaf-path-sum/1
    boolean hasPathSum(Node root, int S) {
        return helper(root, S, 0);
    }
    boolean helper(Node root, int S, int sumSoFar) {
        if(root == null) return false;
        if(root.left == null && root.right == null && ) {
            return (root.data + sumSoFar) == S;
        }
        if(helper(root.left, S, sumSoFar + root.data));
            return true;
        if(helper(root.right, S, sumSoFar + root.data))
            return true;
        return false;         
    }

    //11. Print single child node 
    public static void printSingleChildNodes(Node node, Node parent){
        ArrayList<Integer> list = new ArrayList<Integer>();
        singleChild(node, null, list);
    }
    public static void singleChild(Node node, Node parent, ArrayList<Integer> list) {
        if(node == null) return;
        if(parent != null && parent.left == node && parent.right == null) {
            list.add(node.data);
        }
        if(parent != null && parent.right == node && parent.left == null) {
            list.add(node.data);
        }
        singleChild(node.left, node, list);
        singleChild(node.right, node, list);
    }

    //12. Remove leaves from the binary tree
    public static Node removeLeaves(Node node){
        if(node == null) return null;
        if(node.left == null && node.right == null) {
            return null;
        }   
        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right); 
    }

    //13. Normal tree to left cloned 
    public static Node createLeftClone(Node root) {
        if(root == null) return null;
        
        Node leftCloneTree = createLeftClone(root.left);
        Node rightTree = createLeftClone(root.right);

        Node newNode = new Node(root.data);
        newNode.left = leftCloneTree;
        root.left = newNode;
        root.right = rightTree;
        return root;
    }

    //14. left clone to Normal clone
    public static Node cloneToNormal(Node root) {
        if(root == null) return null;

        Node leftTree = cloneToNormal(root.left.left);
        Node rightTree = cloneToNormal(root.right);

        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    //15. find tilt of Binary tree
    //https://leetcode.com/problems/binary-tree-tilt/
    public int findTilt(TreeNode root) {
        int sum = tilt1(root);
        return tilt;
    }
    int tilt;
    public  int tilt1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int ls = tilt1(root.left);
        int rs = tilt1(root.right);
        
        int nt = Math.abs(ls - rs);
        tilt = tilt + nt;
        
        return ls + rs + root.val;
    }




    public static void main(String[] args) {
        System.out.println("manish");
    }
}
