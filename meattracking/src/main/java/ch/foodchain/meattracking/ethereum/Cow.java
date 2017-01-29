package ch.foodchain.meattracking.ethereum;


public class Cow {

    private final int outsideTimeInSeconds;
    private final int insideTimeInSeconds;
    private final int stepCounter;
    private final int status;
    private final String farmAddress;

    public Cow(int outsideTimeInSeconds, int insideTimeInSeconds, int stepCounter, int status, String farmAddress) {
        this.outsideTimeInSeconds =outsideTimeInSeconds;
        this.insideTimeInSeconds = insideTimeInSeconds;
        this.stepCounter = stepCounter;
        this.status = status;
        this.farmAddress = farmAddress;
    }

    public int outsideTimeInSeconds() {
        return outsideTimeInSeconds;
    }

    public int insideTimeInSeconds() {
        return insideTimeInSeconds;
    }

    public int stepCounter() {
        return stepCounter;
    }

    public int status() {
        return status;
    }

    public String farmAddress() {
        return farmAddress;
    }
}
