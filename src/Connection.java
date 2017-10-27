import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection implements IConnection {
    private InetAddress ip;
    private BufferedReader read;

    @Override
    public Socket connect() throws IOException {
        return new Socket(ip, 26789);
    }

    @Override
    public void disconnect(Socket s) throws IOException {
        s.close();
    }

    @Override
    public void main() {
        try {
            Socket s = connect();
            read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = "";

            while (!line.equals("QUIT")) {
                line = read.readLine();

                switch (line) {
                    case "":
                        //PLAYER 1
                        break;
                    case "QUIT":
                        //Disconnect
                        disconnect(s);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int init(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
            main();
            return 1;
        } catch (UnknownHostException e) {
            return 0;
        }
    }
}
