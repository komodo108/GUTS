import com.guts.michael.connection.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selector {

    public static Selector selector;
    private JFrame frame;
    private JButton client, server;

    public static Selector getSelector() {
        if(selector == null) {
            selector = new Selector();
            return selector;
        } else {
            return selector;
        }
    }

    private Selector() {
        init();
    }

    private void init() {
        client = new JButton("Client");
        client.setBounds(Views.getDEFAULT_WIDTH()/2 - 85, Views.getDEFAULT_HEIGHT()/2 - 35, 80, 40);
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientView.getClientView();
                frame.dispose();
            }
        });

        server = new JButton("Server");
        server.setBounds(Views.getDEFAULT_WIDTH()/2 + 5, Views.getDEFAULT_HEIGHT()/2 - 35, 80, 40);
        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Server().start();
                frame.dispose();
            }
        });

        frame = new JFrame(Views.getDEFAULT_NAME());
        frame.setSize(Views.getDEFAULT_SIZE());
        frame.setResizable(Views.isRESIZABLE());

        frame.add(client);
        frame.add(server);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
