package foci;

public record Meccs(int fordulo, String hazaiCsapat, String vendegCsapat, int hazaiGolokFelido,
                    int vendegGolokFelido, int hazaiGolokVege, int vendegGolokVege) {

    public String vegEredmeny() {
        if (hazaiGolokVege >= vendegGolokVege) return String.format("%d-%d", hazaiGolokVege, vendegGolokVege);
        else return String.format("%d-%d", vendegGolokVege, hazaiGolokVege);
    }
}
