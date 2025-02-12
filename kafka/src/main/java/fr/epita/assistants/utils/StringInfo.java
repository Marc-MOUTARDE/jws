package fr.epita.assistants.utils;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class StringInfo {
    String word;
    Integer nbVowels;
    Integer nbConsonants;
    Boolean isPalindrome;
}
