import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class RollBackEngine {
    public static void rollback(String commithash, String fileName){
        File snapshot = new File(".mygit/objects" + "_" + fileName);
        File targetFile = new File(fileName);
        if(!snapshot.exists()){
            System.out.println("No snapshot found for commit hash: " + commithash);
            return;
        }
        try{
            Files.copy(snapshot.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Rolled back " + fileName + " to commit: " + commithash);
        } catch (IOException e) {
            System.err.println("Error during rollback: " + e.getMessage());
        }
    }
}
