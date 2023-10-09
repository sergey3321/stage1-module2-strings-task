package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */

    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < source.length(); i++) {
            for (String delimiter : delimiters) {
                if (source.startsWith(delimiter, i)) {
                    if (start != i) {
                        result.add(source.substring(start, i));
                    }
                    start = i + delimiter.length();
                }
            }
        }
        if (start != source.length()) {
            result.add(source.substring(start));
        }
        return result;
    }
}
