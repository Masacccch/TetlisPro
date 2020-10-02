package TETLIS;

public class OBJ{
    private int type ;
    private Z[] z = new Z[4];
    private int foword = 1 ;

    public OBJ(){
        this.type = -1;
        z[0] = new Z(0,0);
        z[1] = new Z(0,1);
        z[2] = new Z(1,1);
        z[3] = new Z(0,0);
    }

    public OBJ( int type){
        this.type = type ;
        switch (type){
            case 1:
                z[0] = new Z(1,0);
                z[1] = new Z(2,0);
                z[2] = new Z(0,1);
                z[3] = new Z(1,1);
                break;
            case 2:
                z[0] = new Z(0,0);
                z[1] = new Z(1,0);
                z[2] = new Z(1,1);
                z[3] = new Z(2,1);
                break;
            case 3:
                z[0] = new Z(0,0);
                z[1] = new Z(0,1);
                z[2] = new Z(1,1);
                z[3] = new Z(2,1);
                break;
            case 4:
                z[0] = new Z(2,0);
                z[1] = new Z(0,1);
                z[2] = new Z(1,1);
                z[3] = new Z(2,1);
                break;
            case 5:
                z[0] = new Z(0,0);
                z[1] = new Z(0,1);
                z[2] = new Z(0,2);
                z[3] = new Z(0,3);
                break;
            case 6:
                z[0] = new Z(0,0);
                z[1] = new Z(1,0);
                z[2] = new Z(0,1);
                z[3] = new Z(1,1);
                break;
            case 7:
                z[0] = new Z(1,0);
                z[1] = new Z(0,1);
                z[2] = new Z(1,1);
                z[3] = new Z(2,1);
                break;
        }
    }

    public void next(){
        for (int i = 0; i < z.length; i++) {
            z[i].next();
        }
    }

    public int getType() {
        return type;
    }

    public int[][] getZZZ(){
        int[][] x= new int[4][2];
        for (int i = 0; i < 4; i++) {
            x[i][0] = z[i].getX();
            x[i][1] = z[i].getY();
        }
        return x ;
    }

    public int[] getUnder(){
        int[] a = new int[4] ;
        switch (type){
            case -1:
                switch (foword){
                    case 1:
                    case 2:
                        a[1] = 1;
                        a[2] = 1;
                        break;
                    case 3:
                    case 4:
                        a[0] = 1;
                        a[1] = 1;
                        break;
                }
                break;
            case 1:
                switch (foword){
                    case 1:
                    case 3:
                        a[1]=1;
                        a[2]=1;
                        a[3]=1;
                        break;
                    case 2:
                    case 4:
                        a[2]=1;
                        a[3]=1;
                        break;
                }
                break;
            case 2:
                switch (foword){
                    case 1:
                    case 3:
                        a[0]=1;
                        a[2]=1;
                        a[3]=1;
                        break;
                    case 2:
                    case 4:
                        a[2]=1;
                        a[3]=1;
                        break;
                }
                break;
            case 3:
                switch (foword){
                    case 1:
                        a[1] = 1;
                        a[2] = 1;
                        a[3] = 1;
                        break;
                    case 2:
                        a[3] = 1;
                        a[2] = 1;
                        break;
                    case 3 :
                        a[3] = 1;
                        a[1] = 1;
                        a[2] = 1;
                        break;
                    case 4:
                        a[3] = 1;
                        a[1] = 1;
                        break;
                }
                break;
            case 4:
                switch (foword){
                    case 1:
                        a[1] = 1;
                        a[2] = 1;
                        a[3] = 1;
                        break;
                    case 2:
                        a[3] = 1;
                        a[2] = 1;
                        break;
                    case 3 :
                        a[3] = 1;
                        a[1] = 1;
                        a[2] = 1;
                        break;
                    case 4:
                        a[2] = 1;
                        a[1] = 1;
                        break;
                }
                break;
            case 5:
                switch (foword){
                    case 1:
                    case 3:
                        a[3]=1;
                        break;
                    case 2:
                    case 4:
                        a[0]=1;
                        a[3] = 1;
                        a[1] = 1;
                        a[2] = 1;
                        break;
                }
                break;
            case 6:
                switch (foword){
                    case 1:
                        a[2] = 1;
                        a[3] = 1;
                        break;
                }
                break;
            case 7:
                switch (foword){
                    case 1:
                        a[1] = 1;
                        a[2] = 1;
                        a[3] = 1;
                        break;
                    case 2:
                        a[1] = 1;
                        a[3] = 1;
                        break;
                    case 3 :
                        a[0] = 1;
                        a[1] = 1;
                        a[3] = 1;
                        break;
                    case 4:
                        a[0] = 1;
                        a[1] = 1;
                        break;
                }
                break;
        }
        return a ;
    }
    public void moveLR(int arr){
        for (int i = 0; i < 4; i++) {
            z[i].moveLR(arr);
        }
    }

