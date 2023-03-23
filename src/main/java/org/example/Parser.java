package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Parser {
    private String[] firstArray;
    private String[] secondArray;

    public Parser(String fileName){
        try(FileInputStream fis = new FileInputStream(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"))){
            String str;
            int i = 0;
            int j = 0;
            while ((str = in.readLine()) != null) {
                try {

                    if(firstArray == null){
                        this.firstArray = new String[Integer.parseInt(str)];
                    }else{
                        this.secondArray = new String[Integer.parseInt(str)];
                    }
                }catch (NumberFormatException e){
                    try{
                        assert this.firstArray != null;
                        this.firstArray[i] = str;
                        i++;
                    }catch (ArrayIndexOutOfBoundsException ex){
                        this.secondArray[j] = str;
                        j++;
                    }
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printToFile(List<String> table){

        try{
            Files.write(Paths.get("src/main/resources/output.txt"), table, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public String[] getFirstArray() {
        return this.firstArray;
    }

    public String[] getSecondArray() {
        return this.secondArray;
    }
}
