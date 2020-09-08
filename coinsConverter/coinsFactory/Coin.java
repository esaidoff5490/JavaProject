package coinsConverter.coinsFactory;
public abstract class Coin implements ICalculate {
    public abstract double calculate (double amount);
    public abstract double getValue();

}
