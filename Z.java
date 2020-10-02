package TETLIS;

public class Z{
    private int x;
    private int y;

    public Z(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void next(){
        this.y++ ;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getXY(){
        int[] a ={this.x,this.y};
        return a;
    }

    public void changeXY(int x,int y){
        this.x += x;
        this.y += y ;
    }

    public void moveLR(int arr){
        if(arr == -1) this.x-- ;
        if(arr == 1) this.x++ ;
    }
}
