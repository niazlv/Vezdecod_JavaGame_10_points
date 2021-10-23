import lib.Window;
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
        super(800, 600, "Game in java (20 points)", true, "Cambria Math", 46);
    }
    //Количество углов в многоугольнике
    int n = 3;
    //размер многоугольника(радиус от центра)
    int radius = 200;
    //толщина стенок многоугольника
    int thickness = 3;

    @Override
    protected void onFrame(double elapsed) {
        //рисуем фон
        canvas.drawTexture(background, 0, 0, width, height);
        drawPolygon(n,radius,thickness);
    }
    private void drawPolygon(int n, int radius, int thickness){
        //рисуем многоугольник
        int x,y;
        int lastx, lasty;

        lastx = (width / 2) + (int)(Math.sin(0) * radius);
        lasty = (height / 2) + (int)(Math.cos(0) * radius);
        for(double i = 0; i <= (Math.PI * 2); i+=((Math.PI * 2) / n)) {
            x = (width / 2) + (int)(Math.sin(i) * radius);
            y = (height / 2) + (int)(Math.cos(i) * radius);
            canvas.drawLine(10, lastx, lasty, x, y, thickness);
            lastx = x;
            lasty = y;
        }
    }

    public static void main(String[] args) {
        new main().show();
    }
}
