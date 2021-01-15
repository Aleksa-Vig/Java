import javax.swing.*;
import java.awt.*;

/**
 * this is the seperate window that
 * opens the stored password info for the user
 * to access
 */
public class PasswordInfoWindow {

    private JFrame infoFrame= new JFrame();
    JTextArea infoArea= new JTextArea();

    PasswordInfoWindow(passwordCell obj){
        //Text for info area
        String text= "Name: "+obj.getName()+"\nPassword: "+obj.getPassword()+"\nUsername: "+obj.getUsername()+"\nEmail: "+obj.getEmail();
        infoFrame.setResizable(false);
        infoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        infoFrame.setSize(400, 190);
        infoFrame.setLayout(null);

        //the text field that stores info
        infoArea.setText(text);
        infoArea.setFont(new Font("SansSerif",Font.BOLD, 20));
        infoArea.setBounds(60,10,400,150);
        infoFrame.add(infoArea);

        infoFrame.setVisible(true);

    }
}
