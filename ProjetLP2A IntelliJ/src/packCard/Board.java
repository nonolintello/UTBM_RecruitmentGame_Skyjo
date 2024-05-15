package packCard;
import java.util.ArrayList;
import game.Vector;


public class Board{
    private ArrayList<CardList> board;

    public Board() {
        this.board= new ArrayList<CardList>();
    }

    public Card getByCoor(Vector vect) { //return the Card located in coordinates of the Vector in paramaters
        return this.board.get(vect.getX()).getByIndex(vect.getY());//we get the column with x and the row with the y of the Vector
    }

    public void setByCoor(Vector vect,Card C){//set the Card located in coordinates of the Vector in paramaters
        this.board.get(vect.getX()).setByIndex(vect.getY(),C);
    }

    public int rowDetection() { //this method is use to detect when all the values of a column are the same and all reveal and return the value of the column if true
        boolean same;
        for(int x=0;x<4;x++){//for each column
            same = true; //variable to check if all the values of the columns are the same
            int val = this.getByCoor(new Vector(x,0)).getVal(); //we get the value of the top of the column in order to compare it afterwards
            for(int y=0;y<3;y++){//for each row of the column
                Card C = this.getByCoor(new Vector(x,y)); // we get the card we should compare
                if(C.getVal() != val || C.getState()==false){ // we need to check if the card has the same value as val AND has a state of true to say that the row is ok, the opposite of this is when state is false OR val different of val
                    same = false;
                }

            }
            if(same){//if the row is complete with same values and all reveal, we return his index in the board to use it later
                return x;
            }
        }
        return -1;// if not complete then we return -1 to know that any row was fine
    }
    public void setCardStateAtCoord(Vector vect,boolean state){// set the Card which is located at coordinates of Vector vect to the parameters state
        this.getByCoor(vect).setState(state);
    }


    public boolean allReveal() { //this method is use to detect when all the cards of the board are turned (visible) and if : return true
        for(int x=0;x<4;x++){
            for(int y=0;y<3;y++){// we travel the whole board
                Card C = this.getByCoor(new Vector(x,y)); // in C we collect the Card where we are in our board traveling
                if(C.getState()==false && C.getVal()!= 0){ // the board is reveal if C state's is true OR C value's equals 0 (because of when we delete a full row, we put Card with 0 values and cannot set their state to true) that's why here we check the opposite :
                    return false;										// it means we check when C state's is false AND C value's different of 0
                }
            }
        }
        return true;//if we are there and haven't return then it means all the cards are visible
    }

    public void fillBoardWithDeck(Deck deck){ //method who fill the board of a player with the deck, useful to instantiate Board for Player for instance
        for(int x=0;x<4;x++){
            CardList CL = new CardList();
            this.board.add(CL);
            for(int y=0;y<3;y++){
                this.board.get(x).add(deck.stripFirst());

            }
        }
    }

    public CardList getColumn(int index){
        return this.board.get(index);
    }
}