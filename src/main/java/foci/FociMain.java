package foci;

import java.nio.file.Path;
import java.util.Scanner;

public class FociMain {

    public static void main(String[] args) {
//      1.  Olvassa be a meccs.txt állományban talált adatokat, s annak felhasználásával oldja meg a következő feladatokat! Ha az állományt nem tudja beolvasni, az első 10 mérkőzés adatait jegyezze be a programba és dolgozzon azzal!
        Bajnoksag bajnoksag = new Bajnoksag();
        Path path = Path.of("src", "main", "resources", "meccs.txt");
        bajnoksag.loadFromFile(path);

//      2. Kérje be a felhasználótól egy forduló számát, majd írja a képernyőre a bekért forduló mérkőzéseinek adatait a következő formában: Edes-Savanyu: 2-0 (1-0)! Soronként egy mérkőzést tüntessen fel! A különböző sorokban a csapatnevek ugyanazon a pozíción kezdődjenek! Például: Edes-Savanyu: 2-0 (1-0) Ijedtek-Hevesek: 0-2 (0-2) ...
        System.out.println("2. feladat:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kérem a forduló számát: ");
        int fordulo = scanner.nextInt();
        scanner.nextLine();
        bajnoksag.printFordulo(fordulo);


//      3. Határozza meg, hogy a bajnokság során mely csapatoknak sikerült megfordítaniuk az állást a második félidőben! Ez azt jelenti, hogy a csapat az első félidőben vesztésre állt ugyan, de sikerült a mérkőzést megnyernie. A képernyőn soronként tüntesse fel a forduló sorszámát és a győztes csapat nevét!
        System.out.println("\n3. feladat:");
        bajnoksag.printForditottMeccsek();


//      4. Kérje be a felhasználótól egy csapat nevét, és tárolja el! A következő két feladat megoldásához ezt a csapatnevet használja! Ha nem tudta beolvasni, használja a Lelkesek csapatnevet!
        System.out.println("\n4. feladat:");
        System.out.println("Kérem a csapat nevét: ");
        String csapat = scanner.nextLine();


//      5. Határozza meg, majd írja ki, hogy az adott csapat összesen hány gólt lőtt és hány gólt kapott! Például: lőtt: 23 kapott: 12
        System.out.println("\n5. feladat:");
        bajnoksag.printCsapatGolok(csapat);


//      6. Határozza meg, hogy az adott csapat otthon melyik fordulóban kapott ki először és melyik csapattól! Ha egyszer sem kapott ki (ilyen csapat például a Bogarak), akkor „A csapat otthon veretlen maradt.” szöveget írja a képernyőre!
        System.out.println("\n\n6. feladat:");
        System.out.println(bajnoksag.elsoVereseg(csapat));

//      7. Készítsen statisztikát, amely megadja, hogy az egyes végeredmények hány alkalommal fordultak elő! Tekintse egyezőnek a fordított eredményeket (például 4-2 és 2-4)! A nagyobb számot mindig előre írja! Az elkészült listát a stat.txt állományban helyezze el! Például: 2-1: 18 darab 4-0: 2 darab 2-0: 19 darab ...
        Path statPath = Path.of("src", "main", "resources", "stat.txt");
        bajnoksag.writeToFile(statPath);
    }
}
