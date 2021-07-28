package org.example;

import org.example.generator.UniqueNucleotideWordsGenerator;

public class App
{
    public static void main( String[] args )
    {
        UniqueNucleotideWordsGenerator
                .generateUniqueNucleotideWords(96)
                .forEach(System.out::println);
    }
}
