public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    public E[] toArray(){
        E[] totalElements = (E[]) new Object[this.size];
        for(int i = 0; i < this.size; i++){
            totalElements[i] = elements[i];
        }
        return totalElements;
    }

    public void transformAll(MyTransformer mt){
        for(int i = 0 ; i < size; i++){
            this.elements[i] = (E) mt.transformElement(elements[i]);
        }

    }
    @SuppressWarnings("unchecked")
    public void chooseAll(MyChooser mc){
        E[] new_elements = (E[]) new Object[this.size];
        int new_size = 0;
        for(int i = 0; i < size; i++){
            if(elements[i] != null)
                if(mc.chooseElement(elements[i])){
                    // System.out.println(elements[i]);
                    new_elements[new_size] = this.elements[i];
                    new_size++;
                }
        }
        this.elements = (E[]) new Object[new_size];
        for (int i = 0; i < new_size; i++){
            this.elements[i] = new_elements[i];
        }
        this.size = new_size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
}
