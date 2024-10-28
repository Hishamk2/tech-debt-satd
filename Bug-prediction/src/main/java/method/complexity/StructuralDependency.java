/*

Total fanout is very accurate.

For fanout we only miss two categories, which are rare:

1) method(int, float) and method(flot, int) they are the same to us..


2) class1.method() and class2.method() are same to us..

This does not incur any false positive but makes the process extremely faster than using
symbol analysis
 */

package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.VoidType;
import method.Method;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructuralDependency {

    private int totalFanOut;
    private int uniqueFanOut;

    public StructuralDependency() {

        this.totalFanOut = -1;
        this.uniqueFanOut = -1;
    }


    public void calculateFanOut(String code){
        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();
      Set<String> uniqueMethodsList= new HashSet();
      List<MethodCallExpr> methodCallExprsList = methodDeclaration.findAll(MethodCallExpr.class);
      for(MethodCallExpr methodCallExpr:methodCallExprsList){

          // we use name and number of arguments for making a unique method..
          // this way we might have few false negative, but no false positive, which is our
          //main focus
          String uniqueName= methodCallExpr.getName()+"--"+
                  methodCallExpr.getArguments().size();
          if(!uniqueMethodsList.contains(uniqueName)){
              uniqueMethodsList.add(uniqueName);
          }
      }

        setTotalFanOut(methodCallExprsList.size());
        setUniqueFanOut(uniqueMethodsList.size());

    }


    public int getUniqueFanOut() {
        return uniqueFanOut;
    }

    public void setUniqueFanOut(int uniqueFanOut) {
        this.uniqueFanOut = uniqueFanOut;
    }

    public int getTotalFanOut() {
        return totalFanOut;
    }

    public void setTotalFanOut(int totalFanOut) {
        this.totalFanOut = totalFanOut;
    }



}
