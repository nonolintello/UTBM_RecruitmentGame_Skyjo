package packCard;

public class Discard extends CardList{

    public void setFirst(Card C){// this is not really a setFirst but since we only use the discard with revealed cards,
        // we thought of overriding the default setFirst with one that reveals the card in the same time.
        C.setState(true);
        this.add(C);

    }



}


