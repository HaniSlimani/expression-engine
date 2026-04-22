package pobj.expr;

public class Question4 {

    // e1 = (2 + 3) * 4
    public static Expression e1() {
        return new Mult(
            new Add(new Constant(2), new Constant(3)),
            new Constant(4)
        );
    }

    // e2 = (x + 3) * (x + 4)
    public static Expression e2() {
        return new Mult(
            new Add(new Var("x"), new Constant(3)),
            new Add(new Var("x"), new Constant(4))
        );
    }

    // e3 = (x + 10) * (y + -8)
    public static Expression e3() {
        return new Mult(
            new Add(new Var("x"), new Constant(10)),
            new Add(new Var("y"), new Constant(-8))
        );
    }
}
