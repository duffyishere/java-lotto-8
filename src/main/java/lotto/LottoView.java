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
        return parseLotto(inputValues);
    }

    private Lotto parseLotto(String[] inputValues) {
        validateLotto(inputValues);
        List<Integer> numbers = Arrays.stream(inputValues).map(Integer::parseInt).toList();
        return new Lotto(numbers);
    }

    private void validateLotto(String[] inputValues) {
        if (inputValues.length != 6)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        int[] numbers = new int[46];
        for (String inputValue : inputValues) {
            int number = parseInteger(inputValue);
            if (number < 1 || number > 45)
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
            if (1 < numbers[number])
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            numbers[number]++;
        }
    }

    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String inputValue = Console.readLine().trim();
        int bonusNumber = parseInteger(inputValue);
        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    public void validateBonusNumber(int number) {
        if (number < 1 || number > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
    }
}
