package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    public List<Lotto> purchaseLotto(BigDecimal amount) {
        int count = amount.divide(new BigDecimal(1000)).intValue();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }

    public Map<LottoRank, Integer> calculateResult(WinningNumber winningNumber, List<Lotto> lottos) {
        Map<LottoRank, Integer> result = initResultMap();
        for (Lotto lotto : lottos) {
            int matchCount = countMatchingNumbers(winningNumber, lotto);
            boolean matchBonus = lotto.getNumbers().contains(winningNumber.getBonusNumber());
            LottoRank rank = LottoRank.of(matchCount, matchBonus);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private static Map<LottoRank, Integer> initResultMap() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private int countMatchingNumbers(WinningNumber winningNumber, Lotto lotto) {
        return (int) winningNumber.getWinningLotto().getNumbers().stream()
                .filter(lotto.getNumbers()::contains)
                .count();
    }

    public double calculateProfitRate(Map<LottoRank, Integer> result, BigDecimal purchaseAmount) {
        long totalProfit = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        BigDecimal totalProfitDecimal = BigDecimal.valueOf(totalProfit);

        BigDecimal profitRateDecimal = totalProfitDecimal.divide(purchaseAmount, 10, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);

        return profitRateDecimal.doubleValue();
    }
}
