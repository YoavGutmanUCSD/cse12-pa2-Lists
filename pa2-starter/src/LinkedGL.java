// import java.util.Arrays;


public class LinkedGL<E> implements MyList<E> {

    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        public String details(){
            String nextDetails;
            String currentDetails = String.format("Current node value: %s", this.value);
            if(this.next == null){
                nextDetails = String.format("Next node is null.");
            }
            else {
                nextDetails = String.format("Next node value is: %s", this.value);
            }
            return currentDetails + "\n" + nextDetails;
        }
    }

    Node front;
    int size;

    public LinkedGL(E[] contents) {
        this.size = contents.length;
        this.front = generateLinkedList(contents);

    }
    // without padding in the beginning
    private Node generateLinkedList(E[] contents) {
        Node front = new Node(null, null);
        Node current = front;
        for(int i = 0; i < contents.length; i++){
            current.value = contents[i];
            current.next = new Node(null, null);
            current = current.next;
        }
        return front;
    }
    // note: it's fine to return Object[] arrays
    public E[] toArray() {
        E[] contents = (E[]) new Object[size];
        Node current = this.front;
        for(int i = 0; i < size; i++){
            if(current != null){
                contents[i] = current.value;
                current = current.next; 
            }
        }
        return contents;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void transformAll(MyTransformer mt){
        Node current = front;
        for(int i = 0; i < size; i++){
            if(current.value != null){
                current.value = (E) mt.transformElement(current.value);
            }
            current = current.next;
        }
    }
    // public void chooseAll(MyChooser mc){
    //     Node current = front;
    //     int i = 0;
    //     while(current != null){
    //         if (current.value == null) {
    //             delinkNodeAtIndex(i);
    //         }
    //         if (mc.chooseElement(current.value)) {
                
    //         }

    //         i++;
    //         current = current.next;
    //     }
    // }
    public void chooseAll(MyChooser mc){
        Node previous = null;
        Node current = front;
        for(int i = 0; i < size; i++){
            boolean shouldSelect;
            if (current == null) 
                shouldSelect = false;

            else if (current.value == null) 
                shouldSelect = false;

            else 
                shouldSelect = mc.chooseElement(current.value);
            System.out.println();
            System.out.println(i);
            System.out.println(current.value);
            System.out.println(shouldSelect);
            if(!shouldSelect){
                delinkNodeAtIndex(i);
                i = 0;
            }
            current = current.next;
            // if(!shouldSelect){
            //     delinkNodeAtIndex(i);
            //     // if(previous == null){
            //     //     this.front = this.front.next;
            //     //     current = this.front;
            //     // }
            //     // else{
            //     //     // skip the next one
            //     //     previous.next = previous.next.next;
            //     //     // take the one right after the previous next
            //     //     // this should keep current 1 ahead
            //     //     current = previous.next.next;
            // }
            // else {
            //     // increment the counters otherwise
            //     previous = current;
            //     current = current.next;
            // }
        }
    }

    // helper method
    // private void delinkNodeAtIndex(Node current, Node previous){ //, boolean isFirst){
    // }
    // helper method (tested - works!)
    private void delinkNodeAtIndex(int index){ //, boolean isFirst){
        if(index >= size) {
            return;
        }
        Node current = front;
        Node previous = current;
        this.size--;
        if(index == 0){
            this.front = this.front.next;
            return;
        } 
        for(int i = 0; i < index; i++){
            previous = current;
            current = current.next;
        }
        if(index == size) {
            previous.next = null;
        }
        else previous.next = previous.next.next;
    }
}
