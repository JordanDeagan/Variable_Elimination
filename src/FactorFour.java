import java.util.ArrayList;
import java.util.List;

public class FactorFour implements Factor{
    private List<Character> varables;
    private List<Float> tableValues;
    private Float[][][][] table;
    private int size;

    FactorFour(List<Character> vars, List<Float> values, int numOptions){
        table = new Float[numOptions][numOptions][numOptions][numOptions];
        tableValues = values;
        varables = vars;
        size = numOptions;
        int val = 0;
        for (int a = 0; a < numOptions; a++) {
            for (int b = 0; b < numOptions; b++) {
                for (int c = 0;c<numOptions;c++){
                    for(int d = 0;d<numOptions;d++) {
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
    public List<Float> getVarValues(Character val, int value){
        if(this.containsVar(val)){
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(val)==0) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            vals.add(table[value][i][j][k]);
                        }
                    }
                }
            } else if(varables.indexOf(val)==1) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            vals.add(table[i][value][j][k]);
                        }
                    }
                }
            } else if(varables.indexOf(val)==2) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            vals.add(table[i][j][value][k]);
                        }
                    }
                }
            } else {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            vals.add(table[i][j][k][value]);
                        }
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
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val1][val2][i][j]);
                    }
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val1][i][val2][j]);
                    }
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val1][i][j][val2]);
                    }
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][val1][val2][j]);
                    }
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][val1][j][val2]);
                    }
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][j][val1][val2]);
                    }
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0) {
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val2][val1][i][j]);
                    }
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val2][i][val1][j]);
                    }
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[val2][i][j][val1]);
                    }
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][val2][val1][j]);
                    }
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][val2][j][val1]);
                    }
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2){
                for (int i = 0;i<size;i++) {
                    for (int j = 0; j < size; j++) {
                        vals.add(table[i][j][val2][val1]);
                    }
                }
            }
            return vals;
        }
        return null;
    }

    public List<Float> get3VarVals(Character var1, Character var2, Character var3, int val1, int val2, int val3){
        if(this.containsVar(var1) && this.containsVar(var2) && this.containsVar(var3)) {
            ArrayList<Float> vals = new ArrayList<>();
            if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1 && varables.indexOf(var3)==2) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][val2][val3][i]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==1 && varables.indexOf(var3)==3) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][val2][i][val3]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2 && varables.indexOf(var3)==3){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][i][val2][val3]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==2 && varables.indexOf(var3)==1){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][val3][val2][i]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3 && varables.indexOf(var3)==1){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][val3][i][val2]);
                }
            } else if(varables.indexOf(var1)==0 && varables.indexOf(var2)==3 && varables.indexOf(var3)==2){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val1][i][val3][val2]);
                }
            }
            else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0 && varables.indexOf(var3)==2) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][val1][val3][i]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==0 && varables.indexOf(var3)==3) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][val1][i][val3]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2 && varables.indexOf(var3)==3){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val1][val2][val3]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==2 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][val1][val2][i]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3 && varables.indexOf(var3)==2){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val1][val3][val2]);
                }
            } else if(varables.indexOf(var1)==1 && varables.indexOf(var2)==3 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][val1][i][val2]);
                }
            }
            else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0 && varables.indexOf(var3)==1) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][val3][val1][i]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==0 && varables.indexOf(var3)==3) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][i][val1][val3]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][val2][val1][i]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==1 && varables.indexOf(var3)==3){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val2][val1][val3]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][i][val1][val2]);
                }
            } else if(varables.indexOf(var1)==2 && varables.indexOf(var2)==3 && varables.indexOf(var3)==1){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val3][val1][val2]);
                }
            }
            else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0 && varables.indexOf(var3)==1) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][val3][i][val1]);
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==0 && varables.indexOf(var3)==2) {
                for (int i = 0;i<size;i++) {
                    vals.add(table[val2][i][val3][val1]);
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1 && varables.indexOf(var3)==2){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val2][val3][val1]);
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==1 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][val2][i][val1]);
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2 && varables.indexOf(var3)==0){
                for (int i = 0;i<size;i++) {
                    vals.add(table[val3][i][val2][val1]);
                }
            } else if(varables.indexOf(var1)==3 && varables.indexOf(var2)==2 && varables.indexOf(var3)==1){
                for (int i = 0;i<size;i++) {
                    vals.add(table[i][val3][val2][val1]);
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
//        char d = varables.get(3);
//        String out = a + "," + b + "," + c + "," + d + " = " + table[0][0][0][0] + "\n" +
//                a + "," + b + "," + c + ",~" + d + " = " + table[0][0][0][1] + "\n" +
//                a + "," + b + ",~" + c + "," + d + " = " + table[0][0][1][0] + "\n" +
//                a + "," + b + ",~" + c + ",~" + d + " = " + table[0][0][1][1] + "\n" +
//                a + ",~" + b + "," + c + "," + d + " = " + table[0][1][0][0] + "\n" +
//                a + ",~" + b + "," + c + ",~" + d + " = " + table[0][1][0][1] + "\n" +
//                a + ",~" + b + ",~" + c + "," + d + " = " + table[0][1][1][0] + "\n" +
//                a + ",~" + b + ",~" + c + ",~" + d + " = " + table[0][1][1][1] + "\n~" +
//                a + "," + b + "," + c + "," + d + " = " + table[1][0][0][0] + "\n~" +
//                a + "," + b + "," + c + ",~" + d + " = " + table[1][0][0][1] + "\n~" +
//                a + "," + b + ",~" + c + "," + d + " = " + table[1][0][1][0] + "\n~" +
//                a + "," + b + ",~" + c + ",~" + d + " = " + table[1][0][1][1] + "\n~" +
//                a + ",~" + b + "," + c + "," + d + " = " + table[1][1][0][0] + "\n~" +
//                a + ",~" + b + "," + c + ",~" + d + " = " + table[1][1][0][1] + "\n~" +
//                a + ",~" + b + ",~" + c + "," + d + " = " + table[1][1][1][0] + "\n~" +
//                a + ",~" + b + ",~" + c + ",~" + d + " = " + table[1][1][1][1];
//        return out;
//    }
}
