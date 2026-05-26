public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Commands:  init, add <file>, commit <msg>, rollback <hash> <file>");
            return;
        }
        String command = args[0];
        switch(command){
            case "init":
                Repository.init();
                break;
            case "add":
                if(args.length < 2){
                    System.out.println("Usage: add <file>");
                    return;
                }
                StagingArea.add(args[1]);
                break;
            case "commit":
                if(args.length < 2){
                    System.out.println("Usage: commit <message>");
                    return;
                }
                CommitEngine.commit(args[1]);
                break;
            case "rollback":
                if(args.length < 3){
                    System.out.println("Usage: rollback <hash> <file>");
                    return;
                }
                RollBackEngine.rollback(args[1], args[2]);
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }
}