package foci;

import java.nio.file.Path;
import java.util.Scanner;

public class FociMain {

    public static void main(String[] args) {
        Bajnoksag bajnoksag = new Bajnoksag();
        Path path = Path.of("src", "main", "resources", "meccs.txt");
        bajnoksag.loadFromFile(path);

//      2. feladat
        System.out.println("2. feladat:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kérem a forduló számát: ");
        String fordulo = scanner.nextLine();
        bajnoksag.printFordulo(fordulo);
        System.out.println();

//    3. feladat
        System.out.println("3. feladat:");
        bajnoksag.printForditottMeccsek();
    }
}
