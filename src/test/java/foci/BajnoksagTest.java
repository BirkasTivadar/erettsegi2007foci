package foci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BajnoksagTest {

    Bajnoksag bajnoksag;

    Path path;

    @BeforeEach
    void init() {
        bajnoksag = new Bajnoksag();
        path = Path.of("src", "main", "resources", "meccs.txt");
    }

    @Test
    void loadFromFile() {
        bajnoksag.loadFromFile(path);
        assertEquals(112, bajnoksag.getMeccsList().size());
    }
}