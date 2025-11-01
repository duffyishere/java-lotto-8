package lotto;

public class WinningNumber {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumber(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, int bonusNumber) {
        if(bonusNumber < 1 || bonusNumber > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");

        if(winningLotto.getNumbers().contains(bonusNumber))
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 다른 번호와 중복될 수 없습니다.");
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
    public int getBonusNumber() {
        return bonusNumber;
    }
}
