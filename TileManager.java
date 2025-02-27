
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public final class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public String mapTileLoc[];

    public TileManager(GamePanel gp, int lev) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        if (lev == 1) {
            loadMap("/maps/map01.txt");
        } else if (lev == 2) {
            loadMap("/maps/map02.txt");
        }

    }

    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Grass Tile-1.png.png"));
            tile[0].placeable = true;
            tile[0].enemyWalkable = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Wall Tile-1.png.png"));
            tile[1].placeable = false;
            tile[1].enemyWalkable = false;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Water Tile-1.png.png"));
            tile[2].placeable = false;
            tile[2].enemyWalkable = false;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Ground Tile-1.png.png"));
            tile[3].placeable = false;
            tile[3].enemyWalkable = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Tree-1.png.png"));
            tile[4].placeable = false;
            tile[4].enemyWalkable = false;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Road Tile-1.png.png"));
            tile[5].placeable = false;
            tile[5].enemyWalkable = false;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/sprites/pkg/Base Tile-1.png.png"));
            tile[6].placeable = false;
            tile[6].enemyWalkable = false;

        } catch (IOException e) {
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                int col = 0;
                int row = 0;
                int x = 0;
                int y = 0;
                int tileNumber = 0;
                mapTileLoc = new String[gp.totalTiles];

                while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                    String line = br.readLine();

                    while (col < gp.maxScreenCol) {

                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        int xpos = x;
                        int ypos = y;
                        mapTileLoc[tileNumber] = xpos + " " + ypos;
                        tileNumber++;
                        col++;
                        x += gp.tileSize;
                    }
                    if (col == gp.maxScreenCol) {

                        col = 0;
                        x = 0;
                        row++;
                        y += gp.tileSize;
                    }

                }
            }

        } catch (IOException | NumberFormatException e) {
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {

                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}
