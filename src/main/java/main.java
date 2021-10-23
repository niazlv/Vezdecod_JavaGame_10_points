import lib.Window;
import lib.math.Vec2;
import lib.render.Canvas;
import lib.render.Texture;
import lib.vertex.DefaultVertexFormats;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class main extends Window {
    private final Texture background = Texture.load("background.png");

    public main() {
        super(800, 600, "Game in java (40 points)", true, "Cambria Math", 46);
    }
    //размер многоугольника(радиус от центра)
    int radius = 200;
    //толщина стенок многоугольника
    int thickness = 2;

    @Override
    protected void onKeyButton(int key, int scancode, int action, int mods) {
        super.onKeyButton(key, scancode, action, mods);
        if (action == GLFW.GLFW_RELEASE) {
            switch (key) {
                //увеличить n можно при помощи клавиш Page UP, UP, +
                case GLFW.GLFW_KEY_UP:
                case GLFW.GLFW_KEY_PAGE_UP:
                case GLFW.GLFW_KEY_EQUAL:
                case GLFW.GLFW_KEY_KP_ADD:
                    radius+=5;
                    break;
                //уменьшить n можно при помощи клавиш Page DOWN, DOWN, -
                case GLFW.GLFW_KEY_PAGE_DOWN:
                case GLFW.GLFW_KEY_KP_SUBTRACT:
                case GLFW.GLFW_KEY_DOWN:
                case GLFW.GLFW_KEY_MINUS:
                    if(radius > 0)
                        radius-=5;
                    break;

            }
        }
    }

    @Override
    protected void onScroll(double dx, double dy) {
        if(dy<0 && radius>0)
            radius += dy*5;
        else if (dy>0)
            radius += dy*5;
    }

    @Override
    protected void onFrame(double elapsed) {
        //рисуем фон
        canvas.drawTexture(background, 0, 0, width, height);


        /*
        *   Рисуем круги(все возможное, что реализовал)
        */
        //не закрашенный круг/(полигон с 360 углами)(использую самописную функцию)
        drawPolygon(0x000000,360,width/2,height/2,radius,thickness);

        //Закрашенный круг/(полигон с 360 углами)(использую самописную функцию)
        //fillPolygon(0x000000,360,width/2,height/2,radius,thickness);

        //Закрашенный круг, использую функцию библиотеки для рисования закрашенных кругов   (Recommended)
        //canvas.drawCircle(0x000000,width/2,height/2,radius,thickness);

        //закрашенный круг, использую самописную функцию закрашивания и рисования круга
        //drawCircle(width/2,height/2,radius,0x000000);

        /*
        * Далее нарисуйте вторую окружность такого же размера, как и предыдущая,
        * с центром на месте курсора,
        * ОКРАСЬТЕ её одним цветом,
        * ЕСЛИ она не пересекается с другой окружностью,
        * ИНАЧЕ — другим.
        */
        //Красим круг в крассный, если она пересекается с окружностью, иначе красим её в зеленый
        if(isCross(width/2,height/2,radius,(int)cursorX,(int)cursorY,radius))
            canvas.fillCircle(0xff0000,cursorX,cursorY,radius);
        else
            canvas.fillCircle(0x00ff00,cursorX,cursorY,radius);
    }

    private void drawCircle(int xc,int yc, int radius, int color){
        for(int y=-radius; y<=radius; y++)
            for(int x=-radius; x<=radius; x++)
                if(x*x+y*y <= radius*radius)
                    canvas.drawLine(color,xc+x, yc+y,xc+x+1, yc+y+1,1);
    }

    private void drawPolygon(int color,int n,int x1, int y1, int radius, int thickness){
        //рисуем многоугольник
        int x,y;
        int lastx, lasty;

        lastx = x1 + (int)(Math.sin(0) * radius);
        lasty = y1 + (int)(Math.cos(0) * radius);
        for(double i = 0; i < (Math.PI * 2); i+=((Math.PI * 2) / n)) {
            x = x1 + (int)(Math.sin(i) * radius);
            y = y1 + (int)(Math.cos(i) * radius);
            canvas.drawLine(color, lastx, lasty, x, y, thickness);
            lastx = x;
            lasty = y;
        }
        canvas.drawLine(color, lastx, lasty, x1 + (int)(Math.sin(0) * radius), y1 + (int)(Math.cos(0) * radius), thickness);
    }

    private void fillPolygon(int color, int n, int x1,int y1,int radius, int thickness){
        for(int i = radius; i>0;i--)
            drawPolygon(color, n, x1,y1,i,thickness);
    }

    private boolean isCross(int x1,int y1, int radius1,int x2,int y2, int radius2){
        int x = Math.abs(x1-x2);
        int y = Math.abs(y1-y2);
        int l = (int)Math.sqrt((x*x)+(y*y));
        if (l < radius1+radius2)
            return (true);
        else
            return (false);
    }
    public static void main(String[] args) {
        new main().show();
    }
}
