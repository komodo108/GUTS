import com.guts.michael.connection.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

public class ConnectionView {

    private static ConnectionView connectionView;
    private JFrame frame;
    private JTextField ip;
    private JButton go;
    private JLabel error;

    private Connection connection;

    private final String DEFAULT_NAME = "Enter Server IP";
    private final int DEFAULT_WIDTH = 400;
    private final int DEFAULT_HEIGHT = 400;
    private final Dimension DEFAULT_SIZE = new Dimension(400, 400);
    private final boolean RESIZABLE = false;

    public static ConnectionView getConnectionView() {
        if(connectionView == null) {
            connectionView = new ConnectionView();
            return connectionView;
        } else {
            return connectionView;
        }
    }

    private ConnectionView() {
        init();
    }

    private void init() {

        error = new JLabel();
        error.setBounds(DEFAULT_WIDTH/2 - 40, DEFAULT_HEIGHT/2 + 40, 80, 90);

        ip = new JTextField();
        ip.setBounds((DEFAULT_WIDTH/2 - 120), (DEFAULT_HEIGHT/2 - 20), 240, 20);
        ip.setMinimumSize(new Dimension(240, 20));

        go = new JButton("Go");
        go.setMaximumSize(new Dimension(60, 20));
        go.setBounds((DEFAULT_WIDTH/2 - 30), (DEFAULT_HEIGHT/2 + 22), 60, 20);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection = new Connection(ip.getText());
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame = new JFrame(DEFAULT_NAME);
        frame.setSize(DEFAULT_SIZE);
        frame.setResizable(RESIZABLE);

        frame.add(ip);
        frame.add(go);
        frame.add(error);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private String format(String text) {
        return "<html><div><p style='text-align: center;'>" + text + "</p></div></html>";
    }
}
