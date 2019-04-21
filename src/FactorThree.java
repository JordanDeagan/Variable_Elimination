import java.util.ArrayList;
import java.util.List;

public class FactorThree implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][][] table;
    private int size;
    FactorThree(List<Character> vars, List<Float> values, int numOptions){
        table = new Float[numOptions][numOptions][numOptions];
        tableValues = values;
        varables = vars;
        size = numOptions;
        int val = 0;
        for (int v = 0; v < numOptions; v++) {
            for (int i = 0; i < numOptions; i++) {
                for (int f = 0;f<numOptions;f++){
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
    public List<Float> getVarValues(Character val, int value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(val)==0) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[value][i][j]);
                    }
                }
            } else if(varables.indexOf(val)==1) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][value][j]);
                    }
                }
            } else {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][j][value]);
                    }
                }
            }
            return vals;
        }
        return null;
    }

    public List<Float> get2VarVals(Character var1, Character var2, int val1, int val2){
        if(this.containsVar(var1) && this.containsVar(var2)) {
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1) {
                for (int i = 0;i<size;i++){
                    vals.add(table[val1][val2][i]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2){
                for (int i = 0;i<size;i++){
                    vals.add(table[val1][i][val2]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0) {
                for (int i = 0;i<size;i++){
                    vals.add(table[val2][val1][i]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2){
                for (int i = 0;i<size;i++){
                    vals.add(table[i][val1][val2]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0) {
                for (int i = 0;i<size;i++){
                    vals.add(table[val2][i][val1]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1){
                for (int i = 0;i<size;i++){
                    vals.add(table[i][val2][val1]);
                }
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
    public int getSize() {
        return size;
    }
//    @Override
//    public String toString() {
//        char a = varables.get(0);
//        char b = varables.get(1);
//        char c = varables.get(2);
//        String out = a + "," + b + "," + c + " = " + table[0][0][0] + "\n" +
//                a + "," + b + ",~" + c + " = " + table[0][0][1] + "\n" +
//                a + ",~" + b + "," + c + " = " + table[0][1][0] + "\n" +
//                a + ",~" + b + ",~" + c + " = " + table[0][1][1] + "\n~" +
//                a + "," + b + "," + c + " = " + table[1][0][0] + "\n~" +
//                a + "," + b + ",~" + c + " = " + table[1][0][1] + "\n~" +
//                a + ",~" + b + "," + c + " = " + table[1][1][0] + "\n~" +
//                a + ",~" + b + ",~" + c + " = " + table[1][1][1];
//        return out;
//    }
}
