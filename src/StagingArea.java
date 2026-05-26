import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StagingArea {
    
        private static final String INDEX_PATH = ".mygit/index";
        public static void add(String fileName){
         File file = new File(fileName);
         if(!file.exists()){
             System.out.println("File " + fileName + " does not exist.");
             return;
         }
         try(FileWriter writer = new FileWriter(INDEX_PATH, true)){
             writer.write(fileName + "\n");
             System.out.println("Staged file: " + fileName );
         } catch (IOException e) {
             System.err.println("Error staging file: " + e.getMessage());
        }
    }
}