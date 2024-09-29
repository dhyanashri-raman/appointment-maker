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
                cancel(splittedInput);
            }
            else if (splittedInput[0].equals("R")) {
                reschedule(splittedInput);
            }
            else if (splittedInput[0].equals("PA")) {
                appts.printByAppointment();
            }
            else if (splittedInput[0].equals("PP")) {
                appts.printByPatient();
            }
            else if (splittedInput[0].equals("PL")) {
                appts.printByLocation();
            }
            // FIGURE OUT THIS ONE
            else if (splittedInput[0].equals("PS")) {
                appts.getProfile().getTotalCharge();
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

    

    public String cancel(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        String providerName = splittedInput[6];
    
    
        Specialty specialty = null;
        Location location = null;
        Provider provider = null;
    
    
        Date date = new Date(year, month, day);
    
    
        Timeslot timeslot1 = null;
        int time = Integer.parseInt(splittedInput[2]);
        if (time==1) {
            timeslot1 = Timeslot.SLOT1;
        }
        else if (time==2) {
            timeslot1 = Timeslot.SLOT2;
        }
        else if (time==3) {
            timeslot1 = Timeslot.SLOT3;
        }
        else if (time==4) {
            timeslot1 = Timeslot.SLOT4;
        }
        else if (time==5) {
            timeslot1 = Timeslot.SLOT5;
        }
        else if (time==6) {
            timeslot1 = Timeslot.SLOT6;
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
        // date, timeslot, patient's first name, last name, dob, provider's last name
    
    
        if (appts.identifyAppointment(profile, date, timeslot1) != -1)
        {
            int inptApp = appts.identifyAppointment(profile, date, timeslot1);
            Appointment currApp = appts.getAppointment(inptApp);
            Appointment appointment = new Appointment(currApp.getDate(), currApp.getTimeslot(), currApp.getProfile(), currApp.getProvider(), currApp.getLocation(), currApp.getSpecialty());
            appts.remove(appointment);
            return "Appointment Removed";
        }
        return "Unsuccessful: Appointment does not exist";
    }
 

    public String reschedule(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        String providerName = splittedInput[6];

        Specialty specialty = null;
        Location location = null;
        Provider provider = null;

        Date date = new Date(year, month, day);

        Timeslot timeslot1 = null;
        int time = Integer.parseInt(splittedInput[2]);
        if (time==1) {
            timeslot1 = Timeslot.SLOT1;
        }
        else if (time==2) {
            timeslot1 = Timeslot.SLOT2;
        }
        else if (time==3) {
            timeslot1 = Timeslot.SLOT3;
        }
        else if (time==4) {
            timeslot1 = Timeslot.SLOT4;
        }
        else if (time==5) {
            timeslot1 = Timeslot.SLOT5;
        }
        else if (time==6) {
            timeslot1 = Timeslot.SLOT6;
        }

        Timeslot timeslot2 = null;
        int time2 = Integer.parseInt(splittedInput[6]);
        if (time==1) {
            timeslot2 = Timeslot.SLOT1;
        }
        else if (time==2) {
            timeslot2 = Timeslot.SLOT2;
        }
        else if (time==3) {
            timeslot2 = Timeslot.SLOT3;
        }
        else if (time==4) {
            timeslot2 = Timeslot.SLOT4;
        }
        else if (time==5) {
            timeslot2 = Timeslot.SLOT5;
        }
        else if (time==6) {
            timeslot2 = Timeslot.SLOT6;
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


        // date, timeslot, patient's first name, last name, dob, provider's last name
        int apptIndex = appts.identifyAppointment(profile, date, timeslot1); // returns index where that appointment was found - having error with private
        Appointment appt = new Appointment(appts.getAppointment(apptIndex).getDate(), appts.getAppointment(apptIndex).getTimeslot(), appts.getAppointment(apptIndex).getProfile(), appts.getAppointment(apptIndex).getProvider(), appts.getAppointment(apptIndex).getLocation(), appts.getAppointment(apptIndex).getSpecialty());

        if (apptIndex != -1) {
            for (int i = 0; i < appts.getSize(); i++) {
                if (appts.getAppointment(i).getTimeslot().equals(timeslot2)) {
                    return "TIMESLOT TAKEN, CANNOT RESCHEDULE";
                }
            }
            appts.getAppointment(apptIndex).setTimeslot(timeslot2);
        }
        return "Rescheduled appointment!";
    }

}