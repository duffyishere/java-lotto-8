package lotto;

public class WinningNumber {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumber(Lotto winningLotto, int bonusNumber) {
        validate(bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
    public int getBonusNumber() {
        return bonusNumber;
    }
}
