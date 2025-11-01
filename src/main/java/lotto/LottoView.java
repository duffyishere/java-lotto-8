package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class LottoView {
    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputValue = Console.readLine().trim();
        int purchaseAmount = parseInteger(inputValue);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 로또는 1000원 단위로만 입력 가능합니다.");
    }

    private int parseInteger(String inputValue) {
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자만 입력 가능합니다.");
        }
    }

    public Lotto inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] inputValues = Console.readLine().trim().split(",");
        List<Integer> numbers = Arrays.stream(inputValues).map(Integer::parseInt).toList();
        return new Lotto(numbers);
    }

    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String inputValue = Console.readLine().trim();
        return parseInteger(inputValue);
    }
}
