package util.readwrite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import main.Settings;
import method.Method;

import java.io.*;
import java.util.Map;

public class FileOperations {

    static int progress = 0;
    static BufferedWriter writer;
    static File fileName;
    static InputStream is;
    static BufferedReader buf;
    static File f;
    static File[] files;
    static String json;
    static ObjectMapper mapper;
    static MapType type;
    static Map<String, Object> data;
    static String line;
    static StringBuilder sb;

    public static File[] returnAllFiles(){

        f = new File(Settings.JSON_HISTORY_DIR + Settings.PROJECT);
        files=f.listFiles();
        return files;

    }

    public static void loadProjects(){


    }
    public static Map<String, Object> loadJson(File file) throws IOException {
        //source: https://stackoverflow.com/questions/13916086/jackson-recursive-parsing-into-mapstring-object/13926850#13926850

        json = loadAsString(file);
        mapper = new ObjectMapper();
        type = mapper.getTypeFactory().constructMapType(
                Map.class, String.class, Object.class);
        data = mapper.readValue(json, type);
        return data;
    }

    public static String loadAsString(File file) throws IOException {

        //source https://javarevisited.blogspot.com/2015/09/how-to-read-file-into-string-in-java-7.html

        is = new FileInputStream(file);
        buf = new BufferedReader(new InputStreamReader(is));

        line = buf.readLine();
        sb = new StringBuilder();

        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        buf.close();
        return sb.toString();


    }

   public static void writeHeaders() throws IOException{

       fileName = new File(Settings.RESULT_DIR+ Settings.PROJECT+".csv");
       writer = new BufferedWriter(new FileWriter(fileName));

       writer.append(
                       Header.getBasicHeaders() +
                       Header.getComplexityHeaders()+
                       Header.getStructuralHeaders()+
                       Header.getHalsteadHeaders()+
                       Header.getModifier()+
                       Header.getOtherHeaders()+
                       Header.getDeveloperHeader()+
                       Header.getChangeHeaders()
                       +"\n"

               //     Header.getStatementHeaders() +
                   //    Header.getMathHeaders()+
                   //    Header.getStructuralHeaders()+
                    //   Header.getChangeHeaders()
       );

       writer.flush();
       writer.close();
   }

   public static void saveResult(Method method) throws IOException {


        // this if is for debugging
       //if(method.getJsonfileName().toString().
        //       replace(Settings.JSON_HISTORY_DIR+Settings.PROJECT+"/","").equals("3335.json")){
         //  System.out.println(method.getActualSource());
     //  }

       fileName = new File(Settings.RESULT_DIR+ Settings.PROJECT+".csv");
       writer = new BufferedWriter(new FileWriter(fileName,true));

            writer.append(
                    WriteFeatureValues.getBasicValues(method)
                    + WriteFeatureValues.getComplexityValues(method)
                    +WriteFeatureValues.getStructuralValues(method)
                    +WriteFeatureValues.getHalsteadValues(method)
                    + WriteFeatureValues.getModifierValues(method)
                    +WriteFeatureValues.getOtherValues(method)
                    +WriteFeatureValues.getDeveloperValues(method)
                    + WriteFeatureValues.getChangeValues(method)
                    + "\n"
            );

        method = null; // clear the memory
        progress++;

        //System.out.println(progress);
        writer.flush();
        writer.close();
        //return  writer;

    }

}
