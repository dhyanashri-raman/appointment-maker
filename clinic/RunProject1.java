/**
 * The RunProject1 class serves as the entry point to the clinic scheduling application.
 * It initializes and runs the Scheduler class, which handles the scheduling operations.
 *
 * @version 1.0
 * @since 2024-09-30
 */
package clinic;

public class RunProject1 {
    /**
     * The main method that starts the clinic scheduling application.
     * It creates an instance of the Scheduler class and calls its run method.
     *
     * @param args   command-line arguments passed to the program (not used).
     */
    public static void main(String[] args) {
        new Scheduler().run();
    }
}