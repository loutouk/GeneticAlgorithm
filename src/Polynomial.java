/**
 * Louis Boursier
 * 30/09/2018
 */

import java.util.ArrayList;

public class Polynomial {

    // Map of <Index, Coefficient>
    // For 2 terms : 2 + 3x --> Map(0) = 2 and Map(1) = 3
    // For 3 terms : 7 + x + 4x² --> Map(0) = 7 and Map(1) = 1 and Map(2) = 4
    private ArrayList<Integer> terms;

    public Polynomial(int... coefficients){
        terms = new ArrayList<>();
        for(int i : coefficients){
            terms.add(i);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0 ; i<terms.size() ; i++){
            if(i==0){
                str += terms.get(i);
            }else if(i==1){
                str += " + " + terms.get(i) + "x" ;
            }else{
                str += " + " + terms.get(i) + "x^" + i ;
            }
        }
        return str;
    }

    public double calculate(double x){
        double result = 0;
        for(int i=0 ; i<terms.size() ; i++){
            if(i==0){
                result += terms.get(i);
            }else if(i==1){
                result += terms.get(i) * x ;
            }else{
                result += terms.get(i) * Math.pow(x, i) ;
            }
        }
        return result;
    }

    public int size(){
        return terms.size();
    }

    public int getTerm(int index){
        return terms.get(index);
    }

    public void setTerm(int index, int coef){
        terms.set(index, coef);
    }
}
