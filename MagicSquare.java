import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MagicSquare implements MagicSquareInterface {
    private int[][] matrixMagicSquare;

    public MagicSquare(String filename) throws FileNotFoundException {
        matrixMagicSquare = readMatrix(filename);
    }

    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = new Scanner(file);

        int size = Integer.parseInt(scnr.nextLine());

        int[][] mat = new int[size][size];
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

    public MagicSquare(String filename, int dimension) throws IOException {
        matrixMagicSquare = new int[dimension][dimension];
        writeMatrix(matrixMagicSquare, filename);
    }

    private void writeMatrix(int[][] matrix, String filename) throws IOException {
        int size = matrixMagicSquare.length;
        matrix = new int[size][size];

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
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }

    @Override
    public boolean isMagicSquare() {
        int size = matrixMagicSquare.length;
        int target = (size * ((int) Math.pow(size, 2) + 1)) / 2;
        for (int row = 0; row < size; row++) {
            int rowSum = 0;
            for (int col = 0; col < size; col++) {
                rowSum += matrixMagicSquare[row][col];
            }
            if (rowSum != target) {
                return false;
            }
        }
        for (int row = 0; row < size; row++) {
            int colSum = 0;
            for (int col = 0; col < size; col++) {
                colSum += matrixMagicSquare[col][row];
            }
            if (colSum != target) {
                return false;
            }
        }
        int diagSum1 = 0;
        int diagSum2 = 0;
        int maxIndex = size - 1;
        for (int i = 0; i < size; i++){
            diagSum1 += matrixMagicSquare[i][i];
            diagSum2 += matrixMagicSquare[i][maxIndex - i];
        }
        if((diagSum1 != target) || (diagSum2 != target)) {
            return false;
        }
        int allNumsCheck = size * size;
        boolean[] nums = new boolean[allNumsCheck + 1];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){
                int goodNum = matrixMagicSquare[i][j];

                if(goodNum >= 1 && goodNum <= allNumsCheck) {
                    nums[goodNum] = true;
                }
            }
        }
        for(int i = 1; i < allNumsCheck; i++) {
            if(!nums[i]) {
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
        //int size = matrixMagicSquare.length;
        String str = "The matrix" + "\n";
        int[][] copyMatrix = getMatrix();

        if(isMagicSquare()) {
            for(int i = 0; i < matrixMagicSquare.length; i++) {
                for(int j = 0; j < matrixMagicSquare[i].length; j++) {
                    str += copyMatrix[i][j] + " ";
                }
                str += "\n";
            }
        str += "is a magic square.";
        return str;
        }
        else {
            for(int i = 0; i < matrixMagicSquare.length; i++) {
                for(int j = 0; j < matrixMagicSquare[i].length; j++) {
                    str += copyMatrix[i][j] + " ";
                }
                str += "\n";
            }
        str += "is not a magic square.";
        return str;
        }
    }
}
