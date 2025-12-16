package pobj.expr;


public class Constant implements Expression {

    private final int value;

    // Constructeur sans argument : valeur nulle
    public Constant() {
        this.value = 0;
    }

    // Constructeur avec argument
    public Constant(int value) {
        this.value = value;
    }

    // Getter
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Constant))
            return false;
        Constant other = (Constant) obj;
        return this.value == other.value;
    }
    @Override
    public int eval() {
        return value;
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
