package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {

    @Override
    public Integer visit(Constant c) {
        return c.getValue();
    }

    @Override
    public Integer visit(Var v) {
        throw new UnsupportedOperationException("Cannot evaluate a variable.");
    }

    @Override
    public Integer visit(Add a) {
        // on évalue les sous-expressions via accept
        int leftVal = a.getLeft().accept(this);
        int rightVal = a.getRight().accept(this);
        return leftVal + rightVal;
    }

    @Override
    public Integer visit(Mult m) {
        // on évalue les sous-expressions via accept
        int leftVal = m.getLeft().accept(this);
        int rightVal = m.getRight().accept(this);
        return leftVal * rightVal;
    }
}
