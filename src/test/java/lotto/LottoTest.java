package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    private LottoService lottoService = new LottoService();

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외가 발생한다.")
    @Test
    void 당첨번호와_보너스번호가_중복되면_예외() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumber(winningLotto, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("맞은 개수와 보너스 여부에 따라 Rank가 정확히 선택된다.")
    @Test
    void 당첨_등수_판단() {
        assertThat(LottoRank.of(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.of(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.of(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.of(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.of(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.of(2, false)).isEqualTo(LottoRank.MISS);
    }

    @DisplayName("5등 1개에 해당할 경우 수익률은 정확히 62.5%이다.")
    @Test
    void 수익률_계산() {
        Map<LottoRank, Integer> result = Map.of(
                LottoRank.FIRST, 0,
                LottoRank.SECOND, 0,
                LottoRank.THIRD, 0,
                LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 1,
                LottoRank.MISS, 0
        );
        BigDecimal purchaseAmount = new BigDecimal(8000);

        double profitRate = lottoService.calculateProfitRate(result, purchaseAmount);

        assertThat(profitRate).isEqualTo(62.5);
    }}
