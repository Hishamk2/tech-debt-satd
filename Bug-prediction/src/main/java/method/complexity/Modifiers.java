package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

public class Modifiers {

    int isPublic;
    int isPrivate;
    int isProtected;
    int isDefault;
    int isStatic;
    int isAbstract;

    int isGetterOrSetter;
    public Modifiers(){

        isPrivate = 0;
        isPublic = 0;
        isProtected = 0;
        isDefault = 0;
        isStatic = 0;
        isAbstract = 0;
        isGetterOrSetter = 0;
    }


    public void extractModifiers(String code){
        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();
        int getter = checkGetter(code);
        int setter = checkSetter(code);
        setIsGetterOrSetter(getter | setter); // set 0 only if both are 0

        if(methodDeclaration.isAbstract()){
            setAbstract(1);
        }

        if(methodDeclaration.isStatic()){
            setStatic(1);
        }

        if (methodDeclaration.isPrivate()){
            setPrivate(1);
        }
        else if(methodDeclaration.isProtected()){
            setProtected(1);
        }
        else if(methodDeclaration.isDefault()){
            setDefault(1);
        }
        else if(methodDeclaration.isPublic()){
            setPublic(1);
        }

    }
    public int checkSetter(String code){
        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();

        PrettyPrinterConfiguration conf = new PrettyPrinterConfiguration();
        conf.setPrintComments(false);
        PrettyPrinter prettyPrinter = new PrettyPrinter(conf);
        String[] lines = prettyPrinter.print(methodDeclaration).split("\r\n|\r|\n");

        if(lines.length!=3){
            // SLOC must be 3 after pretty printing.
            return 0;
        }

        if(methodDeclaration.getParameters().size()!=1){
            // setter should have one parameter.
            return 0;
        }

        if(!methodDeclaration.isPublic()){
            // must be public
            return 0;
        }
        if(!methodDeclaration.getName().toString().startsWith("set")){
            // must start with set
            return 0;
        }
        if(methodDeclaration.findAll(ReturnStmt.class).size() != 0){
            // must be void return type
            return 0;
        }
        if(methodDeclaration.findAll(AssignExpr.class).size()==0){
            // must be assignment expression.
            return 0;
        }

        return 1;
    }

    public int checkGetter(String code){
        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();

        PrettyPrinterConfiguration conf = new PrettyPrinterConfiguration();
        conf.setPrintComments(false);
        PrettyPrinter prettyPrinter = new PrettyPrinter(conf);
        String[] lines = prettyPrinter.print(methodDeclaration).split("\r\n|\r|\n");

        if(lines.length!=3){
            // SLOC must be 3 after pretty printing.
            return 0;
        }

        if(methodDeclaration.getParameters().size()>0){
            // getter should not hava any parameter.
            return 0;
        }
        if(!methodDeclaration.isPublic()){
            // must be public
            return 0;
        }
        if(!methodDeclaration.getName().toString().startsWith("get")){
            // must start with get
            return 0;
        }
        if(methodDeclaration.findAll(ReturnStmt.class).size() != 1){
            // must not be void return type
            return 0;
        }


        return 1;
    }

    public int isAbstract(){
        return isAbstract;
    }

    public void setAbstract(int aAbstract){
        isAbstract = aAbstract;
    }

    public int isPublic() {
        return isPublic;
    }

    public void setPublic(int aPublic) {
        isPublic = aPublic;
    }

    public int isPrivate() {
        return isPrivate;
    }

    public void setPrivate(int aPrivate) {
        isPrivate = aPrivate;
    }

    public int isProtected() {
        return isProtected;
    }

    public void setProtected(int aProtected) {
        isProtected = aProtected;
    }

    public int isDefault() {
        return isDefault;
    }

    public void setDefault(int aDefault) {
        isDefault = aDefault;
    }

    public int isStatic() {
        return isStatic;
    }

    public void setStatic(int aStatic) {
        isStatic = aStatic;
    }

    public int getIsGetterOrSetter() {
        return isGetterOrSetter;
    }

    public void setIsGetterOrSetter(int isGetterOrSetter) {
        this.isGetterOrSetter = isGetterOrSetter;
    }


}
