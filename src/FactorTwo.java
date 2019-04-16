import java.util.ArrayList;
import java.util.List;

public class FactorTwo implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][] table;
    FactorTwo(List<Character> vars, List<Float> values){
        table = new Float[2][2];
        tableValues = values;
        varables = vars;
        int val = 0;
        for (int i = 0; i < 2; i++) {
            for (int f = 0;f<2;f++){
                table[i][f] = values.get(val);
                val++;
            }
        }
    }

    @Override
    public int factorSize(){
        return 2;
    }

    @Override
    public boolean containsVar(Character var){
        return varables.contains(var);
    }

    @Override
    public List<Float> getVarValues(Character val, boolean value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            int v = value? 0 : 1;
            if(varables.indexOf(val)==0) {
                vals.add(table[v][0]);
                vals.add(table[v][1]);
            } else {
                vals.add(table[0][v]);
                vals.add(table[1][v]);
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
        return varables;
    }

    @Override
    public String toString() {
        char a = varables.get(0);
        char b = varables.get(1);
        String out= a + "," + b + " = " + table[0][0] + "\n" +
                a + ",~" + b + " = " + table[0][1] + "\n~" +
                a + "," + b + " = " + table[1][0] + "\n~" +
                a + ",~" + b + " = " + table[1][1];
        return out;
    }
}
