package game;
import GameGUI.*;
import packCard.*;

public class Player {
    private GameGUI GUI;
    private Board board;
    public Card hand;

    public int score;

    // Most methods in this class are redundant to the ones in GameGUI
    // that is because we need to be able to communicate to the GameGUI through the GameController,
    // which only has access to the players.

    public void resetDecision(){
        this.GUI.setDecision(null);
    }
    public Vector getDecision(){ // This is used to update an attribute of the GUI attribute of this class,
        // it is the main way we communicate a player's decision to the gameController.
        return this.GUI.getDecision();
    }
    public void setHand(Card c){
        this.hand = c;
    }
    public Player(Deck deck,Card Discard){
        this.board = new Board();
        this.board.fillBoardWithDeck(deck);
        this.hand = new Card(-10);
        this.GUI = new GameGUI(board, Discard, hand);

    }

    public void setThrowVisible(boolean bool){
        this.GUI.setThrowVisible(bool);
    }

    public Card getHand(){
        return this.hand;
    }

    public void emptyHand(){
        this.hand = new Card(-10);
    }


    public void UpdatePlayerGUI(Card Discard){
        this.GUI.updateGUI(board,Discard,hand);
    }

    public void setTask(String task){
        this.GUI.setTask(task);
    }
    public Board getBoard() {
        return this.board;
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }

    public int getTotalScore(){
        int score = 0;
        for(int x = 0; x <3 ; x++){
            for(int y = 0;y<4; y++){
                score += this.getBoard().getByCoor(new Vector(y,x)).getVal();
            }

        }
        return score;
    }

    public void deleteSameRows(CardList discard){ // checks for rows of same value in the player's board
        // and if it finds one, deletes it.
        int col = this.getBoard().rowDetection();
        if(col >= 0) {
            CardList CL = this.board.getColumn(col);
            for(int i = 0; i < 3; i++){
                discard.add(CL.getByIndex(i));
                CL.setByIndex(i,new Card(0));
            }
            this.GUI.hideColumn(col);
        }
    }

    public void revealBoard(){
        for(int x = 0; x <3 ; x++){
            for(int y = 0;y<4; y++){
                this.getBoard().setCardStateAtCoord(new Vector(y,x),true);
            }

        }
    }

}
