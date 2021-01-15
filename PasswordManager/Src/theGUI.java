import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class theGUI implements ActionListener {

    private PasswordGen ps1= new PasswordGen(26);
    private passwordReader pr1= new passwordReader();

    //frame elements
    private JFrame frame; //frame
    private JLabel titleLabel; //title label
    private JPanel leftSide; //left pane
    private JPanel rightSide; //right pane
    private JScrollPane rightSideScroll; //scrollable area storing password buttons
    private JPanel scrollPanePanel;
    private JLabel textLabel; //label to the left of input/output text field in leftside pane
    private JTextField leftEnter; //where to enter the password
    private JButton generate; //where to generate password
    private JButton check; //where to check password
    private JLabel gen_check_response; //response label
    private ArrayList<passwordCell> passwordList;

    public theGUI(){
        frame= new JFrame();


        passwordList = new ArrayList<>(); //init list

        //creating title label
        ImageIcon logo = new ImageIcon(new ImageIcon("keys.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)); //create an image icon
        titleLabel = new JLabel("Password Manager"); //create label
        titleLabel.setIcon(logo);
        titleLabel.setFont(new Font("SansSerif",Font.BOLD, 30));
        titleLabel.setIconTextGap(30); //sets the gap of image from text
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);

        generateLeftside();
        generateRightSide();

        //normal setup stuff
        frame.setTitle("Password Hub");
        frame.setResizable(false); //prevent from resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
        frame.setSize(1000,500);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(new Color(0,128,255));

        //adding
        frame.add(rightSide, BorderLayout.EAST);
        frame.add(leftSide, BorderLayout.WEST);
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * generate left pane
     */
    public void generateLeftside(){

        //leftsidePanel will hold password checker and generator
        leftSide = new JPanel();
        leftSide.setPreferredSize(new Dimension(500,900));
        leftSide.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        leftSide.setBackground(new Color(0,128,255));

        //label for textField
        textLabel= new JLabel("Generated / Check Password");
        textLabel.setPreferredSize(new Dimension(180,50));
        textLabel.setFont(new Font("SansSerif",Font.BOLD, 12));

        //text field
        leftEnter= new JTextField("Enter/Copy Password here");
        leftEnter.setPreferredSize(new Dimension(220,20));

        //button to generate password
        generate= new JButton("Generate Strong Password");
        generate.addActionListener(this);
        generate.setFocusable(false);
        generate.setFont(new Font("SansSerif",Font.BOLD, 12));
        generate.setPreferredSize(new Dimension(230,50));
        generate.setBackground(Color.RED);

        //button to check password
        check= new JButton("Check Password Strength");
        check.addActionListener(this);
        check.setFocusable(false);
        check.setFont(new Font("SansSerif",Font.BOLD, 12));
        check.setPreferredSize(new Dimension(220,50));
        check.setBackground(Color.GREEN);

        gen_check_response= new JLabel();
        gen_check_response.setVerticalAlignment(JLabel.TOP);
        gen_check_response.setHorizontalTextPosition(JLabel.LEFT);
        gen_check_response.setVerticalTextPosition(JLabel.TOP);
        gen_check_response.setPreferredSize(new Dimension(450,300));
        gen_check_response.setFont(new Font("SansSerif",Font.BOLD, 20));


        leftSide.add(textLabel);
        leftSide.add(leftEnter);
        leftSide.add(generate);
        leftSide.add(check);
        leftSide.add(gen_check_response);

    }

    /**
     * Generate the right side of the gui
     */
    public void generateRightSide(){
        //Title label
        JLabel savedPasswords= new JLabel("Saved Passwords");
        savedPasswords.setFont(new Font("SansSerif",Font.BOLD, 25));

        //panel that holds all componenents of rightside
        rightSide = new JPanel();
        scrollPanePanel= new JPanel();  //panel inside the scroll panel
        scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.PAGE_AXIS));
        scrollPanePanel.setBackground(Color.white);

        rightSide.setPreferredSize(new Dimension(500,900));
        rightSide.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        rightSide.setBackground(new Color(0,128,255));
        rightSideScroll = new JScrollPane(scrollPanePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   //the scroll panel holding the scrollPanePanel
        rightSideScroll.getViewport().setPreferredSize(new Dimension(500,300));  //constrict the viewport of the scroll pane
        rightSideScroll.getViewport().setMinimumSize(new Dimension(500,300));  //constrict the viewport of the scroll pane
        rightSideScroll.setPreferredSize(new Dimension(500,300));
        rightSideScroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

        JButton addButton= new JButton("Add Password");
        addButton.setFocusable(false);
        addButton.setPreferredSize(new Dimension(300,60));
        addButton.setFont(new Font("SansSerif",Font.BOLD, 25));
        addButton.setBackground(Color.GREEN);
        addButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    create_Add_Dialogue();
                }

        });


        //adding to righsidePanel
        rightSide.add(savedPasswords);
        rightSide.add(rightSideScroll);
        rightSide.add(addButton);

       /////////////populating the sroll pane with buttons
        this.passwordList= pr1.readPasswords();  //get the updated list from the storage file
        //loop through data and create buttons
        for(passwordCell obj: passwordList){
            createCell(obj);
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==generate){
            leftEnter.setText(ps1.generatePass());
            gen_check_response.setText("Secure password generated in text field");
        }
        else if(e.getSource()== check){
            ArrayList<Boolean> truthArray= passwordValidator.checkValidity(leftEnter.getText());
            String text= String.format("<html>Contains capital letter(s): %b<br/>Contains lowercase letter(s): %b<br/>Contains digit(s): %b<br/>Contains special characters(s): %b<br/>More than 16 characters: %b<br/></html>", truthArray.get(0), truthArray.get(1), truthArray.get(2), truthArray.get(3),truthArray.get(4));
            gen_check_response.setText(text);
        }
    }



    /**
     * this method creates a remove, cell button pair used for accessing stored passwords
     * @param obj the passwordCell object that button represents
     */
    public void createCell (passwordCell obj){
        //panel that hold each password buttons corresponding remove button
        JPanel pairPanel= new JPanel();
        pairPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 5,10));
        JButton removeButton= new JButton("Remove");
        JButton listButton= new JButton(obj.getName());
        removeButton.setFocusable(false);
        listButton.setFocusable(false);
        //using an inner class all buttons will have their own listener
        //this is inefficient but it is temporary
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(obj.getName()+ "-->button was pressed");
                PasswordInfoWindow infoWindow= new PasswordInfoWindow(obj);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("removed "+obj.getName()+" from passwords");
                passwordList.remove(obj);
                System.out.println(passwordList.toString());
                //updates the frame to show removed password from list
                scrollPanePanel.remove(pairPanel);
                scrollPanePanel.revalidate();
                scrollPanePanel.repaint();
                //update the file with current passwords, not method is pretty inefficient if user has larger and larger sizes of stored passwords
                pr1.writePasswords(passwordList);

            }
        });
        pairPanel.setPreferredSize(new Dimension(500,40));
        pairPanel.setMaximumSize(new Dimension(500,40));
        listButton.setPreferredSize(new Dimension(320,30));
        removeButton.setPreferredSize(new Dimension(100,30));
        //repainting the rightside panel
        pairPanel.add(removeButton);
        pairPanel.add(listButton);
        //pairPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        scrollPanePanel.add(pairPanel);
    }

    /**
     * this method creates the dialog box that opens when "add password" is clicked
     */
    public void create_Add_Dialogue (){
        JDialog dialog= new JDialog();
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 5,5 ));
        dialog.setSize(400,250);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel nameL= new JLabel("Name");
        nameL.setFont(new Font("SansSerif",Font.PLAIN, 10));;
        nameL.setPreferredSize(new Dimension(90,30));
        JTextField name=new JTextField();
        name.setPreferredSize(new Dimension(200, 30));

        JLabel passwordL= new JLabel("Password");
        passwordL.setFont(new Font("SansSerif",Font.PLAIN, 10));
        passwordL.setPreferredSize(new Dimension(90,30));
        JTextField password=new JTextField();
        password.setPreferredSize(new Dimension(200, 30));

        JLabel usernameL= new JLabel("Username");
        usernameL.setFont(new Font("SansSerif",Font.PLAIN, 10));;
        usernameL.setPreferredSize(new Dimension(90,30));
        JTextField username=new JTextField();
        username.setPreferredSize(new Dimension(200, 30));

        JLabel emailL= new JLabel("Email");
        emailL.setFont(new Font("SansSerif",Font.PLAIN, 10));;
        emailL.setPreferredSize(new Dimension(90,30));
        JTextField email=new JTextField();
        email.setPreferredSize(new Dimension(200, 30));

        JButton submit= new JButton("Submit");
        submit.setFont(new Font("SansSerif",Font.PLAIN, 10));;
        submit.setForeground(Color.green);
        submit.setPreferredSize(new Dimension(200,40));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordCell added_password = new passwordCell(name.getText(), password.getText(), username.getText(), email.getText());
                passwordList.add(added_password);
                createCell(added_password);
                pr1.writePasswords(passwordList);
                scrollPanePanel.revalidate();
                scrollPanePanel.repaint();
                dialog.dispose();
            }
        });

        dialog.add(nameL);
        dialog.add(name);
        dialog.add(passwordL);
        dialog.add(password);
        dialog.add(usernameL);
        dialog.add(username);
        dialog.add(emailL);
        dialog.add(email);
        dialog.add(submit);

        dialog.setVisible(true);
    }

}
