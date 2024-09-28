package clinic;

import java.util.Scanner;

public class Scheduler {
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    String [] splittedInput = input.split(",");
    System.out.println("Scheduler is running");
    List appts = new List();

    public void run() {
        while(!splittedInput[0].equals("Q")) {
            if (splittedInput[0].equals("S")) { // date, timeslot, patient's first name, last name, dob, provider's last name
                schedule(input); // taking all values except first one
            }
            else if (splittedInput[0].equals("C")) {
                cancel(input);
            }
            else if (splittedInput[0].equals("R")) {
                reschedule(input);
            }
            else if (splittedInput[0].equals("PA")) {

            }
            else if (splittedInput[0].equals("PP")) {

            }
            else if (splittedInput[0].equals("PL")) {

            }
            else if (splittedInput[0].equals("PS")) {

            }
            input = in.nextLine();
            splittedInput = input.split(",");
        }
    }

    public void schedule(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        Date date = new Date(year, month, day);

        int time = Integer.parseInt(splittedInput[2]);
        if (time==1) {
            Timeslot slot1 = Timeslot.SLOT1;
        }
        else if (time==2) {
            Timeslot slot1 = Timeslot.SLOT2;
        }
        else if (time==3) {
            Timeslot slot1 = Timeslot.SLOT3;
        }
        else if (time==4) {
            Timeslot slot1 = Timeslot.SLOT4;
        }
        else if (time==5) {
            Timeslot slot1 = Timeslot.SLOT5;
        }
        else if (time==6) {
            Timeslot slot1 = Timeslot.SLOT6;
        }

        String firstName = splittedInput[3];
        String lastName = splittedInput[4];
        String [] dobString = splittedInput[5].split("/");
        int dobMonth = Integer.parseInt(dobString[0]);
        int dobDay = Integer.parseInt(dobString[1]);
        int dobYear = Integer.parseInt(dobString[2]);
        Date dobDate = new Date(year, month, day);
        Date dob = new Date(dobYear, dobMonth, dobDay);
        Profile profile = new Profile(firstName, lastName, dob);

        Appointment appt = new Appointment(date, timeslot, profile, );

        appts.add(appt);
    }

    public void cancel(String input) {

    }

    public void reschedule(String input) {

    }

}