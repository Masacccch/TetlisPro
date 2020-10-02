package TETLIS;
import processing.core.*;

public class Main extends PApplet {

    @Override
    public void settings() {
        size(960 , 860);
    }

    long counter = 0;
    int waiter = 0;

    int Score = 0;
    int IncScore = 1;
    int BonusScore = 10000;

    final int Xnum = 10;
    final int Ynum = 15;

    int startFlag = 0;

    int[][] field = new int[Xnum][Ynum];
    int[][] created = new int[Xnum][Ynum];

    int Speed = 30;
    int sCounter = 0;

    //test object a
    OBJ Target = new OBJ(1+(int)random(7));
    OBJ nextTarget = new OBJ(1+(int)random(7));

    @Override
    public void setup() {
        //under line fill 3
        for (int i = 0; i < Xnum; i++) {
            field[i][Ynum - 1] = 9;
            created[i][Ynum - 1] = 3;
        }
        background(200);
        strokeWeight(2);
        rect(100, 100, 500, 700);
        showNext();
        textSize(50);
        text("   ↑    :  Start",630,640);
        text("←↓→ : Move",630,700);
        text("ENTER : Rotate",600,800);
    }

    @Override
    public void keyReleased() {
        if (keyCode == RIGHT) {
            if (Target.canLR(1) && canLLorRR(1)) {
                Target.moveLR(1);
            }
        } else if (keyCode == LEFT) {
            if (Target.canLR(-1) && canLLorRR(-1)) {
                Target.moveLR(-1);
            }
        }
        if (keyCode == ENTER) {
            for (int i = 0; i < 13; i++) {
                if (Target.canTurn(i, created)) {
                    Target.turn(i);
                    break;
                }
            }
        }
        if(keyCode == UP){
            if(startFlag == -1) startFlag = -2 ;
            else startFlag = 1;
        }

        if (keyCode == DOWN) {
            if (sCounter == 0) {
                Speed = 10;
                sCounter = 1;
                IncScore = 3;
            } else if (sCounter == 1) {
                Speed = 30;
                sCounter = 0;
                IncScore = 1;
            }
        }
    }

    boolean canLLorRR( int f) { // RIGHT:1 ,LEFT :-1
        int[][] b = Target.getZZZ();
        for (int i = 0; i < 4; i++) {
            if (created[b[i][0] + f][b[i][1]] > 0) {
                return false;
            }
        }
        return true;
    }

    void Release() {
        Target = nextTarget ;
        nextTarget = new OBJ(1+(int)random(7));
    }

    boolean chechUndRemove() {
        int checkSum = 0;
        for (int i = 0; i < Xnum; i++) {
            checkSum += created[i][0];
        }
        if (checkSum > 0) return false;
        int BonusCnt = 1 ;
        for (int i = 0; i < Ynum - 1; i++) {
            int c = 0;
            for (int j = 0; j < Xnum; j++) {
                if (created[j][i] > 0) {
                    c++;
                }
            }
            if (c >= Xnum) {
                for (int l = i; l > 0; l--) {
                    for (int k = 0; k < Xnum; k++) {
                        created[k][l] = created[k][l - 1];
                    }
                }
                Score += BonusScore;
                BonusScore *= ++BonusCnt ;
            }
            BonusScore = 10000;
        }
        return true;
    }
    
    void setColor(int type){
        switch (type){
            case 7:
                fill(245,51,200);
                break;
            case 1:
                fill(77,245,51);
                break;
            case 2:
                fill(206,245,51);
                break;
            case 3:
                fill(51,151,245);
                break;
            case 4:
                fill(74,51,245);
                break;
            case 5:
                fill(51,238,245);
                break;
            case 6:
                fill(245,232,51);
                break;
        }
    }

    void showNext(){
        stroke(0);
        rect(700,100,200,150);
        strokeWeight(3);
        OBJ next =new OBJ(nextTarget.getType());
        setColor(next.getType());
        int zure = 0;
        if(next.getType() == 5) {
            next.turn(1);
            zure =  75 ;
        }
        int[][] za = next.getZZZ();
        for (int i = 0; i <4; i++) {
            rect(725+zure+za[i][0]*50,125-zure/2+za[i][1]*50,50,50);
        }
        strokeWeight(0);
        stroke(200);
        fill(255);
    }




    @Override
    public void draw() {

        textSize(70);
        fill(130);
        text("TETLIS GAME",270,80);
        fill(255);

        if(startFlag == 0 ) {
        }else if(startFlag == -1){
        }else if(startFlag == -2){
            Score = 0;
            IncScore = 1;
            Speed = 30;
            for (int i = 0; i < Ynum-1; i++) {
                for (int j = 0; j < Xnum; j++) {
                    created[j][i] = 0 ;
                }
            }
            startFlag = 1 ;
        }else if(startFlag == 1){

            Score += IncScore ;
            // fill table 0
            for (int i = 0; i < Ynum - 1; i++) { //y
                for (int j = 0; j < Xnum; j++) { //x
                    field[j][i] = 0;
                }
            }
            //set created
            for (int i = 0; i < Ynum - 1; i++) { //y
                for (int j = 0; j < Xnum; j++) { //x
                    if (created[j][i] > 0) {
                        field[j][i] = created[j][i];
                    }
                }
            }

            // put object und check under can down
            int[][] A = Target.getZZZ();
            int[] UnderNumber = Target.getUnder();
            int dflag = 0;
            for (int i = 0; i < 4; i++) {
                field[A[i][0]][A[i][1]] = Target.getType();
                if (UnderNumber[i] > 0) {
                    if (field[A[i][0]][A[i][1] + 1] > 0) {
                        dflag = 1;
                    }
                }
            }

            rect(700,300,200,150);
            textSize(30);
            fill(40);
            text("now Score",750,450);
            text(Score,720,380);
            fill(255);
            if (dflag != 0) {
                if (waiter == 3) {
                    for (int i = 0; i < 4; i++) {
                        created[A[i][0]][A[i][1]] = Target.getType();
                    }
                    Speed = 30;
                    if (!chechUndRemove()) {
                        print("GAME OVER");
                        Speed = Integer.MAX_VALUE;
                        IncScore = 0;
                        startFlag = -1;
                    }
                    Release();
                    waiter = 0;
                    showNext();
                }
            }

            if (counter++ % Speed == 0) {
                rect(100, 100, 500, 700);
                // if ok then down
                if (dflag == 0) {
                    Target.next();
                } else {
                    waiter++;
                }

                // Show table
                for (int i = 0; i < Ynum; i++) {
                    String s = nf(i, 2);
                    print(s + ";");
                    stroke(0);
                    strokeWeight(3);
                    for (int j = 0; j < Xnum; j++) {
                        if (field[j][i] == 0) {
                            print("  ");
                        }else if(field[j][i] == 9){
                            fill(50);
                            print(field[j][i] + " ");
                            rect(j*50+100,i*50+100,50,50);
                        } else {
                            print(field[j][i] + " ");
                            setColor(field[j][i]);
                            rect(j*50+100,i*50+100,50,50);
                        }
                    }
                    println();
                }
                strokeWeight(0);
                stroke(200);
                fill(255);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("TETLIS.Main");
    }
}

