
public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;

    }

    public String checkTile(Entity entity) {
        switch (entity.direction) {
            case "down":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 10 && checkx > -10 && checky < 48 && checky > -1) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }

                break;
            case "right":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
            case "left":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = entity.x - x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
        }
        return "none";
    }

    public String checkTile2(Entity entity, String direction) {
        switch (direction) {
            case "down":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 10 && checkx > 0 && checky < 48 && checky > 0) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }

                break;
            case "right":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
            case "left":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 0 && checkx > -48 && checky < 10 && checky > 0) {
                        int tileType = gp.tileM.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }
                break;
        }
        return "none";
    }

    public String checkTile3(Entity entity) {
        switch (entity.direction) {
            case "down":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 10 && checkx > -10 && checky < 48 && checky > -1) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }

                break;
            case "right":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
            case "left":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = entity.x - x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
        }
        return "none";
    }

    public String checkTile4(Entity entity, String direction) {
        switch (direction) {
            case "down":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 10 && checkx > 0 && checky < 48 && checky > 0) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }

                break;
            case "right":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 48 && checkx > 0 && checky < 10 && checky > -10) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "right";
                        } else if (tileType == 6) {
                            return "stop";
                        } else {
                            return "none";
                        }
                    }

                }
                break;
            case "left":
                for (int i = 0; i < gp.totalTiles; i++) {
                    String[] temp = gp.tileM2.mapTileLoc[i].split(" ");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    int checkx = x - entity.x;
                    int checky = y - entity.y;
                    if (checkx < 0 && checkx > -48 && checky < 10 && checky > 0) {
                        int tileType = gp.tileM2.mapTileNum[x / gp.tileSize][y / gp.tileSize];
                        if (gp.tileM2.tile[tileType].enemyWalkable == false && tileType != 6) {
                            return "down";
                        } else if (tileType == 6) {
                            return "stop";
                        }
                    }

                }
                break;
        }
        return "none";
    }

}
