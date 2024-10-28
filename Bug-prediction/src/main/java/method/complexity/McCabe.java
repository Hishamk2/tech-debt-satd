/*
Adopted and corrected from: https://github.com/rodhilton/jasome
Decision points: The decision points are if, conidtional (?:) for, for-each,
while, do, catch, case statements in a source code.
There were so many problems with the original implementation..
was missing: foreach, catch, and default in switch.

McCabe calculation: Empirical analysis of the relationship between CC and SLOC in a large corpus of Java methods
 */
package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.stmt.*;

import java.util.List;

public class McCabe {


    private int mcCabeComplexity; // we consider swtich cases as well.


    private  int mcCabeCompleixtyWOcases;// we don't conser switch cases..

    public McCabe(){
        this.mcCabeComplexity = -1;
        this.mcCabeCompleixtyWOcases = -1;
    }

    public int getMcCabeComplexity() {
        return mcCabeComplexity;
    }

    public void setMcCabeComplexity(int mcCabeComplexity) {
        this.mcCabeComplexity = mcCabeComplexity;
    }

    public int getMcCabeCompleixtyWOcases() {
        return mcCabeCompleixtyWOcases;
    }

    public void setMcCabeCompleixtyWOcases(int mcCabeCompleixtyWOcases) {
        this.mcCabeCompleixtyWOcases = mcCabeCompleixtyWOcases;
    }

    public void calculateMcCabe(String code) {


        //https://www.guru99.com/cyclomatic-complexity.html#4

        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();
        List<IfStmt> ifStmts = methodDeclaration.findAll(IfStmt.class);
        List<ConditionalExpr> conditionalExpressions = methodDeclaration.
                findAll(ConditionalExpr.class);
        List<ForStmt> forStmts = methodDeclaration.findAll(ForStmt.class);
        List<ForeachStmt> forEachStmts = methodDeclaration.findAll(ForeachStmt.class);
        List<WhileStmt> whileStmts = methodDeclaration.findAll(WhileStmt.class);
        List<DoStmt> doStmts = methodDeclaration.findAll(DoStmt.class);
        List<SwitchEntryStmt> switchEntryStmts = methodDeclaration.
                findAll(SwitchEntryStmt.class);
        List<CatchClause> catchClauses = methodDeclaration.
                findAll(CatchClause.class);


        this.setMcCabeComplexity (ifStmts.size() +
                forStmts.size() +
                forEachStmts.size()+
                whileStmts.size() +
                doStmts.size() +
                switchEntryStmts.size() +
                catchClauses.size()+
                conditionalExpressions.size() +
                1);

        this.setMcCabeCompleixtyWOcases(this.getMcCabeComplexity()-switchEntryStmts.size());
    }



}

