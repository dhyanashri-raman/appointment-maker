package clinic;

import java.util.Scanner;

public class Scheduler {
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    String [] splittedInput = input.split(",");
    List appts = new List();

    public void run() {
        System.out.println("Scheduler is running");
        while(!splittedInput[0].equals("Q")) {
            if (splittedInput[0].equals("S")) { // date, timeslot, patient's first name, last name, dob, provider's last name
                schedule(splittedInput); // taking all values except first one
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
        System.out.println("Scheduler terminated.");
    }

    public void schedule(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        String providerName = splittedInput[6];

        Specialty specialty = null;
        Location location = null;
        Provider provider = null;

        Date date = new Date(year, month, day);

        Timeslot timeslot = null;
        int time = Integer.parseInt(splittedInput[2]);
        if (time==1) {
            timeslot = Timeslot.SLOT1;
        }
        else if (time==2) {
            timeslot = Timeslot.SLOT2;
        }
        else if (time==3) {
            timeslot = Timeslot.SLOT3;
        }
        else if (time==4) {
            timeslot = Timeslot.SLOT4;
        }
        else if (time==5) {
            timeslot = Timeslot.SLOT5;
        }
        else if (time==6) {
            timeslot = Timeslot.SLOT6;
        }

        if(providerName.equals("Patel")) {
            provider = provider.Patel;
        }
        else if(providerName.equals("Lim")){
            provider = provider.Lim;
        }
        else if(providerName.equals("Zimnes")) {
            provider = provider.Zimnes;
        }
        else if(providerName.equals("Harper")) {
            provider = provider.Harper;
        }
        else if(providerName.equals("Kaur")) {
            provider = provider.Kaur;
        }
        else if(providerName.equals("Taylor")) {
            provider = provider.Taylor;
        }
        else if(providerName.equals("Ramesh")) {
            provider = provider.Ramesh;
        }
        else if(providerName.equals("Ceravolo")) {
            provider = provider.Ceravolo;
        }


        specialty = provider.getSpecialty();
        location = provider.getLocation();


        String firstName = splittedInput[3];
        String lastName = splittedInput[4];
        String [] dobString = splittedInput[5].split("/");
        int dobMonth = Integer.parseInt(dobString[0]);
        int dobDay = Integer.parseInt(dobString[1]);
        int dobYear = Integer.parseInt(dobString[2]);
        Date dobDate = new Date(year, month, day);
        Date dob = new Date(dobYear, dobMonth, dobDay);
        Profile profile = new Profile(firstName, lastName, dob);


        // date, timeslot, patient's first name, last name, dob, provider's last name
        Appointment appt = new Appointment(date, timeslot, profile, provider, location, specialty);

        appts.add(appt);
    }

    public void cancel(String input) {

    }

    public void reschedule(String input) {

    }

}