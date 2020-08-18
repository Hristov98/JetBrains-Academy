package matrixTransposition;

import matrixTransposition.transposers.*;

public class TransposerFactory {
    public TransposerMethod getTransposer(int userChoice) {
        switch (userChoice) {
            case 1: {
                return new TransposerMainDiagonal();
            }
            case 2: {
                return new TransposerSideDiagonal();
            }
            case 3: {
                return new TransposerVerticalLine();
            }
            case 4: {
                return new TransposerHorizontalLine();
            }
            default: {
                return null;
            }
        }
    }
}
