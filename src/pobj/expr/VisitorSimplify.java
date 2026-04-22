package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

    @Override
    public Expression visit(Constant c) {
        return c; 
    }

    @Override
    public Expression visit(Var v) {
        return v; 
    }

    @Override
    public Expression visit(Add a) {
        Expression leftSimpl = a.getLeft().accept(this);
        Expression rightSimpl = a.getRight().accept(this);

        if (Question10.isConstant(leftSimpl) && Question10.isConstant(rightSimpl)) {
            int val = Question10.evalConstantExpression(
                    new Add(leftSimpl, rightSimpl)
            );
            return new Constant(val);
        }

        if (Question10.isConstant(leftSimpl) && Question10.evalConstantExpression(leftSimpl) == 0) {
            return rightSimpl;
        }
        if (Question10.isConstant(rightSimpl) && Question10.evalConstantExpression(rightSimpl) == 0) {
            return leftSimpl;
        }

        return new Add(leftSimpl, rightSimpl);
    }

    @Override
    public Expression visit(Mult m) {
        Expression leftSimpl = m.getLeft().accept(this);
        Expression rightSimpl = m.getRight().accept(this);

        if (Question10.isConstant(leftSimpl) && Question10.isConstant(rightSimpl)) {
            int val = Question10.evalConstantExpression(
                    new Mult(leftSimpl, rightSimpl)
            );
            return new Constant(val);
        }

        if ((Question10.isConstant(leftSimpl) && Question10.evalConstantExpression(leftSimpl) == 0)
                || (Question10.isConstant(rightSimpl) && Question10.evalConstantExpression(rightSimpl) == 0)) {
            return new Constant(0);
        }

        if (Question10.isConstant(leftSimpl) && Question10.evalConstantExpression(leftSimpl) == 1) {
            return rightSimpl;
        }
        if (Question10.isConstant(rightSimpl) && Question10.evalConstantExpression(rightSimpl) == 1) {
            return leftSimpl;
        }

        return new Mult(leftSimpl, rightSimpl);
    }
}
