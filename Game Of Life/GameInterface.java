package life;

import life.evolutionAlgorithm.ConcreteGenerationAlgorithm;
import life.evolutionAlgorithm.GenerationAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameInterface extends JFrame {
    private JPanel controlPanel;
    private JPanel worldPanel;
    private JLabel generationCounter;
    private JLabel aliveCellCounter;
    private JLabel[][] cells;
    private GameWorld world;
    private int generations;
    private Thread evolutionExecutor;
    private boolean evolutionIsRunning;

    public GameInterface() {
        generationCounter = new JLabel();
        aliveCellCounter = new JLabel();
        generations = 1;
        evolutionIsRunning = false;

        initialiseGameWorld();
        initialiseGenerationCounter();
        initialiseCellCounter();
        initialiseControlPanel();
        initialiseWorldPanel();
        initialiseCellGrid();

        initialiseFrame();
    }

    private void initialiseGameWorld() {
        world = new GameWorld(100);
        world.setGameWorld();
    }

    private void initialiseGenerationCounter() {
        generationCounter.setText("Generation #1");
        generationCounter.setName("GenerationLabel");
        generationCounter.setFont(new Font(generationCounter.getName(), Font.PLAIN, 25));
    }

    private void initialiseCellCounter() {
        aliveCellCounter.setText(String.format("Alive: %d", getCurrentAliveCells()));
        aliveCellCounter.setName("AliveLabel");
        aliveCellCounter.setFont(new Font(aliveCellCounter.getName(), Font.PLAIN, 25));
    }

    private void initialiseControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 250, 545);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        addButtons();
        addInformationLabels();
    }

    private void addButtons() {
        JButton startButton = new JButton("Start");
        startButton.setSize(150, 50);
        startButton.addActionListener(event -> startButtonClicked());

        JToggleButton pauseButton = new JToggleButton("Pause");
        pauseButton.setSize(150, 50);
        pauseButton.setName("PlayToggleButton");
        pauseButton.addItemListener(this::pauseButtonClicked);

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.setSize(150, 30);
        resetButton.addActionListener(event -> resetButtonClicked());

        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(resetButton);
    }

    private void startButtonClicked() {
        if (!evolutionIsRunning) {
            evolutionIsRunning = true;
            evolutionExecutor = new Thread(this::displayEvolution);
            evolutionExecutor.start();
        }
    }

    private void displayEvolution() {
        GenerationAlgorithm algorithm = new ConcreteGenerationAlgorithm();

        try {
            while (true) {
                if (evolutionIsRunning) {
                    boolean[][] nextGeneration = algorithm.calculateNextGeneration(world.getGameWorld());
                    world.setGameWorld(nextGeneration);

                    updateCellGrid();
                    updateInformationLabels(generations, getCurrentAliveCells());
                    generations++;

                    Thread.sleep(50);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Unpausing world...");
                    }
                }
            }


        } catch (InterruptedException e) {
            System.out.println("Resetting world...");
        }


    }

    private void updateCellGrid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (world.getGameWorld()[i][j]) {
                    cells[i][j].setBackground(Color.BLACK);
                } else {
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void updateInformationLabels(int currentGeneration, int currentAliveCells) {
        generationCounter.setText(String.format("Generation #%d", currentGeneration));
        aliveCellCounter.setText(String.format("Alive: %d", currentAliveCells));
    }

    private void pauseButtonClicked(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            evolutionIsRunning = false;
        } else if (event.getStateChange() == ItemEvent.DESELECTED) {
            evolutionIsRunning = true;
        }
    }

    private void resetButtonClicked() {
        if (evolutionIsRunning) {
            evolutionExecutor.interrupt();
            generations = 1;
            initialiseGameWorld();
            initialiseGenerationCounter();
            initialiseCellCounter();
            updateCellGrid();
            evolutionIsRunning = false;
        }
    }

    private int getCurrentAliveCells() {
        int counter = 0;
        for (boolean[] row : world.getGameWorld()) {
            for (boolean isLivingCell : row) {
                if (isLivingCell) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private void addInformationLabels() {
        controlPanel.add(generationCounter);
        controlPanel.add(aliveCellCounter);
    }

    private void initialiseWorldPanel() {
        worldPanel = new JPanel();
        worldPanel.setBounds(0, 0, 550, 545);
        worldPanel.setLayout(new GridLayout(world.getGameWorld().length, world.getGameWorld().length));
    }

    private void initialiseCellGrid() {
        boolean[][] worldCells = world.getGameWorld();
        cells = new JLabel[worldCells.length][worldCells.length];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                if (worldCells[i][j]) {
                    cell.setBackground(Color.BLACK);
                }
                cell.setOpaque(true);

                cells[i][j] = cell;
                worldPanel.add(cells[i][j]);
            }
        }
    }

    private void initialiseFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setSize(800, 550);
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
    }

    private void addPanels() {
        add(controlPanel, BorderLayout.WEST);
        add(worldPanel, BorderLayout.CENTER);
    }
}