import java.util.Arrays;

public class LinkedGL<E> implements MyList<E> {

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

    public LinkedGL(E[] contents) {
        this.size = contents.length;
        this.front = generateLinkedList(contents);
        printAllNodesInOrder();
    }

    private Node generateLinkedList(E[] contents) {
        if (contents.length == 0) {
            return null;
        }
        Node linkedList = new Node(null, null);
        for(int i = 0; i < contents.length; i++){
            // System.out.println(linkedList.value);
            // System.out.println(linkedList.next);
            linkedList.value = contents[i];
            linkedList.next = new Node(null, null);
            linkedList = linkedList.next;
        }
        // linkedList.next = null; // delete the null node at the end
        return linkedList;
    }
    // tested - works
    // note: it's fine to return Object[] arrays
    public E[] toArray() {
        Node current = front;
        E[] contents = (E[])(new Object[this.size]);
        int index = 0;
         
        while(current != null & index < this.size){
            contents[index] = current.value;
            current = current.next;
            index++;
        }
        
        return contents;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void transformAll(MyTransformer mt){
        Node current = front;
        while (current != null){
            // perform transformation
            if (current.value == null) {
                continue;
            }
            current.value = (E) mt.transformElement(current.value);
            
            // prepare for next iteration
            current = current.next;
        }
    }
    public void chooseAll(MyChooser mc){
        Node previous = null;
        Node current = this.front;
        int i = 0;
        // for(i = 0; i < this.size; i++){
        while(current != null){
            if(current ==null){
                return;
            }
            printNodeDetails(current);
            if (!mc.chooseElement(current.value)){
                // System.out.format("Rejected value: %s", current.value);
            //     System.out.format("rejected element: %s\n", current.value);
            //     if(i > 0 & previous != null & current != null){
            //         previous.next = current.next;
            //     }    
            //     else {
            //         this.front = current.next;
            //     }    
            //     current = current.next;
            //     this.size--;
                delinkNode(current, previous);
            }       

            // prepare for next iteration
            previous = current;
            current = current.next;

        }
        System.out.println("Final node:");
        printNodeDetails(previous);
        printNodeDetails(current);
        if (!mc.chooseElement(previous.value)){
            previous.next = null;
        }
    }
    
    // helper method
    private void delinkNode(Node current, Node previous){ //, boolean isFirst){
        System.out.format("rejected element: %s\n", current.value);
        // if(!isFirst & previousious != null & current != null){
        // if (previous == null || current == null){
            // return;
        // }
        if(previous != null & current != null){
            previous.next = current.next;
        }
        else if (current.next == null){
            current = null;
            this.size--;
            return;
        }
        else {
            this.front = current.next;
        }    
        current = current.next;
        this.size--;
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
