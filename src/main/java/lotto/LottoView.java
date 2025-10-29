package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class LottoView {
    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputValue = Console.readLine().trim();
        int purchaseAmount = parseInteger(inputValue);
        if (purchaseAmount % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로만 입력 가능합니다.");
        return purchaseAmount;
    }

    private int parseInteger(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자만 입력 가능합니다.");
        }
    }
}
