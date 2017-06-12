package ca.bart.pc.minesweeper;

import android.content.Context;
import android.view.View;

import ca.bart.pc.minesweeper.View.grid.Cell;

/**
 * Created by PC on 2017-06-10.
 */

public class Engine {

    private static Engine instance;

    private Context context;

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

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
    }

    private void setGrid(final Context context, int[][] grid){
        for(int x = 0; x<WIDTH; x++){
            for(int y=0; y<HEIGHT; y++){
                if(MinesweeperGrid[x][y] == null){
                    MinesweeperGrid[x][y] = new Cell(context, y*HEIGHT+x);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }
    public View getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / HEIGHT;


        return MinesweeperGrid[x][y];
    }
}
