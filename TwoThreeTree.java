
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class TwoThreeTree {
    Node root = null;
    
    public TwoThreeTree() {
        root = new Node(true);
    }
    
    public boolean insert(Integer n) {
        if (root.search(n).key.contains(n)) {
            return false;
        } else {
            root = root.insert(n);
            return true;
        }
    }
    
    /*
    public void printTree() {
        printTree(root, 0);
    }
    
    public void printTree(Node n, int c) {
        for (int i = 0; i < c; i++) {
            System.out.print("...");
        }
        c++;
        System.out.println(n.toString());
        if(!n.isLeaf()) {
            for (Node child : n.child) {
                printTree(child, c);
            }
        }
    }*/
    
    public String search(Integer i) {
        return root.search(i).toString();
    }
    
    public class Node {
        
        private ArrayList<Integer> key = new ArrayList<>();
        private ArrayList<Node> child = new ArrayList<>();
        
        private boolean is_root = false;
        
        
        
        public Node(boolean is_root) {
            this.is_root = is_root;
        }
        
        public Node(Integer n) {
            key.add(n);
        }
        
        public boolean isLeaf() {
            if (child.isEmpty()) {
                return true;
            }
            return false;
        }
        
        public Node insert(Integer n) {
            if (isLeaf()) {
                key.add(n);
                Collections.sort(key);
            }
            if (!isLeaf()) {
                Node next = getDirection(n).insert(n);
                if (next.key.size() > 2) {
                    split(next);
                    Collections.sort(child,getComp());
                }
            }
            
            if (is_root && key.size() == 3) {
                Node left = new Node(key.remove(0));
                Node right = new Node(key.remove(key.size() - 1));
                
                int size = child.size();
                
                Collections.sort(child, getComp());
                for (int i = 0; i < size/2; i++) {
                    left.child.add(child.remove(0));
                }
                for (int i = 0; i < size/2; i++) {
                    right.child.add(child.remove(0));
                }

                this.child.add(left);
                this.child.add(right);
                
            }
            return this;
        }
        
        @Override
        public String toString() {
            String result = "";
            for (int i : key) {
                if (key.indexOf(i) == 0) {
                    result += i;
                } else {
                    result += " " + i;
                }
            }
            return result;
        }

        private Node getDirection(Integer n) {
            for (int i = 0; i < key.size(); i++) {
                if (n < key.get(i)) {
                    return child.get(i);
                }
            }
            return child.get(child.size()-1);
        }

        private void split(Node n) {

            
            this.key.add(n.key.remove(1));
            Collections.sort(key);

            
            Node left = new Node(n.key.remove(0));
            Node right = new Node(n.key.remove(0));
                
                int size = n.child.size();
                for (int i = 0; i < size/2; i++) {
                    left.child.add(n.child.remove(0));
                }
                for (int i = 0; i < size/2; i++) {
                    right.child.add(n.child.remove(0));
                }

                child.add(left);
                child.add(right);
                
                this.child.remove(n);
            
        }
        
        public Comparator<Node> getComp() {
            Comparator<Node> comp = new Comparator<Node>() {

                @Override
                public int compare(Node o1, Node o2) {
                    return o1.key.get(0).compareTo(o2.key.get(0));
                }
            };
            return comp;
        }
        
        public Node search(Integer n) {
            if (key.contains(n)) {
                return this;
            }
            if (!isLeaf()) {
                return getDirection(n).search(n);
            } else {
                return this;
            }
        }
    }
}
