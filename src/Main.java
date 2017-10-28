import com.guts.michael.connection.Server;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("server")) {
            new Server().start();
        } else {
            ConnectionView.getConnectionView();
        }
    }

}
