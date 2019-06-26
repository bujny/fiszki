package tech.fiszki.data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataLoader {

    public static void loadMock(DaoSession daoSession) {

        if (daoSession.getWordDao().count() > 0) {
            return;
        }

        ReviewAlgorithm reviewAlgorithm = ReviewAlgorithm.builder().name("Algorithm1").build();
        daoSession.getReviewAlgorithmDao().save(reviewAlgorithm);
        ReviewDay d1 = ReviewDay.builder().daysAfterBeginning(0).reviewAlgorithm(reviewAlgorithm).build();
        d1.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d2 = ReviewDay.builder().daysAfterBeginning(1).reviewAlgorithm(reviewAlgorithm).build();
        d2.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d3 = ReviewDay.builder().daysAfterBeginning(2).reviewAlgorithm(reviewAlgorithm).build();
        d3.setReviewAlgorithm(reviewAlgorithm);
        reviewAlgorithm.setReviewDays(Arrays.asList(d1, d2, d3));

        int startedDaysAgo = 5;
        LocalDateTime startDate = LocalDateTime.now().minusHours(1).minusDays(startedDaysAgo);

        List<Association> associations1 = new ArrayList<>();
        Association as =new Association();
        as.setAssociationWord("skojarzenie1");
        associations1.add(as);

        //Word word1 = new Word("duck","kaczka","us",associations1);

        Word w1 = Word.builder().originalWord("Word1").translatedWord("Word1").language("us").associations(associations1).build();
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


        Word w2 = Word.builder().originalWord("Word2").translatedWord("Word2").language("us").build();
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

        Word w3 = Word.builder().originalWord("Word3").translatedWord("Word3").language("us").build();
        daoSession.getWordDao().save(w3);
        Repetition r1w3 = Repetition.builder().successRate(0.2).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r1w3.setWord(w3);
        Repetition r2w3 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r2w3.setWord(w3);
        Repetition r3w3 = Repetition.builder().successRate(0.5).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w3).build();
        r3w3.setWord(w3);
        w3.setRepetitions(Arrays.asList(r1w3, r2w3, r3w3));


        Word w4 = Word.builder().originalWord("Word4").translatedWord("Word4").language("us").build();
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


        Word w5 = Word.builder().originalWord("Word5").translatedWord("Word5").language("us").build();
        daoSession.getWordDao().save(w5);
        Repetition r1w5 = Repetition.builder().successRate(0.3).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w5).build();
        r1w5.setWord(w5);
        Repetition r2w5 = Repetition.builder().successRate(0.5).repetitionDate(Date.from(startDate.plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).word(w5).build();
        r2w5.setWord(w5);
        w5.setRepetitions(Arrays.asList(r1w5, r2w5));

        Word w6 = Word.builder().originalWord("Word6").translatedWord("Word6").language("us").build();
        daoSession.getWordDao().save(w6);
        Repetition r1w6 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w6).build();
        r1w6.setWord(w6);
        Repetition r2w6 = Repetition.builder().successRate(0.7).repetitionDate(Date.from(startDate.plusDays(2).atZone(ZoneId.systemDefault()).toInstant())).word(w6).build();
        r2w6.setWord(w6);
        w6.setRepetitions(Arrays.asList(r1w6, r2w6));

        Word w7 = Word.builder().originalWord("Word7").translatedWord("Word7").language("us").build();
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

        Word w8 = Word.builder().originalWord("Word8").translatedWord("Word8").language("us").translatedWord("Word8").build();
        daoSession.getWordDao().save(w8);
        Repetition r1w8 = Repetition.builder().successRate(0.4).repetitionDate(Date.from(startDate.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant())).word(w8).build();
        r1w8.setWord(w8);
        w8.setRepetitions(Arrays.asList(r1w8));

        Word w9 = Word.builder().originalWord("Word9").translatedWord("Word9").language("us").build();
        w9.setAssociations(new ArrayList<Association>());
        w9.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w9);

        Word w10 = Word.builder().originalWord("Word10").translatedWord("Word10").language("us").build();
        w10.setAssociations(new ArrayList<Association>());
        w10.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w10);

        Word w11 = Word.builder().originalWord("Word11").translatedWord("Word11").language("us").build();
        w11.setAssociations(new ArrayList<Association>());
        w11.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w11);


        Word w12 = Word.builder().originalWord("Word12").translatedWord("Word12").language("us").build();
        w12.setAssociations(new ArrayList<Association>());
        w12.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w12);


        Word w13 = Word.builder().originalWord("Word13").translatedWord("Word13").language("us").build();
        w13.setAssociations(new ArrayList<Association>());
        w13.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w13);


        Word w14 = Word.builder().originalWord("Word14").translatedWord("Word14").language("us").build();
        w14.setAssociations(new ArrayList<Association>());
        w14.setRepetitions(new ArrayList<Repetition>());
        daoSession.getWordDao().save(w14);


        ArrayList<Word> words = new ArrayList<>();
        for (int i = 20; i < 100; i++) {
            words.add(Word.builder().originalWord("Word" + i).translatedWord("Word" + i).language("us").build());
        }


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
        daoSession.getWordDao().save(w9);
        daoSession.getWordDao().save(w10);
        daoSession.getWordDao().save(w11);
        daoSession.getWordDao().save(w12);
        daoSession.getWordDao().save(w13);
        daoSession.getWordDao().save(w14);

        for (Word word : words) {
            daoSession.getWordDao().save(word);
        }

    }

    public static void load(DaoSession daoSession) {
        if (daoSession.getWordDao().count() > 0) {
            return;
        }

        ReviewAlgorithm reviewAlgorithm = ReviewAlgorithm.builder().name("Algorithm1").build();
        daoSession.getReviewAlgorithmDao().save(reviewAlgorithm);
        ReviewDay d1 = ReviewDay.builder().daysAfterBeginning(0).reviewAlgorithm(reviewAlgorithm).build();
        d1.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d2 = ReviewDay.builder().daysAfterBeginning(1).reviewAlgorithm(reviewAlgorithm).build();
        d2.setReviewAlgorithm(reviewAlgorithm);
        ReviewDay d3 = ReviewDay.builder().daysAfterBeginning(2).reviewAlgorithm(reviewAlgorithm).build();
        d3.setReviewAlgorithm(reviewAlgorithm);
        reviewAlgorithm.setReviewDays(Arrays.asList(d1, d2, d3));


        Word w1 = (Word.builder().originalWord("Abracadabra").translatedWord("Abrakadabra").language("us").repetitions(new ArrayList<>()).build());
        w1.setAssociations(Arrays.asList(
                Association.builder().word(w1).associationWord("Abra").build(),
                Association.builder().word(w1).associationWord("Pokemon").build()
        ));
        Word w2 = (Word.builder().originalWord("Agenda").translatedWord("Program").language("us").repetitions(new ArrayList<>()).build());
        w2.setAssociations(Arrays.asList(
                Association.builder().word(w2).associationWord("Schedule1").build(),
                Association.builder().word(w2).associationWord("Schedule2").build()
        ));
        Word w3 = (Word.builder().originalWord("Aircraft").translatedWord("Samolot").language("us").repetitions(new ArrayList<>()).build());
        w3.setAssociations(Arrays.asList(
                Association.builder().word(w3).associationWord("Airplane").build()
        ));
        Word w4 = (Word.builder().originalWord("Alliance").translatedWord("Sojusz").language("us").repetitions(new ArrayList<>()).build());
        w4.setAssociations(Arrays.asList(
                Association.builder().word(w4).associationWord("II World War").build(),
                Association.builder().word(w4).associationWord("League").build()
        ));
        Word w5 = (Word.builder().originalWord("Attorney").translatedWord("Prawnik").language("us").repetitions(new ArrayList<>()).build());
        w5.setAssociations(Arrays.asList(
                Association.builder().word(w5).associationWord("Lawyer").build()
        ));
        Word w6 = (Word.builder().originalWord("Burden").translatedWord("Brzemie").language("us").repetitions(new ArrayList<>()).build());
        w6.setAssociations(Arrays.asList(
                Association.builder().word(w6).associationWord("The One Ring of Power").build()
        ));
        Word w7 = (Word.builder().originalWord("Precious").translatedWord("Cenny").language("us").repetitions(new ArrayList<>()).build());
        w7.setAssociations(Arrays.asList(
                Association.builder().word(w7).associationWord("Gollum").build(),
                Association.builder().word(w7).associationWord("Lord of The Rings").build()
        ));
        Word w8 = (Word.builder().originalWord("Cabinet").translatedWord("Gabinet").language("us").repetitions(new ArrayList<>()).build());
        w8.setAssociations(Arrays.asList(
                Association.builder().word(w8).associationWord("Office").build()
        ));
        Word w9 = (Word.builder().originalWord("Carrier").translatedWord("Przewoźnik").language("us").repetitions(new ArrayList<>()).build());
        w9.setAssociations(Arrays.asList(
                Association.builder().word(w9).associationWord("Messenger").build()
        ));
        Word w10 = (Word.builder().originalWord("Coal").translatedWord("Węgiel").language("us").repetitions(new ArrayList<>()).build());
        w10.setAssociations(Arrays.asList(
                Association.builder().word(w10).associationWord("Miner").build(),
                Association.builder().word(w10).associationWord("Mine").build()
        ));
        Word w11 = (Word.builder().originalWord("Council").translatedWord("Rada").language("us").repetitions(new ArrayList<>()).build());
        w11.setAssociations(Arrays.asList(
                Association.builder().word(w11).associationWord("Mine").build()

        ));
        Word w12 = (Word.builder().originalWord("Counter").translatedWord("Licznik").language("us").repetitions(new ArrayList<>()).build());
        w12.setAssociations(Arrays.asList(
                Association.builder().word(w12).associationWord("Register").build(),
                Association.builder().word(w12).associationWord("1,2,3").build()

        ));
        Word w13 = (Word.builder().originalWord("Dignity").translatedWord("Godność").language("us").repetitions(new ArrayList<>()).build());
        w13.setAssociations(Arrays.asList(
                Association.builder().word(w13).associationWord("Majesty").build()

        ));
        Word w14 = (Word.builder().originalWord("Dispute").translatedWord("Spór").language("us").repetitions(new ArrayList<>()).build());
        w14.setAssociations(Arrays.asList(
                Association.builder().word(w14).associationWord("Quarrel").build()

        ));
        Word w15 = (Word.builder().originalWord("Enchanter").translatedWord("Czarodziej").language("us").repetitions(new ArrayList<>()).build());
        w15.setAssociations(Arrays.asList(
                Association.builder().word(w15).associationWord("Witch").build(),
                Association.builder().word(w15).associationWord("Wizzard").build()

        ));
        Word w16 = (Word.builder().originalWord("Exhibit").translatedWord("Eksponat").language("us").repetitions(new ArrayList<>()).build());
        w16.setAssociations(Arrays.asList(
                Association.builder().word(w16).associationWord("Display unit").build()

        ));
        Word w17 = (Word.builder().originalWord("Fence").translatedWord("Płot").language("us").repetitions(new ArrayList<>()).build());
        w17.setAssociations(Arrays.asList(
                Association.builder().word(w17).associationWord("").build()

        ));
        Word w18 = (Word.builder().originalWord("Gaze").translatedWord("Spojrzenie").language("us").repetitions(new ArrayList<>()).build());
        w18.setAssociations(Arrays.asList(
                Association.builder().word(w18).associationWord("Look").build()

        ));
        Word w19 = (Word.builder().originalWord("Headquaters").translatedWord("Siedziba").language("us").repetitions(new ArrayList<>()).build());
        w19.setAssociations(Arrays.asList(
                Association.builder().word(w19).associationWord("Base").build()

        ));
        Word w20 = (Word.builder().originalWord("Heritage").translatedWord("Dziedzictwo").language("us").repetitions(new ArrayList<>()).build());
        w20.setAssociations(Arrays.asList(
                Association.builder().word(w20).associationWord("Legacy").build()

        ));
        Word w21 = (Word.builder().originalWord("Infant").translatedWord("Niemowlę").language("us").repetitions(new ArrayList<>()).build());
        w21.setAssociations(Arrays.asList(
                Association.builder().word(w21).associationWord("Little kid").build()

        ));
        Word w22 = (Word.builder().originalWord("Joint").translatedWord("Ścięgno").language("us").repetitions(new ArrayList<>()).build());
        w22.setAssociations(Arrays.asList(
                Association.builder().word(w22).associationWord("Muscles").build()
        ));
        Word w23 = (Word.builder().originalWord("Sinister").translatedWord("Złowieszczy").language("us").repetitions(new ArrayList<>()).build());
        w23.setAssociations(Arrays.asList(
                Association.builder().word(w23).associationWord("Evil").build()

        ));
        Word w24 = (Word.builder().originalWord("Conjuring").translatedWord("Sztuczki magiczne").language("us").repetitions(new ArrayList<>()).build());
        w24.setAssociations(Arrays.asList(
                Association.builder().word(w24).associationWord("Horror").build()

        ));
        Word w25 = (Word.builder().originalWord("Iris").translatedWord("Tęczówka").language("us").repetitions(new ArrayList<>()).build());
        w25.setAssociations(Arrays.asList(
                Association.builder().word(w25).associationWord("Eye").build(),
                Association.builder().word(w25).associationWord("Colorful").build()

        ));
        Word w26 = (Word.builder().originalWord("Smeared").translatedWord("Rozmazany").language("us").repetitions(new ArrayList<>()).build());
        w26.setAssociations(Arrays.asList(
                Association.builder().word(w26).associationWord("Make-up").build(),
                Association.builder().word(w26).associationWord("Blurred").build()


        ));
        Word w27 = (Word.builder().originalWord("Mezanine").translatedWord("Półpiętro").language("us").repetitions(new ArrayList<>()).build());
        w27.setAssociations(Arrays.asList(
                Association.builder().word(w27).associationWord("Macklemore - Thrift Shop").build(),
                Association.builder().word(w27).associationWord("Floor").build()
        ));
        Word w28 = (Word.builder().originalWord("Mortgage").translatedWord("Kredyt hipoteczny").language("us").repetitions(new ArrayList<>()).build());
        w28.setAssociations(Arrays.asList(
                Association.builder().word(w28).associationWord("Monopoly").build()
        ));
        Word w29 = (Word.builder().originalWord("Wick").translatedWord("Knot").language("us").repetitions(new ArrayList<>()).build());
        w29.setAssociations(Arrays.asList(
                Association.builder().word(w29).associationWord("Candle").build()
        ));
        Word w30 = (Word.builder().originalWord("Occupation").translatedWord("Zawód").language("us").repetitions(new ArrayList<>()).build());
        w30.setAssociations(Arrays.asList(
                Association.builder().word(w30).associationWord("Job").build()
        ));
        Word w31 = (Word.builder().originalWord("Porch").translatedWord("Ganek").language("us").repetitions(new ArrayList<>()).build());
        w31.setAssociations(Arrays.asList(
                Association.builder().word(w31).associationWord("House").build(),
                Association.builder().word(w31).associationWord("Veranda").build(),
                Association.builder().word(w31).associationWord("PyTorch").build()

        ));
        Word w32 = (Word.builder().originalWord("Torch").translatedWord("Pochodnia").language("us").repetitions(new ArrayList<>()).build());
        w32.setAssociations(Arrays.asList(
                Association.builder().word(w32).associationWord("PyTorch").build(),
                Association.builder().word(w32).associationWord("Fire").build(),
                Association.builder().word(w32).associationWord("Light").build()
        ));

        daoSession.getWordDao().save(w1);
        daoSession.getWordDao().save(w2);
        daoSession.getWordDao().save(w3);
        daoSession.getWordDao().save(w4);
        daoSession.getWordDao().save(w5);
        daoSession.getWordDao().save(w6);
        daoSession.getWordDao().save(w7);
        daoSession.getWordDao().save(w8);
        daoSession.getWordDao().save(w9);
        daoSession.getWordDao().save(w10);
        daoSession.getWordDao().save(w11);
        daoSession.getWordDao().save(w12);
        daoSession.getWordDao().save(w13);
        daoSession.getWordDao().save(w14);
        daoSession.getWordDao().save(w15);
        daoSession.getWordDao().save(w16);
        daoSession.getWordDao().save(w17);
        daoSession.getWordDao().save(w18);
        daoSession.getWordDao().save(w19);
        daoSession.getWordDao().save(w20);
        daoSession.getWordDao().save(w21);
        daoSession.getWordDao().save(w22);
        daoSession.getWordDao().save(w23);
        daoSession.getWordDao().save(w24);
        daoSession.getWordDao().save(w25);
        daoSession.getWordDao().save(w26);
        daoSession.getWordDao().save(w27);
        daoSession.getWordDao().save(w28);
        daoSession.getWordDao().save(w29);
        daoSession.getWordDao().save(w30);
        daoSession.getWordDao().save(w31);
        daoSession.getWordDao().save(w32);

        for (Association association : w1.getAssociations()) {
            association.setWordId(w1.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w2.getAssociations()) {
            association.setWordId(w2.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w3.getAssociations()) {
           association.setWordId(w3.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w4.getAssociations()) {
            association.setWordId(w4.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w5.getAssociations()) {
            association.setWordId(w5.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w6.getAssociations()) {
            association.setWordId(w6.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w7.getAssociations()) {
            association.setWordId(w7.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w8.getAssociations()) {
            association.setWordId(w8.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w9.getAssociations()) {
            association.setWordId(w9.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w10.getAssociations()) {
            association.setWordId(w10.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w11.getAssociations()) {
            association.setWordId(w11.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w12.getAssociations()) {
            association.setWordId(w12.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w13.getAssociations()) {
            association.setWordId(w13.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w14.getAssociations()) {
            association.setWordId(w14.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w15.getAssociations()) {
            association.setWordId(w15.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w16.getAssociations()) {
            association.setWordId(w16.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w17.getAssociations()) {
            association.setWordId(w17.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w18.getAssociations()) {
            association.setWordId(w18.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w19.getAssociations()) {
            association.setWordId(w19.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w20.getAssociations()) {
            association.setWordId(w20.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w21.getAssociations()) {
            association.setWordId(w21.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w22.getAssociations()) {
            association.setWordId(w22.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w23.getAssociations()) {
            association.setWordId(w23.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w24.getAssociations()) {
            association.setWordId(w24.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w25.getAssociations()) {
            association.setWordId(w25.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w26.getAssociations()) {
            association.setWordId(w26.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w27.getAssociations()) {
            association.setWordId(w27.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w28.getAssociations()) {
            association.setWordId(w28.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w29.getAssociations()) {
            association.setWordId(w29.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w30.getAssociations()) {
            association.setWordId(w30.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w31.getAssociations()) {
            association.setWordId(w31.getId());
            daoSession.getAssociationDao().save(association);
        }
        for (Association association : w32.getAssociations()) {
            association.setWordId(w32.getId());
            daoSession.getAssociationDao().save(association);
        }

        daoSession.getReviewDayDao().save(d1);
        daoSession.getReviewDayDao().save(d2);
        daoSession.getReviewDayDao().save(d3);

        daoSession.getReviewAlgorithmDao().save(reviewAlgorithm);

    }
}
