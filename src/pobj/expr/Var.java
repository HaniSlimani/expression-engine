package pobj.expr;

public class Var implements Expression {

    private final String name;

    // Constructeur
    public Var(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Var))
            return false;
        Var other = (Var) obj;
        return this.name.equals(other.name);
    }
    @Override
    public int eval() {
        throw new UnsupportedOperationException("Cannot evaluate a variable.");
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
