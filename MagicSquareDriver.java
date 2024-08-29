import java.io.FileNotFoundException;

/**
 * MagicSquareDriver takes the users command line arguments in the terminal. The
 * user can choose to check or create a magic square.
 * 
 * @author Gabriel Tinsley
 * @version 1.0 CS221 Fall 2024
 */
public class MagicSquareDriver {
    public static void main(String[] args) {

        // print usage message when not the correct number of arguments
        if(args.length != 2 && args.length != 3) {
            System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size (has to be odd)> ");
            System.exit(0);
        }

        String type = args[0]; // type of argument "-check" or "-create"
        String name = args[1]; // name of file the user wants to "-check" or "create"

        // switch statement that changes with the type of argument
        switch (type) {
            case "-check":
                MagicSquare magicSquareCheck = null;

                try {
                    magicSquareCheck = new MagicSquare(name);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println(magicSquareCheck);
                break;

            case "-create":
                int magicSquareSize = Integer.parseInt(args[2]);
                MagicSquare magicSquareCreate = null;

                try {
                    magicSquareCreate = new MagicSquare(name, magicSquareSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(magicSquareCreate);
                break;

            default:
                // prints usage message when user is incorrect
                System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size (has to be odd)> ");
                break;

        }
    }

}
