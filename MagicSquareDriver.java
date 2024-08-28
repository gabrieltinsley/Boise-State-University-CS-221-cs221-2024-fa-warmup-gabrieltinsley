import java.io.FileNotFoundException;

public class MagicSquareDriver {
    public static void main(String[] args) {
    if(args.length != 2 && args.length !=3) {
        System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size> ");
        System.exit(-1);
    }

    String type = args[0];
    String name = args[1];

    switch(type) {
        case "-check":
            MagicSquare magicSquareCheck = null;

            try{
                magicSquareCheck = new MagicSquare(name);
            } catch (FileNotFoundException e){
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
