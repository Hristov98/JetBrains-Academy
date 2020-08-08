package life;

import java.util.Random;

public class GameWorld {
    private final boolean[][] gameWorld;
    private final Random random;

    public GameWorld(int size) {
        gameWorld = new boolean[size][size];
        random = new Random();
    }

    public void setGameWorld() {
        for (int i = 0; i < gameWorld.length; i++) {
            for (int j = 0; j < gameWorld[i].length; j++) {
                gameWorld[i][j] = random.nextBoolean();
            }
        }
    }

    public void setGameWorld(boolean[][] newWorld) {
        for (int i = 0; i < gameWorld.length; i++) {
            System.arraycopy(newWorld[i], 0, gameWorld[i], 0, gameWorld[i].length);
        }
    }

    public boolean[][] getGameWorld() {
        return gameWorld;
    }
}
