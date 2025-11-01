package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

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
}
