
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Turret2 extends Entity {

    public String state2;
    private final int[][] placements = new int[17][13];
    public int kills2;
    int bulCount2;
    GamePanel gp;

    public Turret2(GamePanel gp, Cursor c, int turNum) {

        this.gp = gp;
        this.turNum = turNum;
        setDefaultValues(c);
        getTurret1Image();
    }

    private void setDefaultValues(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
        this.direction = "up";
        this.state2 = "none";
        this.kills2 = 0;
    }

    public void getTurret1Image() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Flame Thrower-1.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Flame Thrower-2.png.png"));

        } catch (IOException e) {
        }
    }

    public void update(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;

        switch (this.direction) {
            case "down":
                image = down1;
                break;
            case "up":
                image = up1;
                break;
            default:
                image = right1;
                break;
        }

        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }

    public void place(Cursor c) {
        this.x = c.curX;
        this.y = c.curY;
        placements[this.x / gp.tileSize][this.y / gp.tileSize] = 1;
    }

    public int checkRange(Player[] enemies) {
        for (Player enemy : enemies) {
            if ((enemy.x - this.x) < 10 && enemy.y < this.y && (this.y - enemy.y) < 100 && (enemy.x - this.x) > -20) {
                this.direction = "up";
                return enemy.spriteNum;
            }

        }
        return -1;
    }

    public void shoot(Player[] enemies, int fireTo) {
        for (Player enemy : enemies) {
            if (enemy.spriteNum == fireTo) {
                enemy.enHealth -= 15;
                break;
            }
        }
    }

    public String checkState() {
        return this.state2;
    }

    public boolean checkPlacement() {
        return true;
    }

    public void killCount() {
        this.kills2++;
        if (this.kills2 > 15) {
            this.state2 = "upgrade";
        }
    }

    public void reset() {
        this.state2 = "none";
        this.kills2 = 0;
        this.x = 0;
        this.y = -100;
        this.bulCount2 = 0;
    }
}
