package ca.bart.pc.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ca.bart.pc.minesweeper.View.grid.Cell;

/**
 * Created by PC on 2017-06-10.
 */

public class Engine {


    TextView mineRestante;
    private static Engine instance;

    private Context context;

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];


    public boolean gridCreated;
    public static final int BOMB_NUMBER =10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    public static Engine getInstance()
    {
        if(instance == null)
        {
            instance = new Engine();

        }
        return instance;
    }

    private Engine(){}

    public void createGrid(Context context){
        this.context = context;

        //créé la grille et la garde en mémoire
        int [][] GenerateGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);
        TestGrid.print(GenerateGrid, WIDTH, HEIGHT);
        setGrid(context,GenerateGrid);
        gridCreated = true;
    }

    private void setGrid(final Context context, int[][] grid){
        for(int x = 0; x<WIDTH; x++){
            for(int y=0; y<HEIGHT; y++){
                if(MinesweeperGrid[x][y] == null){
                    MinesweeperGrid[x][y] = new Cell(context, x,y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }
    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / HEIGHT;


        return MinesweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y){
        return MinesweeperGrid[x][y];
    }

    public void click(int posX, int posY){
        if(posX >= 0 &&  posY >= 0 && posX < WIDTH && posY < HEIGHT && !getCellAt(posX,posY).isClicked()){
            getCellAt(posX, posY).setClicked();

            if(getCellAt(posX, posY).getValue() == 0)
            {
                for(int x = -1; x <=1; x++){
                    for(int y = -1 ; y<=1 ; y++){
                        if(x != y){
                            click(posX + x , posY + y);
                        }
                    }
                }
            }

            if(getCellAt(posX, posY).isBomb()){
                onGameLost();
            }
        }
        checkEnd();

    }

    private boolean checkEnd(){
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;
        for(int x = 0; x < WIDTH ; x++){
            for(int y = 0 ; y < HEIGHT ; y++){
                if(getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlagged()){
                    notRevealed--;
                }
                if(getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb()){
                    bombNotFound--;
                }
            }
        }
        if(bombNotFound ==0 && notRevealed ==0){
            Toast.makeText(context, "Game Won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag(int x ,int y){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();

        int mine = BOMB_NUMBER;
        if(isFlagged) {
            mine++;
        }
        else {
            mine--;
        }


        mineRestante.setText("Mines restante: " + mine);
    }

    private void onGameLost(){
        //handle lost game
        Toast.makeText(context, "You LOOOOSE", Toast.LENGTH_SHORT).show();

        for(int x = 0; x < WIDTH ; x++){
            for(int y = 0 ; y < HEIGHT ; y++) {
                getCellAt(x,y).setRevealed();
            }
        }
    }

    public void Reset() {
        int mine = BOMB_NUMBER;
        gridCreated = false;
        createGrid(context);
        mineRestante.setText("Mines restante: " + mine);
    }
}
