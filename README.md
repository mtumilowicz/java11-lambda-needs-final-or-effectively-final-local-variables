# java11-lambda-needs-final-or-effectively-final-local-variables

good idea, but not exactly right:

RESTRICTIONS ON LOCAL VARIABLES
You may be asking yourself why local variables have these restrictions. First, there’s a
key difference in how instance and local variables are implemented behind the
scenes. Instance variables are stored on the heap, whereas local variables live on
the stack. If a lambda could access the local variable directly and the lambda were
used in a thread, then the thread using the lambda could try to access the variable
after the thread that allocated the variable had deallocated it. Hence, Java implements
access to a free local variable as access to a copy of it rather than access to the original
variable. This makes no difference if the local variable is assigned to only once—
hence the restriction.