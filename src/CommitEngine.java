import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class CommitEngine {
    
    // Ensure this line is exactly written as below:
    public static void commit(String message) {
        File index = new File(".mygit/index");
        
        if (!index.exists() || index.length() == 0) {
            System.out.println("Nothing to commit (Staging area is empty).");
            return;
        }

        String commitHash = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Generating commit hash: " + commitHash);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(index))) {
            String line;
            while ((line = reader.readLine()) != null) {
                File originalFile = new File(line);
                if (originalFile.exists()) {
                    File snapshot = new File(".mygit/objects/" + commitHash + "_" + originalFile.getName());
                    Files.copy(originalFile.toPath(), snapshot.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            
            saveCommitMetadata(commitHash, message);
            
            PrintWriter writer = new PrintWriter(index);
            writer.print("");
            writer.close();
            
            System.out.println("[" + commitHash + "] Commit successful: " + message);
            
        } catch (IOException e) {
            System.err.println("Commit failed: " + e.getMessage());
        }
    }

    private static void saveCommitMetadata(String hash, String message) throws IOException {
        try (FileWriter fw = new FileWriter(".mygit/HEAD", true)) {
            fw.write(hash + " - " + message + " - Date: " + System.currentTimeMillis() + "\n");
        }
    }
}