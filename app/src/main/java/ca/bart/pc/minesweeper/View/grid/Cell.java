package ca.bart.pc.minesweeper.View.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.bart.pc.minesweeper.Engine;
import ca.bart.pc.minesweeper.R;

/**
 * Created by PC on 2017-06-11.
 */

public class Cell extends CellDeBase implements View.OnClickListener, View.OnLongClickListener {

Button restartButton = (Button)findViewById(R.id.new_game_button);
boolean isPressed = false;

    public Cell(Context context, int x, int y){
        super(context);

        setPosition(x,y);
        TextView matext = (TextView)findViewById(R.id.minesText);


        setOnClickListener(this);
        setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Engine.getInstance().click(getXPos(), getYPos());

        restartButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Engine.getInstance().Reset();
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        Engine.getInstance().flag(getXPos(), getYPos());

        return true;
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
                drawButton(canvas);
            }
        }
    }

    private void drawFlag(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonflag);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);
    }



    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.button);
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);

    }

    private void drawBombExploded(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonerror);
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
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.buttonvide);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button8);
                break;
        }
        drawable.setBounds(0,0,getWidth(), getHeight());
        drawable.draw(canvas);
    }


}

