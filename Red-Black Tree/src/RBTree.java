public class RBTree<T extends Comparable<T>> {
    private Node<T> root;
    private final Node<T> NIL;


    public RBTree() {
        NIL = new Node<>();
        NIL.color = Color.BLACK;

        this.root = NIL;
        this.root.left = NIL;
        this.root.right = NIL;
    }


    public void insert(T x){
        //Create a Node
        Node<T> node = new Node<>();
        node.data = x;
        node.parent = NIL;
        node.left = NIL;       //Set left and right subtree's as NIL
        node.right = NIL;
        node.color = Color.RED;

        Node<T> rootNode = this.root;
        Node<T> traverseNode = NIL;


        while(rootNode != NIL){
            traverseNode = rootNode;
            if(node.data.compareTo(traverseNode.data) < 0){
                rootNode = rootNode.left;
            }else{
                rootNode = rootNode.right;
            }
        }
        node.parent = traverseNode;

        if(traverseNode == NIL){
            this.root = node;
        }else if (node.data.compareTo(traverseNode.data) < 0){
            traverseNode.left = node;
            node.parent = traverseNode;
        }else{
            traverseNode.right = node;
            node.parent = traverseNode;
        }

        //Apply RBTree properties
        insertFix(node);

    }

    private void insertFix(Node<T> node){
        //Recoloring/Rebalancing of Nodes
        while(node != root && node.parent.color == Color.RED){
            Node<T> nodeP = node.parent;    //node Parent
            Node<T> nodeG = nodeP.parent;   //node Grandparent


            if (nodeP == nodeG.left) {
                Node<T> uncle = nodeG.right;

                //Case 1: Uncle and Parent are red, recolor
                if(uncle.color == Color.RED){
                    nodeP.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    nodeG.color = Color.RED;
                    node = nodeG;

                }else{
                    //Case 2/3: Uncle is black and Parent is red, rotate, then recolor
                    if(node == nodeP.right){
                        node = nodeP;
                        leftRotate(node);
                    }
                    nodeP.color = Color.BLACK;
                    nodeG.color = Color.RED;
                    rightRotate(nodeG);
                }
            }else{
                Node<T> uncle = nodeG.left;
                
                //Case 1: Uncle and Parent are red, recolor
                if (uncle.color == Color.RED) {
                    nodeP.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    nodeG.color = Color.RED;
                    node = nodeG;

                }else{
                    //Case 2/3: Uncle is black and Parent is red, rotate, then recolor
                    if(node == nodeP.left){
                        node = nodeP;
                        rightRotate(node);
                    }
                    nodeP.color = Color.BLACK;
                    nodeG.color = Color.RED;
                    leftRotate(nodeG);
                }
            }
        }

        //Root node must always be black
         this.root.color = Color.BLACK;
    }

    //Use left rotation on subtree
    private void leftRotate(Node<T> node){
        Node<T> nodeRC = node.right;    //Node Right Child
        node.right = nodeRC.left;
        if(nodeRC.left != NIL){
            nodeRC.left.parent = node;
        }
        nodeRC.parent = node.parent;
        if(node.parent == NIL){   
            this.root = nodeRC;
        }else if (node == node.parent.left){
            node.parent.left = nodeRC;
        }else{
            node.parent.right = nodeRC;
        }
        nodeRC.left = node;
        node.parent = nodeRC;
    }

    //Use right rotation on subtree
    private void rightRotate(Node<T> node){
        Node<T> nodeLC = node.left;     //Node Left Child
        node.left = nodeLC.right;
        if(nodeLC.right != NIL){
            nodeLC.right.parent = node;
        }
        nodeLC.parent = node.parent;
        if(node.parent == NIL){ 
            this.root = nodeLC;
        }else if (node == node.parent.right){
            node.parent.right = nodeLC;
        }else{
            node.parent.left = nodeLC;
        }
        nodeLC.right = node;
        node.parent = nodeLC;
    }

    public Node<T> delete(T x){
        Node<T> deletedNode = null;
        if(search(x) != null){
            deletedNode = search(x);
            
        }else{
            System.out.println("Does not exist");
        }

        return deletedNode;
    }

    //Node Info stylization
    public String nodeInfo(Node<T> node){
        return "{Node: " + node.data + ", Parent: " + node.parent.data + ", Left Node: " + node.left.data + ", Right Node: " + node.right.data + ", Color: " + node.color + "}";
    }

    public Node<T> search(T x){
        Node<T> traverseNode = this.root;
        while(traverseNode != NIL && x.compareTo(traverseNode.data) != 0){
            if(x.compareTo(traverseNode.data) < 0){
                traverseNode = traverseNode.left;
            }else{
                traverseNode = traverseNode.right;
            }
        }

        if(traverseNode.data == x){
            return traverseNode;
        }else{
            return null;
        }
        
    }

}
