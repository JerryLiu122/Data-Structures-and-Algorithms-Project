package functionalityB;

import java.util.ArrayList;

public class searchTree 
{
	private node root;
    private ArrayList<String> al;


    public searchTree() 
    {
        root = null;
    }

    public boolean emptyOrNot() 
    {
        return root == null;
    }

    public void makeEmpty() 
    {
        root = null;
    }

    public void insert(String word) 
    {
        root = insert(root, word.toCharArray(), 0);
    }

    public node insert(node node, char[] word, int ptr) 
    {
        if (node == null) 
        {
        	node = new node(word[ptr]);	
        }
        if (word[ptr] < node.data)
        {
        	node.left = insert(node.left, word, ptr);
        }
        else if (word[ptr] > node.data)
        {
            node.right = insert(node.right, word, ptr);
        }
        else 
        {
            if (ptr + 1 < word.length) 
            {
            	node.middle = insert(node.middle, word, ptr + 1);
            }
            else
            {
                node.endOrNot = true;	
            }
        }
        return node;
    }

    public void delete(String word) 
    {
        delete(root, word.toCharArray(), 0);
    }

    private void delete(node node, char[] word, int ptr) 
    {
        if (node == null)
        {
            return;
        }
        if (word[ptr] < node.data) 
        {
            delete(node.left, word, ptr);
        }
        else if (word[ptr] > node.data) 
        {
            delete(node.right, word, ptr);
        }
        else 
        {
            if (node.endOrNot && ptr == word.length - 1) 
            {
            	  node.endOrNot = false;
            }    
            else if (ptr + 1 < word.length) 
            {
            	delete(node.middle, word, ptr + 1);
            }
                
        }
    }

    public String[] search(String word) 
    {
        String[] emptyArr = new String[0];  
            if (word == null) 
            {
                return emptyArr;
            }

            if (word.charAt(0) == ' ') 
            {
                return emptyArr;
            }
        StringBuilder sb = new StringBuilder();
        node prefixRoot = crawlToPrefix(root, word.toCharArray(), 0);
        findAll(prefixRoot, "", sb, word);
        if (sb.length() < 1) 
        {
            System.out.println("No Matching String Found");
            return emptyArr;
        }
        return sb.toString().split("\n");
    }

    private node crawlToPrefix(node node, char[] word, int ptr) 
    {
        if (node == null) 
        {
        	 return null;
        }
        if (word[ptr] < node.data) 
        {
        	return crawlToPrefix(node.left, word, ptr);
        }
        else if (word[ptr] > node.data) 
        {
        	return crawlToPrefix(node.right, word, ptr);
        }         
        else 
        {
            if (ptr == word.length - 1)
            {
                return node;
            }
            else
            {
            	return crawlToPrefix(node.middle, word, ptr + 1);
            }
        }
    }

    private void findAll(node node, String str, StringBuilder sb, String pattern) 
    {
        if (node != null) 
        {
            findAll(node.left, str, sb, pattern);
            str = str + node.data;
            if (node.endOrNot) 
            {
                if (pattern.length() == 1) 
                {
                    if (pattern.equals(str.substring(0, 1)))
                    {
                        sb.append(pattern + str.substring(1) + "\n");
                    }
                } 
                else
                {
                    sb.append(pattern + str.substring(1) + "\n");
                }

            }
            findAll(node.middle, str, sb, pattern);
            str = str.substring(0, str.length() - 1);
            findAll(node.right, str, sb, pattern);
        }
    }
}
