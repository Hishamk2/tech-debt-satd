package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.util.List;

public class SLOC {

    private int slocAsItIs;
    private int slocStandard; // as it is, but no comment, and no blank lines
    private int slocNoCommentPretty; // no comment, no blank, and prettyprinted
    private double commentCodeRatio;

    public SLOC(){
        slocAsItIs = -1;
        slocNoCommentPretty = -1;
        slocStandard = -1;
        commentCodeRatio = -1;
    }

    public void calculate(String code){

        setSlocAsItIs(calculateSLOCAsItIs(code));
        setSlocNoCommentPretty(calculateNoCommentSLOCPretty(code));
        setSlocStandard(calculateSlocStandard(code));
        setCommentCodeRatio((double) calculateLinesOfComments(code)/getSlocStandard());
     //   System.out.println(code);
      //  System.out.println(getSlocStandard());
    }

    public int calculateSLOCAsItIs(String code){

        String[] lines = code.split("\r\n|\r|\n");
        return lines.length;

    }

    public int calculateSlocStandard(String code){

        JavaParser.getStaticConfiguration().setAttributeComments(true);
        BodyDeclaration cu = JavaParser.parseBodyDeclaration(code);

        List<Comment> comments = cu.getAllContainedComments();

        for(Comment comment:comments) {
            if (comment.isJavadocComment()){
                code = code.replace(comment.getContent(), "");
                code = code.replace("/***/", "");
                continue;
            }

            if(comment.isBlockComment()) {
                code = code.replace(comment.toString().trim(), "");
            }
        }

        int total =  calculateSLOCAsItIs(code);

        // calculate total blank lines and line comments
        // (if line comment is written after a code line at the same line, it will not be counted as comment only)

        int blankAndLineComments = 0;

        String[] lines = code.split("\r\n|\r|\n");
        for(String line : lines){
            if(line.trim().equals("") || line.trim().startsWith("//")){
                blankAndLineComments++;
            }
        }
        return total - blankAndLineComments;
    }

    public int calculateNoCommentSLOCPretty(String code){

        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code).asMethodDeclaration();
        PrettyPrinterConfiguration conf = new PrettyPrinterConfiguration();
        conf.setPrintComments(false);
        PrettyPrinter prettyPrinter = new PrettyPrinter(conf);

        String[] lines = prettyPrinter.print(methodDeclaration).split("\r\n|\r|\n");
        return lines.length;
    }

    public static int calculateLinesOfComments(String code){
        JavaParser.getStaticConfiguration().setAttributeComments(true);
        List<Comment> comments = JavaParser.parseBodyDeclaration(code).asMethodDeclaration().getAllContainedComments();
        int count =0;

        for(Comment comment:comments){

            String[] lines = comment.getContent().split("\r\n|\r|\n");
            for(String line: lines){
                // dont consider /*, \*, \\ as comments if they don't have anything else in a line
              //  line = line.replace("//", "");
                if(line.trim().length()>2){
                    count++;
                }
            }
        }
        return count;
    }

    public int getSlocAsItIs() {
        return slocAsItIs;
    }

    public void setSlocAsItIs(int slocAsItIs) {
        this.slocAsItIs = slocAsItIs;
    }

    public int getSlocStandard() {
        return slocStandard;
    }

    public void setSlocStandard(int slocStandard) {
        this.slocStandard = slocStandard;
    }

    public int getSlocNoCommentPretty() {
        return slocNoCommentPretty;
    }

    public void setSlocNoCommentPretty(int slocNoCommentPretty) {
        this.slocNoCommentPretty = slocNoCommentPretty;
    }

    public double getCommentCodeRatio() {
        return commentCodeRatio;
    }

    public void setCommentCodeRatio(double commentCodeRatio) {
        this.commentCodeRatio = commentCodeRatio;
    }

}
