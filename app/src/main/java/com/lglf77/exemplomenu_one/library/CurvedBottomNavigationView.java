package com.lglf77.exemplomenu_one.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.view.menu.MenuView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CurvedBottomNavigationView extends BottomNavigationView {

    // Declarar variável
    private Path mPath;
    private Paint mPaint;

    // O raio do botão de fab
    public final int CURVE_CICLE_RADIUS = 45;

    // As coordenadas da primeira curva
    public Point mFirstCurveStartPoint = new Point();
    public Point mFirstCurveEndPoint = new Point();
    public Point mFirstCurveControlPoint1 = new Point();
    public Point mFirstCurveControlPoint2 = new Point();

    // As coordenadas da segunda curva
    public Point mSecondCurveStartPoint = new Point();
    public Point mSecondCurveEndPoint = new  Point();
    public Point mSecondCurveControlPoint1 = new Point();
    public Point mSecondCurveControlPoint2 = new Point();

    public int mNavigationBarWidth,mNavigationBarHeight;

    public CurvedBottomNavigationView(Context context) {
        super(context);
        initView();
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.WHITE); // Cor Background fundo do menu bottom navigation
        setBackgroundColor(Color.TRANSPARENT); // Cor do sobresalto ao fundo do botão de animação, que faz contato com a cor do fragmento
    }

    // Ctrl+O

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Obtenha largura e altura da barra de navegação

        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();

        // Obtenha largura e altura da barra de navegação
        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();

        // As coordenadas (x, y) do ponto inicial antes da curva
        mFirstCurveStartPoint.set((mNavigationBarWidth / 2)
                - (CURVE_CICLE_RADIUS * 2)
                - (CURVE_CICLE_RADIUS / 3), 0);

        // as coordenadas (x, y) do ponto final após a curva (Ao iniciar animaçao sem o clique)
        // Profundidade do background da curva
        mFirstCurveEndPoint.set((mNavigationBarWidth / 2),
                CURVE_CICLE_RADIUS + (CURVE_CICLE_RADIUS / 4));

        // o mesmo para a segunda curva
        mSecondCurveStartPoint = mFirstCurveEndPoint;

        mSecondCurveEndPoint.set((mNavigationBarWidth / 2)
                + (CURVE_CICLE_RADIUS * 2) + (CURVE_CICLE_RADIUS / 3), 0);

        // As coordenadas (x, y_ do primeiro ponto de controle na curva cúbica
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + (CURVE_CICLE_RADIUS)
                        + (CURVE_CICLE_RADIUS/4),
                mFirstCurveStartPoint.y);
        // As coordenadas (x, y_ do segundo ponto de controle na curva cúbica
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (CURVE_CICLE_RADIUS * 2)
                        + CURVE_CICLE_RADIUS,
                mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x
                        + (CURVE_CICLE_RADIUS*2) - CURVE_CICLE_RADIUS,
                mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x
                        - (CURVE_CICLE_RADIUS + (CURVE_CICLE_RADIUS/4)),
                mSecondCurveEndPoint.y);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(0,0);
        mPath.lineTo(mFirstCurveStartPoint.x,mFirstCurveStartPoint.y);

        mPath.cubicTo(mFirstCurveControlPoint1.x,mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x,mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x,mFirstCurveEndPoint.y);

        mPath.cubicTo(mSecondCurveControlPoint1.x,mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x,mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x,mSecondCurveEndPoint.y);

        mPath.lineTo(mNavigationBarWidth,0);
        mPath.lineTo(mNavigationBarWidth,mNavigationBarHeight);
        mPath.lineTo(0,mNavigationBarHeight);
        mPath.close();

        canvas.drawPath(mPath, mPaint);

    }

}

