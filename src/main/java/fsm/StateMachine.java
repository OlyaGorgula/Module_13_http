package fsm;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {
    private State state = State.idle;
    private String message;
    private int time;

    private List<StateMachineListener> listeners = new ArrayList<>();

    public void addListener(StateMachineListener listener){
        listeners.add(listener);
    }

    public void handle(String text){
        if (text.equals("Додати нагадування")){
            onCreateNotificationPressed();
            return;
        }
        onTextReceived(text);

        try{
            int number = Integer.parseInt(text);
            onNumberReceived(number);
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    private void onCreateNotificationPressed(){
        System.out.println("onCreateNotificationPressed");
        if (state== State.idle){
            switchState(State.waitForMessage);
            //state = State.waitForMessage;
        }
    }

    private void onTextReceived(String text){
        System.out.println("onTextReceived text="+text);
        if (state== State.waitForMessage){
            message = text;
            switchState(State.waitFirTime);
        }
    }

    private void onNumberReceived(int number){
        System.out.println("onNumberReceived number = "+number);
        if (state== State.waitFirTime){
            this.time = number;
            switchState(State.idle);

            for (StateMachineListener listener: listeners){
                listener.onMessageAndTimeReceived(message, time);
            }
            //TODO Create notification for specific user
        }
    }

    private void switchState(State newState){

        System.out.println("Switch state "+state + " => "+newState);
        state = newState;
    }
}
