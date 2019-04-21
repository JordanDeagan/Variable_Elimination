import java.util.*;

public class Var_elim {
    Var_elim(){}

    Factor restrict(Factor factor, Character variable, int value){
        Factor result = null;
        ArrayList<Character> partial = new ArrayList<>(factor.getVariables());
        partial.remove(variable);
        if(factor.factorSize()==2){
            result = this.new1Factor(partial,factor.getVarValues(variable,value),factor.getSize());
        } else if (factor.factorSize()==3){
            result = this.new2Factor(partial,factor.getVarValues(variable,value),factor.getSize());
        } else if (factor.factorSize()==4){
            result = this.new3Factor(partial,factor.getVarValues(variable,value),factor.getSize());
        }
        return result;
    }

    Factor multiple(Factor factor1, Factor factor2){
        Factor result = null;
        int size = factor1.getSize();
        ArrayList<Float> mult = new ArrayList<>();
        ArrayList<Character> vars = new ArrayList<>();
        if(factor1.factorSize()==1 || factor2.factorSize()==1){
            Factor single;
            Factor more;
            if (factor1.factorSize()==1){
                single = factor1;
                more = factor2;
            } else {
                single = factor2;
                more = factor1;
            }
            Character onCombine = single.getVariables().get(0);
            ArrayList<Float> original = new ArrayList<>();
            for (int i = 0;i<size;i++){
                original.add(single.getVarValues(onCombine,i).get(0));
            }
            ArrayList<ArrayList<Float>> extra = new ArrayList<>();
            for (int i = 0;i<size;i++){
                extra.add(new ArrayList<>(more.getVarValues(onCombine,i)));
            }
            for (int i = 0;i<size;i++){
                for(int j = 0;j<extra.get(i).size();j++){
                    extra.get(i).set(j,extra.get(i).get(j)*original.get(i));
                }
            }
            vars = new ArrayList<>(more.getVariables());
            if(more.factorSize()==2){
                if (more.getVariables().indexOf(onCombine)==0){
                    for(int i = 0;i<size;i++){
                        mult.addAll(extra.get(i));
                    }
                } else {
                    for(int i = 0;i<size;i++){
                        for(int j = 0;j<size;j++) {
                            mult.add(extra.get(j).get(i));
                        }
                    }
                }
                result = this.new2Factor(vars,mult,size);
            } else if(more.factorSize()==3){
                if (more.getVariables().indexOf(onCombine)==0){
                    for(int i = 0;i<size;i++){
                        mult.addAll(extra.get(i));
                    }
                } else if (more.getVariables().indexOf(onCombine)==1) {
                    for (int i = 0; i < Math.pow(size , 2); i += size) {
                        for (int j = 0; j < size; j++) {
                            for (int k = 0;k<size;k++) {
                                mult.add(extra.get(j).get(i+k));
                            }
                        }
                    }
                } else {
                    for(int i = 0;i<Math.pow(size,2);i++){
                        for(int j = 0;j<size;j++) {
                            mult.add(extra.get(j).get(i));
                        }
                    }
                }
                result = this.new3Factor(vars,mult,size);
            } else {
//                System.out.println("4");
                if (more.getVariables().indexOf(onCombine)==0){
//                    System.out.println("0");
                    for(int i = 0;i<size;i++){
                        mult.addAll(extra.get(i));
                    }
                } else if (more.getVariables().indexOf(onCombine)==1) {
//                    System.out.println("1");
//                    System.out.println("size = "+size);
                    for (int i = 0; i < Math.pow(size,3); i+=Math.pow(size,2)) {
//                        System.out.println("i = "+i);
                        for (int j = 0; j < size; j++) {
//                            System.out.println("j = "+j);
                            for (int k = 0; k < Math.pow(size,2); k++) {
//                                System.out.println("k = "+k);
                                mult.add(extra.get(j).get(i+k));
                            }
                        }
                    }
                } else if(more.getVariables().indexOf(onCombine)==2){
//                    System.out.println("2");
                    for (int i = 0; i < Math.pow(size,3); i+=size) {
                        for (int j = 0; j < size; j++) {
                            for (int k = 0; k < size; k++) {
                                mult.add(extra.get(j).get(i+k));
                            }
                        }
                    }
                } else {
//                    System.out.println("3");
                    for(int i = 0;i<Math.pow(size,3);i++){
                        for(int j = 0;j<size;j++) {
                            mult.add(extra.get(j).get(i));
                        }
                    }
                }
                result = this.new4Factor(vars, mult,size);
            }
        } else if(factor1.factorSize()==2 || factor2.factorSize()==2) {
            Factor two;
            Factor other;
            if(factor1.factorSize()==2){
                two = factor1;
                other = factor2;
            } else {
                two = factor2;
                other = factor1;
            }
            ArrayList<Character> onCombine = new ArrayList<>(other.getVariables());
            onCombine.retainAll(two.getVariables());
            if(onCombine.size()==1){
                Character onC = onCombine.get(0);
                vars = new ArrayList<>(other.getVariables());
                ArrayList<Character> alt = new ArrayList<>(two.getVariables());
                alt.remove(onC);
                vars.addAll(alt);

                ArrayList<ArrayList<Float>> original = new ArrayList<>();
                for (int i = 0;i<size;i++){
                    original.add(new ArrayList<>(two.getVarValues(onC,i)));
                }

                ArrayList<ArrayList<Float>> extra = new ArrayList<>();
                for (int i = 0;i<size;i++){
                    extra.add(new ArrayList<>(other.getVarValues(onC,i)));
                }

                ArrayList<ArrayList<Float>> temp = new ArrayList<>();
                for(int i = 0;i<Math.pow(size,other.factorSize());i++){
                    temp.add(new ArrayList<>());
                }

                for(int i = 0;i<size;i++){
                    for(int j = 0;j<Math.pow(size,(other.factorSize()-1));j++) {
                        for(int k = 0;k<size;k++) {
                            temp.get(i * (int)Math.pow(size,(other.factorSize()-1)) + j).add(extra.get(i).get(j) * original.get(i).get(k));
                        }
                    }
                }
                if(other.factorSize()==2){
                    if (other.getVariables().indexOf(onC) == 0) {
                        for (int i = 0;i<temp.size();i++) {
                            mult.addAll(temp.get(i));
                        }
                    } else {
                        for (int i = 0;i<size;i++) {
                            for (int j = 0;j<Math.pow(size,2);i+=size) {
                                mult.addAll(temp.get(i+j));
                            }
                        }
                    }
                    result = this.new3Factor(vars, mult,size);
                } else if (other.factorSize()==3){
                    if(other.getVariables().indexOf(onC)==0){
                        for (int i = 0;i<(size*size);i++) {
                            mult.addAll(temp.get(i));
                        }
                    } else if (other.getVariables().indexOf(onC)==1){
                        for (int i = 0;i<size;i++) {
                            for (int j = 0;j<Math.pow(size,3);i+=size) {
                                mult.addAll(temp.get(i+j));
                            }
                        }
                    } else {
                        for (int i = 0;i<Math.pow(size,2);i+=size) {
                            for (int j = 0;j<Math.pow(size,3);i+=Math.pow(size,2)) {
                                for (int k = 0;k<size;k++) {
                                    mult.addAll(temp.get(i + j + k));
                                }
                            }
                        }
                    }
                    result = this.new4Factor(vars, mult,size);
                }
            } else if(onCombine.size()==2){
                vars = new ArrayList<>(other.getVariables());
                if(vars.indexOf(onCombine.get(0))>vars.indexOf(onCombine.get(1))){
                    onCombine = new ArrayList<>(Arrays.asList(onCombine.get(1),onCombine.get(0)));
                }
                ArrayList<Float> twoValues = new ArrayList<>();
                for (int i = 0;i<size;i++){
                    twoValues.addAll(two.getVarValues(onCombine.get(0),i));
                }
                if(other.factorSize()==2){
                    mult=new ArrayList<>(other.getAllValues());
                    result = this.new2Factor(vars, mult, size);
                } else if(other.factorSize()==3){
                    FactorThree three = (FactorThree)other;

                    ArrayList<ArrayList<Float>> threeVals = new ArrayList<>();
                    for (int i = 0;i<size;i++){
                        for (int j = 0;j<size;j++) {
                            threeVals.add(new ArrayList<>(three.get2VarVals(onCombine.get(0),onCombine.get(1),i,j)));
                        }
                    }
                    if(three.getVariables().indexOf(onCombine.get(0))==0 && three.getVariables().indexOf(onCombine.get(1))==1){
                        for(int i = 0;i<Math.pow(size,2);i++){
                            for (int j = 0;j<size;j++){
                                mult.add(threeVals.get(i).get(j) * twoValues.get(i));
                            }
                        }
                    } else if(three.getVariables().indexOf(onCombine.get(0))==0 && three.getVariables().indexOf(onCombine.get(1))==2){
                        for(int i = 0;i<Math.pow(size,2);i+=size){
                            for (int j = 0;j<size;j++){
                                for (int k = 0;k<size;k++) {
                                    for (int l = 0;l<size;l++) {
                                        mult.add(threeVals.get(i+l).get(j + k) * twoValues.get(i+l));
                                    }
                                }
                            }
                        }
                    } else if(three.getVariables().indexOf(onCombine.get(0))==1 && three.getVariables().indexOf(onCombine.get(1))==2){
                        for (int k = 0;k<Math.pow(size , 2);k+=size) {
                            for (int i = 0; i < size; i++) {
                                for (int j = 0; j < size; j++) {
                                    mult.add(threeVals.get(i).get(j+k) * twoValues.get(i));
                                }
                            }
                        }
                    }
                    result = this.new3Factor(vars, mult,size);
                } else if(other.factorSize()==4){
                    FactorFour four = (FactorFour)other;

                    ArrayList<ArrayList<Float>> fourVals = new ArrayList<>();
                    for (int i = 0;i<size;i++){
                        for (int j = 0;j<size;j++) {
                            fourVals.add(new ArrayList<>(four.get2VarVals(onCombine.get(0),onCombine.get(1),i,j)));
                        }
                    }
                    if((four.getVariables().indexOf(onCombine.get(0))==0 && four.getVariables().indexOf(onCombine.get(1))==1)){
                        for(int i = 0;i<Math.pow(size,2);i++){
                            for (int j = 0;j<Math.pow(size,2);j++){
                                mult.add(fourVals.get(i).get(j) * twoValues.get(i));
                            }
                        }
                    } else if((four.getVariables().indexOf(onCombine.get(0))==0 && four.getVariables().indexOf(onCombine.get(1))==2)){
                        for(int i = 0;i<Math.pow(size,2);i+=size){
                            for (int j = 0;j<Math.pow(size,2);j+=size){
                                for (int l = 0;l<size;l++) {
                                    for (int k = 0;k<size;k++) {
                                        mult.add(fourVals.get(i+l).get(j + k) * twoValues.get(i+l));
                                    }
                                }
                            }
                        }
                    } else if((four.getVariables().indexOf(onCombine.get(0))==0 && four.getVariables().indexOf(onCombine.get(1))==3)){
                        for(int i = 0;i<Math.pow(size,2);i+=size){
                            for (int j = 0;j<Math.pow(size,2);j++){
                                for (int l = 0;l<size;l++) {
                                    mult.add(fourVals.get(i+l).get(j) * twoValues.get(i+l));
                                }
                            }
                        }
                    } else if((four.getVariables().indexOf(onCombine.get(0))==1 && four.getVariables().indexOf(onCombine.get(1))==2)){
                        for(int i = 0;i<Math.pow(size,2);i+=size){
                            for (int j = 0;j<Math.pow(size,2);j++) {
                                for (int k = 0;k<size;k++){
                                    mult.add(fourVals.get(j).get(i+k) * twoValues.get(j));
                                }
                            }
                        }
                    } else if((four.getVariables().indexOf(onCombine.get(0))==1 && four.getVariables().indexOf(onCombine.get(1))==3)){
                        for(int i = 0;i<Math.pow(size,2);i+=size){
                            for (int j = 0;j<Math.pow(size,2);j+=size) {
                                for (int k = 0;k<size;k++){
                                    for (int l = 0;l<size;l++) {
                                        mult.add(fourVals.get(j+l).get(i + k) * twoValues.get(j+l));
                                    }
                                }
                            }
                        }
                    } else if((four.getVariables().indexOf(onCombine.get(0))==2 && four.getVariables().indexOf(onCombine.get(1))==3)){
                        for(int i = 0;i<Math.pow(size,2);i++){
                            for (int j = 0;j<Math.pow(size,2);j++) {
                                mult.add(fourVals.get(j).get(i) * twoValues.get(j));
                            }
                        }
                    }
                    result = this.new4Factor(vars, mult,size);
                }
            }
        }
//        System.out.println("result = "+result);
        return result;
    }

