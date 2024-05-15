package packCard;
import java.util.Random;

public class Deck {


    private CardList DeckList;
    private int CardsPerUnique = 10;
    private int InferiorCard = 1;
    private int SuperiorCard = 23;


    public int getSize(){
        return this.DeckList.getSize();
    }
    public void setByIndex(int i, Card C){
        this.DeckList.setByIndex(i,C);
    }
    public Card getByIndex(int i){
        return this.DeckList.getByIndex(i);
    }
    public void removeByIndex(int i){
        this.DeckList.removeByIndex(i);
    }

    public Card stripFirst(){
        Card c = this.getByIndex(this.DeckList.getSize()-1);
        this.removeByIndex(this.DeckList.getSize()-1);
        return c;
    }


    public void shuffleDeck(){
        int size = this.DeckList.getSize();
        Random random = new Random();
        for (int i = size-1;i > 0; i--){
            int j = random.nextInt(i+1);
            Card temp = this.getByIndex(i);
            this.setByIndex(i,this.getByIndex(j));
            this.setByIndex(j,temp);

        }
    }

    public Deck(){
        DeckList = new CardList();
    }

    public Deck(int setting){// this method is used to initialize and fill a deck

        if (setting == 1){
            DeckList = new CardList();
            for(int i = InferiorCard; i <= SuperiorCard; i++){
                for(int j = 0; j < CardsPerUnique;j++){
                    Card temp = new Card(i);
                    DeckList.add(temp);
                }
            }
        }
        else if (setting == 2){
            DeckList = new CardList();


        }
        else{
            System.out.println("wrong setting passed to Deck constructor");
        }
    }

    public boolean isEmpty(){
        if (this.DeckList.getSize() == 0){
            return true;
        }else{
            return false;
        }
    }

    public void fillWith(Discard dc){
        for(int x=0;x<dc.getSize();x++){
            DeckList.add(dc.getByIndex(x));
            dc.removeByIndex(x);
        }
    }

    public String toString(){
        return DeckList.toString();
    }


}
