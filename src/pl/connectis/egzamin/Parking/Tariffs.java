public enum Tariffs {

    UP_TO_6H(240),UP_TO_12H(480),UP_TO_24H(600),ABOVE_24H(1200);

    private int tariff;

    Tariffs(int tariff) {
        this.tariff = tariff;
    }

    public int getTariff() {
        return tariff;
    }


}