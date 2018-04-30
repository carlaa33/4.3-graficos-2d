package mx.edu.ittepic.graficos2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int[] numeros={1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();
    private int radio=0;
    int sX,sY,mX,mY,hX,hY;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PapelView papel = new PapelView(this);

        setContentView(papel);
    }


    private class PapelView extends View {




        public PapelView(Context context) {
            super(context);
            sX=sY=mX=mY=hX=hY=0;
            final int[] s = {5};
            final int[] m={12};
            final int[] h={1};
            timer=new CountDownTimer(61000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    double angle = Math.PI / 30 * (s[0] - 16);
                    sX = (int) (1080 / 2 + Math.cos(angle) * radio - rect.width() / 2);
                    sY = (int) (1080 / 2 + Math.sin(angle) * radio - rect.width() / 2);
                    double angle2 = Math.PI / 30 * (m[0] - 16);
                    mX = (int) (1080 / 2 + Math.cos(angle2) * radio - rect.width() / 2);
                    mY = (int) (1080 / 2 + Math.sin(angle2) * radio - rect.width() / 2);
                    double angle3 = Math.PI / 6 * (h[0] - 3);
                    hX = (int) (1080 / 2 + Math.cos(angle3) * radio - rect.width() / 2);
                    hY = (int) (1080 / 2 + Math.sin(angle3) * radio - rect.width() / 2);
                    s[0]=s[0]+1;
                    invalidate();

                }

                @Override
                public void onFinish() {
                    //Se ejecuta al finalizar el conteo
                    invalidate();
                    s[0]= 0;
                    m[0]=m[0]+1;
                    if(m[0]%60==0){
                        h[0]=h[0]+1;
                    }
                    timer.start();

                }
            };
            timer.start();
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Objeto Paint
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            // Pintar el canvas
            canvas.drawPaint(paint);

            // Obtener dimensiones
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setAntiAlias(true);

            // Texto
            canvas.drawText("ancho" + ancho + "altura" + alto, 30, 30, paint);

            // Lineas
            paint.setColor(Color.WHITE);
            canvas.drawLine(0, 40, ancho, 40, paint);
            canvas.drawLine(20, 0, 20, alto, paint);

            paint.setTextSize(50);

            int min = Math.min(alto,ancho);
            radio=(min/2)-100;

            for (int n : numeros) {
                String tmp = String.valueOf(n);
                paint.getTextBounds(tmp, 0, tmp.length(), rect);
                double angle = Math.PI / 6 * (n - 3);

                int x = (int) (ancho / 2 + Math.cos(angle) * radio - rect.width() / 2);
                int y = (int) (ancho / 2 + Math.sin(angle) * radio - rect.width() / 2);

                canvas.drawText(tmp, x, y, paint);
            }
            //Segundos
            canvas.drawLine(ancho/2,ancho/2,sX,sY,paint);
            //Minutos
            canvas.drawLine(ancho/2,ancho/2,mX,mY,paint);
            //Horas
            canvas.drawLine(ancho/2,ancho/2,hX,hY,paint);
        }


    }

}



