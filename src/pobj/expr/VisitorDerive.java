package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {

    private final Var var;

    public VisitorDerive(Var var) {
        this.var = var;
    }

    @Override
    public Expression visit(Constant c) {
        return new Constant(0);
    }

    @Override
    public Expression visit(Var v) {
        if (v.getName().equals(var.getName())) {
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }

    @Override
    public Expression visit(Add a) {
        Expression leftDer = a.getLeft().accept(this);
        Expression rightDer = a.getRight().accept(this);
        return new Add(leftDer, rightDer);
    }

    @Override
    public Expression visit(Mult m) {
        Expression leftDer = m.getLeft().accept(this);
        Expression rightDer = m.getRight().accept(this);
        Expression part1 = new Mult(m.getLeft(), rightDer);
        Expression part2 = new Mult(leftDer, m.getRight());
        return new Add(part1, part2);
    }
}
