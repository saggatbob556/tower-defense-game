
import java.awt.Color;
import java.awt.Graphics2D;

public class Cursor {

    public int curX;
    public int curY;
    public int currentTile;
    public boolean yes;
    GamePanel gp;
    KeyHandler keyh;

    public Cursor() {
        setDefaultValues();
    }

    public Cursor(GamePanel gp, KeyHandler keyh) {
        this.gp = gp;
        this.keyh = keyh;
        setDefaultValues();
    }

    private void setDefaultValues() {

        curX = 0;
        curY = 0;
        currentTile = 0;
    }

    public void update() {
        if (keyh.upArrowPressed == true) {
            if (!((curY - gp.tileSize) < 0)) {
                curY -= gp.tileSize;
                currentTile -= 16;
            }
        }
        if (keyh.downArrowPressed == true) {
            if (!((curY + gp.tileSize) > 528)) {
                curY += gp.tileSize;
                currentTile += 16;
            }
        }
        if (keyh.leftArrowPressed == true) {
            if (!((curX - gp.tileSize) < 0)) {
                curX -= gp.tileSize;
                currentTile--;
            }
        }
        if (keyh.rightArrowPressed == true) {
            if (!((curX + gp.tileSize) > 728)) {
                curX += gp.tileSize;
                currentTile++;
            }
        }
    }

    public void draw(Graphics2D g2) {
        String[] temp = gp.tileM.mapTileLoc[currentTile].split(" ");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        int type = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
        if (gp.tileM.tile[type].placeable == true) {
            yes = true;
            g2.setColor(Color.GREEN);
            g2.drawRect(curX, curY, gp.tileSize, gp.tileSize);
        } else {
            yes = false;
            g2.setColor(Color.RED);
            g2.drawRect(curX, curY, gp.tileSize, gp.tileSize);
        }

    }

    public void draw2(Graphics2D g2) {
        String[] temp = gp.tileM2.mapTileLoc[currentTile].split(" ");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        int type = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
        if (gp.tileM2.tile[type].placeable == true) {
            yes = true;
            g2.setColor(Color.GREEN);
            g2.drawRect(curX, curY, gp.tileSize, gp.tileSize);
        } else {
            yes = false;
            g2.setColor(Color.RED);
            g2.drawRect(curX, curY, gp.tileSize, gp.tileSize);
        }

    }
}
