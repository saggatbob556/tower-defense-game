
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Flame extends Entity {

    GamePanel gp;
    public int bulNum;
    public String bState = "";
    private int dir;

    public Flame(GamePanel gp, int bulNum) {

        this.gp = gp;
        this.bulNum = bulNum;
        this.dir = 1;
        setDefaultValues();
        getTurret1Image();
    }

    private void setDefaultValues() {
        this.x = 0;
        this.y = -100;
    }

    public void getTurret1Image() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Flame-1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Flame-2.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Flame-3.png.png"));

        } catch (IOException e) {
        }
    }

    public void start(int startX, int startY) {
        this.x = startX;
        this.y = startY - 48;
        this.bState = "fired";
    }

    public void setState() {
        this.bState = "killed";
    }

    public void setDir(int d) {
        this.dir = d;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;
        if (dir == 1) {
            image = up1;
        } else {
            image = down1;
        }

        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }

    public void draw2(Graphics2D g2) {

        BufferedImage image;

        image = down2;

        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }
}
