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

//      3. feladat
        System.out.println("3. feladat:");
        bajnoksag.printForditottMeccsek();

//      4. feladat
        System.out.println("4. feladat:");
        System.out.println("kérem a csapat nevét: ");
        String csapat = scanner.nextLine();

//      5. feladat
        System.out.println("5. feladat:");
        bajnoksag.printCsapatGolok(csapat);
    }
}
