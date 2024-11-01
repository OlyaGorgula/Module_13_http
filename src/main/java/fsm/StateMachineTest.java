package fsm;

import java.util.Scanner;
import java.util.SplittableRandom;

public class StateMachineTest {
    public static void main(String[] args) {
        StateMachine fsm = new StateMachine();

        fsm.addListener(((message, time) -> {
            System.out.println("listener called");
            System.out.println("Message: = "+message);
            System.out.println("Time: = "+time);
        }));

        Scanner scanner = new Scanner(System.in);

        while (true){
            String text = scanner.nextLine();
            fsm.handle(text);

        }

    }
}
