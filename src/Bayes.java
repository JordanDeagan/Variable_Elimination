import java.util.*;

public class Bayes {
    public static void main(String[] args){
        Var_elim ve = new Var_elim();
        ArrayList<Character> M = new ArrayList<>(Collections.singletonList('m'));
        ArrayList<Character> A = new ArrayList<>(Collections.singletonList('a'));
        ArrayList<Character> V = new ArrayList<>(Arrays.asList('a','m','v'));
        ArrayList<Character> W = new ArrayList<>(Arrays.asList('s','m','v','w'));
        ArrayList<Character> S = new ArrayList<>(Collections.singletonList('s'));
        ArrayList<Character> B = new ArrayList<>(Arrays.asList('s','b'));
        ArrayList<Float> fm = new ArrayList<>(Arrays.asList(.0357f,.9643f));
        ArrayList<Float> na = new ArrayList<>(Arrays.asList(.3f,.7f));
        ArrayList<Float> nsv = new ArrayList<>(Arrays.asList(.8f,.2f,.5f,.5f,.4f,.6f,0f,1f));
        ArrayList<Float> mw = new ArrayList<>(Arrays.asList(.99f,.01f,.9f,.1f,.75f,.25f,.5f,.5f,.65f,.35f,.4f,.6f,.2f,.8f,0f,1f));
        ArrayList<Float> ms = new ArrayList<>(Arrays.asList(.05f,.95f));
        ArrayList<Float> fb = new ArrayList<>(Arrays.asList(.6f,.4f,.1f,.9f));
//        System.out.println("hello");
        Factor FM = ve.new1Factor(M,fm,2);
        Factor NA = ve.new1Factor(A,na,2);
        Factor MS = ve.new1Factor(S,ms,2);
        Factor FB = ve.new2Factor(B,fb,2);
        Factor NSV = ve.new3Factor(V,nsv,2);
        Factor MW = ve.new4Factor(W,mw,2);
//        System.out.println("hey");
        HashMap<Character,Integer> given = new HashMap<>();
        Factor splash = ve.inference(Arrays.asList(FM,NA,MS,NSV,MW),'w',Arrays.asList('a','m','s','v'),given);
//        given.put('w',true);
        given.put('m',0);
        System.out.println(splash.getVarValues('w',0).get(0));
        System.out.println();
//        System.out.println(ve.restrict(FM,'m',true));d
        Factor splashWMoon = ve.inference(Arrays.asList(NA,MS,NSV,MW),'w',Arrays.asList('a','s','v'),given);
//        System.out.println(splashWMoon);
        float wGivenM = splashWMoon.getVarValues('w',0).get(0);
//        System.out.println(MS);
        float s = MS.getVarValues('s',0).get(0);
        given.put('s',0);
        Factor splashIfSick = ve.inference(Arrays.asList(NA,NSV,MW),'w',Arrays.asList('a','v'),given);
//        System.out.println(splashIfSick);
        float wGivenS = splashIfSick.getVarValues('w',0).get(0);
        float sGivenW = (wGivenS*s)/wGivenM;
        System.out.println(sGivenW);
        System.out.println();

        HashMap<Character,Integer> given2 = new HashMap<>();
        Factor bowl = ve.inference(Arrays.asList(FB,MS),'b',Arrays.asList('s'),given2);
        given2.put('s',0);
        Factor bowlGivenSick = ve.inference(Arrays.asList(FB),'b',Arrays.asList(),given2);
        float b = bowl.getVarValues('b',0).get(0);
        float bGivenS = bowlGivenSick.getVarValues('b',0).get(0);
        float sGivenB = (bGivenS*s)/b;
        float sGivenBW = (bGivenS*s*wGivenS)/(b*wGivenM);
//        float sGivenBW2 = (bGivenS*s*wGivenM)/(b*wGivenM);
        System.out.println(sGivenBW);
//        System.out.println(sGivenBW2);
        System.out.println();

        given.put('a',0);
        Factor splashIfSick2 = ve.inference(Arrays.asList(NSV,MW),'w',Arrays.asList('v'),given);
        given.remove('s');
        Factor splashWAway = ve.inference(Arrays.asList(MS,NSV,MW),'w',Arrays.asList('s','v'),given);
        float wGivenS2 = splashIfSick2.getVarValues('w',0).get(0);
        float wGivenA = splashWAway.getVarValues('w',0).get(0);
        float sGivenBW2 = (bGivenS*s*wGivenS2)/(b*wGivenA);
        System.out.println(sGivenBW2);



    }
}
