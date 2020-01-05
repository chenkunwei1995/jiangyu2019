package com.xzj.helper.file.txt;

import java.io.*;
import java.util.List;

public class TxtFileOperation {
    public static void writeFile(String path,String fileContent){
        try {
            File f = new File(path);

            FileWriter writer = new FileWriter(f, true);
            PrintWriter pw=new PrintWriter(writer);
            pw.println(fileContent);
            pw.flush();
            writer.flush();
            pw.close();
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public static void readLine(String path, List<String> content){
        try{
            FileReader reader=new FileReader(path);
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            while((line=bufferedReader.readLine())!=null){
                content.add(line);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
