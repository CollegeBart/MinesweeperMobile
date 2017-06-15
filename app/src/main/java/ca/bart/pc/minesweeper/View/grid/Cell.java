package ca.bart.pc.minesweeper.View.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import ca.bart.pc.minesweeper.Engine;
import ca.bart.pc.minesweeper.R;

/**
 * Created by PC on 2017-06-11.
 */

public class Cell extends CellDeBase implements View.OnClickListener, View.OnLongClickListener {

    public Cell(Context context, int x, int y){
        super(context);

        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawButton(canvas);
        if(isFlagged()){
            drawFlag(canvas);
        }else if ( isRevealed() && isBomb() && !isClicked()){
            drawBomb(canvas);
        }else{
            if(isClicked()){
                if(getValue() == -1 ) {
                    drawBombExploded(canvas);
                }else{
                    drawNumber(canvas);
                }
            }else {
                drawButtonPressed(canvas);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Engine.getInstance().click(getXPos(), getYPos());
    }

    @Override
    public boolean onLongClick(View v) {
        Engine.getInstance().flag(getXPos(), getYPos());
        return true;
    }

    private void drawFlag(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonflag);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);
    }


    private void drawButtonPressed(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.buttonvide);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);
    }
    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.button);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);

    }

    private void drawBombExploded(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonbomb);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);

    }
    private void drawBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonbomb);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);

    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;

        switch(getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.button);
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button1);

            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button2);

            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button3);

            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button4);

            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button5);

            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button6);

            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button7);

            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button8);

        }
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);
    }


}

