package functionalityB;

public class node {
	char data;
    boolean isEnd;
    node left, middle, right;

    /** Constructor **/
    public node(char data) {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

