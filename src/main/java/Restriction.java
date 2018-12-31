import java.util.function.Consumer;

/**
 * Created by mtumilowicz on 2018-12-31.
 */
class Restriction {
    private int counter = 0;
    
    public void field() {
        Consumer<Object> c = x -> counter++;
        c.accept(new Object());
    }
    
    public void local() {
        int counter = 0;

        // Consumer<Object> c = x -> counter++; // Compile-time error: Variable used in lambda expression should be final or effectively final
        // c.accept(new Object());
    }
}
