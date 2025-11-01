package lotto;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoService lottoService;
    private final LottoView lottoView;

    public LottoController(LottoService lottoService, LottoView lottoView) {
        this.lottoService = lottoService;
        this.lottoView = lottoView;
    }

    public void run() {
        int purchaseAmount = lottoView.inputPurchaseAmount();
        List<Lotto> lottos = lottoService.purchaseLotto(purchaseAmount);
        lottoView.printPurchasedLotto(lottos);

        Lotto lotto = lottoView.inputWinningNumbers();
        int bonusNumber = lottoView.inputBonusNumber();
        WinningNumber winningNumber = new WinningNumber(lotto, bonusNumber);

        Map<LottoRank, Integer> result = lottoService.calculateResult(winningNumber, lottos);

        double profitRate = lottoService.calculateProfitRate(result, purchaseAmount);

        lottoView.printStatistics(result, profitRate);
    }
}
