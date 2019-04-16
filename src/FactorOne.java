import java.util.ArrayList;
import java.util.List;

public class FactorOne implements Factor{
    private List<Character> variables;
    private List<Float> tableValues;
    private Float[] table;
    FactorOne(List<Character> vars, List<Float> values){
        table = new Float[2];
        tableValues = values;
        variables = vars;
        int val = 0;
        for (int f = 0;f<2;f++){
            table[f] = values.get(val);
            val++;
        }
    }

    @Override
    public int factorSize(){
        return 1;
    }

    @Override
    public boolean containsVar(Character var){
        return variables.contains(var);
    }

    @Override
    public List<Float> getVarValues(Character val, boolean value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            if (value){
                vals.add(table[0]);
            } else {
                vals.add(table[1]);
            }
            return vals;
        }
        return null;
    }

    @Override
    public List<Float> getAllValues() {
        return tableValues;
    }

    @Override
    public List<Character> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        char a = variables.get(0);
        String out = a + " = " + table[0] + "\n~" + a + " = " + table[1];
        return out;
    }
}
