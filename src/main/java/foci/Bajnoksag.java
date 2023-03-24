package foci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        meccsList.sort((meccs1, meccs2) -> {
            return meccs1.fordulo() - meccs2.fordulo();
        });
    }

    private void loadLines(String line) {
        String[] meccsAdatok = line.split(" ");
        int fordulo = Integer.parseInt(meccsAdatok[0]);
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

    public void printFordulo(int fordulo) {
        meccsList.stream()
                .filter(meccs -> meccs.fordulo() == fordulo)
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

    public String elsoVereseg(String csapat) {
        return meccsList.stream()
                .filter(meccs -> meccs.hazaiCsapat().equals(csapat) && meccs.vendegGolokVege() > meccs.hazaiGolokVege())
                .findFirst()
                .map(meccs -> String.format("%d %s", meccs.fordulo(), meccs.vendegCsapat()))
                .orElse("A csapat otthon veretlen maradt.");
    }

    public Map<String, Integer> getEredmenyek() {
        Map<String, Integer> result = new HashMap<>();
        meccsList.forEach(meccs -> result.merge(meccs.vegEredmeny(), 1, Integer::sum));
        return result;
    }

    public void writeToFile(Path path) {
        Map<String, Integer> eredmenyek = getEredmenyek();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Integer> entry : eredmenyek.entrySet()) {
                bufferedWriter.write(entry.getKey()
                        .concat(": ")
                        .concat(entry.getValue().toString())
                        .concat(" darab")
                        .concat(System.lineSeparator()));
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not write file", ioException);
        }
    }
}
