public class LambdaTest {
    public static void printString(String s, Print<String> print) {
        print.print(s);
    }
    public static void main(String[] args) {
        printString("test", (x) -> System.out.println(x));
    }
}

@FunctionalInterface
interface Print<T> {
    public void print(T x);
}
