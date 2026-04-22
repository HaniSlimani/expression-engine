package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval {

    private final Map<String, Integer> env;

    public VisitorEvalVar(Map<String, Integer> env) {
        this.env = env;
    }

    @Override
    public Integer visit(Var v) {
        if (!env.containsKey(v.getName())) {
            throw new UnsupportedOperationException("Variable " + v.getName() + " not in environment.");
        }
        return env.get(v.getName());
    }
}
