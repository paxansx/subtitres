
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String looking = "from"; // сюда пишеи что ищем
        File file = new File("C:\\Users\\ilia\\Desktop\\Subtitr");
        String filePath = "C:\\Users\\ilia\\Desktop\\Test\\"+looking;
        for (File go: file.listFiles()){
            try(BufferedReader buff = new BufferedReader(new FileReader(go))) {
                String line;
                ArrayList<String> line1 = new ArrayList<>();
                while ((line = buff.readLine()) != null) {
                    if(!line.equals("")&&!line.matches("\\w+")){// так я убрал строки с пробеламми и строки с номерами диалога.
                        line1.add(line);
                    }
                }
                for (int j=0; j<line1.size();j++){

                    if (line1.get(j).toLowerCase().contains(looking.toLowerCase())){
                        // вывод такой из за того что в 2% случаев 2 строки текста в фразе а не 1. более частые вариант в if
                        if (line1.get(j-1).contains("-->")) {
                            appendUsingFileWriter(filePath, "\n"+go.getName()+"\n"+line1.get(j - 1)+"\n"+line1.get(j)+"\n");
                        }else {
                            appendUsingFileWriter(filePath, "\n"+go.getName()+"\n"+line1.get(j-2)+"\n"+line1.get(j - 1)+"\n"+line1.get(j)+"\n");

                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

}