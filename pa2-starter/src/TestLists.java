
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection; 
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLists {

    public static Collection<Object[]> LISTNUMS =
        Arrays.asList(new Object[][] { {"Linked"}, {"Array"} });
    private String listType;

    public TestLists(String listType) {
        super();
        this.listType = listType;
    }

    @Parameterized.Parameters(name = "{0}List")
    public static Collection<Object[]> bags() {
        return LISTNUMS;
    }

    private <E> MyList<E> makeList(E[] contents) {
        switch (this.listType) {
            case "Linked":
                return new LinkedGL<E>(contents);
            case "Array":
                return new ArrayGL<E>(contents);
        }
        return null;
    }

    // Don't change code above this line, it ensures the autograder works as
    // expected


    // This is a sample test; you can keep it, change it, or remove it as you like.
    // Note that it uses the method `assertArrayEquals`, which you should use to
    // test equality of arrays in this PA.
    @Test
    public void testSimpleToArray() {
        // Using the generic list to create an Integer list
        Integer[] int_input = {1, 2, 3};
        MyList<Integer> int_s = makeList(int_input);
        // printAllValues(int_s.toArray());
        assertArrayEquals(int_input, int_s.toArray());

        // Using the generic list to create a String list
        String[] string_input = {"a", "b", "c"};
        MyList<String> string_s = makeList(string_input);
        // printAllValues(string_s.toArray());
        assertArrayEquals(string_input, string_s.toArray());
    }
    @Test
    public void testEmpty() {
        // Creating non-empty list
        Integer[] int_input = {1, 2, 3};
        MyList<Integer> int_s = makeList(int_input);
        assertFalse(int_s.isEmpty());
        
        // creating empty list
        Boolean[] emptyList = new Boolean[0];
        MyList<Boolean> bool_s = makeList(emptyList);
        assertTrue(bool_s.isEmpty());
    }

    @Test
    public void testTransform() {
        // Creating non-empty list
        String[] string_input = {"a", "b", "c"};
        MyList<String> string_s = makeList(string_input);
        UpperCaseTransformer mt = new UpperCaseTransformer();
        string_s.transformAll(mt);
        String[] desired = {"A", "B", "C"};
        
        assertArrayEquals(desired, string_s.toArray());
    }

    @Test
    public void testChooseAll() {
        // Creating string list for LongWordChooser
        // String[] string_input = {"adefgh", "b", "c"};
        String[] string_input = {"b", "c", "adefgh"};
        // String[] desired = new String[0];
        String[] desired = {"adefgh"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new LongWordChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());
        
        
        // creating empty list
        // Boolean[] emptyList = new Boolean[0];
        // MyList<Boolean> bool_s = makeList(emptyList);
        // assertTrue(bool_s.isEmpty());
    }
    @Test
    public void testChooseNotFront(){
        String[] string_input = {"adefgh", "b", "c"};
        String[] desired = {"adefgh"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new LongWordChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());
    }
    @Test
    public void testChooseCenter(){
        String[] string_input = {"b", "adefgh", "c"};
        String[] desired = {"adefgh"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new LongWordChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());
    }
    @Test
    public void testChooseMany(){
        String[] string_input = {"b", "adefgh", "c", "d", "asdfgh", "qwerty", "e", "f", "wedfcv"};
        String[] desired = {"adefgh", "asdfgh", "qwerty", "wedfcv"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new LongWordChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());

        
    }

    private void printAllValues(Object[] o){
        System.out.print("\n[");
        for(int i = 0; i < o.length-1; i++){
            System.out.format("%s, ", o[i]);
        }
        System.out.format("%s]\n",o[o.length-1]);
    }

    @Test
    public void testChooseLower() {
        String[] string_input = {"B", "C", "adefgh", "daled"};
        String[] desired = {"adefgh", "daled"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new LowerCaseChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());
    }
    @Test
    public void testChooseUpper() {
        String[] string_input = {"B", "C", "adefgh", "daled"};
        String[] desired = {"B", "C"};
        MyList<String> string_s = makeList(string_input);
        MyChooser<String> words = new UpperCaseChooser();
        string_s.chooseAll(words);
        System.out.format("\ntype: %s\n", string_s);
        printAllValues(string_s.toArray()); 
        assertArrayEquals(desired, string_s.toArray());
    }

    @Test
    public void testTransformLower() {
        // Creating non-empty list
        String[] string_input = {"A", "B", "C"};
        MyList<String> string_s = makeList(string_input);
        LowerCaseTransformer mt = new LowerCaseTransformer();
        string_s.transformAll(mt);
        String[] desired = {"a", "b", "c"};
        
        assertArrayEquals(desired, string_s.toArray());
    }
    @Test
    public void testTransformMiddle() {
        // Creating non-empty list
        String[] string_input = {"demacia", "noxus", "piltieitlip"};
        MyList<String> string_s = makeList(string_input);
        MiddleLetterTransformer mt = new MiddleLetterTransformer();
        string_s.transformAll(mt);
        String[] desired = {"a", "x", "e"};
        
        assertArrayEquals(desired, string_s.toArray());
    }
}
