import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mitou on 19/02/19.
 */
public class YatzyTest {

    YatzyScoreCalculator yatzyScoreCalculator;

    @Test
    public void should_return_chance_score() {
        int[] dice = {1,1,3,3,6};
        int score = YatzyScoreCalculator.CHANCE.getScore(dice);
        assertThat(score).isEqualTo(14);
    }

    @Test
    public void should_return_chance_score_case2() {
        int[] dice = {4,5,5,6,1};
        int score = YatzyScoreCalculator.CHANCE.getScore(dice);

        assertThat(score).isEqualTo(21);
    }

    @Test
    public void should_return_50_for_yatzy() {
        int[] dice = {1,1,1,1,1};
        int score = YatzyScoreCalculator.YATZY.getScore(dice);
        assertThat(score).isEqualTo(50);
    }

    @Test
    public void should_return_0_for_unmatching_yatzy() {
        int[] dice = {1,1,1,2,1};
        int score = YatzyScoreCalculator.YATZY.getScore(dice);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void should_return_score_for_fours() {
        int[] dice = {1,1,2,4,4};
        int score = YatzyScoreCalculator.FOURS.getScore(dice);
        assertThat(score).isEqualTo(8);
    }

    @Test
    public void should_return_score_for_twos() {
        int[] dice = {2,3,2,5,1};
        int score = YatzyScoreCalculator.TWOS.getScore(dice);
        assertThat(score).isEqualTo(4);
    }

    @Test
    public void should_return_score_for_ones() {
        int[] dice = {3,3,3,4,5};
        int score = YatzyScoreCalculator.ONES.getScore(dice);
        assertThat(score).isEqualTo(0);
    }

    @Test
    public void should_return_score_for_three_of_a_kind() {
        int[] dice = {3,3,3,4,5};
        int score = YatzyScoreCalculator.THREE_OF_A_KIND.getScore(dice);
        assertThat(score).isEqualTo(9);
    }

    @Test
    public void should_return_score_for_three_of_a_kind_when_present_more_than_three_times() {
        int[] dice = {3,3,3,3,5};
        int score = YatzyScoreCalculator.THREE_OF_A_KIND.getScore(dice);
        assertThat(score).isEqualTo(9);
    }

    @Test
    public void should_return_score_0_when_three_of_a_kind_is_not_matched() {
        int[] dice = {3,3,4,5,6};
        int score = YatzyScoreCalculator.THREE_OF_A_KIND.getScore(dice);
        assertThat(score).isEqualTo(0);
    }

    @Test
    public void should_return_score_for_four_of_a_kind() {
        int[] dice = {2,2,2,2,2};
        int score = YatzyScoreCalculator.FOUR_OF_A_KIND.getScore(dice);
        assertThat(score).isEqualTo(8);
    }

    @Test
    public void should_return_score_for_small_straight() {
        int[] dice = {1,2,3,4,5};
        int score = YatzyScoreCalculator.SMALL_STRAIGHT.getScore(dice);
        assertThat(score).isEqualTo(15);
    }

    @Test
    public void should_return_score_for_large_straight() {
        int[] dice = {2,3,4,5,6};
        int score = YatzyScoreCalculator.LARGE_STRAIGHT.getScore(dice);
        assertThat(score).isEqualTo(20);
    }

    @Test
    public void should_return_score_for_a_full_house() {
        int[] dice = {1,1,2,2,2};
        int score = YatzyScoreCalculator.FULL_HOUSE.getScore(dice);
        assertThat(score).isEqualTo(8);
    }

    @Test
    public void should_return_score_0_for_unmatched_a_full_house() {
        int[] dice = {2,2,3,3,4};
        int score = YatzyScoreCalculator.FULL_HOUSE.getScore(dice);
        assertThat(score).isEqualTo(0);
    }
}
