package tech.fiszki.data.query_results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordSuccessfulRepetitions {
    private String word;
    private int successfulRepetitionCount;
}
