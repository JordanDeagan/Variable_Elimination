import java.util.ArrayList;
import java.util.List;

public class FactorOne implements Factor{
    private List<Character> variables;
    private List<Float> tableValues;
    private Float[] table;
    private int size;
    FactorOne(List<Character> vars, List<Float> values, int numOptions){
        table = new Float[numOptions];
        tableValues = values;
        variables = vars;
        size = numOptions;
        int val = 0;
        for (int f = 0;f<numOptions;f++){
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
    public List<Float> getVarValues(Character val, int value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            vals.add(table[value]);
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
    public int getSize() {
        return size;
    }

//    @Override
//    public String toString() {
//        char a = variables.get(0);
//        String out = a + " = " + table[0] + "\n~" + a + " = " + table[1];
//        return out;
//    }
}
