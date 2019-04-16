import java.util.ArrayList;
import java.util.List;

public class FactorThree implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][][] table;
    FactorThree(List<Character> vars, List<Float> values){
        table = new Float[2][2][2];
        tableValues = values;
        varables = vars;
        int val = 0;
        for (int v = 0; v < 2; v++) {
            for (int i = 0; i < 2; i++) {
                for (int f = 0;f<2;f++){
                    table[v][i][f] = values.get(val);
                    val++;
                }
            }
        }
    }

    @Override
    public int factorSize(){
        return 3;
    }

    @Override
    public boolean containsVar(Character var){
        return varables.contains(var);
    }

    @Override
    public List<Float> getVarValues(Character val, boolean value){
        if(this.containsVar(val)){
            int v = value? 0 : 1;
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(val)==0) {
                vals.add(table[v][0][0]);
                vals.add(table[v][0][1]);
                vals.add(table[v][1][0]);
                vals.add(table[v][1][1]);
            } else if(varables.indexOf(val)==1) {
                vals.add(table[0][v][0]);
                vals.add(table[0][v][1]);
                vals.add(table[1][v][0]);
                vals.add(table[1][v][1]);
            } else {
                vals.add(table[0][0][v]);
                vals.add(table[0][1][v]);
                vals.add(table[1][0][v]);
                vals.add(table[1][1][v]);
            }
            return vals;
        }
        return null;
    }

    public List<Float> get2VarVals(Character var1, Character var2, boolean val1, boolean val2){
        if(this.containsVar(var1) && this.containsVar(var2)) {
            int v1 = val1 ? 0 : 1;
            int v2 = val2 ? 0 : 1;
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1) {
                vals.add(table[v1][v2][0]);
                vals.add(table[v1][v2][1]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2){
                vals.add(table[v1][0][v2]);
                vals.add(table[v1][1][v2]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0) {
                vals.add(table[v2][v1][0]);
                vals.add(table[v2][v1][1]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2){
                vals.add(table[0][v1][v2]);
                vals.add(table[1][v1][v2]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0) {
                vals.add(table[v2][0][v1]);
                vals.add(table[v2][1][v1]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1){
                vals.add(table[0][v2][v1]);
                vals.add(table[1][v2][v1]);
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
        char c = varables.get(2);
        String out = a + "," + b + "," + c + " = " + table[0][0][0] + "\n" +
                a + "," + b + ",~" + c + " = " + table[0][0][1] + "\n" +
                a + ",~" + b + "," + c + " = " + table[0][1][0] + "\n" +
                a + ",~" + b + ",~" + c + " = " + table[0][1][1] + "\n~" +
                a + "," + b + "," + c + " = " + table[1][0][0] + "\n~" +
                a + "," + b + ",~" + c + " = " + table[1][0][1] + "\n~" +
                a + ",~" + b + "," + c + " = " + table[1][1][0] + "\n~" +
                a + ",~" + b + ",~" + c + " = " + table[1][1][1];
        return out;
    }
}