    public boolean canLR(int arr){
        for (int i = 0; i < 4; i++) {
            if(arr == 1) if( z[i].getX() == 9 ) return false ;
            if(arr == -1) if( z[i].getX() == 0 ) return false ;
        }
        return  true ;
    }

    public void turn(int arr){
        int marge;
        if(arr == 0) marge = arr/3 ;
        else marge = 1 + arr/3 ;
        for (int i = 0; i < 4; i++) {
            switch (arr%3){
                case 0://Right
                    z[i].changeXY(marge,0);
                    break;
                case 1://Left
                    z[i].changeXY(-marge,0);
                    break;
                case 2://Up
                    z[i].changeXY(0,-marge);
                    break;
            }
        }

        switch (type){
            case 1:
                switch (foword){
                    case 1:
                    case 3:
                        z[1].changeXY(0,1);
                        z[2].changeXY(2,1);
                        foword = 2 ;
                        break;
                    case 2:
                    case 4:
                        z[1].changeXY(0,-1);
                        z[2].changeXY(-2,-1);
                        foword = 1;
                        break;
                }
                break;
            case 2:
                switch (foword){
                    case 1:
                    case 3:
                        z[0].changeXY(0,1);
                        z[3].changeXY(-2,1);
                        foword = 2 ;
                        break;
                    case 2:
                    case 4:
                        z[0].changeXY(0,-1);
                        z[3].changeXY(2,-1);
                        foword = 1;
                        break;
                }
                break;
            case 3:
                switch (foword) {
                    case 1:
                        z[2].changeXY(0, -1);
                        z[3].changeXY(-2,1);
                        foword = 2;
                        break;
                    case 2:
                        z[2].changeXY(-3, 0);
                        z[3].changeXY(-1,-2);
                        foword = 3;
                        break;
                    case 3:
                        z[2].changeXY(2, -1);
                        z[3].changeXY(0,1);
                        foword = 4;
                        break;
                    case 4:
                        z[2].changeXY(1, 2);
                        z[3].changeXY(3, 0);
                        foword = 1;
                        break;
                }
                break;
            case 4:
                switch (foword) {
                    case 1:
                        z[2].changeXY(2, -0);
                        z[1].changeXY(2,-2);
                        foword = 2;
                        break;
                    case 2:
                        z[2].changeXY(0, -1);
                        z[1].changeXY(2,1);
                        foword = 3;
                        break;
                    case 3:
                        z[2].changeXY(-2, 0);
                        z[1].changeXY(-2,2);
                        foword = 4;
                        break;
                    case 4:
                        z[2].changeXY(0, 1);
                        z[1].changeXY(-2, -1);
                        foword = 1;
                        break;
                }
                break;
            case 5:
                switch (foword){
                    case 1:
                    case 3:
                        z[0].changeXY(-1,1);
                        z[2].changeXY(1,-1);
                        z[3].changeXY(2,-2);
                        foword = 2 ;
                        break;
                    case 2:
                    case 4:
                        z[0].changeXY(1,-1);
                        z[2].changeXY(-1,1);
                        z[3].changeXY(-2,2);
                        foword = 1;
                        break;
                }
                break;
            case 7:
                switch (foword){
                    case 1:
                        z[1].changeXY(1,1);
                        foword = 2 ;
                        break;
                    case 2:
                        z[0].changeXY(-1,1);
                        foword = 3;
                        break;
                    case 3:
                        z[3].changeXY(-1,-1);
                        foword = 4;
                        break;
                    case 4:
                        z[0].changeXY(1,-1);
                        z[1].changeXY(-1,-1);
                        z[3].changeXY(1,1);
                        foword = 1;
                        break;
                }
                break;
        }
    }

