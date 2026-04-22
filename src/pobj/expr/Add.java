package pobj.expr;

public class Add extends BinOp {

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "( " + left.toString() + " + " + right.toString() + " )";
    }
    @Override
    public int eval() {
        return left.eval() + right.eval();
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
