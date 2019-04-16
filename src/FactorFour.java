import java.util.ArrayList;
import java.util.List;

public class FactorFour implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][][][] table;

    FactorFour(List<Character> vars, List<Float> values){
        table = new Float[2][2][2][2];
        tableValues = values;
        varables = vars;
        int val = 0;
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                for (int c = 0;c<2;c++){
                    for(int d = 0;d<2;d++) {
                        table[a][b][c][d] = values.get(val);
                        val++;
                    }
                }
            }
        }
    }

    @Override
    public int factorSize() {
        return 4;
    }

    @Override
    public boolean containsVar(Character var) {
        return varables.contains(var);
    }

    @Override
    public List<Float> getVarValues(Character val, boolean value){
        if(this.containsVar(val)){
            int v = value? 0 : 1;
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(val)==0) {
                vals.add(table[v][0][0][0]);
                vals.add(table[v][0][0][1]);
                vals.add(table[v][0][1][0]);
                vals.add(table[v][0][1][1]);
                vals.add(table[v][1][0][0]);
                vals.add(table[v][1][0][1]);
                vals.add(table[v][1][1][0]);
                vals.add(table[v][1][1][1]);
            } else if(varables.indexOf(val)==1) {
                vals.add(table[0][v][0][0]);
                vals.add(table[0][v][0][1]);
                vals.add(table[0][v][1][0]);
                vals.add(table[0][v][1][1]);
                vals.add(table[1][v][0][0]);
                vals.add(table[1][v][0][1]);
                vals.add(table[1][v][1][0]);
                vals.add(table[1][v][1][1]);
            } else if(varables.indexOf(val)==2) {
                vals.add(table[0][0][v][0]);
                vals.add(table[0][0][v][1]);
                vals.add(table[0][1][v][0]);
                vals.add(table[0][1][v][1]);
                vals.add(table[1][0][v][0]);
                vals.add(table[1][0][v][1]);
                vals.add(table[1][1][v][0]);
                vals.add(table[1][1][v][1]);
            } else {
                vals.add(table[0][0][0][v]);
                vals.add(table[0][0][1][v]);
                vals.add(table[0][1][0][v]);
                vals.add(table[0][1][1][v]);
                vals.add(table[1][0][0][v]);
                vals.add(table[1][0][1][v]);
                vals.add(table[1][1][0][v]);
                vals.add(table[1][1][1][v]);
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
                vals.add(table[v1][v2][0][0]);
                vals.add(table[v1][v2][0][1]);
                vals.add(table[v1][v2][1][0]);
                vals.add(table[v1][v2][1][1]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2){
                vals.add(table[v1][0][v2][0]);
                vals.add(table[v1][0][v2][1]);
                vals.add(table[v1][1][v2][0]);
                vals.add(table[v1][1][v2][1]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3){
                vals.add(table[v1][0][0][v2]);
                vals.add(table[v1][0][1][v2]);
                vals.add(table[v1][1][0][v2]);
                vals.add(table[v1][1][1][v2]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2){
                vals.add(table[0][v1][v2][0]);
                vals.add(table[1][v1][v2][1]);
                vals.add(table[0][v1][v2][0]);
                vals.add(table[1][v1][v2][1]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3){
                vals.add(table[0][v1][0][v2]);
                vals.add(table[0][v1][1][v2]);
                vals.add(table[1][v1][0][v2]);
                vals.add(table[1][v1][1][v2]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3){
                vals.add(table[0][0][v1][v2]);
                vals.add(table[0][1][v1][v2]);
                vals.add(table[1][0][v1][v2]);
                vals.add(table[1][1][v1][v2]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0) {
                vals.add(table[v2][v1][0][0]);
                vals.add(table[v2][v1][0][1]);
                vals.add(table[v2][v1][1][0]);
                vals.add(table[v2][v1][1][1]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0){
                vals.add(table[v2][0][v1][0]);
                vals.add(table[v2][0][v1][1]);
                vals.add(table[v2][1][v1][0]);
                vals.add(table[v2][1][v1][1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0){
                vals.add(table[v2][0][0][v1]);
                vals.add(table[v2][0][1][v1]);
                vals.add(table[v2][1][0][v1]);
                vals.add(table[v2][1][1][v1]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1){
                vals.add(table[0][v2][v1][0]);
                vals.add(table[1][v2][v1][1]);
                vals.add(table[0][v2][v1][0]);
                vals.add(table[1][v2][v1][1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1){
                vals.add(table[0][v2][0][v1]);
                vals.add(table[0][v2][1][v1]);
                vals.add(table[1][v2][0][v1]);
                vals.add(table[1][v2][1][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2){
                vals.add(table[0][0][v2][v1]);
                vals.add(table[0][1][v2][v1]);
                vals.add(table[1][0][v2][v1]);
                vals.add(table[1][1][v2][v1]);
            }
            return vals;
        }
        return null;
    }

    public List<Float> get3VarVals(Character var1, Character var2, Character var3, boolean val1, boolean val2, boolean val3){
        if(this.containsVar(var1) && this.containsVar(var2) && this.containsVar(var3)) {
            int v1 = val1 ? 0 : 1;
            int v2 = val2 ? 0 : 1;
            int v3 = val3 ? 0 : 1;
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1 && varables.indexOf(var3)==2) {
                vals.add(table[v1][v2][v3][0]);
                vals.add(table[v1][v2][v3][1]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1 && varables.indexOf(var3)==3) {
                vals.add(table[v1][v2][0][v3]);
                vals.add(table[v1][v2][1][v3]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2 && varables.indexOf(var3)==3){
                vals.add(table[v1][0][v2][v3]);
                vals.add(table[v1][1][v2][v3]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2 && varables.indexOf(var3)==1){
                vals.add(table[v1][v3][v2][0]);
                vals.add(table[v1][v3][v2][1]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3 && varables.indexOf(var3)==2){
                vals.add(table[v1][v3][0][v2]);
                vals.add(table[v1][v3][1][v2]);
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3 && varables.indexOf(var3)==2){
                vals.add(table[v1][0][v3][v2]);
                vals.add(table[v1][1][v3][v2]);
            }
            else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0 && varables.indexOf(var3)==2) {
                vals.add(table[v2][v1][v3][0]);
                vals.add(table[v2][v1][v3][1]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0 && varables.indexOf(var3)==3) {
                vals.add(table[v2][v1][0][v3]);
                vals.add(table[v2][v1][1][v3]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2 && varables.indexOf(var3)==3){
                vals.add(table[0][v1][v2][v3]);
                vals.add(table[1][v1][v2][v3]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2 && varables.indexOf(var3)==0){
                vals.add(table[v3][v1][v2][0]);
                vals.add(table[v3][v1][v2][1]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3 && varables.indexOf(var3)==2){
                vals.add(table[0][v1][v3][v2]);
                vals.add(table[1][v1][v3][v2]);
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3 && varables.indexOf(var3)==0){
                vals.add(table[v3][v1][0][v2]);
                vals.add(table[v3][v1][1][v2]);
            }
            else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0 && varables.indexOf(var3)==1) {
                vals.add(table[v2][v3][v1][0]);
                vals.add(table[v2][v3][v1][1]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0 && varables.indexOf(var3)==3) {
                vals.add(table[v2][0][v1][v3]);
                vals.add(table[v2][1][v1][v3]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1 && varables.indexOf(var3)==0){
                vals.add(table[v3][v2][v1][0]);
                vals.add(table[v3][v2][v1][1]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1 && varables.indexOf(var3)==3){
                vals.add(table[0][v2][v1][v3]);
                vals.add(table[1][v2][v1][v3]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3 && varables.indexOf(var3)==0){
                vals.add(table[v3][0][v1][v2]);
                vals.add(table[v3][1][v1][v2]);
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3 && varables.indexOf(var3)==1){
                vals.add(table[0][v3][v1][v2]);
                vals.add(table[1][v3][v1][v2]);
            }
            else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0 && varables.indexOf(var3)==1) {
                vals.add(table[v2][v3][0][v1]);
                vals.add(table[v2][v3][1][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0 && varables.indexOf(var3)==2) {
                vals.add(table[v2][0][v3][v1]);
                vals.add(table[v2][1][v3][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1 && varables.indexOf(var3)==2){
                vals.add(table[0][v2][v3][v1]);
                vals.add(table[1][v2][v3][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1 && varables.indexOf(var3)==0){
                vals.add(table[v3][v2][0][v1]);
                vals.add(table[v3][v2][1][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2 && varables.indexOf(var3)==0){
                vals.add(table[v3][0][v2][v1]);
                vals.add(table[v3][1][v2][v1]);
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2 && varables.indexOf(var3)==1){
                vals.add(table[0][v3][v2][v1]);
                vals.add(table[1][v3][v2][v1]);
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
        char d = varables.get(3);
        String out = a + "," + b + "," + c + "," + d + " = " + table[0][0][0][0] + "\n" +
                a + "," + b + "," + c + ",~" + d + " = " + table[0][0][0][1] + "\n" +
                a + "," + b + ",~" + c + "," + d + " = " + table[0][0][1][0] + "\n" +
                a + "," + b + ",~" + c + ",~" + d + " = " + table[0][0][1][1] + "\n" +
                a + ",~" + b + "," + c + "," + d + " = " + table[0][1][0][0] + "\n" +
                a + ",~" + b + "," + c + ",~" + d + " = " + table[0][1][0][1] + "\n" +
                a + ",~" + b + ",~" + c + "," + d + " = " + table[0][1][1][0] + "\n" +
                a + ",~" + b + ",~" + c + ",~" + d + " = " + table[0][1][1][1] + "\n~" +
                a + "," + b + "," + c + "," + d + " = " + table[1][0][0][0] + "\n~" +
                a + "," + b + "," + c + ",~" + d + " = " + table[1][0][0][1] + "\n~" +
                a + "," + b + ",~" + c + "," + d + " = " + table[1][0][1][0] + "\n~" +
                a + "," + b + ",~" + c + ",~" + d + " = " + table[1][0][1][1] + "\n~" +
                a + ",~" + b + "," + c + "," + d + " = " + table[1][1][0][0] + "\n~" +
                a + ",~" + b + "," + c + ",~" + d + " = " + table[1][1][0][1] + "\n~" +
                a + ",~" + b + ",~" + c + "," + d + " = " + table[1][1][1][0] + "\n~" +
                a + ",~" + b + ",~" + c + ",~" + d + " = " + table[1][1][1][1];
        return out;
    }
}
