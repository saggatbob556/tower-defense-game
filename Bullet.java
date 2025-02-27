
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Bullet extends Entity {

    GamePanel gp;
    public int bulNum;
    public String bState = "";
    private int endX;
    private int endY;

    public Bullet(GamePanel gp, int bulNum) {

        this.gp = gp;
        this.bulNum = bulNum;
        setDefaultValues();
        getTurret1Image();
    }

    private void setDefaultValues() {
        this.x = 0;
        this.y = -100;
        this.speed = 20;
    }

    public void getTurret1Image() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1 bullet-1.png.png"));

        } catch (IOException e) {
        }
    }

    public void start(int startX, int startY, int eX, int eY) {
        this.x = startX;
        this.y = startY;
        this.endX = eX;
        this.endY = eY;
        this.bState = "fired";
    }

    public void update() {
        boolean tmp;
        if (this.x < this.endX) {
            this.x += this.speed;
            tmp = true;
        } else if (this.x > this.endX) {
            this.x -= this.speed;
            tmp = true;
        } else {
            tmp = false;
        }
        boolean tmp2;
        if (this.y < this.endY) {
            this.y += this.speed;
            tmp2 = true;
        } else if (this.y > this.endY) {
            this.y -= this.speed;
            tmp2 = true;
        } else {
            tmp2 = false;
        }

        if (tmp2 == false && tmp == false) {
            this.bState = "killed";
        }
    }

    public void setState() {
        this.bState = "killed";
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;

        image = up1;

        g2.drawImage(image, this.x, this.y, 10, 10, null);
    }
}
