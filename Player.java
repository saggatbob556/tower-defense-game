
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Player extends Entity {

    GamePanel gp;
    double enHealth = 15;
    int maxHealth;
    int entype;

    public Player(GamePanel gp, int enNum, int wait, int waveNum, int health, int type) {

        this.gp = gp;
        this.entype = type;

        setDefaultValues(enNum, wait, waveNum, health);
        getPlayerImage();
    }

    private void setDefaultValues(int enNum, int wait, int waveNum, int h) {
        this.x = 95;
        this.y = 1;
        this.speed = 2;
        this.direction = "down";
        this.spriteNum = enNum;
        this.waitTime = wait;
        this.waveNumb = waveNum;
        this.enHealth = h;
        this.maxHealth = h;
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/TabkEnemy-3.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Thick Tank-1.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/TabkEnemy-7.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Thick Tank-2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/TabkEnemy-5.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Thick Tank-4.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/TabkEnemy-1.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Thick Tank-3.png.png"));

        } catch (IOException e) {
        }
    }

    public void update(String dot) {
        String tmp = this.direction;
        this.direction = dot;
        switch (this.direction) {
            case "down":
                this.y += speed;
                break;
            case "up":
                this.y -= speed;
                break;
            case "right":
                this.x += speed;
                break;
            case "left":
                this.x -= speed;
                break;
            case "stop":
                this.direction = tmp;
                break;
            case "die":
                this.x = 0;
                this.y = -100;
            case "none":
                this.direction = tmp;
                switch (this.direction) {
                    case "down":
                        this.y += speed;
                        break;
                    case "up":
                        this.y -= speed;
                        break;
                    case "right":
                        this.x += speed;
                        break;
                    case "left":
                        this.x -= speed;
                        break;
                }

        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (this.entype == 1) {
            switch (this.direction) {
                case "up":
                    image = up1;
                    break;
                case "down":
                    image = down1;
                    break;
                case "left":
                    image = left1;
                    break;
                case "right":
                    image = right1;
                    break;
            }
        } else {
            switch (this.direction) {
                case "up":
                    image = up2;
                    break;
                case "down":
                    image = down2;
                    break;
                case "left":
                    image = left2;
                    break;
                case "right":
                    image = right2;
                    break;
            }
        }
        if (this.enHealth > (this.maxHealth / 2)) {
            g2.setColor(Color.GREEN);
            g2.fillRect(this.x + 12, this.y + 2, 16, 5);
        } else if (this.enHealth < (this.maxHealth / 2) && this.enHealth > (this.maxHealth / 3)) {
            g2.setColor(Color.YELLOW);
            g2.fillRect(this.x + 12, this.y + 2, 16, 5);
        } else {
            g2.setColor(Color.RED);
            g2.fillRect(this.x + 12, this.y + 2, 16, 5);
        }
        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }

    public String getDirection() {
        return this.direction;
    }

    public void decreaseWait() {
        this.waitTime -= 1;
    }

    public int getWave() {
        return this.waveNumb;
    }
}
