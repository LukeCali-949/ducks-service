package iu.edu.lukemeng.ducksservice.repository;

import iu.edu.lukemeng.ducksservice.model.Duck;
import iu.edu.lukemeng.ducksservice.model.DuckData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DucksRepositoryTest {

    @Test
    void add() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        DuckData duckData = new DuckData(45, "Mallard");
        DuckData duckData2 = new DuckData(25, "decoy");

        ducksRepository.add(duckData);
        ducksRepository.add(duckData2);

        DuckData foundDuck = ducksRepository.getDuck(45);

        DuckData foundDuck2 = ducksRepository.getDuck(25);

        assertEquals(foundDuck.type(), "Mallard");
        assertEquals(foundDuck2.type(), "decoy");

        // test 2

        DuckData duckData5 = new DuckData(15, "Mallard");
        DuckData duckData6 = new DuckData(75, "Rubber");

        ducksRepository.add(duckData5);
        ducksRepository.add(duckData6);

        DuckData foundDuck3 = ducksRepository.getDuck(15);

        DuckData foundDuck4 = ducksRepository.getDuck(75);

        assertEquals(foundDuck3.id(), 15);

        assertEquals(foundDuck4.id(), 75);

    }

    @Test
    void getDuck() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        DuckData duckData = new DuckData(87, "Redhead");
        DuckData duckData2 = new DuckData(32, "Rubber");

        ducksRepository.add(duckData);
        ducksRepository.add(duckData2);

        DuckData foundDuck = ducksRepository.getDuck(87);

        DuckData foundDuck2 = ducksRepository.getDuck(32);

        assertEquals(foundDuck.type(), "Redhead");
        assertEquals(foundDuck2.type(), "Rubber");

        assertEquals(foundDuck.id(), 87);
        assertEquals(foundDuck2.id(), 32);


    }

    @Test
    void getAllDucks() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        DuckData duckData = new DuckData(87, "Redhead");
        DuckData duckData2 = new DuckData(32, "Rubber");
        DuckData duckData3 = new DuckData(45, "Mallard");
        DuckData duckData4 = new DuckData(25, "decoy");

        ducksRepository.add(duckData);
        ducksRepository.add(duckData2);
        ducksRepository.add(duckData3);
        ducksRepository.add(duckData4);

        List<Duck> ducks = ducksRepository.getAllDucks();

        /// test 1

        assertEquals(ducks.size(), 4);

        assertEquals(ducks.get(0).getId(), 87);




    }

    @Test
    void search() throws IOException {
        DucksRepository ducksRepository = new DucksRepository();
        DuckData duckData = new DuckData(87, "Redhead");
        DuckData duckData2 = new DuckData(32, "Mallard");
        DuckData duckData3 = new DuckData(45, "Mallard");
        DuckData duckData4 = new DuckData(25, "decoy");

        ducksRepository.add(duckData);
        ducksRepository.add(duckData2);
        ducksRepository.add(duckData3);
        ducksRepository.add(duckData4);

        List<DuckData> matchedDucks = ducksRepository.search("Mallard");

        assertEquals(matchedDucks.size(), 2);

        assertEquals(matchedDucks.get(0).id(), 32);
    }
}