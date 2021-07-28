package org.example.generator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueNucleotideWordsGeneratorTest {

    @ParameterizedTest
    @CsvSource(value = {
            "AAAAAA,AAAAAA,false",
            "AAAAAA,AGAAAT,false",
            "AAAAAA,AGCAAT,true",
            "AGCTAA,GAAACT,true",
            "GTGACT,CCAATA,true",
            "CGCGGA,TATAGC,true"
    })
    void shouldCorrectlyDetermineWhetherDifferenceCriteriaAreMet(String first, String second, boolean expected) {
        //given//when//then
        assertEquals(expected, UniqueNucleotideWordsGenerator.meetDifferenceCriteria(first, second));
    }

    @Test
    void shouldReturnListOfGivenSize() {
        //given//when//then
        assertEquals(1, UniqueNucleotideWordsGenerator.generateUniqueNucleotideWords(1).size());
        assertEquals(5, UniqueNucleotideWordsGenerator.generateUniqueNucleotideWords(5).size());
        assertEquals(7, UniqueNucleotideWordsGenerator.generateUniqueNucleotideWords(7).size());
        assertEquals(9, UniqueNucleotideWordsGenerator.generateUniqueNucleotideWords(9).size());
        assertEquals(12, UniqueNucleotideWordsGenerator.generateUniqueNucleotideWords(12).size());
    }
}