    Factor sumOut(Factor factor, Character variable){
        ArrayList<Character> partial = new ArrayList<>(factor.getVariables());
        partial.remove(variable);
        int size = factor.getSize();
        Factor result = null;
        if(factor.factorSize()==2){
            ArrayList<Float> summed = new ArrayList<>();
            for (int i = 0;i<size;i++){
                Float temp = 0f;
                for (Float num: factor.getVarValues(partial.get(0),i)){
                    temp+=num;
                }
                summed.add(temp);
            }
            result = this.new1Factor(partial,summed, size);
        } else if (factor.factorSize()==3){
            FactorThree useful = (FactorThree)factor;
            ArrayList<Float> summed = new ArrayList<>();
            for (int i = 0;i<size;i++){
                for (int j = 0;j<size;j++){
                    Float temp = 0f;
                    for (Float num: useful.get2VarVals(partial.get(0),partial.get(1),i,j)){
                        temp+=num;
                    }
                    summed.add(temp);
                }
            }
            result = this.new2Factor(partial,summed, size);
        } else if (factor.factorSize()==4){
            FactorFour useful = (FactorFour)factor;
            ArrayList<Float> summed = new ArrayList<>();
            for (int i = 0;i<size;i++){
                for (int j = 0;j<size;j++){
                    for (int k = 0;k<size;k++){
                        Float temp = 0f;
                        for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),i,j,k)){
                            temp+=num;
                        }
                        summed.add(temp);
                    }
                }
            }
            result = this.new3Factor(partial,summed, size);
        }
        return result;
    }

    public Factor inference(List<Factor> factorList, Character quereyVars, List<Character> orderedHiddenVars, Map<Character,Integer> evidence){
        ArrayList<Factor> factors=new ArrayList<>(factorList);
        for(Map.Entry<Character,Integer> entry:evidence.entrySet()){
            for(int j = 0;j<factors.size();j++){
                if(factors.get(j).containsVar(entry.getKey())){
                    Factor temp = this.restrict(factors.get(j),entry.getKey(),entry.getValue());
                    if (temp==null) throw new AssertionError("restrict returned null");
                    factors.set(j,temp);
                }
//                System.out.println(factors.get(j));
            }
        }
//        System.out.println("restricted");
        for(Character var:orderedHiddenVars){
            factors = this.multiplyAndSum(factors,var);
        }
//        System.out.println("multiplied");
        if(factors.size()!=1){
            throw new AssertionError("not all factor's summed out");
        }
        return this.normalize(factors.get(0));
    }

    private ArrayList<Factor> multiplyAndSum(ArrayList<Factor> factors, Character sumOn){
        Factor f1 = null;
        ArrayList<Factor> result = new ArrayList<>();
        for (Factor f:factors){
//            System.out.println(f);
            if(f.containsVar(sumOn)){
                if(f1==null){
                    f1=f;
                } else {
                    Factor temp = this.multiple(f1,f);
                    if(temp==null) throw new AssertionError("Multiply returned null");
                    f1 = temp;
                }
            } else {
                result.add(f);
            }
        }
//        System.out.println("Multiple and Sum");
        if(f1==null){
            return result;
        }
        result.add(this.sumOut(f1,sumOn));
        return result;
    }

    private Factor normalize(Factor factor){
        ArrayList<Float> old = new ArrayList<>(factor.getAllValues());
        float total = 0;
        for (float num:old){
            total += num;
        }
        ArrayList<Float> newF = new ArrayList<>();
        for (float num:old){
            newF.add(num/total);
        }
        Factor simplified = null;
        if(factor.factorSize()==1){
            simplified = this.new1Factor(factor.getVariables(),newF, factor.getSize());
        } else if (factor.factorSize()==2){
            simplified = this.new2Factor(factor.getVariables(),newF, factor.getSize());
        } else if (factor.factorSize()==3){
            simplified = this.new3Factor(factor.getVariables(),newF, factor.getSize());
        }
        return simplified;
    }

//    Factor new1Factor(){
//        ArrayList<Character> vars = new ArrayList<>(Collections.singletonList('a'));
//        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.9f, .1f));
//        return new FactorOne(vars, vals);
//    }
    Factor new1Factor(List<Character> vars, List<Float> vals, int size){
        return new FactorOne(vars, vals,size);
    }

//    Factor new2Factor(){
//        ArrayList<Character> vars = new ArrayList<>(Arrays.asList('a', 'b'));
//        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.9f, .1f, .4f, .6f));
//        return new FactorTwo(vars, vals);
//    }
    Factor new2Factor(List<Character> vars, List<Float> vals, int size){
        return new FactorTwo(vars, vals,size);
    }

//    Factor new3Factor(){
//        ArrayList<Character> vars = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
//        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.63f, .27f, .08f, .02f, .28f, .12f, .48f, .12f));
//        return new FactorThree(vars, vals);
//    }
    Factor new3Factor(List<Character> vars, List<Float> vals, int size){
        return new FactorThree(vars, vals,size);
    }

    Factor new4Factor(List<Character> vars, List<Float> vals, int size){
        return new FactorFour(vars, vals,size);
    }
}
