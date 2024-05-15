package packCard;
import java.util.ArrayList;

public class CardList {
    private ArrayList<Card> cardList;


    public void setByIndex(int i,Card C){ //set the card of index i of the cardList to Card C
        this.cardList.set(i,C);
    }
    public Card getByIndex(int i){//get the card of index i of the cardList
        return this.cardList.get(i);

    }
    public void add(Card C){//add the card C to cardList (in tail)
        this.cardList.add(C);
    }
    public int getSize(){// get the size of the cardList
        return this.cardList.size();
    }
    public void removeByIndex(int i){//remove the card of index i of the cardList
        this.cardList.remove(i);
    }
    // WARNING : the last index of the list is the first Card of the CardList if we think about the game
    public Card getFirst(){//return the Card which is at the last index of the list
        if(this.getSize() == 0){
            return new Card(-1);
        }
        return this.getByIndex(this.getSize()-1);
    }

    public Card stripFirst(){//strip the Card which is at the last index of the list : it removes the Card and then return it
        Card c = this.getByIndex(this.getSize()-1);
        this.removeByIndex(this.getSize()-1);
        return c;
    }

    public CardList(){ //constructor of CardList
        this.cardList = new ArrayList<Card>(); // Create an ArrayList object
    }

    public String toString(){ //call toString method of ArrayList which need toString method of Card
        return cardList.toString();
    }
}