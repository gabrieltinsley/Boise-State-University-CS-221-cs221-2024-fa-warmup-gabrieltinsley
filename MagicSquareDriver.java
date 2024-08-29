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

        if (args.length != 2 && args.length != 3) {
            System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size> ");
            return;
        }

        if (args[0] != "-check" || args[0] != "-create") {
            System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size> ");
            return;
        }
        
        if (Integer.parseInt(args[2]) % 2 != 1) {
            System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size> ");
            return;
        }

        String type = args[0]; // type of argument "-check" or "-create"
        String name = args[1]; // name of file the user wants to "-check" or "create"

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
                break;

        }
    }

}
