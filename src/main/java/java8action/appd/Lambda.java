package java8action.appd;

import java.util.function.Function;

public class Lambda {
    Function<Object, String> f = Object::toString;

//    Function<Integer,Integer> a = x -> x + 1;
//    Function<Integer,Integer> b = x -> x ^ 2;
//    Function<Integer,Integer> c = a.andThen(b);
//    int d =  c.apply(1);

    public static void main(String[] args) {
        byte a = 0;
        a = 10;
        a = 127;

        System.out.println(a);
    }
}
