package foci;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Bajnoksag {

    private final List<Meccs> meccsList = new ArrayList<>();

    public void loadFromFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                loadLines(line);
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not read file", ioException);
        }
    }

    private void loadLines(String line) {
        String[] meccsAdatok = line.split(" ");
        String fordulo = meccsAdatok[0];
        int hazaiGolokVege = Integer.parseInt(meccsAdatok[1]);
        int vendegGolokVege = Integer.parseInt(meccsAdatok[2]);
        int hazaiGolokFelido = Integer.parseInt(meccsAdatok[3]);
        int vendegGolokFelido = Integer.parseInt(meccsAdatok[4]);
        String hazaiCsapat = meccsAdatok[5];
        String vendegCsapat = meccsAdatok[6];
        meccsList.add(new Meccs(fordulo, hazaiCsapat, vendegCsapat, hazaiGolokFelido, vendegGolokFelido, hazaiGolokVege, vendegGolokVege));
    }

    public List<Meccs> getMeccsList() {
        return List.copyOf(meccsList);
    }

    public void printFordulo(String fordulo) {
        meccsList.stream()
                .filter(meccs -> meccs.fordulo().equals(fordulo))
                .forEach(this::printMeccs);
    }

    public void printMeccs(Meccs meccs) {
        System.out.printf("%s-%s: %d-%d (%d-%d)%n", meccs.hazaiCsapat(), meccs.vendegCsapat(), meccs.hazaiGolokVege(), meccs.vendegGolokVege(), meccs.hazaiGolokFelido(), meccs.vendegGolokFelido());
    }

    private boolean forditottMeccs(Meccs meccs) {
        return (meccs.hazaiGolokFelido() > meccs.vendegGolokFelido() && meccs.vendegGolokVege() > meccs.hazaiGolokVege())
                || (meccs.vendegGolokFelido() > meccs.hazaiGolokFelido()) && meccs.hazaiGolokVege() > meccs.vendegGolokVege();
    }

    public void printForditottMeccsek() {
        meccsList.stream()
                .filter(this::forditottMeccs)
                .forEach(this::printForditott);
    }

    private void printForditott(Meccs meccs) {
        if (meccs.hazaiGolokVege() > meccs.vendegGolokVege()) {
            System.out.printf("%s %s%n", meccs.fordulo(), meccs.hazaiCsapat());
        } else {
            System.out.printf("%s %s%n", meccs.fordulo(), meccs.vendegCsapat());
        }
    }

    public void printCsapatGolok(String csapat) {
        int lottGolok = 0;
        int kapottGolok = 0;
        for (Meccs meccs : meccsList) {
            if (meccs.hazaiCsapat().equals(csapat)) {
                lottGolok += meccs.hazaiGolokVege();
                kapottGolok += meccs.vendegGolokVege();
            } else if (meccs.vendegCsapat().equals(csapat)) {
                lottGolok += meccs.vendegGolokVege();
                kapottGolok += meccs.hazaiGolokVege();
            }
        }
        System.out.printf("lőtt: %d kapott: %d", lottGolok, kapottGolok);
    }
}
