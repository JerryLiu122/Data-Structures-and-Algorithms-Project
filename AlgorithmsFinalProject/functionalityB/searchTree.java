package functionalityB;

import java.util.ArrayList;

public class searchTree {
	private node root;
    private ArrayList<String> al;

    /** Constructor **/
    public searchTree() {
        root = null;
    }

    /** function to check if empty **/
    public boolean isEmpty() {
        return root == null;
    }

    /** function to clear **/
    public void makeEmpty() {
        root = null;
    }

    /** function to insert for a word **/
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    /** function to insert for a word **/
    public node insert(node r, char[] word, int ptr) {
        if (r == null)
            r = new node(word[ptr]);

        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else
                r.isEnd = true;
        }
        return r;
    }

    /** function to delete a word **/
    public void delete(String word) {
        delete(root, word.toCharArray(), 0);
    }

    /** function to delete a word **/
    private void delete(node r, char[] word, int ptr) {
        if (r == null)
            return;

        if (word[ptr] < r.data)
            delete(r.left, word, ptr);
        else if (word[ptr] > r.data)
            delete(r.right, word, ptr);
        else {
            /** to delete a word just make isEnd false **/
            if (r.isEnd && ptr == word.length - 1)
                r.isEnd = false;

            else if (ptr + 1 < word.length)
                delete(r.middle, word, ptr + 1);
        }
    }

    public String[] search(String word) {
        String[] emptyArr = new String[0];
        try {
   
            if (word == null) {
                return emptyArr;
            }
        } catch (Exception e) {
            System.out.println("Searching for Null");
        }
        try {

            if (word.charAt(0) == ' ') {
                return emptyArr;
            }

        } catch (Exception e) {
            System.out.println("Empty space detected at the start of the string");

        }

        StringBuilder sb = new StringBuilder();

        node prefixRoot = crawlToPrefixLastNode(root, word.toCharArray(), 0);
        findAllSuggestions(prefixRoot, "", sb, word);
        if (sb.length() < 1) {
            System.out.println("No Matching String Found");
            return emptyArr;
        }

        return sb.toString().split("\n");
    }



    /** function to print tree **/
    public String toString() {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : " + al;
    }

    /** function to traverse tree **/
    private void traverse(node node, String str) {
        if (node != null) {
            traverse(node.left, str);

            str = str + node.data;
            if (node.isEnd)
                al.add(str);

            traverse(node.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(node.right, str);
        }
    }

    private node crawlToPrefixLastNode(node node, char[] word, int ptr) {
        if (node == null)
            return null;
        if (word[ptr] < node.data)
            return crawlToPrefixLastNode(node.left, word, ptr);
        else if (word[ptr] > node.data)
            return crawlToPrefixLastNode(node.right, word, ptr);
        else {
            if (ptr == word.length - 1)
                return node;
            else
                return crawlToPrefixLastNode(node.middle, word, ptr + 1);
        }
    }

    private void findAllSuggestions(node node, String str, StringBuilder sb, String pattern) {
        if (node != null) {
            findAllSuggestions(node.left, str, sb, pattern);
            str = str + node.data;
            if (node.isEnd) {
                if (pattern.length() == 1) {
                    if (pattern.equals(str.substring(0, 1)))
                        sb.append(pattern + str.substring(1) + "\n");
                } else
                    sb.append(pattern + str.substring(1) + "\n");

            }
            findAllSuggestions(node.middle, str, sb, pattern);
            str = str.substring(0, str.length() - 1);
            findAllSuggestions(node.right, str, sb, pattern);
        }
    }
}
