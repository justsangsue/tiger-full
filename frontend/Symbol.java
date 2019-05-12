/**
 * Excerpted and modified from "The Definitive ANTLR 4 Reference",
 **/

public class Symbol { // A generic programming language symbol

    String name;      // All symbols at least have a name
    Type type;
    Scope scope;      // All symbols know what scope contains them.
    String typeName;  // when type is user define type this variable is a clue.

    public Symbol(String name) { this.name = name; }
    public Symbol(String name, Type type) { this(name); this.type = type; }
    public String getName() { return name; }

    public String toString() {
        if ( type!=Type.tVoid ) return '<' + getName() + ":" + type+'>';
        return getName();
    }

    public Type getType(){
        return type;
    }
    public void setType(Type _type){
        type = _type;
    }
}