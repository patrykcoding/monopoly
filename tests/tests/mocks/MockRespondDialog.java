package tests.mocks;

import monopoly.RespondDialog;
import monopoly.TradeDeal;

public class MockRespondDialog implements RespondDialog {
    public MockRespondDialog(TradeDeal deal) {}

    @Override
    public boolean getResponse() {
        return true;
    }
}
