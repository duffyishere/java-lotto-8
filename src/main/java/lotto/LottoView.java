package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public void printPurchasedLotto(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto ticket : lottos) {
            System.out.println(ticket.getNumbers());
        }}

    public void printStatistics(Map<LottoRank, Integer> result, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.get(LottoRank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(LottoRank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(LottoRank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(LottoRank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(LottoRank.FIRST) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}
