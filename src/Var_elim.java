import java.util.*;

public class Var_elim {
    Var_elim(){}

    Factor restrict(Factor factor, Character variable, boolean value){
        Factor result = null;
        ArrayList<Character> partial = new ArrayList<>(factor.getVariables());
        partial.remove(variable);
        float t, f;
        t = value ? 1 : 0;
        f = value ? 0 : 1;
        if(factor.factorSize()==1){
            result = this.new1Factor(factor.getVariables(),new ArrayList<>(Arrays.asList(t,f)));
        } else if(factor.factorSize()==2){
            result = this.new1Factor(partial,factor.getVarValues(variable,value));
        } else if (factor.factorSize()==3){
            result = this.new2Factor(partial,factor.getVarValues(variable,value));
        } else if (factor.factorSize()==4){
            result = this.new3Factor(partial,factor.getVarValues(variable,value));
        }
        return result;
    }

    Factor multiple(Factor factor1, Factor factor2){
        Factor result = null;
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
            float firstTrue = single.getVarValues(onCombine,true).get(0);
            float firstFalse = single.getVarValues(onCombine,false).get(0);
            ArrayList<Float> secondTrue = new ArrayList<>(more.getVarValues(onCombine,true));
            ArrayList<Float> secondFalse = new ArrayList<>(more.getVarValues(onCombine,false));
            vars = new ArrayList<>(more.getVariables());
            for(int i = 0;i<secondFalse.size();i++){
                secondTrue.set(i,secondTrue.get(i)*firstTrue);
                secondFalse.set(i,secondFalse.get(i)*firstFalse);
            }
            if(more.factorSize()==2){
                if (more.getVariables().indexOf(onCombine)==0){
                    mult.addAll(secondTrue);
                    mult.addAll(secondFalse);
                } else {
                    mult.add(secondTrue.get(0));
                    mult.add(secondFalse.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondFalse.get(1));
                }
                result = this.new2Factor(vars,mult);
            } else if(more.factorSize()==3){
                if (more.getVariables().indexOf(onCombine)==0){
                    mult.addAll(secondTrue);
                    mult.addAll(secondFalse);
                } else if (more.getVariables().indexOf(onCombine)==1) {
                    mult.add(secondTrue.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondFalse.get(0));
                    mult.add(secondFalse.get(1));
                    mult.add(secondTrue.get(2));
                    mult.add(secondTrue.get(3));
                    mult.add(secondFalse.get(2));
                    mult.add(secondFalse.get(3));
                } else {
                    mult.add(secondTrue.get(0));
                    mult.add(secondFalse.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondFalse.get(1));
                    mult.add(secondTrue.get(2));
                    mult.add(secondFalse.get(2));
                    mult.add(secondTrue.get(3));
                    mult.add(secondFalse.get(3));
                }
                result = this.new3Factor(vars,mult);
            } else {
                if (more.getVariables().indexOf(onCombine)==0){
                    mult.addAll(secondTrue);
                    mult.addAll(secondFalse);
                } else if (more.getVariables().indexOf(onCombine)==1) {
                    mult.add(secondTrue.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondTrue.get(2));
                    mult.add(secondTrue.get(3));
                    mult.add(secondFalse.get(0));
                    mult.add(secondFalse.get(1));
                    mult.add(secondFalse.get(2));
                    mult.add(secondFalse.get(3));
                    mult.add(secondTrue.get(4));
                    mult.add(secondTrue.get(5));
                    mult.add(secondTrue.get(6));
                    mult.add(secondTrue.get(7));
                    mult.add(secondFalse.get(4));
                    mult.add(secondFalse.get(5));
                    mult.add(secondFalse.get(6));
                    mult.add(secondFalse.get(7));
                } else if(more.getVariables().indexOf(onCombine)==2){
                    mult.add(secondTrue.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondFalse.get(0));
                    mult.add(secondFalse.get(1));
                    mult.add(secondTrue.get(2));
                    mult.add(secondTrue.get(3));
                    mult.add(secondFalse.get(2));
                    mult.add(secondFalse.get(3));
                    mult.add(secondTrue.get(4));
                    mult.add(secondTrue.get(5));
                    mult.add(secondFalse.get(4));
                    mult.add(secondFalse.get(5));
                    mult.add(secondTrue.get(6));
                    mult.add(secondTrue.get(7));
                    mult.add(secondFalse.get(6));
                    mult.add(secondFalse.get(7));
                } else {
                    mult.add(secondTrue.get(0));
                    mult.add(secondFalse.get(0));
                    mult.add(secondTrue.get(1));
                    mult.add(secondFalse.get(1));
                    mult.add(secondTrue.get(2));
                    mult.add(secondFalse.get(2));
                    mult.add(secondTrue.get(3));
                    mult.add(secondFalse.get(3));
                    mult.add(secondTrue.get(4));
                    mult.add(secondFalse.get(4));
                    mult.add(secondTrue.get(5));
                    mult.add(secondFalse.get(5));
                    mult.add(secondTrue.get(6));
                    mult.add(secondFalse.get(6));
                    mult.add(secondTrue.get(7));
                    mult.add(secondFalse.get(7));
                }
                result = this.new4Factor(vars, mult);
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
                if(other.factorSize()==2){
                    ArrayList<Float> temp1 = new ArrayList<>(other.getAllValues());
                    ArrayList<Float> temp2 = new ArrayList<>();
                    ArrayList<Float> temp3 = new ArrayList<>();
                    ArrayList<Float> temp4 = new ArrayList<>();
                    ArrayList<Float> temp5 = new ArrayList<>();

                    ArrayList<Float> onTrue = new ArrayList<>(two.getVarValues(onC,true));
                    ArrayList<Float> onFalse = new ArrayList<>(two.getVarValues(onC,false));

                    for(int i = 0;i<2;i++){
                        if(other.getVariables().indexOf(onC)==0){
                            temp2.add(temp1.get(0)*onTrue.get(i));
                            temp3.add(temp1.get(1)*onTrue.get(i));
                            temp4.add(temp1.get(2)*onFalse.get(i));
                            temp5.add(temp1.get(3)*onFalse.get(i));
                        } else{
                            temp2.add(temp1.get(0)*onTrue.get(i));
                            temp3.add(temp1.get(1)*onFalse.get(i));
                            temp4.add(temp1.get(2)*onTrue.get(i));
                            temp5.add(temp1.get(3)*onFalse.get(i));
                        }
                    }
                    mult.addAll(temp2);
                    mult.addAll(temp3);
                    mult.addAll(temp4);
                    mult.addAll(temp5);
                    result = this.new3Factor(vars, mult);
                } else if (other.factorSize()==3){
                    FactorThree three = (FactorThree) other;
                    ArrayList<Float> temp1 = new ArrayList<>(three.getAllValues());
                    ArrayList<Float> temp2 = new ArrayList<>();
                    ArrayList<Float> temp3 = new ArrayList<>();
                    ArrayList<Float> temp4 = new ArrayList<>();
                    ArrayList<Float> temp5 = new ArrayList<>();
                    ArrayList<Float> temp6 = new ArrayList<>();
                    ArrayList<Float> temp7 = new ArrayList<>();
                    ArrayList<Float> temp8 = new ArrayList<>();
                    ArrayList<Float> temp9 = new ArrayList<>();

                    ArrayList<Float> onTrue = new ArrayList<>(two.getVarValues(onC,true));
                    ArrayList<Float> onFalse = new ArrayList<>(two.getVarValues(onC,false));

                    for(int i = 0;i<2;i++){
                        if(three.getVariables().indexOf(onC)==0){
                            temp2.add(temp1.get(0)*onTrue.get(i));
                            temp3.add(temp1.get(1)*onTrue.get(i));
                            temp4.add(temp1.get(2)*onTrue.get(i));
                            temp5.add(temp1.get(3)*onTrue.get(i));
                            temp6.add(temp1.get(4)*onFalse.get(i));
                            temp7.add(temp1.get(5)*onFalse.get(i));
                            temp8.add(temp1.get(6)*onFalse.get(i));
                            temp9.add(temp1.get(7)*onFalse.get(i));
                        } else if (three.getVariables().indexOf(onC)==1){
                            temp2.add(temp1.get(0)*onTrue.get(i));
                            temp3.add(temp1.get(1)*onTrue.get(i));
                            temp4.add(temp1.get(2)*onFalse.get(i));
                            temp5.add(temp1.get(3)*onFalse.get(i));
                            temp6.add(temp1.get(4)*onTrue.get(i));
                            temp7.add(temp1.get(5)*onTrue.get(i));
                            temp8.add(temp1.get(6)*onFalse.get(i));
                            temp9.add(temp1.get(7)*onFalse.get(i));
                        } else {
                            temp2.add(temp1.get(0)*onTrue.get(i));
                            temp3.add(temp1.get(1)*onFalse.get(i));
                            temp4.add(temp1.get(2)*onTrue.get(i));
                            temp5.add(temp1.get(3)*onFalse.get(i));
                            temp6.add(temp1.get(4)*onTrue.get(i));
                            temp7.add(temp1.get(5)*onFalse.get(i));
                            temp8.add(temp1.get(6)*onTrue.get(i));
                            temp9.add(temp1.get(7)*onFalse.get(i));
                        }
                    }
                    mult.addAll(temp2);
                    mult.addAll(temp3);
                    mult.addAll(temp4);
                    mult.addAll(temp5);
                    mult.addAll(temp6);
                    mult.addAll(temp7);
                    mult.addAll(temp8);
                    mult.addAll(temp9);
                    result = this.new4Factor(vars, mult);
                }
            } else if(onCombine.size()==2){
                vars = new ArrayList<>(other.getVariables());
                if(other.factorSize()==2){
                    mult=new ArrayList<>(other.getAllValues());
                    result = this.new2Factor(vars, mult);
                } else if(other.factorSize()==3){
                    FactorThree three = (FactorThree)other;
                    ArrayList<Float> twoValues = new ArrayList<>(two.getAllValues());
                    ArrayList<Float> threeVals = new ArrayList<>(three.getAllValues());
                    if((three.getVariables().indexOf(onCombine.get(0))==0 || three.getVariables().indexOf(onCombine.get(1))==0)
                            &&(three.getVariables().indexOf(onCombine.get(0))==1 || three.getVariables().indexOf(onCombine.get(1))==1)){
                        mult.add(threeVals.get(0)*twoValues.get(0));
                        mult.add(threeVals.get(1)*twoValues.get(0));
                        mult.add(threeVals.get(2)*twoValues.get(1));
                        mult.add(threeVals.get(3)*twoValues.get(1));
                        mult.add(threeVals.get(4)*twoValues.get(2));
                        mult.add(threeVals.get(5)*twoValues.get(2));
                        mult.add(threeVals.get(6)*twoValues.get(3));
                        mult.add(threeVals.get(7)*twoValues.get(3));
                    } else if((three.getVariables().indexOf(onCombine.get(0))==0 || three.getVariables().indexOf(onCombine.get(1))==0)
                            &&(three.getVariables().indexOf(onCombine.get(0))==2 || three.getVariables().indexOf(onCombine.get(1))==2)){
                        mult.add(threeVals.get(0)*twoValues.get(0));
                        mult.add(threeVals.get(1)*twoValues.get(1));
                        mult.add(threeVals.get(2)*twoValues.get(0));
                        mult.add(threeVals.get(3)*twoValues.get(1));
                        mult.add(threeVals.get(4)*twoValues.get(2));
                        mult.add(threeVals.get(5)*twoValues.get(3));
                        mult.add(threeVals.get(6)*twoValues.get(2));
                        mult.add(threeVals.get(7)*twoValues.get(3));
                    } else if((three.getVariables().indexOf(onCombine.get(0))==1 || three.getVariables().indexOf(onCombine.get(1))==1)
                            &&(three.getVariables().indexOf(onCombine.get(0))==2 || three.getVariables().indexOf(onCombine.get(1))==2)){
                        mult.add(threeVals.get(0)*twoValues.get(0));
                        mult.add(threeVals.get(1)*twoValues.get(1));
                        mult.add(threeVals.get(2)*twoValues.get(2));
                        mult.add(threeVals.get(3)*twoValues.get(3));
                        mult.add(threeVals.get(4)*twoValues.get(0));
                        mult.add(threeVals.get(5)*twoValues.get(1));
                        mult.add(threeVals.get(6)*twoValues.get(2));
                        mult.add(threeVals.get(7)*twoValues.get(3));
                    }
                    result = this.new3Factor(vars, mult);
                } else if(other.factorSize()==4){
                    FactorFour four = (FactorFour)other;
                    ArrayList<Float> twoValues = new ArrayList<>(two.getAllValues());
                    ArrayList<Float> fourVals = new ArrayList<>(four.getAllValues());
                    if((four.getVariables().indexOf(onCombine.get(0))==0 || four.getVariables().indexOf(onCombine.get(1))==0)
                            &&(four.getVariables().indexOf(onCombine.get(0))==1 || four.getVariables().indexOf(onCombine.get(1))==1)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(0));
                        mult.add(fourVals.get(2)*twoValues.get(0));
                        mult.add(fourVals.get(3)*twoValues.get(0));
                        mult.add(fourVals.get(4)*twoValues.get(1));
                        mult.add(fourVals.get(5)*twoValues.get(1));
                        mult.add(fourVals.get(6)*twoValues.get(1));
                        mult.add(fourVals.get(7)*twoValues.get(1));
                        mult.add(fourVals.get(8)*twoValues.get(2));
                        mult.add(fourVals.get(9)*twoValues.get(2));
                        mult.add(fourVals.get(10)*twoValues.get(2));
                        mult.add(fourVals.get(11)*twoValues.get(2));
                        mult.add(fourVals.get(12)*twoValues.get(3));
                        mult.add(fourVals.get(13)*twoValues.get(3));
                        mult.add(fourVals.get(14)*twoValues.get(3));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    } else if((four.getVariables().indexOf(onCombine.get(0))==0 || four.getVariables().indexOf(onCombine.get(1))==0)
                            &&(four.getVariables().indexOf(onCombine.get(0))==2 || four.getVariables().indexOf(onCombine.get(1))==2)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(0));
                        mult.add(fourVals.get(2)*twoValues.get(1));
                        mult.add(fourVals.get(3)*twoValues.get(1));
                        mult.add(fourVals.get(4)*twoValues.get(0));
                        mult.add(fourVals.get(5)*twoValues.get(0));
                        mult.add(fourVals.get(6)*twoValues.get(1));
                        mult.add(fourVals.get(7)*twoValues.get(1));
                        mult.add(fourVals.get(8)*twoValues.get(2));
                        mult.add(fourVals.get(9)*twoValues.get(2));
                        mult.add(fourVals.get(10)*twoValues.get(3));
                        mult.add(fourVals.get(11)*twoValues.get(3));
                        mult.add(fourVals.get(12)*twoValues.get(2));
                        mult.add(fourVals.get(13)*twoValues.get(2));
                        mult.add(fourVals.get(14)*twoValues.get(3));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    } else if((four.getVariables().indexOf(onCombine.get(0))==0 || four.getVariables().indexOf(onCombine.get(1))==0)
                            &&(four.getVariables().indexOf(onCombine.get(0))==3 || four.getVariables().indexOf(onCombine.get(1))==3)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(1));
                        mult.add(fourVals.get(2)*twoValues.get(0));
                        mult.add(fourVals.get(3)*twoValues.get(1));
                        mult.add(fourVals.get(4)*twoValues.get(0));
                        mult.add(fourVals.get(5)*twoValues.get(1));
                        mult.add(fourVals.get(6)*twoValues.get(0));
                        mult.add(fourVals.get(7)*twoValues.get(1));
                        mult.add(fourVals.get(8)*twoValues.get(2));
                        mult.add(fourVals.get(9)*twoValues.get(3));
                        mult.add(fourVals.get(10)*twoValues.get(2));
                        mult.add(fourVals.get(11)*twoValues.get(3));
                        mult.add(fourVals.get(12)*twoValues.get(2));
                        mult.add(fourVals.get(13)*twoValues.get(3));
                        mult.add(fourVals.get(14)*twoValues.get(2));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    } else if((four.getVariables().indexOf(onCombine.get(0))==1 || four.getVariables().indexOf(onCombine.get(1))==1)
                            &&(four.getVariables().indexOf(onCombine.get(0))==2 || four.getVariables().indexOf(onCombine.get(1))==2)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(0));
                        mult.add(fourVals.get(2)*twoValues.get(1));
                        mult.add(fourVals.get(3)*twoValues.get(1));
                        mult.add(fourVals.get(4)*twoValues.get(2));
                        mult.add(fourVals.get(5)*twoValues.get(2));
                        mult.add(fourVals.get(6)*twoValues.get(3));
                        mult.add(fourVals.get(7)*twoValues.get(3));
                        mult.add(fourVals.get(8)*twoValues.get(0));
                        mult.add(fourVals.get(9)*twoValues.get(0));
                        mult.add(fourVals.get(10)*twoValues.get(1));
                        mult.add(fourVals.get(11)*twoValues.get(1));
                        mult.add(fourVals.get(12)*twoValues.get(2));
                        mult.add(fourVals.get(13)*twoValues.get(2));
                        mult.add(fourVals.get(14)*twoValues.get(3));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    } else if((four.getVariables().indexOf(onCombine.get(0))==1 || four.getVariables().indexOf(onCombine.get(1))==1)
                            &&(four.getVariables().indexOf(onCombine.get(0))==3 || four.getVariables().indexOf(onCombine.get(1))==3)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(1));
                        mult.add(fourVals.get(2)*twoValues.get(0));
                        mult.add(fourVals.get(3)*twoValues.get(1));
                        mult.add(fourVals.get(4)*twoValues.get(2));
                        mult.add(fourVals.get(5)*twoValues.get(3));
                        mult.add(fourVals.get(6)*twoValues.get(2));
                        mult.add(fourVals.get(7)*twoValues.get(3));
                        mult.add(fourVals.get(8)*twoValues.get(0));
                        mult.add(fourVals.get(9)*twoValues.get(1));
                        mult.add(fourVals.get(10)*twoValues.get(0));
                        mult.add(fourVals.get(11)*twoValues.get(1));
                        mult.add(fourVals.get(12)*twoValues.get(2));
                        mult.add(fourVals.get(13)*twoValues.get(3));
                        mult.add(fourVals.get(14)*twoValues.get(2));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    } else if((four.getVariables().indexOf(onCombine.get(0))==2 || four.getVariables().indexOf(onCombine.get(1))==2)
                            &&(four.getVariables().indexOf(onCombine.get(0))==3 || four.getVariables().indexOf(onCombine.get(1))==3)){
                        mult.add(fourVals.get(0)*twoValues.get(0));
                        mult.add(fourVals.get(1)*twoValues.get(1));
                        mult.add(fourVals.get(2)*twoValues.get(2));
                        mult.add(fourVals.get(3)*twoValues.get(3));
                        mult.add(fourVals.get(4)*twoValues.get(0));
                        mult.add(fourVals.get(5)*twoValues.get(1));
                        mult.add(fourVals.get(6)*twoValues.get(2));
                        mult.add(fourVals.get(7)*twoValues.get(3));
                        mult.add(fourVals.get(8)*twoValues.get(0));
                        mult.add(fourVals.get(9)*twoValues.get(1));
                        mult.add(fourVals.get(10)*twoValues.get(2));
                        mult.add(fourVals.get(11)*twoValues.get(3));
                        mult.add(fourVals.get(12)*twoValues.get(0));
                        mult.add(fourVals.get(13)*twoValues.get(1));
                        mult.add(fourVals.get(14)*twoValues.get(2));
                        mult.add(fourVals.get(15)*twoValues.get(3));
                    }
                    result = this.new4Factor(vars, mult);
                }
            }
        }
        return result;
    }

    Factor sumOut(Factor factor, Character variable){
        ArrayList<Character> partial = new ArrayList<>(factor.getVariables());
        partial.remove(variable);
        Factor result = null;
        if(factor.factorSize()==2){
            float bTrue = 0;
            float bFalse = 0;
            for (Float num: factor.getVarValues(partial.get(0),true)){
                bTrue+=num;
            }
            for (Float num: factor.getVarValues(partial.get(0),false)){
                bFalse+=num;
            }
            ArrayList<Float> summed = new ArrayList<>(Arrays.asList(bTrue,bFalse));
            result = this.new1Factor(partial,summed);
        } else if (factor.factorSize()==3){
            float bTruecTrue = 0;
            float bTruecFalse = 0;
            float bFalsecTrue = 0;
            float bFalsecFalse = 0;
            FactorThree useful = (FactorThree)factor;
            for (Float num: useful.get2VarVals(partial.get(0),partial.get(1),true,true)){
                bTruecTrue+=num;
            }
            for (Float num: useful.get2VarVals(partial.get(0),partial.get(1),true,false)){
                bTruecFalse+=num;
            }
            for (Float num: useful.get2VarVals(partial.get(0),partial.get(1),false,true)){
                bFalsecTrue+=num;
            }
            for (Float num: useful.get2VarVals(partial.get(0),partial.get(1),false,false)){
                bFalsecFalse+=num;
            }
            ArrayList<Float> summed = new ArrayList<>(Arrays.asList(bTruecTrue,bTruecFalse,bFalsecTrue,bFalsecFalse));
            result = this.new2Factor(partial,summed);
        } else if (factor.factorSize()==4){
            float ttt = 0;
            float ttf = 0;
            float tft = 0;
            float tff = 0;
            float ftt = 0;
            float ftf = 0;
            float fft = 0;
            float fff = 0;
            FactorFour useful = (FactorFour)factor;
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),true,true,true)){
                ttt+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),true,true,false)){
                ttf+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),true,false,true)){
                tft+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),true,false,false)){
                tff+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),false,true,true)){
                ftt+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),false,true,false)){
                ftf+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),false,false,true)){
                fft+=num;
            }
            for (Float num: useful.get3VarVals(partial.get(0),partial.get(1),partial.get(2),false,false,false)){
                fff+=num;
            }
            ArrayList<Float> summed = new ArrayList<>(Arrays.asList(ttt,ttf,tft,tff,ftt,ftf,fft,fff));
            result = this.new3Factor(partial,summed);
        }
        return result;
    }

    public Factor inference(List<Factor> factorList, Character quereyVars, List<Character> orderedHiddenVars, Map<Character,Boolean> evidence){
        ArrayList<Factor> factors=new ArrayList<>(factorList);
        for(Map.Entry<Character,Boolean> entry:evidence.entrySet()){
            for(int j = 0;j<factors.size();j++){
                if(factors.get(j).containsVar(entry.getKey())){
                    Factor temp = this.restrict(factors.get(j),entry.getKey(),entry.getValue());
                    if (temp==null) throw new AssertionError("restrict returned null");
                    factors.set(j,temp);
                }
            }
        }
        for(Character var:orderedHiddenVars){
            factors = this.multiplyAndSum(factors,var);
        }
        if(factors.size()!=1){
            throw new AssertionError("not all factor's summed out");
        }
        return this.normalize(factors.get(0));
    }

    private ArrayList<Factor> multiplyAndSum(ArrayList<Factor> factors, Character sumOn){
        Factor f1 = null;
        ArrayList<Factor> result = new ArrayList<>();
        for (Factor f:factors){
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
            simplified = this.new1Factor(factor.getVariables(),newF);
        } else if (factor.factorSize()==2){
            simplified = this.new2Factor(factor.getVariables(),newF);
        } else if (factor.factorSize()==3){
            simplified = this.new3Factor(factor.getVariables(),newF);
        }
        return simplified;
    }

    Factor new1Factor(){
        ArrayList<Character> vars = new ArrayList<>(Collections.singletonList('a'));
        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.9f, .1f));
        return new FactorOne(vars, vals);
    }
    Factor new1Factor(List<Character> vars, List<Float> vals){
        return new FactorOne(vars, vals);
    }

    Factor new2Factor(){
        ArrayList<Character> vars = new ArrayList<>(Arrays.asList('a', 'b'));
        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.9f, .1f, .4f, .6f));
        return new FactorTwo(vars, vals);
    }
    Factor new2Factor(List<Character> vars, List<Float> vals){
        return new FactorTwo(vars, vals);
    }

    Factor new3Factor(){
        ArrayList<Character> vars = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        ArrayList<Float> vals = new ArrayList<>(Arrays.asList(.63f, .27f, .08f, .02f, .28f, .12f, .48f, .12f));
        return new FactorThree(vars, vals);
    }
    Factor new3Factor(List<Character> vars, List<Float> vals){
        return new FactorThree(vars, vals);
    }

    Factor new4Factor(List<Character> vars, List<Float> vals){
        return new FactorFour(vars, vals);
    }
}
