package GameGUI;
import packCard.*;
import game.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class GameGUI extends JFrame implements ActionListener, ScreenConstants{

    private JButton[] boardButtons = new JButton[12];
    private JButton buttonDeck;
    private JButton buttonDiscard;
    private JButton buttonThrowAway;
    private JLabel HandLabel;
    private JPanel numberPanel;
    private JLabel TaskLabel;
    private Vector Decision;

    private static Map<String, ImageIcon> imageMap;



    public Vector getDecision(){
        return this.Decision;
    } // The type of the return is a vector here because we use it to identify one element of the board, which can be repesented as
    // a 2 dimensional list.
    public GameGUI(Board board, Card Discard, Card Hand) {
        super("UTBM Recruitment Game (totally not Skyjo)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        imageMap = new HashMap<>();
        BufferedImage img;
         try{ // this bit of code fills a HashMap with ImageIcon objects, being every single image of the promo logos
             // and the image for a hidden card.
             Image temp;
             for(int y = -2; y < 24;y++){
                 img = ImageIO.read(new File(getImagePath(""+y)));
                 // Scale the image to fit the button's size
                 // Create an ImageIcon object with the scaled image
                 temp = img.getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,Image.SCALE_SMOOTH);
                 imageMap.put(Integer.toString(y),new ImageIcon(temp));
             }
             img = ImageIO.read(new File(getImagePath("?")));
             temp = img.getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,Image.SCALE_SMOOTH);
             imageMap.put("?",new ImageIcon(temp));

         }catch (Exception ex){
             ex.printStackTrace();
         }



        buttonDeck = new JButton("?");
        buttonDeck.addActionListener(this);
        buttonDeck.setBounds(DECK_WIDTH_ORIGIN, DECK_HEIGHT_ORIGIN, DECK_WIDTH, DECK_HEIGHT);
        buttonDiscard = new JButton(Discard.getValGUI());
        buttonDiscard.addActionListener(this);
        buttonDiscard.setBounds(DISCARD_WIDTH_ORIGIN, DISCARD_HEIGHT_ORIGIN, DISCARD_WIDTH, DISCARD_HEIGHT);
        buttonThrowAway = new JButton("Throw away ?");
        buttonThrowAway.addActionListener(this);
        buttonThrowAway.setBounds(THROW_WIDTH_ORIGIN,THROW_HEIGHT_ORIGIN,THROW_WIDTH,THROW_HEIGHT);
        buttonThrowAway.setVisible(false);

        TaskLabel = new JLabel("Wait for other players to start !");
        TaskLabel.setBounds(TASK_WIDTH_ORIGIN,TASK_HEIGHT_ORIGIN,TASK_WIDTH,TASK_HEIGHT);
        TaskLabel.setHorizontalAlignment(JLabel.CENTER);


        HandLabel = new JLabel("Default Hand Value");
        HandLabel.setText("Your Hand : " + Hand.getValGUI());
        HandLabel.setBounds(HAND_WIDTH_ORIGIN,HAND_HEIGHT_ORIGIN,HAND_WIDTH,HAND_HEIGHT);

        // same operations repeated here, initialize a button or a label, set its default text and bounds and give it
        // an ActionListener.




        numberPanel = new JPanel(null);
        int buttonIndex = 0;
        for (int row = 0; row < 3; row++) {// Initialize the list of buttons used to diplay the cards
            for (int col = 0; col < 4; col++) {
                Vector vect = new Vector(col,row);
                JButton button = new JButton(board.getByCoor(vect).getValGUI());
                button.setBounds(GRID_START_WIDTH + col * (BUTTON_WIDTH + BUTTON_SPACE_WIDTH), GRID_START_HEIGHT + row * (BUTTON_HEIGHT + BUTTON_SPACE_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT);

                button.setIcon(imageMap.get(board.getByCoor(vect).getValGUI()));
                button.addActionListener(this);

                boardButtons[buttonIndex++] = button;
                numberPanel.add(button);
            }
        }
        // Add objects to the GUI
        add(HandLabel);
        add(buttonDeck);
        add(TaskLabel);
        add(buttonThrowAway);
        add(buttonDiscard);
        add(numberPanel);



        setVisible(true);
    }

    public void updateGUI(Board board, Card Discard, Card Hand){

        // Updates all elements of the GUI, except the TaskLabel, because we thought it was stupid to update the entire
        // GUI when all you needed was the task.
        buttonDiscard.setText(Discard.getValGUI());
        buttonDeck.setText("?");
        Hand.setState(true);
        HandLabel.setText("Your HAND : " + Hand.getValGUI());
        for(int i=0;i<12;i++){
            Vector vect = new Vector(i % 4, i / 4);
            ImageIcon icon = imageMap.get(board.getByCoor(vect).getValGUI());
            boardButtons[i].setIcon(icon);
            boardButtons[i].setMargin(new Insets(0, 15, 0, 0)); // Used to solve a bug that causes the Button's icon
            // to be slightly offset to the right, now real answer found but this works (sad).

        }
    }

    public void actionPerformed(ActionEvent e) {// this method is called on each press of any button in the board,
        // it allows us to get which button has been clicked, if it is from the board  or the other 3 buttons.
        Object source = e.getSource();
        if (source instanceof JButton) {
            int buttonIndex = findButtonIndex((JButton) source);
            this.setDecision(new Vector(buttonIndex % 4, buttonIndex / 4));
            //System.out.println("Coordinates of Pressed button : \n x : " + buttonIndex % 4 + " y : " + buttonIndex / 4);
        }
    }

    public void setThrowVisible(boolean bool){
        buttonThrowAway.setVisible(bool);
    }
    public void setDecision(Vector vect){
        this.Decision=vect;
    }

    public int findButtonIndex(JButton buttonToFind) {
        // Loop through the list and compare each button with the buttonToFind
        for (int i = 0; i < 12; i++) {
            if (this.boardButtons[i].equals(buttonToFind)) {
                return i; // Return the index of the first matching button
            }
        }
        if (buttonDeck.equals(buttonToFind)){
            return -2;
        }
        if (buttonDiscard.equals(buttonToFind)){
            return -3;
        }
        if (buttonThrowAway.equals(buttonToFind)){
            return -1;
        }
        else{
            return 0;
        }


    }

    public String getImagePath(String ValGUI){ // given a number or symbol, it returns the path to that number's
        // equivalent in promo logos.
            switch (ValGUI) {
                case "Empty !":
                    return "images/empty.jpg";
                case "?":
                    return "images/interrogation.jpg";
                default:
                    return "images/" + ValGUI + ".jpg";

            }
    }
    public void setTask(String task){
        TaskLabel.setText(task);
    }

    public void hideColumn(int col){
        for(int x = 0; x < 12;x++){
            if (x % 4 == col){
                boardButtons[x].setVisible(false);
            }
        }
    }
}

//