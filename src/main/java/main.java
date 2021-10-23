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
        super(800, 600, "Game in java (10 points)", true, "Cambria Math", 46);
    }
    @Override
    protected void onFrame(double elapsed) {

        canvas.drawTexture(background, 0, 0, width, height);
    }

    public static void main(String[] args) {
        new main().show();
    }
}
