package monopoly;

public class MockRespondDialog implements RespondDialog {
    public MockRespondDialog(TradeDeal deal) {}

    @Override
    public boolean getResponse() {
        return true;
    }
}
