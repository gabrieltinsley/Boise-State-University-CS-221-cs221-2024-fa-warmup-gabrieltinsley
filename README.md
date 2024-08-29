****************
* warmup
* CS221
* August 28, 2024
* Gabriel Tinsley
**************** 

OVERVIEW:

 MagicSquare is a 2D array which has each integer 1 through n^2.
 Also the sum of the rows, columns and two diagnols are the same value. 
 MagicSquareDriver takes your command line arguments to read or create 
 the MagicSquare.


INCLUDED FILES:

 * MagicSquare.java - source file
 * MagicSquareDriver.java - source file
 * MagicSquareInterface.java - source file
 * MagicSquaresTest.java - source file
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the 
 driver class (and all dependencies) with the command:
 $ javac MagicSquareDriver.java

 Run the compiled class file with the command:
 $ java MagicSquareDriver -create filename number

 Where number is **odd** (1,3,5,7...)

 Console output will show if the filename is a magic square

 Check if a file is a magic square with the command:
 $ java MagicSquare -check filename

 Console output will show if the filename is a magic square


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 MagicSquare implements the MagicSquare interface, which define methods for
 determining if the 2D array is a magic square and returning a copy of the 
 2D array. MagicSquare has the ability to read and write 2D arrays in a file. 
 When reading a file, MagicSquare calls a private utility method readMatrix to 
 open the file and parse out the information into a 2D array. If the file
 cannot be opened the FileNotFoundException is thrown.

 When writing a file, MagicSquare has to pass in the dimension (odd number).
 Next, the private utility method writeMatrix takes the dimension, and creates
 a magic square with 1 through n^2 and the sum of the rows, columns, and two
 diagnols are equal. The 2D array is then written to a file with the given
 filename.

 MagicSquareDriver uses command line arguments to check or create a magic 
 square. If the user does not enter 2 to 3 arguments, the usage message
 is printed to the terminal. If the user does not put the arguments in the 
 right order a usage message is printed to the console. The "-check" argument
 uses the MagicSquare method that only takes a filename to open and read the 
 file to determine if it is a magic square. The "-create" argument uses the
 MagicSquare method that takes a filename and a dimension to write the file.

 MagicSquaresTest is a console app to test my MagicSquare class. All the
 constructors and methods are tested with a clear pass or fail printed to
 the console. All the tests appear to have passed with my MagicSquare class.


TESTING:

 MagicSquaresTest was written before my MagicSquare class. This tester helped
 as I worked to pass all the tests with my MagicSquare class.

 MagicSquaresTest includes:
 - Validating encapsulation
 - 1 through n^2 is in the magic square
 - The diagnols, columns and rows are equal
 - The String output is correct

 I made sure my program can handle bad input by making three different usage
 statements for if the user does not type in 2 to 3 arguements, if "-check" or 
 "-create" is not the second argument, and if an even number is typed for creating
 a magic square. There are no remaining bugs that I am aware of.


DISCUSSION:
 
 One major problem I had was in my private utility method writeMatrix. When I
 would use my "-create" argument in MagicSquareDriver class I would get the file
 I created and there would be the correct numbers inside but on my terminal I
 kept getting:

 $ java MagicSquareDriver -create file.txt 3
 The matrix
 0 0 0 
 0 0 0
 0 0 0
 is not a magic square

 After a couple of hours looking at my code I found my problem was that my 
 instance variable was not equal to the matrix in the writeMatrix. Once I 
 set my instance variable equal to the matrix I passed all the tests and 
 my "-create" began to work aswell.
 I did not have to research anything besides looking through the slides on 
 encapsulation and attending TA sessions in the Kount Learning Center. What
 finally clicked for me was how to use command line arguments to print the
 usage message.