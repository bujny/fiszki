package tech.fiszki.data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DataLoader {

    public static void load2(DaoSession daoSession) {

        ReviewAlgorithm reviewAlgorithm = ReviewAlgorithm.builder().name("Algorithm1").build();
        daoSession.getReviewAlgorithmDao().save(reviewAlgorithm);
        ReviewDay d1 = ReviewDay.builder().daysAfterBeginning(0).reviewAlgorithm(reviewAlgorithm).build();
        d1.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d2 = ReviewDay.builder().daysAfterBeginning(1).reviewAlgorithm(reviewAlgorithm).build();
        d2.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d3 = ReviewDay.builder().daysAfterBeginning(2).reviewAlgorithm(reviewAlgorithm).build();
        d3.setReviewAlgorithm(reviewAlgorithm);
        reviewAlgorithm.setReviewDays(Arrays.asList(d1,d2,d3));

        int startedDaysAgo = 5;
        LocalDateTime startDate = LocalDateTime.now().minusHours(1).minusDays(startedDaysAgo);

        Word w1 = Word.builder().originalWord("Word1").language("us").build();
        daoSession.getWordDao().save(w1);
        Repetition r1w1 = Repetition.builder().successRate(0.6).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w1).build();
        //!!!!!!    Need to setWord not with builder after Word is saved to db (then it gets id)
        r1w1.setWord(w1);
        daoSession.getRepetitionDao().save(r1w1);
        Repetition r2w1 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w1).build();
        r2w1.setWord(w1);
        Repetition r3w1 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w1).build();
        r3w1.setWord(w1);
        w1.setRepetitions(Arrays.asList(r1w1, r2w1, r3w1));


        Word w2 = Word.builder().originalWord("Word2").language("us").build();
        daoSession.getWordDao().save(w2);

        Repetition r1w2 = Repetition.builder().successRate(0.2).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w2).build();
        r1w2.setWord(w2);
        Repetition r2w2 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant())).word(w2).build();
        r2w2.setWord(w2);
        Repetition r3w2 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w2).build();
        r3w2.setWord(w2);
        Repetition r4w2 = Repetition.builder().successRate(0.6).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w2).build();
        r4w2.setWord(w2);
        Repetition r5w2 = Repetition.builder().successRate(0.6).repetitionDate(Date.from(startDate.plusDays(4).atZone(ZoneId.systemDefault()).toInstant())).word(w2).build();
        r5w2.setWord(w2);
        w2.setRepetitions(Arrays.asList(r1w2, r2w2, r3w2, r4w2, r5w2));

        Word w3 = Word.builder().originalWord("Word3").language("us").build();
        daoSession.getWordDao().save(w3);
        Repetition r1w3 = Repetition.builder().successRate(0.2).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r1w3.setWord(w3);
        Repetition r2w3 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r2w3.setWord(w3);
        Repetition r3w3 = Repetition.builder().successRate(0.5).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r3w3.setWord(w3);
        w3.setRepetitions(Arrays.asList(r1w3, r2w3, r3w3));


        Word w4 = Word.builder().originalWord("Word4").language("us").build();
        daoSession.getWordDao().save(w4);
        Repetition r1w4 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w4).build();
        r1w4.setWord(w4);
        Repetition r2w4 = Repetition.builder().successRate(0.9).repetitionDate(Date.from(startDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant())).word(w4).build();
        r2w4.setWord(w4);
        Repetition r3w4 = Repetition.builder().successRate(0.6).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w4).build();
        r3w4.setWord(w4);
        Repetition r4w4 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusDays(4).atZone(ZoneId.systemDefault()).toInstant())).word(w4).build();
        r4w4.setWord(w4);
        w4.setRepetitions(Arrays.asList(r1w4, r2w4, r3w4, r4w4));


        Word w5 = Word.builder().originalWord("Word5").language("us").build();
        daoSession.getWordDao().save(w5);
        Repetition r1w5 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w5).build();
        r1w5.setWord(w5);
        Repetition r2w5 = Repetition.builder().successRate(0.5).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w5).build();
        r2w5.setWord(w5);
        w5.setRepetitions(Arrays.asList(r1w5, r2w5));

        Word w6 = Word.builder().originalWord("Word6").language("us").build();
        daoSession.getWordDao().save(w6);
        Repetition r1w6 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w6).build();
        r1w6.setWord(w6);
        Repetition r2w6 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w6).build();
        r2w6.setWord(w6);
        w6.setRepetitions(Arrays.asList(r1w6, r2w6));

        Word w7 = Word.builder().originalWord("Word7").language("us").build();
        daoSession.getWordDao().save(w7);
        Repetition r1w7 = Repetition.builder().successRate(0.2).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w7).build();
        r1w7.setWord(w7);
        Repetition r2w7 = Repetition.builder().successRate(0.5).repetitionDate(Date.from(startDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant())).word(w7).build();
        r2w7.setWord(w7);
        Repetition r3w7 = Repetition.builder().successRate(0.8).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w7).build();
        r3w7.setWord(w7);
        Repetition r4w7 = Repetition.builder().successRate(0.9).repetitionDate(Date.from(startDate.plusDays(4).atZone(ZoneId.systemDefault()).toInstant())).word(w7).build();
        r4w7.setWord(w7);
        w7.setRepetitions(Arrays.asList(r1w7, r2w7, r3w7, r4w7));

        Word w8 = Word.builder().originalWord("Word8").language("us").build();
        daoSession.getWordDao().save(w8);
        Repetition r1w8 = Repetition.builder().successRate(0.4).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w8).build();
        r1w8.setWord(w8);
        w8.setRepetitions(Arrays.asList(r1w8));

        Word w9 = Word.builder().originalWord("Word9").language("us").build();
        w9.setAssociations(new ArrayList<Association>());
        w9.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w9);

        Word w10 = Word.builder().originalWord("Word10").language("us").build();
        w10.setAssociations(new ArrayList<Association>());
        w10.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w10);




        daoSession.getReviewDayDao().save(d1);
        daoSession.getReviewDayDao().save(d2);
        daoSession.getReviewDayDao().save(d3);

        daoSession.getReviewAlgorithmDao().save(reviewAlgorithm);


        daoSession.getRepetitionDao().save(r1w1);
        daoSession.getRepetitionDao().save(r2w1);
        daoSession.getRepetitionDao().save(r3w1);
        daoSession.getRepetitionDao().save(r1w2);
        daoSession.getRepetitionDao().save(r2w2);
        daoSession.getRepetitionDao().save(r3w2);
        daoSession.getRepetitionDao().save(r4w2);
        daoSession.getRepetitionDao().save(r5w2);
        daoSession.getRepetitionDao().save(r1w3);
        daoSession.getRepetitionDao().save(r2w3);
        daoSession.getRepetitionDao().save(r3w3);
        daoSession.getRepetitionDao().save(r1w4);
        daoSession.getRepetitionDao().save(r2w4);
        daoSession.getRepetitionDao().save(r3w4);
        daoSession.getRepetitionDao().save(r4w4);
        daoSession.getRepetitionDao().save(r1w5);
        daoSession.getRepetitionDao().save(r2w5);
        daoSession.getRepetitionDao().save(r1w6);
        daoSession.getRepetitionDao().save(r2w6);
        daoSession.getRepetitionDao().save(r1w7);
        daoSession.getRepetitionDao().save(r2w7);
        daoSession.getRepetitionDao().save(r3w7);
        daoSession.getRepetitionDao().save(r4w7);
        daoSession.getRepetitionDao().save(r1w8);

        daoSession.getWordDao().save(w1);
        daoSession.getWordDao().save(w2);
        daoSession.getWordDao().save(w3);
        daoSession.getWordDao().save(w4);
        daoSession.getWordDao().save(w5);
        daoSession.getWordDao().save(w6);
        daoSession.getWordDao().save(w7);
        daoSession.getWordDao().save(w8);

    }
}
