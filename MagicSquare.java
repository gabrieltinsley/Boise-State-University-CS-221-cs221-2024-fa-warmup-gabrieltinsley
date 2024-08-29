import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The MagicSquare class implements the MagicSquareInterface. MagicSquare allows
 * users to create a matrix with the dimensions the user wants and all the rows,
 * columns, and diagnols are equal when added.
 * 
 * @author Gabriel Tinsley
 * @version 1.0 CS221 Fall 2024
 * 
 */
public class MagicSquare implements MagicSquareInterface {

    /**
     * Private instance variable that allows the user to create the matrix
     */
    private int[][] matrixMagicSquare;

    /**
     * Allows the user to open and read a file. If file cannot be opened
     * FileNotFoundException is thrown.
     * 
     * @param filename the String name of the file that read and parses
     *                 information
     * @throws FileNotFoundException
     */
    public MagicSquare(String filename) throws FileNotFoundException {
        matrixMagicSquare = readMatrix(filename);
    }

    /**
     * Allows user to create a filename and write the dimension
     * of the magic square
     * 
     * @param filename  the String name of the file that user creates
     *                  in terminal
     * @param dimension the int size of the magic square that user
     *                  creates in terminal
     * @throws IOException
     */
    public MagicSquare(String filename, int dimension) throws IOException {
        matrixMagicSquare = new int[dimension][dimension];
        writeMatrix(matrixMagicSquare, filename);
    }

    /**
     * Private utility method that takes the filename entered by user and parses
     * information from the file. If there is no file the FileNotFoundException
     * is thrown.
     * 
     * @param filename the String name of the file the user checks in terminal
     * @return matrix output
     * @throws FileNotFoundException
     */
    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = new Scanner(file);

        int size = Integer.parseInt(scnr.nextLine());

        int[][] mat = new int[size][size]; // mat is the matrix being read from a file
        while (scnr.hasNextInt()) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    mat[i][j] = scnr.nextInt();
                }
            }
        }
        scnr.close();
        return mat;
    }

    /**
     * Private utility method that takes the size/dimension of the magic square
     * and creates a magic square from whatever size (odd number) the user enters
     * in the terminal. An IOException is thrown if the user does not enter
     * parameters correctly.
     * 
     * @param matrix   the 2D array with the specific size the user wants
     * @param filename the String name of the file the user creates in the terminal.
     * @throws IOException
     */
    private void writeMatrix(int[][] matrix, String filename) throws IOException {
        int size = matrixMagicSquare.length;
        matrix = new int[size][size];
        matrix = matrixMagicSquare; // makes instance variable equal to matrix

        int row = size - 1;
        int col = size / 2;

        int oldRow;
        int oldCol;

        for (int i = 1; i <= size * size; i++) {
            matrix[row][col] = i;
            oldRow = row;
            oldCol = col;
            row++;
            col++;
            if (row == size) {
                row = 0;
            }
            if (col == size) {
                col = 0;
            }
            if (matrix[row][col] != 0) {
                row = oldRow;
                col = oldCol;
                row--;
            }
        }

        File file = new File(filename);
        PrintWriter out = new PrintWriter(new FileWriter(file));
        out.println(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                out.print(matrix[i][j] + "  ");
            }
            out.println();
        }
        out.close();

    }

    @Override
    public boolean isMagicSquare() {
        int size = matrixMagicSquare.length;
        int target = (size * ((int) Math.pow(size, 2) + 1)) / 2; // target is the sum of all rows, columns and diagnols

        // checks row to see if the sum of row is equal to target, if not equal then return false
        for (int row = 0; row < size; row++) {
            int rowSum = 0;
            for (int col = 0; col < size; col++) {
                rowSum += matrixMagicSquare[row][col];
            }

            if (rowSum != target) {
                return false;
            }
        }

        // checks column to see if the sum of column is equal to target, if not equal then return false
        for (int row = 0; row < size; row++) {
            int colSum = 0;
            for (int col = 0; col < size; col++) {
                colSum += matrixMagicSquare[col][row];
            }

            if (colSum != target) {
                return false;
            }
        }

        // checks the two possible diagnols to see if the sum is equal to target, if not equal then return false
        int diagSum1 = 0; // Diagnol that goes left to right
        int diagSum2 = 0; // Diagnol that goes right to left
        int maxIndex = size - 1; // Gets to end of top row to do right to left diagnol

        for (int i = 0; i < size; i++) {
            diagSum1 += matrixMagicSquare[i][i];
            diagSum2 += matrixMagicSquare[i][maxIndex - i];
        }

        if ((diagSum1 != target) || (diagSum2 != target)) {
            return false;
        }

        // checks all numbers 1 through n^2 are in the magic square, if not then return false
        int numSquared = size * size; // numSquared is equal to n^2
        boolean[] checkNums = new boolean[numSquared + 1]; // checkNums array of boolean that can fit n^2 + 1

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int goodNum = matrixMagicSquare[i][j]; // goodNum checks each int in the matrix

                // goodNum has to be greater than or equal to 1 and less than or equal to n^2, if so return true
                if (goodNum >= 1 && goodNum <= numSquared) {
                    checkNums[goodNum] = true;
                }
            }
        }

        // checks if any number in 1 to n^2 is the same, if there is then return false
        for (int i = 1; i < numSquared; i++) {
            if (!checkNums[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int[][] getMatrix() {
        int size = matrixMagicSquare.length;
        int[][] copy = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = matrixMagicSquare[i][j];
            }
        }

        return copy;
    }

    @Override
    public String toString() {
        String str = "The matrix" + "\n";
        int[][] copyMatrix = getMatrix();

        if (isMagicSquare()) {
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                    str += copyMatrix[i][j] + " ";
                }
                str += "\n";
            }
            str += "is a magic square.";
            return str;

        } else {
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                    str += copyMatrix[i][j] + " ";
                }
                str += "\n";
            }
            str += "is not a magic square.";
            return str;
        }
    }
}
