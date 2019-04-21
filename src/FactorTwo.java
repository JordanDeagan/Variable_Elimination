import java.util.ArrayList;
import java.util.List;

public class FactorTwo implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][] table;
    private int size;
    FactorTwo(List<Character> vars, List<Float> values, int numOptions){
        table = new Float[numOptions][numOptions];
        tableValues = values;
        varables = vars;
        size = numOptions;
        int val = 0;
        for (int i = 0; i < numOptions; i++) {
            for (int f = 0;f<numOptions;f++){
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
    public List<Float> getVarValues(Character val, int value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(val)==0) {
                for (int i = 0;i<size;i++){
                    vals.add(table[value][i]);
                }
            } else {
                for (int i = 0;i<size;i++){
                    vals.add(table[i][value]);
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
//        String out= a + "," + b + " = " + table[0][0] + "\n" +
//                a + ",~" + b + " = " + table[0][1] + "\n~" +
//                a + "," + b + " = " + table[1][0] + "\n~" +
//                a + ",~" + b + " = " + table[1][1];
//        return out;
//    }
}
