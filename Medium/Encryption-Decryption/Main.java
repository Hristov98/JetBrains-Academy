package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static AlgorithmExecutor executor;
    private static String command;
    private static int key;
    private static String message;
    private static String result;
    private static String outputFile;

    public static void main(String[] args) {
        setDefaultValues();
        readCommandLineArguments(args);
        executeAlgorithm();
        outputResult(result);
    }

    private static void setDefaultValues() {
        executor = new AlgorithmExecutor();
        executor.setAlgorithm(new ShiftingAlgorithm());
        command = "enc";
        key = 0;
        message = "";
        outputFile = "";
    }

    private static void readCommandLineArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                command = args[i + 1];
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            }
            if ("-in".equals(args[i])) {
                File file = new File(args[i + 1]);
                try {
                    Scanner input = new Scanner(file);
                    while (input.hasNext()) {
                        message += input.nextLine();
                    }

                    input.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if ("-data".equals(args[i])) {
                message = args[i + 1];
            }
            if ("-out".equals(args[i])) {
                outputFile = args[i + 1];
            }
            if ("-alg".equals(args[i])) {
                if ("unicode".equals(args[i + 1])) {
                    executor.setAlgorithm(new UnicodeAlgorithm());
                }
            }
        }
    }

    private static void executeAlgorithm() {
        result = executor.executeAlgorithm(command, message, key);
    }

    private static void outputResult(String result) {
        if ("".equals(outputFile)) {
            System.out.println(result);
        } else {
            try {
                FileWriter writer = new FileWriter(outputFile);

                writer.write(result);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
