package ca.bart.pc.minesweeper;

import android.util.Log;

/**
 * Created by PC on 2017-06-10.
 */

public class TestGrid {

    public static void print(final int[][] grid, final int width , final int height){
        for(int x =0 ; x<width ; x++){
            String printedText = "| ";
            for(int y = 0; y < height; y++){
                printedText += String.valueOf(grid[x][y]).replace("-1", "B") + " | ";
            }
            Log.e("", printedText);
        }
    }
}
