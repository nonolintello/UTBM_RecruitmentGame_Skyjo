package game;

public class Vector {
    private int x;
    private int y;

    public int getX(){
        return this.x;
    }
    public boolean compare(Vector v1){
        if(this.getX() == v1.getX() && this.getY() == v1.getY()){
            return true;
        }else{
            return false;
        }
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public Vector(int x,int y){
        this.setX(x);
        this.setY(y);
    }
}
