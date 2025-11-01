package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    public List<Lotto> purchaseLotto(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 1000; i <= amount; i += 1000) {
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
            if (rank == LottoRank.MISS) continue;
            result.put(rank, 0);
        }
        return result;
    }

    private int countMatchingNumbers(WinningNumber winningNumber, Lotto lotto) {
        return (int) winningNumber.getWinningLotto().getNumbers().stream()
                .filter(lotto.getNumbers()::contains)
                .count();
    }

    public double calculateProfitRate(Map<LottoRank, Integer> result, int purchaseAmount) {
        long totalProfit = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        double profitRate = (double) totalProfit / purchaseAmount * 100;
        return Math.round(profitRate * 100) / 100.0;
    }
}
