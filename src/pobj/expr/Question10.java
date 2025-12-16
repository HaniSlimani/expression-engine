package pobj.expr;

public class Question10 {

    // retourne vrai si e est un arbre d’expression constant
    public static boolean isConstant(Expression e) {
        VisitorConstant vc = new VisitorConstant();
        return e.accept(vc);
    }

    // retourne la valeur d’une expression constante
    // signale une exception si l’expression n’est pas constante
    public static int evalConstantExpression(Expression e) {
        if (!isConstant(e)) {
            throw new UnsupportedOperationException("Expression is not constant");
        }
        VisitorEval ve = new VisitorEval();
        return e.accept(ve);
    }

    // Visiteur pour déterminer si une expression est constante
    private static class VisitorConstant implements IVisitor<Boolean> {

        @Override
        public Boolean visit(Constant c) {
            return true;
        }

        @Override
        public Boolean visit(Var v) {
            return false;
        }

        @Override
        public Boolean visit(Add a) {
            return a.getLeft().accept(this) && a.getRight().accept(this);
        }

        @Override
        public Boolean visit(Mult m) {
            return m.getLeft().accept(this) && m.getRight().accept(this);
        }
    }
}
