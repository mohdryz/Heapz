import java.lang.Math;

class Heap{
    private Node head;
    private int size;

    public Heap(){
        this.head = null;
        this.size = 0;
    }

    public void createHeap(int[] a){
        for(int i=0;i<a.length;i++){
            Node curr = new Node(a[i],i);
            insertLast(curr);
        }
    }

    public void insertAtLeft(Node parent, Node x){
        parent.setLeft(x);
        if(x!=null)
            x.setParent(parent);
    }

    public void insertAtRight(Node parent, Node x){
        parent.setRight(x);
        if(x!=null)
            x.setParent(parent);
    }

    public void insertLast(Node x){
        if(this.size==0 && x!=null){
            this.head = x;
            this.size = 1;
            return;
        }
        Node parent = nodeAt((size+1)/2);
        if((size+1)%2==0)
            insertAtLeft(parent, x);
        else
            insertAtRight(parent, x);
        this.size = this.size+1;
        bubbleUp(x);
    }

    public Node nodeAt(int n){
        if(n>size)
            throw new RuntimeException("Invalid Index");
        if(n==0)
            return null;
        if(n==1)
            return head;
        Node x = nodeAt(n/2);
        return (n%2==0) ? x.getLeft() : x.getRight();
    }

    public void bubbleUp(Node x){
        while(!x.equals(head) && (x.getParent().getValue() > x.getValue())){
            Node parent = x.getParent();
            Node grandParent = parent.getParent();
            if(grandParent != null){
                if(grandParent.getLeft().equals(parent))
                    insertAtLeft(grandParent, x);
                else if(grandParent.getRight().equals(parent))
                    insertAtRight(grandParent, x);
            }
            if(parent.getLeft().equals(x)){
                Node temp = parent.getRight();
                insertAtRight(parent, x.getRight());
                insertAtRight(x, temp);
                insertAtLeft(parent, x.getLeft());
                insertAtLeft(x, parent);
            }
            else if(parent.getRight().equals(x)){
                Node temp = parent.getLeft();
                insertAtLeft(parent, x.getLeft());
                insertAtLeft(x, temp);
                insertAtRight(parent, x.getRight());
                insertAtRight(x, parent);
            }
            if(parent.equals(head)){
                this.head = x;
                x.setParent(null);
            }
        }
    }

    public Node extractMin(){
        Node x = head;
        if(x.getLeft()==null && x.getRight()==null){
            this.head = null;
            this.size = 0;
            return x;
        }
        Node last = findLast();
        Node lastNodeParent = last.getParent();
        if(lastNodeParent.getLeft().equals(last))
            insertAtLeft(lastNodeParent, null);
        else if(lastNodeParent.getRight().equals(last))
            insertAtRight(lastNodeParent, null);
        
        insertAtLeft(last, x.getLeft());
        insertAtRight(last, x.getRight());
        last.setParent(null);
        x.setLeft(null);
        x.setRight(null);
        x.setParent(null);
        this.head = last;
        this.size = this.size-1;
        sinkDown(last);
        return x;
    }

    public void sinkDown(Node x){
        boolean heapPropertyMissedFlag = true;
        while(x!=null && heapPropertyMissedFlag){
            Node left = x.getLeft();
            Node right = x.getRight();
            Node parent = x.getParent();
            heapPropertyMissedFlag = false;
            if(left!=null && right!=null){
                if((left.getValue() < right.getValue()) && (left.getValue() < x.getValue())){
                    heapPropertyMissedFlag = true;
                    Node temp = left.getRight();
                    insertAtRight(left, x.getRight());
                    insertAtRight(x, temp);
                    insertAtLeft(x, left.getLeft());
                    insertAtLeft(left, x);
                    if(parent!=null){
                        if(parent.getLeft().equals(x))
                            insertAtLeft(parent, left);
                        else if(parent.getRight().equals(x))
                            insertAtRight(parent, left);
                    }
                    if(head.equals(x)){
                        this.head = left;
                        left.setParent(null);
                    }
                }
                else if((left.getValue() >= right.getValue()) && (right.getValue() < x.getValue())){
                    heapPropertyMissedFlag = true;
                    Node temp = right.getLeft();
                    insertAtLeft(right, x.getLeft());
                    insertAtLeft(x, temp);
                    insertAtRight(x, right.getRight());
                    insertAtRight(right, x);
                    if(parent!=null){
                        if(parent.getLeft().equals(x))
                            insertAtLeft(parent, right);
                        else if(parent.getRight().equals(x))
                            insertAtRight(parent, right);
                    }
                    if(head.equals(x)){
                        this.head = right;
                        right.setParent(null);
                    }
                }
            }
            if(right==null && left==null)
                break;
            if(right==null && (left.getValue() < x.getValue())){
                heapPropertyMissedFlag = true;
                Node temp = left.getRight();
                insertAtRight(left, x.getRight());
                insertAtRight(x, temp);
                insertAtLeft(x, left.getLeft());
                insertAtLeft(left, x);
                if(parent!=null){
                    if(parent.getLeft().equals(x))
                        insertAtLeft(parent, left);
                    else if(parent.getRight().equals(x))
                        insertAtRight(parent, left);
                }
                if(head.equals(x)){
                    this.head = left;
                    left.setParent(null);
                }
            }
        }
    }

    public int getSize(){return this.size;}

    public Node getHead(){return this.head;}

    public Node findLast(){ return nodeAt(size); }
}