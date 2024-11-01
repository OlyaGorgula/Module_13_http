package fsm;

public interface StateMachineListener {
    void onMessageAndTimeReceived(String message, int time);
}
