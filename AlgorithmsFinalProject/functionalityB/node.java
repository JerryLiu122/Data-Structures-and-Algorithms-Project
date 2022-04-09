package functionalityB;

public class node {
	char data;
    boolean endOrNot;
    node left, middle, right;

    public node(char data) {
        this.data = data;
        this.endOrNot = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

