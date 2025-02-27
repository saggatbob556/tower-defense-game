
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Turret1 extends Entity {

    public String state;
    private final int[][] placements = new int[17][13];
    int upgradeLevel = 1;
    public int kills;
    int bulCount;
    int orient;
    GamePanel gp;

    public Turret1(GamePanel gp, Cursor c, int turNum) {

        this.gp = gp;
        this.turNum = turNum;
        this.orient = 0;
        setDefaultValues(c);
        getTurret1Image();
    }

    private void setDefaultValues(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
        this.direction = "up";
        this.state = "none";
        this.kills = 0;
    }

    public void getTurret1Image() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-2-1.png-1.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-5.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-2-1.png-2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-3.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-2-1.png-4.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-7.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Turret1-2-1.png-3.png.png"));

        } catch (IOException e) {
        }
    }

    public void update(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;

        if (this.upgradeLevel == 1) {
            switch (this.direction) {
                case "down":
                    image = down1;
                    break;
                case "up":
                    image = up1;
                    break;
                case "right":
                    image = right1;
                    break;
                case "left":
                    image = left1;
                    break;
                default:
                    image = up1;
                    break;
            }
        } else {
            switch (this.direction) {
                case "down":
                    image = down2;
                    break;
                case "up":
                    image = up2;
                    break;
                case "right":
                    image = right2;
                    break;
                case "left":
                    image = left2;
                    break;
                default:
                    image = up2;
                    break;
            }
        }

        // Rotation information
        double rotationRequired = Math.toRadians(this.orient);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }

    public void place(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
        placements[this.x / gp.tileSize][this.y / gp.tileSize] = 1;
    }

    public int checkRange(Player[] enemies) {
        for (Player enemy : enemies) {
            if ((enemy.x - this.x) < 100 && enemy.y < this.y && (this.y - enemy.y) < 100 && (enemy.x - this.x) > -50) {
                this.direction = "up";
                return enemy.spriteNum;
            } else if ((enemy.x - this.x) < 100 && enemy.y > this.y && (enemy.y - this.y) < 100 && (enemy.x - this.x) > -50) {
                this.direction = "down";
                return enemy.spriteNum;
            } else if ((this.y - enemy.y) < 50 && (this.y - enemy.y) > 0 && (enemy.x - this.x) > -50) {
                this.direction = "right";
                return enemy.spriteNum;
            } else if ((this.y - enemy.y) < 50 && (this.y - enemy.y) > 0 && (enemy.x - this.x) < 50) {
                this.direction = "left";
                return enemy.spriteNum;
            }

        }
        return -1;
    }

    public void shoot(Player[] enemies, int fireTo) {
        for (Player enemy : enemies) {
            if (enemy.spriteNum == fireTo && this.upgradeLevel == 1) {
                enemy.enHealth -= 0.2;
                break;
            } else if (enemy.spriteNum == fireTo && this.upgradeLevel == 2) {
                enemy.enHealth -= 0.5;
                break;
            }
        }
    }

    public String checkState() {
        return this.state;
    }

    public boolean checkPlacement() {
        return true;
    }

    public void killCount() {
        this.kills++;
        if (this.bulCount > 100 && upgradeLevel < 2) {
            this.state = "upgrade";
        }
    }

    public void upgradeNeeded(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.drawRect(this.x, this.y, gp.tileSize, gp.tileSize);
    }

    public void upgrade() {
        this.state = "none";
        this.upgradeLevel = 2;
        System.out.println("Upgraded");
    }

    public void bulCount() {
        this.bulCount++;
    }

    public void reset() {
        this.state = "none";
        this.kills = 0;
        this.x = 0;
        this.y = -100;
        this.bulCount = 0;
        this.upgradeLevel = 1;
    }

    public void changeOrientation(int d) {
        //this.orient = d;
    }

}
