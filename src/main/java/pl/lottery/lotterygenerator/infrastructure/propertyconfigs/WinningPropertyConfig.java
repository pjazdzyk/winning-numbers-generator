package pl.lottery.lotterygenerator.infrastructure.propertyconfigs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.lottery.lotterygenerator.winningnumbergenerator.WinningPropertyConfigurable;

@Configuration
@ConfigurationProperties(prefix = "lotto.winning")
class WinningPropertyConfig implements WinningPropertyConfigurable {

    private int minWinNumber;
    private int maxWinNumber;
    private int winNumberCount;

    public int getMinWinNumber() {
        return minWinNumber;
    }

    public void setMinWinNumber(int minWinNumber) {
        this.minWinNumber = minWinNumber;
    }

    public int getMaxWinNumber() {
        return maxWinNumber;
    }

    public void setMaxWinNumber(int maxWinNumber) {
        this.maxWinNumber = maxWinNumber;
    }

    public int getWinNumberCount() {
        return winNumberCount;
    }

    public void setWinNumberCount(int winNumberCount) {
        this.winNumberCount = winNumberCount;
    }

    @Override
    public String toString() {
        return "WinningPropertyConfig{" +
                "minWinNumber=" + minWinNumber +
                ", maxWinNumber=" + maxWinNumber +
                ", winNumberCount=" + winNumberCount +
                '}';
    }
}
