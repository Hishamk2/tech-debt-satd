/*
One point to remeber that, in the following expression,
our program says there are 2 enclosed epxressions
(a+(b+c))

although one can argue that it should be one.

 */

package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.EnclosedExpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Others {
    private int numberOfParameters;
    static Map<EnclosedExpr,Integer> trackDepth = new HashMap<EnclosedExpr, Integer>();

    private int numberOfLocalVariables;
    private  int numberOfEnclosedExpressions;
    private int maxEnclosedNesting; // max nested enclosed expressions
    public static List<EnclosedExpr> listOfExpressions = new ArrayList<>();

    public Others(){
        numberOfParameters = -1;
        numberOfEnclosedExpressions =-1;
        maxEnclosedNesting =-1;
        numberOfLocalVariables = -1; // yet to be tested
    }

    public void calculateOthers(String code){

        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();
        setNumberOfParameters(methodDeclaration.getParameters().size());

        // we don't consider parameters here
        setNumberOfLocalVariables(methodDeclaration.findAll(VariableDeclarator.class).size());

        listOfExpressions = methodDeclaration.findAll(EnclosedExpr.class);
        setNumberOfEnclosedExpressions(listOfExpressions.size());

        if(listOfExpressions.size()==0){
            setMaxEnclosedNesting(0);
        }


        for(EnclosedExpr expr:listOfExpressions){
            int depth = countDepth(expr);
            trackDepth.put(expr, depth);

            if(depth> maxEnclosedNesting){
                setMaxEnclosedNesting(depth);
            }
        }

        listOfExpressions = new ArrayList<EnclosedExpr>();
        trackDepth = new HashMap<EnclosedExpr,Integer>();
    }

    public int countDepth(EnclosedExpr enclosedExpr){

        int sum=0, max=0;

        if(trackDepth.containsKey(enclosedExpr)) {
            return trackDepth.get(enclosedExpr);
        }

        List<EnclosedExpr> list = enclosedExpr.getInner().findAll(EnclosedExpr.class);
        if(list.size()==0)
            return 1;
        for(EnclosedExpr expr: list){
            sum =  1 + countDepth(expr);
            if(max<sum) {
                max = sum;
            }
        }

        return max;
    }



    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public void setNumberOfParameters(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    public int getNumberOfEnclosedExpressions() {
        return numberOfEnclosedExpressions;
    }

    public void setNumberOfEnclosedExpressions(int numberOfEnclosedExpressions) {
        this.numberOfEnclosedExpressions = numberOfEnclosedExpressions;
    }

    public int getMaxEnclosedNesting() {
        return maxEnclosedNesting;
    }

    public void setMaxEnclosedNesting(int maxEnclosedNesting) {
        this.maxEnclosedNesting = maxEnclosedNesting;
    }

    public int getNumberOfLocalVariables() {
        return numberOfLocalVariables;
    }

    public void setNumberOfLocalVariables(int numberOfLocalVariables) {
        this.numberOfLocalVariables = numberOfLocalVariables;
    }

}
