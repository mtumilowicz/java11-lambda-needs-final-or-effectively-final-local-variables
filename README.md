# java11-lambda-needs-final-or-effectively-final-local-variables
_Reference_: https://www.amazon.com/Modern-Java-Action-functional-programming/dp/1617293563  
_Reference_: https://docs.oracle.com/javase/specs/jls/se11/jls11.pdf

# preface
If a variable is effectively final, adding the final modifier to its declaration will
not introduce any compile-time errors. Conversely, a local variable or parameter
that is declared final in a valid program becomes effectively final if the final
modifier is removed.

Any local variable, formal parameter, or exception parameter used but not declared
in a lambda expression must either be declared final or be effectively final, 
or a compile-time error occurs where the use is attempted.

# project description
As you may see from the code below:
* you can modify instance variables
* you cannot modify local variables

```
class Restriction {
    private int counter = 0;
    
    public void field() {
        Consumer<Object> c = x -> counter++;
        c.accept(new Object());
    }
    
    public void local() {
        int counter = 0;

        Consumer<Object> c = x -> counter++; // Compile-time error: Variable used in lambda expression should be final or effectively final
        c.accept(new Object());
    }
}
```

You may be asking yourself why local variables have these restrictions?

# explanation
* local primitive variables
    Local primitive variables live on the stack. If a lambda could access local primitive variable 
    directly and the lambda were used in a thread, then the thread using the lambda could try to 
    access the variable after the thread that allocated the variable had deallocated it. Hence, 
    Java implements access to local primitive variable as access to a copy of it rather than access 
    to the original variable. This makes no difference if the local variable is assigned to only 
    once - hence the restriction.
* local reference variables
    Same reasoning as above, but instead of "local primitive variable" use "reference".