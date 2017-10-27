import java.io.IOException;
import java.net.Socket;

public interface IConnection {

    Socket connect() throws IOException;

    void disconnect(Socket s) throws IOException;

    void main();

    int init(String ip);

}
