
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int totalTiles = maxScreenCol * maxScreenRow;
    public static int baseHealth = 100;
    public static int money = 100;
    public int waveNumber = 1;
    public int turretNum = 0;
    public int turretNum2 = 0;
    public int level = 1;
    String state = "none";
    int fireAt = -1;
    static int score = 0;
    static Font myFont = new Font("Courier New", Font.BOLD, 20);
    static Font myFont2 = new Font("Courier New", Font.BOLD, 75);
    static Font myFont3 = new Font("Courier New", Font.BOLD, 50);

    int FPS = 60;

    TileManager tileM = new TileManager(this, 1);
    TileManager tileM2 = new TileManager(this, 2);
    KeyHandler keyH = new KeyHandler();
    Cursor cursor = new Cursor(this, keyH);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    Flame[] flame = {
        new Flame(this, 0),
        new Flame(this, 1),
        new Flame(this, 2),
        new Flame(this, 3),
        new Flame(this, 4),
        new Flame(this, 5),
        new Flame(this, 6),
        new Flame(this, 7),
        new Flame(this, 8),
        new Flame(this, 9),
        new Flame(this, 10),
        new Flame(this, 11),
        new Flame(this, 12),
        new Flame(this, 13),
        new Flame(this, 14),
        new Flame(this, 15),
        new Flame(this, 16),
        new Flame(this, 17),
        new Flame(this, 18),
        new Flame(this, 19),
        new Flame(this, 20),
        new Flame(this, 21),
        new Flame(this, 22),
        new Flame(this, 23),
        new Flame(this, 24),
        new Flame(this, 25),
        new Flame(this, 26),
        new Flame(this, 27),
        new Flame(this, 28),
        new Flame(this, 29),
        new Flame(this, 30),
        new Flame(this, 31),
        new Flame(this, 32),
        new Flame(this, 33),
        new Flame(this, 34),
        new Flame(this, 35),
        new Flame(this, 36),
        new Flame(this, 37),
        new Flame(this, 38),};

    Turret1[] turrets = {
        new Turret1(this, cursor, 0),
        new Turret1(this, cursor, 1),
        new Turret1(this, cursor, 2),
        new Turret1(this, cursor, 3),
        new Turret1(this, cursor, 4),
        new Turret1(this, cursor, 5),
        new Turret1(this, cursor, 6),
        new Turret1(this, cursor, 7),
        new Turret1(this, cursor, 8),
        new Turret1(this, cursor, 9),
        new Turret1(this, cursor, 10),
        new Turret1(this, cursor, 11),
        new Turret1(this, cursor, 12),
        new Turret1(this, cursor, 13),
        new Turret1(this, cursor, 14),
        new Turret1(this, cursor, 15),
        new Turret1(this, cursor, 16),
        new Turret1(this, cursor, 17),
        new Turret1(this, cursor, 18),
        new Turret1(this, cursor, 19),
        new Turret1(this, cursor, 20),
        new Turret1(this, cursor, 21),
        new Turret1(this, cursor, 22),
        new Turret1(this, cursor, 23),
        new Turret1(this, cursor, 24),
        new Turret1(this, cursor, 25),
        new Turret1(this, cursor, 26),
        new Turret1(this, cursor, 27),
        new Turret1(this, cursor, 28),
        new Turret1(this, cursor, 29),
        new Turret1(this, cursor, 30),
        new Turret1(this, cursor, 31),
        new Turret1(this, cursor, 32),
        new Turret1(this, cursor, 33),
        new Turret1(this, cursor, 34),
        new Turret1(this, cursor, 35),
        new Turret1(this, cursor, 36),
        new Turret1(this, cursor, 37),
        new Turret1(this, cursor, 38)
    };

    Turret2[] turrets2 = {
        new Turret2(this, cursor, 0),
        new Turret2(this, cursor, 1),
        new Turret2(this, cursor, 2),
        new Turret2(this, cursor, 3),
        new Turret2(this, cursor, 4),
        new Turret2(this, cursor, 5),
        new Turret2(this, cursor, 6),
        new Turret2(this, cursor, 7),
        new Turret2(this, cursor, 8),
        new Turret2(this, cursor, 9),
        new Turret2(this, cursor, 10),
        new Turret2(this, cursor, 11),
        new Turret2(this, cursor, 12),
        new Turret2(this, cursor, 13),
        new Turret2(this, cursor, 14),
        new Turret2(this, cursor, 15),
        new Turret2(this, cursor, 16),
        new Turret2(this, cursor, 17),
        new Turret2(this, cursor, 18),
        new Turret2(this, cursor, 19),
        new Turret2(this, cursor, 20),
        new Turret2(this, cursor, 21),
        new Turret2(this, cursor, 22),
        new Turret2(this, cursor, 23),
        new Turret2(this, cursor, 24),
        new Turret2(this, cursor, 25),
        new Turret2(this, cursor, 26),
        new Turret2(this, cursor, 27),
        new Turret2(this, cursor, 28),
        new Turret2(this, cursor, 29),
        new Turret2(this, cursor, 30),
        new Turret2(this, cursor, 31),
        new Turret2(this, cursor, 32),
        new Turret2(this, cursor, 33),
        new Turret2(this, cursor, 34),
        new Turret2(this, cursor, 35),
        new Turret2(this, cursor, 36),
        new Turret2(this, cursor, 37),
        new Turret2(this, cursor, 38)
    };

    int wave1enemies = 10;
    int wave2enemies = 20;
    int wave3enemies = 41;
    int wave4enemies = 20;
    int wave5enemies = 60;
    int wave6enemies = 1;
    int wave7enemies = 3;
    int wave8enemies = 12;

    Player[] level1 = {
        new Player(this, 0, 0, 1, 15, 1),
        new Player(this, 1, 100, 1, 15, 1),
        new Player(this, 2, 200, 1, 15, 1),
        new Player(this, 3, 300, 1, 15, 1),
        new Player(this, 4, 400, 1, 15, 1),
        new Player(this, 5, 500, 1, 15, 1),
        new Player(this, 6, 600, 1, 15, 1),
        new Player(this, 7, 700, 1, 15, 1),
        new Player(this, 8, 800, 1, 15, 1),
        new Player(this, 9, 900, 1, 15, 1),
        new Player(this, 10, 0, 2, 15, 1),
        new Player(this, 11, 50, 2, 15, 1),
        new Player(this, 12, 100, 2, 15, 1),
        new Player(this, 13, 150, 2, 15, 1),
        new Player(this, 14, 200, 2, 15, 1),
        new Player(this, 15, 250, 2, 15, 1),
        new Player(this, 16, 300, 2, 15, 1),
        new Player(this, 17, 350, 2, 15, 1),
        new Player(this, 18, 400, 2, 15, 1),
        new Player(this, 19, 450, 2, 15, 1),
        new Player(this, 20, 500, 2, 15, 1),
        new Player(this, 21, 550, 2, 15, 1),
        new Player(this, 22, 600, 2, 15, 1),
        new Player(this, 23, 650, 2, 15, 1),
        new Player(this, 24, 700, 2, 15, 1),
        new Player(this, 25, 750, 2, 15, 1),
        new Player(this, 26, 800, 2, 15, 1),
        new Player(this, 27, 850, 2, 15, 1),
        new Player(this, 28, 900, 2, 15, 1),
        new Player(this, 29, 950, 2, 15, 1),
        new Player(this, 30, 0, 3, 15, 1),
        new Player(this, 31, 25, 3, 15, 1),
        new Player(this, 32, 50, 3, 15, 1),
        new Player(this, 33, 75, 3, 15, 1),
        new Player(this, 34, 100, 3, 15, 1),
        new Player(this, 35, 125, 3, 15, 1),
        new Player(this, 36, 150, 3, 15, 1),
        new Player(this, 37, 175, 3, 15, 1),
        new Player(this, 38, 200, 3, 15, 1),
        new Player(this, 39, 225, 3, 15, 1),
        new Player(this, 40, 250, 3, 15, 1),
        new Player(this, 41, 275, 3, 15, 1),
        new Player(this, 42, 300, 3, 15, 1),
        new Player(this, 43, 325, 3, 15, 1),
        new Player(this, 44, 350, 3, 15, 1),
        new Player(this, 45, 375, 3, 15, 1),
        new Player(this, 46, 400, 3, 15, 1),
        new Player(this, 47, 425, 3, 15, 1),
        new Player(this, 48, 450, 3, 15, 1),
        new Player(this, 49, 475, 3, 15, 1),
        new Player(this, 50, 490, 3, 15, 1),
        new Player(this, 51, 505, 3, 15, 1),
        new Player(this, 52, 520, 3, 15, 1),
        new Player(this, 53, 535, 3, 15, 1),
        new Player(this, 54, 550, 3, 15, 1),
        new Player(this, 55, 565, 3, 15, 1),
        new Player(this, 56, 580, 3, 15, 1),
        new Player(this, 57, 595, 3, 15, 1),
        new Player(this, 58, 610, 3, 15, 1),
        new Player(this, 59, 625, 3, 15, 1),
        new Player(this, 60, 640, 3, 15, 1),
        new Player(this, 61, 655, 3, 15, 1),
        new Player(this, 62, 670, 3, 15, 1),
        new Player(this, 63, 685, 3, 15, 1),
        new Player(this, 64, 700, 3, 15, 1),
        new Player(this, 65, 715, 3, 15, 1),
        new Player(this, 66, 730, 3, 15, 1),
        new Player(this, 67, 745, 3, 15, 1),
        new Player(this, 68, 760, 3, 15, 1),
        new Player(this, 69, 775, 3, 15, 1),
        new Player(this, 70, 790, 3, 15, 1),
        new Player(this, 71, 0, 4, 45, 2),
        new Player(this, 72, 50, 4, 45, 2),
        new Player(this, 73, 100, 4, 45, 2),
        new Player(this, 74, 150, 4, 45, 2),
        new Player(this, 75, 200, 4, 45, 2),
        new Player(this, 76, 250, 4, 45, 2),
        new Player(this, 77, 300, 4, 45, 2),
        new Player(this, 78, 350, 4, 45, 2),
        new Player(this, 79, 400, 4, 45, 2),
        new Player(this, 80, 450, 4, 45, 2),
        new Player(this, 81, 500, 4, 45, 2),
        new Player(this, 82, 550, 4, 45, 2),
        new Player(this, 83, 600, 4, 45, 2),
        new Player(this, 84, 650, 4, 45, 2),
        new Player(this, 85, 700, 4, 45, 2),
        new Player(this, 86, 750, 4, 45, 2),
        new Player(this, 87, 800, 4, 45, 2),
        new Player(this, 88, 850, 4, 45, 2),
        new Player(this, 89, 900, 4, 45, 2),
        new Player(this, 90, 950, 4, 45, 2),
        new Player(this, 91, 0, 5, 45, 2),
        new Player(this, 92, 25, 5, 15, 1),
        new Player(this, 93, 50, 5, 45, 2),
        new Player(this, 94, 75, 5, 15, 1),
        new Player(this, 95, 100, 5, 45, 2),
        new Player(this, 96, 125, 5, 15, 1),
        new Player(this, 97, 150, 5, 45, 2),
        new Player(this, 98, 175, 5, 15, 1),
        new Player(this, 99, 200, 5, 45, 2),
        new Player(this, 100, 225, 5, 15, 1),
        new Player(this, 101, 250, 5, 45, 2),
        new Player(this, 102, 275, 5, 15, 1),
        new Player(this, 103, 300, 5, 45, 2),
        new Player(this, 104, 325, 5, 15, 1),
        new Player(this, 105, 350, 5, 45, 2),
        new Player(this, 106, 375, 5, 15, 1),
        new Player(this, 107, 400, 5, 45, 2),
        new Player(this, 108, 425, 5, 15, 1),
        new Player(this, 109, 450, 5, 45, 2),
        new Player(this, 110, 475, 5, 15, 1),
        new Player(this, 111, 500, 5, 45, 2),
        new Player(this, 112, 525, 5, 15, 1),
        new Player(this, 113, 550, 5, 45, 2),
        new Player(this, 114, 575, 5, 15, 1),
        new Player(this, 115, 600, 5, 45, 2),
        new Player(this, 116, 625, 5, 15, 1),
        new Player(this, 117, 650, 5, 45, 2),
        new Player(this, 118, 675, 5, 15, 1),
        new Player(this, 119, 700, 5, 45, 2),
        new Player(this, 120, 725, 5, 15, 1),
        new Player(this, 121, 750, 5, 45, 2),
        new Player(this, 122, 775, 5, 15, 1),
        new Player(this, 123, 800, 5, 45, 2),
        new Player(this, 124, 825, 5, 15, 1),
        new Player(this, 125, 850, 5, 45, 2),
        new Player(this, 126, 875, 5, 15, 1),
        new Player(this, 127, 900, 5, 45, 2),
        new Player(this, 128, 925, 5, 15, 1),
        new Player(this, 129, 950, 5, 45, 2),
        new Player(this, 130, 975, 5, 15, 1),
        new Player(this, 131, 1000, 5, 100, 2),
        new Player(this, 132, 1025, 5, 100, 2),
        new Player(this, 133, 1050, 5, 100, 2),
        new Player(this, 134, 1075, 5, 100, 2),
        new Player(this, 135, 1100, 5, 100, 2),
        new Player(this, 136, 1125, 5, 100, 2),
        new Player(this, 137, 1150, 5, 100, 2),
        new Player(this, 138, 1175, 5, 100, 2),
        new Player(this, 139, 1200, 5, 100, 2),
        new Player(this, 140, 1225, 5, 100, 2),
        new Player(this, 141, 1250, 5, 100, 2),
        new Player(this, 142, 1275, 5, 100, 2),
        new Player(this, 143, 1300, 5, 100, 2),
        new Player(this, 144, 1325, 5, 100, 2),
        new Player(this, 145, 1350, 5, 100, 2),
        new Player(this, 146, 1375, 5, 100, 2),
        new Player(this, 147, 1400, 5, 100, 2),
        new Player(this, 148, 1425, 5, 100, 2),
        new Player(this, 149, 1450, 5, 100, 2),
        new Player(this, 150, 1475, 5, 100, 2),
        new Player(this, 151, 1500, 5, 100, 2),
        new Player(this, 152, 0, 6, 500, 2),
        new Player(this, 153, 0, 7, 500, 2),
        new Player(this, 154, 100, 7, 500, 2),
        new Player(this, 155, 200, 7, 500, 2),
        new Player(this, 156, 0, 8, 500, 2),
        new Player(this, 157, 100, 8, 500, 2),
        new Player(this, 158, 200, 8, 500, 2),
        new Player(this, 159, 300, 8, 500, 2),
        new Player(this, 160, 400, 8, 500, 2),
        new Player(this, 161, 600, 8, 500, 2),
        new Player(this, 162, 650, 8, 500, 2),
        new Player(this, 163, 700, 8, 500, 2),
        new Player(this, 164, 750, 8, 500, 2),
        new Player(this, 165, 800, 8, 500, 2),
        new Player(this, 166, 850, 8, 500, 2),
        new Player(this, 167, 900, 8, 500, 2)

    };

    int startNum = 168;
    Player[] level2 = {
        new Player(this, 168, 0, 1, 25, 1),
        new Player(this, 169, 100, 1, 25, 1),
        new Player(this, 170, 200, 1, 25, 1),
        new Player(this, 171, 300, 1, 25, 1),
        new Player(this, 172, 400, 1, 25, 1),
        new Player(this, 173, 500, 1, 25, 1),
        new Player(this, 174, 600, 1, 25, 1),
        new Player(this, 175, 700, 1, 25, 1),
        new Player(this, 176, 800, 1, 25, 1),
        new Player(this, 177, 900, 1, 25, 1),
        new Player(this, 178, 0, 2, 25, 1),
        new Player(this, 179, 50, 2, 25, 1),
        new Player(this, 180, 100, 2, 25, 1),
        new Player(this, 181, 150, 2, 25, 1),
        new Player(this, 182, 200, 2, 25, 1),
        new Player(this, 183, 250, 2, 25, 1),
        new Player(this, 184, 300, 2, 25, 1),
        new Player(this, 185, 350, 2, 25, 1),
        new Player(this, 186, 400, 2, 25, 1),
        new Player(this, 187, 450, 2, 25, 1),
        new Player(this, 188, 500, 2, 25, 1),
        new Player(this, 189, 550, 2, 25, 1),
        new Player(this, 190, 600, 2, 25, 1),
        new Player(this, 191, 650, 2, 25, 1),
        new Player(this, 192, 700, 2, 25, 1),
        new Player(this, 193, 750, 2, 25, 1),
        new Player(this, 194, 800, 2, 25, 1),
        new Player(this, 195, 850, 2, 25, 1),
        new Player(this, 196, 900, 2, 25, 1),
        new Player(this, 197, 950, 2, 25, 1),
        new Player(this, 198, 0, 3, 25, 1),
        new Player(this, 199, 25, 3, 25, 1),
        new Player(this, 200, 50, 3, 25, 1),
        new Player(this, 201, 75, 3, 25, 1),
        new Player(this, 202, 100, 3, 25, 1),
        new Player(this, 203, 125, 3, 25, 1),
        new Player(this, 204, 150, 3, 25, 1),
        new Player(this, 205, 175, 3, 25, 1),
        new Player(this, 206, 200, 3, 25, 1),
        new Player(this, 207, 225, 3, 25, 1),
        new Player(this, 208, 250, 3, 25, 1),
        new Player(this, 209, 275, 3, 25, 1),
        new Player(this, 210, 300, 3, 25, 1),
        new Player(this, 211, 325, 3, 25, 1),
        new Player(this, 212, 350, 3, 25, 1),
        new Player(this, 213, 375, 3, 25, 1),
        new Player(this, 214, 400, 3, 25, 1),
        new Player(this, 215, 425, 3, 25, 1),
        new Player(this, 216, 450, 3, 25, 1),
        new Player(this, 217, 475, 3, 25, 1),
        new Player(this, 218, 490, 3, 25, 1),
        new Player(this, 219, 505, 3, 25, 1),
        new Player(this, 220, 520, 3, 25, 1),
        new Player(this, 221, 535, 3, 25, 1),
        new Player(this, 222, 550, 3, 25, 1),
        new Player(this, 223, 565, 3, 25, 1),
        new Player(this, 224, 580, 3, 25, 1),
        new Player(this, 225, 595, 3, 25, 1),
        new Player(this, 226, 610, 3, 25, 1),
        new Player(this, 227, 625, 3, 25, 1),
        new Player(this, 228, 640, 3, 25, 1),
        new Player(this, 229, 655, 3, 25, 1),
        new Player(this, 230, 670, 3, 25, 1),
        new Player(this, 231, 685, 3, 25, 1),
        new Player(this, 232, 700, 3, 25, 1),
        new Player(this, 233, 715, 3, 25, 1),
        new Player(this, 234, 730, 3, 25, 1),
        new Player(this, 235, 745, 3, 25, 1),
        new Player(this, 236, 760, 3, 25, 1),
        new Player(this, 237, 775, 3, 25, 1),
        new Player(this, 238, 790, 3, 25, 1),
        new Player(this, 239, 0, 4, 64, 2),
        new Player(this, 240, 50, 4, 64, 2),
        new Player(this, 241, 100, 4, 64, 2),
        new Player(this, 242, 150, 4, 64, 2),
        new Player(this, 243, 200, 4, 64, 2),
        new Player(this, 244, 250, 4, 64, 2),
        new Player(this, 245, 300, 4, 64, 2),
        new Player(this, 246, 350, 4, 64, 2),
        new Player(this, 247, 400, 4, 64, 2),
        new Player(this, 248, 450, 4, 64, 2),
        new Player(this, 249, 500, 4, 64, 2),
        new Player(this, 250, 550, 4, 64, 2),
        new Player(this, 251, 600, 4, 64, 2),
        new Player(this, 252, 650, 4, 64, 2),
        new Player(this, 253, 700, 4, 64, 2),
        new Player(this, 254, 750, 4, 64, 2),
        new Player(this, 255, 800, 4, 64, 2),
        new Player(this, 256, 850, 4, 64, 2),
        new Player(this, 257, 900, 4, 64, 2),
        new Player(this, 258, 950, 4, 64, 2),
        new Player(this, 259, 0, 5, 64, 2),
        new Player(this, 260, 25, 5, 25, 1),
        new Player(this, 261, 50, 5, 64, 2),
        new Player(this, 262, 75, 5, 25, 1),
        new Player(this, 263, 100, 5, 64, 2),
        new Player(this, 264, 125, 5, 25, 1),
        new Player(this, 265, 150, 5, 64, 2),
        new Player(this, 266, 175, 5, 25, 1),
        new Player(this, 267, 200, 5, 64, 2),
        new Player(this, 268, 225, 5, 25, 1),
        new Player(this, 269, 250, 5, 64, 2),
        new Player(this, 270, 275, 5, 25, 1),
        new Player(this, 271, 300, 5, 64, 2),
        new Player(this, 272, 325, 5, 25, 1),
        new Player(this, 273, 350, 5, 64, 2),
        new Player(this, 274, 375, 5, 25, 1),
        new Player(this, 275, 400, 5, 64, 2),
        new Player(this, 276, 425, 5, 25, 1),
        new Player(this, 277, 450, 5, 64, 2),
        new Player(this, 278, 475, 5, 25, 1),
        new Player(this, 279, 500, 5, 64, 2),
        new Player(this, 280, 525, 5, 25, 1),
        new Player(this, 281, 550, 5, 64, 2),
        new Player(this, 282, 575, 5, 25, 1),
        new Player(this, 283, 600, 5, 64, 2),
        new Player(this, 284, 625, 5, 25, 1),
        new Player(this, 285, 650, 5, 64, 2),
        new Player(this, 286, 675, 5, 25, 1),
        new Player(this, 287, 700, 5, 64, 2),
        new Player(this, 288, 725, 5, 25, 1),
        new Player(this, 289, 750, 5, 64, 2),
        new Player(this, 290, 775, 5, 25, 1),
        new Player(this, 291, 800, 5, 64, 2),
        new Player(this, 292, 825, 5, 25, 1),
        new Player(this, 293, 850, 5, 64, 2),
        new Player(this, 294, 875, 5, 25, 1),
        new Player(this, 295, 900, 5, 64, 2),
        new Player(this, 296, 925, 5, 25, 1),
        new Player(this, 297, 950, 5, 64, 2),
        new Player(this, 298, 975, 5, 25, 1),
        new Player(this, 299, 1000, 5, 150, 2),
        new Player(this, 300, 1025, 5, 150, 2),
        new Player(this, 301, 1050, 5, 150, 2),
        new Player(this, 302, 1075, 5, 150, 2),
        new Player(this, 303, 1100, 5, 150, 2),
        new Player(this, 304, 1125, 5, 150, 2),
        new Player(this, 305, 1150, 5, 150, 2),
        new Player(this, 306, 1175, 5, 150, 2),
        new Player(this, 307, 1200, 5, 150, 2),
        new Player(this, 308, 1225, 5, 150, 2),
        new Player(this, 309, 1250, 5, 150, 2),
        new Player(this, 310, 1275, 5, 150, 2),
        new Player(this, 311, 1300, 5, 150, 2),
        new Player(this, 312, 1325, 5, 150, 2),
        new Player(this, 313, 1350, 5, 150, 2),
        new Player(this, 314, 1375, 5, 150, 2),
        new Player(this, 315, 1400, 5, 150, 2),
        new Player(this, 316, 1425, 5, 150, 2),
        new Player(this, 317, 1450, 5, 150, 2),
        new Player(this, 318, 1475, 5, 150, 2),
        new Player(this, 319, 1500, 5, 150, 2),
        new Player(this, 320, 0, 6, 600, 2),
        new Player(this, 321, 0, 7, 600, 2),
        new Player(this, 322, 100, 7, 600, 2),
        new Player(this, 323, 200, 7, 600, 2),
        new Player(this, 324, 0, 8, 600, 2),
        new Player(this, 325, 100, 8, 600, 2),
        new Player(this, 326, 200, 8, 600, 2),
        new Player(this, 327, 300, 8, 600, 2),
        new Player(this, 328, 400, 8, 600, 2),
        new Player(this, 329, 600, 8, 600, 2),
        new Player(this, 330, 650, 8, 600, 2),
        new Player(this, 331, 700, 8, 600, 2),
        new Player(this, 332, 750, 8, 600, 2),
        new Player(this, 333, 800, 8, 600, 2),
        new Player(this, 334, 850, 8, 600, 2),
        new Player(this, 335, 900, 8, 600, 2)
    };

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null && wave1enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave1enemies = update(timer, level1, wave1enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave2");
        while (gameThread != null && wave2enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave2enemies = update(timer, level1, wave2enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave3");
        while (gameThread != null && wave3enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave3enemies = update(timer, level1, wave3enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave4");
        while (gameThread != null && wave4enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave4enemies = update(timer, level1, wave4enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave5");
        while (gameThread != null && wave5enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave5enemies = update(timer, level1, wave5enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave6");
        while (gameThread != null && wave6enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave6enemies = update(timer, level1, wave6enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave7");
        while (gameThread != null && wave7enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave7enemies = update(timer, level1, wave7enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        drawInterval = 1000000000 / FPS;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        drawCount = 0;
        waveNumber++;
        money += 30;
        System.out.println("Wave8");
        while (gameThread != null && wave8enemies > 0 && baseHealth > 0) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                wave8enemies = update(timer, level1, wave8enemies, waveNumber);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        if (baseHealth > 0) {
            level++;

            baseHealth = 100;
            money = 100;
            waveNumber = 0;
            turretNum = 0;
            turretNum2 = 0;
            wave1enemies = 10;
            wave2enemies = 20;
            wave3enemies = 41;
            wave4enemies = 20;
            wave5enemies = 60;
            wave6enemies = 1;
            wave7enemies = 3;
            wave8enemies = 12;

            for (Turret1 tur : turrets) {
                tur.reset();
            }

            for (Turret2 tur : turrets2) {
                tur.reset();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            System.out.println("Wave1");

            while (gameThread != null && wave1enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave1enemies = update(timer, level2, wave1enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave2");
            while (gameThread != null && wave2enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave2enemies = update(timer, level2, wave2enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave3");
            while (gameThread != null && wave3enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave3enemies = update(timer, level2, wave3enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave4");
            while (gameThread != null && wave4enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave4enemies = update(timer, level2, wave4enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave5");
            while (gameThread != null && wave5enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave5enemies = update(timer, level2, wave5enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave6");
            while (gameThread != null && wave6enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave6enemies = update(timer, level2, wave6enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave7");
            while (gameThread != null && wave7enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave7enemies = update(timer, level2, wave7enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

            drawInterval = 1000000000 / FPS;
            delta = 0;
            lastTime = System.nanoTime();
            timer = 0;
            drawCount = 0;
            waveNumber++;
            money += 100;
            System.out.println("Wave8");
            while (gameThread != null && wave8enemies > 0 && baseHealth > 0) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    wave8enemies = update(timer, level2, wave8enemies, waveNumber);
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

        if (baseHealth > 0) {
            state = "overWin";
            for (Turret1 tur : turrets) {
                score += tur.kills;
            }
            for (Turret2 tur : turrets2) {
                score += tur.kills2;
            }
            repaint();

            System.out.println("Game Over");
        } else {
            state = "over";
            for (Turret1 tur : turrets) {
                score += tur.kills;
            }
            for (Turret2 tur : turrets2) {
                score += tur.kills2;
            }
            repaint();

            System.out.println("Game Over");
        }

    }

    public int update(long timer, Player[] enemies, int enemyNum, int wave) {
        if ((timer % 100) == 0) {
            for (Player enemy : enemies) {
                if (wave == enemy.getWave()) {
                    if (enemy.waitTime == 0) {
                        Boolean cont = true;
                        String result;
                        if (level == 1) {
                            result = cChecker.checkTile(enemy);
                        } else {
                            result = cChecker.checkTile3(enemy);
                        }
                        if (result.equals("right")) {
                            result = "down";
                            cont = false;
                        }
                        if (result.equals("left")) {
                            result = "down";
                            cont = false;
                        }
                        if (result.equals("down") && cont == true) {
                            if (level == 1) {
                                result = cChecker.checkTile2(enemy, "right");
                            } else {
                                result = cChecker.checkTile4(enemy, "right");
                            }

                            if (result.equals("right")) {
                                result = "left";
                            } else {
                                result = "right";
                            }
                        }
                        if (!result.equals("stop")) {
                            enemy.update(result);
                        } else {
                            enemy.update("shoot");
                            if (waveNumber == 6 || waveNumber == 7 || waveNumber == 8) {
                                baseHealth = 0;
                                for (Turret1 tur : turrets) {
                                    score += tur.kills;
                                }
                                switch (waveNumber) {
                                    case 6:
                                        score -= 400;
                                        break;
                                    case 7:
                                        score -= (wave7enemies - 3) * -10 + 200;
                                        break;
                                    case 8:
                                        score -= (wave8enemies - 12) * -10;
                                        break;
                                    default:
                                        score -= (8 - waveNumber) * 100;
                                        break;
                                }

                                repaint();

                                System.out.println("Game Over");
                            }
                            baseHealth--;
                            enemyNum--;
                            return enemyNum;
                        }
                    } else {
                        enemy.decreaseWait();
                    }
                }
            }
        }
        if ((timer % 150) == 0) {
            if (keyH.shiftPressed == true && (money - 30) >= 0) {
                for (Turret1 tur : turrets) {
                    if (tur.state.equals("upgrade") && cursor.curX == tur.x && cursor.curY == tur.y) {
                        tur.upgrade();
                        money -= 30;
                        break;
                    }
                }
            }
            if (keyH.spacePressed == true && cursor.yes == true && turretNum < 39 && (money - 20) >= 0 && keyH.onePressed == true) {
                for (Turret1 tur : turrets) {
                    if (tur.turNum == turretNum && tur.checkState().equals("none")) {
                        if (tur.checkPlacement()) {
                            turrets[turretNum].place(cursor);
                            turretNum++;
                            money -= 20;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (keyH.spacePressed == true && cursor.yes == true && turretNum2 < 39 && (money - 200) >= 0 && keyH.twoPressed == true) {
                for (Turret2 tur : turrets2) {
                    if (tur.turNum == turretNum2 && tur.checkState().equals("none")) {
                        if (tur.checkPlacement()) {
                            turrets2[turretNum2].place(cursor);
                            turretNum2++;
                            money -= 200;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

            cursor.update();
            if ((timer % 300) == 0) {
                for (Turret1 tur : turrets) {
                    if (turretNum == 0) {
                        turrets[0].update(cursor);
                    } else if (turretNum > 0) {
                        if (tur.turNum > (turretNum - 1)) {
                            tur.update(cursor);
                        } else {
                            fireAt = tur.checkRange(enemies);
                            int temp = fireAt;
                            if (level == 2) {
                                temp -= 168;
                            }
                            tur.bulCount();
                            if (fireAt > -1 && enemies[temp].enHealth > 0) {
                                tur.shoot(enemies, fireAt);
                                int orientation = (int) Math.toDegrees(Math.atan((enemies[temp].y + 30 - tur.y) / (enemies[temp].x + 26 - tur.x)));
                                tur.changeOrientation(orientation);
                                if (enemies[temp].enHealth < 1) {
                                    enemies[temp].update("die");
                                    tur.killCount();
                                    enemyNum--;
                                    money += 10;
                                    if (enemies[temp].maxHealth > 100) {
                                        money += 100;
                                        score += 100;
                                    } else if (enemies[temp].maxHealth > 45) {
                                        money += 25;
                                        score += 25;
                                    } else if (enemies[temp].maxHealth > 15) {
                                        money += 15;
                                        score += 15;
                                    }
                                }
                                fireAt = -1;
                            }
                        }
                    }
                }
            }
        }

        if ((timer % 600) == 0) {
            for (Turret2 tur : turrets2) {
                if (turretNum2 == 0) {
                    turrets2[0].update(cursor);
                } else if (turretNum2 > 0) {
                    if (tur.turNum > (turretNum2 - 1)) {
                        tur.update(cursor);
                    } else {
                        fireAt = tur.checkRange(enemies);
                        int temp = fireAt;
                        if (level == 2) {
                            temp -= 168;
                        }
                        if (fireAt > -1 && enemies[temp].enHealth > 0) {
                            tur.shoot(enemies, fireAt);
                            flame[tur.turNum].start(tur.x, tur.y);
                            if (enemies[temp].enHealth < 1) {
                                enemies[temp].update("die");
                                tur.killCount();
                                enemyNum--;
                                money += 10;
                                if (enemies[temp].maxHealth > 100) {
                                    money += 100;
                                    score += 100;
                                } else if (enemies[temp].maxHealth > 45) {
                                    money += 25;
                                    score += 25;
                                } else if (enemies[temp].maxHealth > 15) {
                                    money += 15;
                                    score += 15;
                                }
                            }
                            fireAt = -1;
                        }
                    }
                }
            }
        }

        if ((timer % 720) == 0) {
            for (Flame f : flame) {
                if (f.bState.equals("fired")) {
                    f.setState();
                }
            }
        }
        return enemyNum;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (level == 1) {
            tileM.draw(g2);
        } else if (level == 2) {
            tileM2.draw(g2);
        }

        if (level == 1) {
            cursor.draw(g2);
        } else if (level == 2) {
            cursor.draw2(g2);
        }

        g2.setFont(myFont);
        g2.setColor(Color.RED);
        if (level == 1) {
            g2.drawString("" + baseHealth, 728, 468);
        } else if (level == 2) {
            g2.drawString("" + baseHealth, 0, 428);
        }
        g2.drawString("Wave: " + waveNumber + "/8", 200, 20);
        g2.drawString("Money: " + money, 350, 20);

        if (level == 1) {
            for (Player enemy : level1) {
                if (enemy.waitTime == 0 && enemy.getWave() == waveNumber) {
                    enemy.draw(g2);
                }
            }
        } else if (level == 2) {
            for (Player enemy : level2) {
                if (enemy.waitTime == 0 && enemy.getWave() == waveNumber) {
                    enemy.draw(g2);
                }
            }
        }

        for (Turret1 tur : turrets) {
            if (tur.turNum < turretNum) {
                tur.draw(g2);
                if (tur.state.equals("upgrade")) {
                    tur.upgradeNeeded(g2);
                }
            }
        }

        for (Turret2 tur : turrets2) {
            if (tur.turNum < turretNum2) {
                tur.draw(g2);
            }
        }

        for (Flame f : flame) {
            if (f.bState.equals("fired")) {
                f.draw(g2);
            }
        }
        if (state.equals("over")) {
            GamePanel.gameOver(g2);
        } else if (state.equals("overWin")) {
            GamePanel.gameOver2(g2);
        }

        g2.dispose();
    }

    public static void gameOver(Graphics2D g2) {
        score -= 5000;
        score += money / 10;

        g2.setFont(myFont2);
        g2.setColor(Color.RED);
        g2.drawString("Game Over", 192, 288);
        g2.setFont(myFont3);
        g2.drawString("Score: " + score, 192, 336);
    }

    public static void gameOver2(Graphics2D g2) {
        score -= (100 - baseHealth) * 2;
        score += money / 2;

        g2.setFont(myFont2);
        g2.setColor(Color.red);
        g2.drawString("You Win!!!!", 192, 288);
        g2.setFont(myFont3);
        g2.drawString("Score: " + score, 192, 336);
    }
}
