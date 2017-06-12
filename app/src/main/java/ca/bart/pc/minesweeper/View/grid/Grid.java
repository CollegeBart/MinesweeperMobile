package ca.bart.pc.minesweeper.View.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import ca.bart.pc.minesweeper.Engine;

/**
 * Created by PC on 2017-06-11.
 */

public class Grid extends GridView {

    public Grid(Context context , AttributeSet attrs){
        super(context, attrs);


        Engine.getInstance().createGrid(context);

        setNumColumns(Engine.WIDTH);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Engine.getInstance().WIDTH * Engine.getInstance().HEIGHT ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return Engine.getInstance().getCellAt(position);
        }
    }
}
