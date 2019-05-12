public interface Scope {

    public String getScopeName();

    /** Next scope and its symbols */
    public Scope getEnclosingScope();

    /** A symbol in current scope */
    public void define(Symbol sym);

    /** Look up name in this scope or in enclosing scope if not here */
    public Symbol resolve(String name);

}
