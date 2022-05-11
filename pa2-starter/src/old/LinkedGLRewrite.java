// import java.util.Arrays;


public class LinkedGLRewrite<E> implements MyList<E> {

    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node front;
    int size;

    public LinkedGLRewrite(E[] contents) {
        this.size = contents.length;
        this.front = generateLinkedList(contents);
        // printAllNodesInOrder();
    }
    // without padding in the beginning
    private Node generateLinkedList(E[] contents) {
        Node front = new Node(null, null);
        for(int i = 0; i < contents.length; i++){
            front.value = contents[i];
            front.next = new Node(null, null);
            size++;
        }
        return front;
    }
    // note: it's fine to return Object[] arrays
    public E[] toArray() {
        E[] contents = (E[]) new Object[size];
        Node current = front;
        for(int i = 0; i < size; i++){
            contents[i] = current.value;
            current = current.next; 
        }
        return contents;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void transformAll(MyTransformer mt){
        Node current = front;
        for(int i = 0; i < size; i++){
            current.value = mt.transformElement(current.value);
            current = current.next;
        }
    }
    public void chooseAll(MyChooser mc){
        Node previous = null;
        Node current = front;
        for(int i = 0; i < size; i++){
            if(!mc.chooseElement(current.value)){
                if(previous == null){
                    this.front = this.front.next;
                    current = this.front;
                }
                else{
                    // skip the next one
                    previous.next = previous.next.next;
                    // take the one right after the previous next
                    // this should keep current 1 ahead
                    current = previous.next.next;
                }
            }
            else {
                // increment the counters otherwise
                previous = current;
                current = current.next;
            }
        }
    }
    
    // helper method
    private void delinkNode(Node current, Node previous){ //, boolean isFirst){
    }
    private void printNodeDetails(Node n){
        if (n == null){
            System.out.format("\nNode is null\n");
            return;
        }
        if (n.next == null){
            System.out.format("\nNext node is null\n");
            System.out.format("Node value: %s\n", n.value);
            return;
        }
        System.out.format("\nNode value: %s\n", n.value);
        System.out.format("Next node value: %s\n", n.next.value);
    }
    public void printAllNodesInOrder(){
        Node current = front;
        System.out.println("Node details below:");
        while(current != null){
            printNodeDetails(current);
            current = current.next;
        }
    }
}
