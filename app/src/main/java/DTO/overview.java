package DTO;

public class overview {
    private int sumOfMoveCal;
    private int sumOfEatCal;


    public overview() {
    }

    public overview(int sumOfMoveCal, int sumOfEatCal) {
        this.sumOfMoveCal = sumOfMoveCal;
        this.sumOfEatCal = sumOfEatCal;
    }

    public int getSumOfMoveCal() {
        return sumOfMoveCal;
    }

    public void setSumOfMoveCal(int sumOfMoveCal) {
        this.sumOfMoveCal = sumOfMoveCal;
    }

    public int getSumOfEatCal() {
        return sumOfEatCal;
    }

    public void setSumOfEatCal(int sumOfEatCal) {
        this.sumOfEatCal = sumOfEatCal;
    }
}