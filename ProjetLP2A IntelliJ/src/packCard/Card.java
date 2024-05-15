package packCard;

public class Card {
    private int value;
    private boolean state;

    public int getVal() {
        return value;
    }
    public boolean getState(){
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }
    public Card(int v) {
        //this.name="Promo "+ String.valueOf(v);
        this.value=v;
        this.state= false;
    }
    public String getValGUI(){
        if(this.getVal() == -10){
            return "Empty !";
        }
        if (this.getState()==false){
            return "?";
        }else{
            return Integer.toString( this.getVal()) ;
        }


    }

    public String toString(){
        return Integer.toString(this.getVal());
    }

}