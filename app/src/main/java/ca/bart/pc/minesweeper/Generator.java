package ca.bart.pc.minesweeper;

import java.util.Random;



/**
 * Created by PC on 2017-06-10.
 */

public class Generator {

    public static int [][] generate ( int bombnumber, final int width, final int height)
    {
        //Random pour générer les bombes
        Random r = new Random();

        int [][] grid = new int[width][height];
        for(int x =0; x< width; x++)
        {
            grid[x] = new int [height];
        }

        while(bombnumber > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if(grid[x][y] != -1){
                grid[x][y] = -1;
                bombnumber--;
            }
        }
        grid = calculSonProchain(grid, width, height);

        return grid;
    }

    private static int [][] calculSonProchain(int [][] grid, final int width, final int height ){
        for(int x = 0; x< width; x++){
            for ( int y =0; y<height ; y++){
                grid[x][y] = getNextnumber(grid,x,y,width,height);
            }
        }
        return grid;
    }

    private static int getNextnumber(final int grid[][], final int x , final int y, final int width, final int height){
        if(grid[x][y] == -1){
            return -1;
        }
        int count = 0;

        if(isMineAt(grid,x-1,y+1,width,height)) count ++; //haut-gauche
        if(isMineAt(grid,x,y+1,width,height)) count ++; //haut
        if(isMineAt(grid,x+1 ,y+1,width,height)) count ++; //haut-droite
        if(isMineAt(grid,x-1,y,width,height)) count ++;//gauche
        if(isMineAt(grid,x+1,y,width,height)) count ++;//droite
        if(isMineAt(grid,x-1,y-1,width,height)) count ++;//bas-gauche
        if(isMineAt(grid,x,y-1,width,height)) count ++;//bas
        if(isMineAt(grid,x+1,y-1,width,height)) count ++;//bas-droit


        return count;
    }

    private static boolean isMineAt(final int [][] grid, final int x, final int y, final int width, final int height)
    {
        if(x>=0 && y>= 0 && x< width && y<height){
            if(grid[x][y] == -1){
                return true;
            }
        }
        return false;
    }

}
