package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {

    // env1 : environnement vide
    public static Map<String, Integer> env1() {
        return new HashMap<>();
    }

    // env2 : x -> 10, y -> 20
    public static Map<String, Integer> env2() {
        Map<String, Integer> env = new HashMap<>();
        env.put("x", 10);
        env.put("y", 20);
        return env;
    }

    // env3 : z -> 9
    public static Map<String, Integer> env3() {
        Map<String, Integer> env = new HashMap<>();
        env.put("z", 9);
        return env;
    }
}