    public boolean canTurn(int arr, int[][] created){
        int tflag = 0 ;
        switch (type){
            case 1:
                switch (foword){
                    case 1:
                    case 3:
                        tflag += canTurnX(arr,created,z[1].getXY(),0,1);
                        tflag += canTurnX(arr,created,z[2].getXY(),2,1);
                        break;
                    case 2:
                    case 4:
                        tflag += canTurnX(arr,created,z[1].getXY(),0,-1);
                        tflag += canTurnX(arr,created,z[2].getXY(),-2,-1);
                        break;
                }
                break;
            case 2:
                switch (foword){
                    case 1:
                    case 3:
                        tflag += canTurnX(arr,created,z[0].getXY(),0,1);
                        tflag += canTurnX(arr,created,z[3].getXY(),-2,1);
                        break;
                    case 2:
                    case 4:
                        tflag += canTurnX(arr,created,z[0].getXY(),0,-1);
                        tflag += canTurnX(arr,created,z[3].getXY(),2,-1);
                        break;
                }
                break;
            case 3:
                switch (foword) {
                    case 1:
                        tflag += canTurnX(arr,created,z[2].getXY(),0, -1);
                        tflag += canTurnX(arr,created,z[3].getXY(),-2,1);
                        break;
                    case 2:
                        tflag += canTurnX(arr,created,z[2].getXY(),-3, 0);
                        tflag += canTurnX(arr,created,z[3].getXY(),-1,-2);
                        break;
                    case 3:
                        tflag += canTurnX(arr,created,z[2].getXY(),2, -1);
                        tflag += canTurnX(arr,created,z[3].getXY(),0,1);
                        break;
                    case 4:
                        tflag += canTurnX(arr,created,z[2].getXY(),1, 2);
                        tflag += canTurnX(arr,created,z[3].getXY(),3, 0);
                        break;
                }
                break;
            case 4:
                switch (foword) {
                    case 1:
                        tflag += canTurnX(arr,created,z[2].getXY(),2, -0);
                        tflag += canTurnX(arr,created,z[1].getXY(),2,-2);
                        break;
                    case 2:
                        tflag += canTurnX(arr,created,z[2].getXY(),0, -1);
                        tflag += canTurnX(arr,created,z[1].getXY(),2,1);
                        break;
                    case 3:
                        tflag += canTurnX(arr,created,z[2].getXY(),-2, 0);
                        tflag += canTurnX(arr,created,z[1].getXY(),-2,2);
                        break;
                    case 4:
                        tflag += canTurnX(arr,created,z[2].getXY(),0, 1);
                        tflag += canTurnX(arr,created,z[1].getXY(),-2, -1);
                        break;
                }
                break;
            case 5:
                switch (foword){
                    case 1:
                    case 3:
                        tflag += canTurnX(arr,created,z[0].getXY(),-1,1);
                        tflag += canTurnX(arr,created,z[2].getXY(),1,-1);
                        tflag += canTurnX(arr,created,z[3].getXY(),2,-2);
                        break;
                    case 2:
                    case 4:
                        tflag += canTurnX(arr,created,z[0].getXY(),1,-1);
                        tflag += canTurnX(arr,created,z[2].getXY(),-1,1);
                        tflag += canTurnX(arr,created,z[3].getXY(),-2,2);
                        break;
                }
                break;
            case 7:
                switch (foword){
                    case 1:
                        tflag += canTurnX(arr,created,z[1].getXY(),1,1);
                        break;
                    case 2:
                        tflag += canTurnX(arr,created,z[0].getXY(),-1,1);
                        break;
                    case 3:
                        tflag += canTurnX(arr,created,z[3].getXY(),-1,-1);
                        break;
                    case 4:
                        tflag += canTurnX(arr,created,z[0].getXY(),1,-1);
                        tflag += canTurnX(arr,created,z[1].getXY(),-1,-1);
                        tflag += canTurnX(arr,created,z[3].getXY(),1,1);
                        break;
                }
                break;
        }
        if(tflag > 0) return false ;
        return true ;
    }

    public int canTurnX(int arr,int[][] c,int[] za,int x,int y) {
        int[] copyZa = za;
        int marge;
        if(arr == 0) marge =0 ;
        else marge = 1 + arr/3 ;
        switch (arr%3){
            case 0://Right
                copyZa[0] += marge ;
                break;
            case 1://Left
                copyZa[0] -= marge;
                break;
            case 2://Up
                copyZa[1] -= marge;
                break;
        }

        int reX = copyZa[0] + x;
        int reY = copyZa[1] + y;
        if (reX < 0 || reX >= 10) return 100;
        if(reY <0) return 200;
        if (c[reX][reY] > 0) {
            return 1;
        }
        return 0;
    }
}
