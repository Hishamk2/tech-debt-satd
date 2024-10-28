package method.change;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeTest {

    @Test
    void testCalculateEditDistance() {
        assertEquals(1, new Change().calculateEditDistance("frog", "fog"));
        assertEquals(7, new Change().calculateEditDistance("elephant", "hippo"));
        assertEquals(6, new Change().calculateEditDistance("stack.push(new PkgControl(pkg, regex));", "stack.push(new ImportControl(pkg, regex));"));

        assertEquals(20, new Change().calculateEditDistance(
                "public static void main(String[] args){\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "        }\n" +
                        "    }",


                "public static void main(String[] args){\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "            test();\n" +
                        "        }\n" +
                        "    }"));


        assertEquals(62, new Change().calculateEditDistance(
                "    public static void main(String[] args){\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "            test();\n" +
                        "        }\n" +
                        "    }",


                "    public static void main(String[] args){\n" +
                        "\n" +
                        "        int j =0;\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "            if(i>2 && j<i){\n" +
                        "\n" +
                        "            }\n" +
                        "            test();\n" +
                        "        }\n" +
                        "    }"));

        assertEquals(24, new Change().calculateEditDistance(
                "    public static void main(String[] args){\n" +
                        "\n" +
                        "        int j =0;\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "            if(i>2 && j<i){\n" +
                        "\n" +
                        "            }\n" +
                        "            test();\n" +
                        "        }\n" +
                        "    }",


                "    public static void main(String[] argddd){\n" +
                        "\n" +
                        "        int j =0;\n" +
                        "        for(int i =0;i<5;i++){\n" +
                        "            System.out.println(i);\n" +
                        "            if(i>2 && j<i){\n" +
                        "                i++;\n" +
                        "\n" +
                        "            }\n" +
                        "            test();\n" +
                        "        }\n" +
                        "    }"));
    }

    @Test

    public void testBugFix(){
        String commit ="I have resolved, so the errors do not exit";
        BugFixDetector.isBugFix(commit);
        assertEquals(true, BugFixDetector.isBuggy);

        commit ="I am #.  The issue bugd is not,  Let's  " ;
        BugFixDetector.isBugFix(commit);
        assertEquals(true, BugFixDetector.isRisky);

        commit ="I am #.  The  bugd is not,  Let's  fix" ;
        BugFixDetector.isBugFix(commit);
        assertEquals(true, BugFixDetector.isPotentiallyBuggy);
    }


}