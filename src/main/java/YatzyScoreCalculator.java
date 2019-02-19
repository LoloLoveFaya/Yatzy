import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public enum YatzyScoreCalculator {
    ONES,
    TWOS,
    THREES,
    FOURS,
    FIVES,
    SIXES,
    THREE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    SMALL_STRAIGHT,
    LARGE_STRAIGHT,
    YATZY,
    CHANCE;

    public boolean isTheAnnouncedCategory(int[] dice) {
        switch (this) {
            case THREE_OF_A_KIND:
                return IntStream.of(dice).map(die -> calculateFrequency(dice, die)).max().getAsInt() >= 3;
            case FOUR_OF_A_KIND:
                return IntStream.of(dice).map(die -> calculateFrequency(dice, die)).max().getAsInt() >= 4;
            case YATZY:
                return IntStream.of(dice).map(die -> calculateFrequency(dice, die)).max().getAsInt() == 5;
            case FULL_HOUSE:
                return isAFullHouse(dice);
            case SMALL_STRAIGHT:
                int[] small_straights = {1, 2, 3, 4, 5};
                return Stream.of(small_straights).anyMatch(s -> Collections.indexOfSubList(convertTableOfIntToListInteger(dice).stream().distinct().sorted().collect(Collectors.toList()), convertTableOfIntToListInteger(s)) != -1);
            case LARGE_STRAIGHT:
                int[] large_straights = {2, 3, 4, 5, 6};
                return Stream.of(large_straights).anyMatch(s -> Collections.indexOfSubList(convertTableOfIntToListInteger(dice).stream().distinct().sorted().collect(Collectors.toList()), convertTableOfIntToListInteger(s)) != -1);
            default:
                return true;
        }
    }

    public int getScore(int[] dice) {
        if (this.isTheAnnouncedCategory(dice)) {
            switch (this) {
                case LARGE_STRAIGHT:
                    return 20;
                case YATZY:
                    return 50;
                case THREE_OF_A_KIND:
                    return calculateSameOfAKindWithMultiplier(dice, 3);
                case FOUR_OF_A_KIND:
                    return calculateSameOfAKindWithMultiplier(dice, 4);
                case SMALL_STRAIGHT:
                    return 15;
                case FULL_HOUSE:
                case CHANCE:
                    return IntStream.of(dice).sum();
                case ONES:
                case TWOS:
                case THREES:
                case FOURS:
                case FIVES:
                case SIXES:
                    return IntStream.of(dice).filter(die -> die == upperCategoryToInt(this)).sum();
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return 0;
        }
    }

    public static int upperCategoryToInt(YatzyScoreCalculator c) {
        switch (c) {
            case ONES:
                return 1;
            case TWOS:
                return 2;
            case THREES:
                return 3;
            case FOURS:
                return 4;
            case FIVES:
                return 5;
            case SIXES:
                return 6;
            default:
                throw new IllegalArgumentException();
        }
    }

    private int calculateFrequency(int[] dice, int elementToCalculate) {
        List<Integer> list = convertTableOfIntToListInteger(dice);
        return Collections.frequency(list, elementToCalculate);
    }

    private int calculateSameOfAKindWithMultiplier(int[] dice, int multiplier) {
        List<Integer> list = convertTableOfIntToListInteger(dice);
        int value = IntStream.of(dice).filter(current -> Collections.frequency(list, current) > 1).findFirst().getAsInt();
        return value * multiplier;
    }

    private List<Integer> convertTableOfIntToListInteger(int[] dice) {
        return Arrays.stream(dice).boxed().collect(Collectors.toList());
    }

    private boolean isAFullHouse(int[] dice) {
        int[] distinct = IntStream.of(dice).distinct().toArray();
        int[] counts = IntStream.of(distinct).map(die -> calculateFrequency(dice, die)).sorted().toArray();
        return counts[0] == 2 && counts[1] == 3;
    }
}
