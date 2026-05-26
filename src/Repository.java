import java.io.File;

public class Repository {
   private static final String GIT_DIR = ".mygit";

   public static void init(){
        File repoFolder = new File(GIT_DIR);
        File objectsFolder = new File(GIT_DIR +"/objects");
        File indexFile = new File(GIT_DIR + "/index");
        File headFile = new File(GIT_DIR + "/HEAD");
        
    
        try{
            if(!repoFolder.exists()){
                repoFolder.mkdir();
                objectsFolder.mkdir();
                indexFile.createNewFile();
                headFile.createNewFile();
                System.out.println("Initialized empty repository in " + repoFolder.getAbsolutePath());
            }else{
                System.out.println("Repository already exists in " + repoFolder.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error initializing repository: " + e.getMessage());
        }
    }
}