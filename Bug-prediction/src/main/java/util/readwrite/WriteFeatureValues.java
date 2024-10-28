package util.readwrite;

import main.Settings;
import method.Method;
import method.change.Change;
import method.complexity.Complexity;
import org.apache.commons.lang3.StringUtils;
import util.Util;

import java.util.List;

public class WriteFeatureValues {



    public static String getBasicValues(Method method){


        List<Complexity> listComplexity = method.getListComplexity();

        String slocAsItIs="";
        String slocPretty="";
        String slocStandard="";
        String commentCodeRatio ="";
        String readability="";
        String simpleReadability ="";
        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);
            slocAsItIs += complexity.getSloc().getSlocAsItIs()+"#";
            slocPretty += complexity.getSloc().getSlocNoCommentPretty()+"#";
            slocStandard += complexity.getSloc().getSlocStandard()+"#";
            commentCodeRatio += complexity.getSloc().getCommentCodeRatio()+"#";
            readability += complexity.getReadability()+"#";
            simpleReadability += complexity.getSimpleReadability()+"#";
        }
        slocAsItIs = StringUtils.chop(slocAsItIs);
        slocPretty = StringUtils.chop(slocPretty);
        slocStandard = StringUtils.chop(slocStandard);
        commentCodeRatio = StringUtils.chop(commentCodeRatio);

        readability = StringUtils.chop(readability);
        simpleReadability = StringUtils.chop(simpleReadability);

        return Integer.toString(method.getAge())+"\t"+ slocAsItIs+"\t"+ slocPretty
               +"\t"+slocStandard+"\t"+commentCodeRatio+"\t"+readability+"\t"+simpleReadability+"\t";

        //return Integer.toString(method.getAge())+"\t"+slocStandard+"\t"+commentCodeRatio+"\t"+readability+"\t";

    }


    public static String getHalsteadValues(Method method){
        List<Complexity> listComplexity = method.getListComplexity();

        String n1 = "";
        String n2 = "";
        String N1 = "";
        String N2 = "";
        String vocabulary = "";
        String length = "";
        String  volume = "";
        String  difficulty = "";
        String  effort = "";
        String  time = "";

        String  bugs = "";
        String maintIndex = "";

        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);

            n1 += complexity.getHalstead().n1+"#";
            n2 += complexity.getHalstead().n2+"#";
            N1 += complexity.getHalstead().N1+"#";
            N2 += complexity.getHalstead().N2+"#";
            vocabulary += complexity.getHalstead().vocabulary+"#";
            length += complexity.getHalstead().length+"#";
            volume += complexity.getHalstead().volume+"#";
            difficulty += complexity.getHalstead().difficulty+"#";

            effort += complexity.getHalstead().effort+"#";
            time += complexity.getHalstead().time+"#";
            bugs += complexity.getHalstead().bugs+"#";
            maintIndex += complexity.getMaintenanceIndex()+"#";
        }

        n1 = StringUtils.chop(n1);
        n2 = StringUtils.chop(n2);
        N1 = StringUtils.chop(N1);
        N2 = StringUtils.chop(N2);
        vocabulary = StringUtils.chop(vocabulary);
        length = StringUtils.chop(length);
        volume = StringUtils.chop(volume);
        difficulty = StringUtils.chop(difficulty);
        effort = StringUtils.chop(effort);
        time = StringUtils.chop(time);
        bugs = StringUtils.chop(bugs);
        maintIndex = StringUtils.chop(maintIndex);

        return n1+"\t"+n2+"\t"+N1+"\t"+N2+"\t"+ vocabulary+"\t"+ length+"\t"+volume+"\t"+difficulty+"\t"+effort+"\t"
                +time+"\t"+bugs+"\t"+maintIndex+"\t";
    }


    public static String getComplexityValues(Method method){
        List<Complexity> listComplexity = method.getListComplexity();

        String NVAR = "";
        String NCOMP = "";
        String McClure = "";
        String McCabe = "";
        String McCabeWOCases = "";
        String IndentSTD = "";
        String MBD ="";
        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);

            NVAR += complexity.getMcClure().getNVAR()+"#";
            NCOMP += complexity.getMcClure().getNCOMP()+"#";
            McClure += complexity.getMcClure().getMCLC()+"#";
            McCabe += complexity.getMcCabe().getMcCabeComplexity()+"#";
            McCabeWOCases += complexity.getMcCabe().getMcCabeCompleixtyWOcases()+"#";
            IndentSTD += complexity.getIndentationComplexity().getStandardDeviation()+"#";
            MBD += complexity.getNestedBlockDepth().getNestedBlockDepthValue()+"#";

        }

        NVAR = StringUtils.chop(NVAR);
        NCOMP = StringUtils.chop(NCOMP);
        McClure = StringUtils.chop(McClure);
        McCabe = StringUtils.chop(McCabe);
        McCabeWOCases = StringUtils.chop(McCabeWOCases);
        IndentSTD = StringUtils.chop(IndentSTD);
        MBD = StringUtils.chop(MBD);
        return NVAR+"\t"+NCOMP+"\t"+McClure+"\t"+McCabe+"\t"+McCabeWOCases+"\t"+ IndentSTD+"\t"+ MBD+"\t";
        //return NVAR+"\t"+NCOMP+"\t"+McClure+"\t"+McCabe+"\t"+ IndentSTD+"\t"+ MBD+"\t";
    }


    public static String getModifierValues(Method method){
        List<Complexity> listComplexity = method.getListComplexity();

        String priv = "";
        String prot = "";
        String defalt = "";
        String publc = "";
        String stat = "";
        String abstrct = "";
        String getterSetter = "";
        String satd = "";
        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);
            priv += complexity.getModifiers().isPrivate()+"#";
            prot += complexity.getModifiers().isProtected()+"#";
            defalt += complexity.getModifiers().isDefault()+"#";
            publc += complexity.getModifiers().isPublic()+"#";
            stat += complexity.getModifiers().isStatic()+"#";
            abstrct += complexity.getModifiers().isAbstract()+"#";
            getterSetter += complexity.getModifiers().getIsGetterOrSetter()+"#";
            satd += complexity.getSatd()+"#";
        }

        priv = StringUtils.chop(priv);
        prot = StringUtils.chop(prot);
        defalt = StringUtils.chop(defalt);
        publc = StringUtils.chop(publc);
        stat = StringUtils.chop(stat);
        abstrct = StringUtils.chop(abstrct);
        getterSetter = StringUtils.chop(getterSetter);
        satd = StringUtils.chop(satd);
        return priv+"\t"+prot+"\t"+defalt+"\t"+publc+"\t"+stat+"\t"+abstrct+"\t"+getterSetter+"\t" + satd +"\t";

    }

    public static String getStructuralValues(Method method) {
        List<Complexity> listComplexity = method.getListComplexity();

        String totalFanOut = "";
        String uniqueFanOut = "";

        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);
            totalFanOut += complexity.getStructuralDependency().getTotalFanOut()+"#";
            uniqueFanOut += complexity.getStructuralDependency().getUniqueFanOut()+"#";
        }
        totalFanOut = StringUtils.chop(totalFanOut);
        uniqueFanOut = StringUtils.chop(uniqueFanOut);
        return totalFanOut+"\t"+uniqueFanOut+"\t";
        //return totalFanOut+"\t";
    }

    public static String getOtherValues(Method method) {
        List<Complexity> listComplexity = method.getListComplexity();

        String parameter = "";
        String local = "";
        String enclosed = "";
        String maxEnclosed = "";
        for(int i = listComplexity.size()-1; i>=0 ;i--){
            Complexity complexity = listComplexity.get(i);
            parameter += complexity.getOthers().getNumberOfParameters()+"#";
            local += complexity.getOthers().getNumberOfLocalVariables()+"#";
            enclosed += complexity.getOthers().getNumberOfEnclosedExpressions()+"#";
            maxEnclosed += complexity.getOthers().getMaxEnclosedNesting()+"#";
        }
        parameter = StringUtils.chop(parameter);
        local = StringUtils.chop(local);
        enclosed = StringUtils.chop(enclosed);
        maxEnclosed = StringUtils.chop(maxEnclosed);
        return parameter+"\t"+local+"\t"+enclosed+"\t"+maxEnclosed+"\t";

    }

    public static String getDeveloperValues(Method method) {
        List<String> listDevelopers = method.getDevelopers();

        String developers = "";

        for(int i = listDevelopers.size()-1; i>=0 ;i--){

            developers += listDevelopers.get(i)+"#";
        }
        developers = StringUtils.chop(developers);
       return developers+"\t";
    }

    public static String getChangeValues(Method method){
        List<Change> listChanges = method.getListChange();

        String projectDates = Util.calculateTimeDifference(method.getIntroDate(), Settings.PROJECT_FIRST_DATE) + "#";
        String commitDates = Util.returnFormattedDate(method.getIntroDate())+"-" + Settings.PROJECT_FIRST_DATE + "#";

        String changeDates = "0#";
        String newAdditions = "0#";
        String diffSizes = "0#";
        String editDistances = "0#";
        String criticalEditDistances = "0#";
        String tangled = "0#";
        String tangledWOMoveRename = "0#";
        String buggyCommit = "0#";
        String potentiallyBuggyCommit ="0#";
        String riskyCommit ="0#";
        for (Change change : listChanges) {

            projectDates += Util.calculateTimeDifference(change.getCommitDate(), Settings.PROJECT_FIRST_DATE) + "#";
            commitDates += Util.returnFormattedDate(change.getCommitDate())+ "-" + Settings.PROJECT_FIRST_DATE + "#";

            changeDates += Util.calculateTimeDifference(change.getCommitDate(),method.getIntroDate())+"#";
            newAdditions += change.getNewAdditionOnly()+"#";
            diffSizes += change.getDiffSize()+"#";
            editDistances += change.getEditDistance()+"#";
            criticalEditDistances += change.getCriticalEditDistance()+"#";
            tangled += change.getTangledCommit()+"#";
            tangledWOMoveRename += change.getTangledCommitWOMoveandRename()+"#";
            buggyCommit += change.getIsBuggy()+"#";
            potentiallyBuggyCommit += change.getIsPotentiallyBuggy()+"#";
            riskyCommit += change.getIsRisky() +"#";
        }

        projectDates = StringUtils.chop(projectDates);
        commitDates = StringUtils.chop(commitDates);
        changeDates = StringUtils.chop(changeDates);
        newAdditions = StringUtils.chop(newAdditions);
        diffSizes = StringUtils.chop(diffSizes);
        editDistances = StringUtils.chop(editDistances);
        criticalEditDistances = StringUtils.chop(criticalEditDistances);
        tangled = StringUtils.chop(tangled);
        tangledWOMoveRename = StringUtils.chop(tangledWOMoveRename);
        buggyCommit = StringUtils.chop(buggyCommit);
        potentiallyBuggyCommit = StringUtils.chop(potentiallyBuggyCommit);
        riskyCommit = StringUtils.chop(riskyCommit);
        //buggyCustom = StringUtils.chop(buggyCustom);

        // return changeDates+"\t"+newAdditions+"\t"+diffSizes+"\t"+editDistances
        //        +"\t"+method.getJsonfileName().toString().
         //      replace(Settings.JSON_HISTORY_DIR+Settings.PROJECT+"/"#"");

        return changeDates+"\t"+projectDates+"\t"+commitDates+"\t"+
                newAdditions+"\t"+diffSizes+"\t"+editDistances + "\t"+
                criticalEditDistances + "\t" +
                tangled+"\t"+tangledWOMoveRename+"\t"+buggyCommit+"\t"+potentiallyBuggyCommit+"\t"+riskyCommit+"\t" +method.getJsonfileName().toString().
                replace(Settings.JSON_HISTORY_DIR+Settings.PROJECT+"/","");    }
}
