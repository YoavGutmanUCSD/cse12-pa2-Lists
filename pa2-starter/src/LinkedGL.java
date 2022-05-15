// import java.util.Arrays;
import java.lang.IllegalArgumentException;


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

    public void chooseAll(MyChooser mc){
        boolean shouldChooseTilEnd = chooseUntilFrontChosen(mc);
        if(!shouldChooseTilEnd) return;
        chooseTilEnd(mc);
    }
    private void chooseTilEnd(MyChooser mc) {
        // this function assumes the front is good
        // has to run after chooseUntilFrontChosen

        Node current = front;

        // check current.next for values
        // that means we have to start at 1
        int i = 1;
        while (i < this.size){

            if(current.next.value == null) {
                delinkNextNode(current);
            }
            else if (!mc.chooseElement(current.next.value)) {
                delinkNextNode(current);
            }
            else {
                current = current.next;
                i++;
            }
        }

    }
    private boolean chooseUntilFrontChosen(MyChooser mc){
        // should return true if there is a point in running chooseTilEnd
        // choose from the beginning until there is a good value
        Node current = front;
        for(int i = 0; i < size; i++){

            // if no nodes match, this prevents a crash.
            if(current == null){
                break;
            }

            if(mc.chooseElement(current.value)){
                return true;
            }

            this.front = this.front.next;
            current = front;
            size--;
        }
        // return true if the array isn't empty
        return front.next != null; // & !front.next.equals(new Node(null, null));
    }
    private void delinkNextNode(Node curr){
        if(curr == null) {
            throw new IllegalArgumentException("delinkNextNode input is null");
        }
        if(curr.next == null){
            throw new IllegalArgumentException("delinkNextNode was given the last node. Bad usage!");
        }
        curr.next = curr.next.next;
        size--;
    }
}
