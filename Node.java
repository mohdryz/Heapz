class Node{
    private int value;
    private int index;
    private Node left;
    private Node right;
    private Node parent;

    public Node(){
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(int value, int index){
        this.value = value;
        this.index = index;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public int getValue(){return this.value;}

    public int getIndex(){return this.index;}

    public Node getLeft(){return this.left;}

    public Node getRight(){return this.right;}

    public Node getParent(){return this.parent;}

    public void setLeft(Node left){this.left = left;}

    public void setRight(Node right){this.right = right;}

    public void setParent(Node parent){this.parent = parent;}
}