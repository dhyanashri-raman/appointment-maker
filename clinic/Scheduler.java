package clinic;

import java.util.Scanner;

//CHECK FOR EMPTY LINES

public class Scheduler {
    public Scheduler() {

    }
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    String [] splittedInput = input.split(",");
    List appts = new List();

    public void run() {
        System.out.println("Scheduler is running");
        if (splittedInput.length!=7) {
            while(!splittedInput[0].equals("Q")) {
                if (splittedInput[0].equals("S")) { // date, timeslot, patient's first name, last name, dob, provider's last name
                    schedule(splittedInput); 
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
                else if (splittedInput[0].equals("PS")) {
                    
                }
                else {
                    System.out.println("Invalid command.");
                }
                input = in.nextLine();
                splittedInput = input.split(",");
            }
        }
        else {
            System.out.println("Invalid command.");
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
        if (!date.isValidDate()) {
            System.out.println("Appointment date: " + date.toString() + " is not a valid calendar date.");
            return;
        }
        if (date.isToday() || date.isBeforeToday()) {
            System.out.println("Appointment date: " + date.toString() + " is today or a date before today.");
        }
        if (date.onWeekend()) {
            System.out.println("Appointment date " + date.toString() + " is Saturday or Sunday.");
        }
        if (!date.isWithinSixMonths()) {
            System.out.println("Appointment date " + date.toString() + " is not within six months.");
        }
    
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
        else {
            System.out.println(splittedInput[2] + " is not a valid time slot.");
            return; 
        }

        if(providerName.equals("Patel")) {
            provider = Provider.Patel;
        }
        else if(providerName.equals("Lim")){
            provider = Provider.Lim;
        }
        else if(providerName.equals("Zimnes")) {
            provider = Provider.Zimnes;
        }
        else if(providerName.equals("Harper")) {
            provider = Provider.Harper;
        }
        else if(providerName.equals("Kaur")) {
            provider = Provider.Kaur;
        }
        else if(providerName.equals("Taylor")) {
            provider = Provider.Taylor;
        }
        else if(providerName.equals("Ramesh")) {
            provider = Provider.Ramesh;
        }
        else if(providerName.equals("Ceravolo")) {
            provider = Provider.Ceravolo;
        }
        else {
            System.out.println(providerName + " - provider doesn't exist.");
            return; 
        }

        specialty = provider.getSpecialty();
        location = provider.getLocation();

        if (appts.timeslotTaken(provider, timeslot)!=-1) {
            System.out.println("Invalid command!");
            return;
        }

        String firstName = splittedInput[3];
        String lastName = splittedInput[4];
        String [] dobString = splittedInput[5].split("/");
        int dobMonth = Integer.parseInt(dobString[0]);
        int dobDay = Integer.parseInt(dobString[1]);
        int dobYear = Integer.parseInt(dobString[2]);
        Date dob = new Date(dobYear, dobMonth, dobDay);
        Profile profile = new Profile(firstName, lastName, dob);

        if (dob.isValidDate()) {
            System.out.println("Patient dob: " + dob.toString() + " is not a valid calendar date.");
            return;
        }

        if (dob.isFutureDate() || dob.isToday()) {
            System.out.println("Patient dob: " + dob.toString() + " is today or a date after today.");
            return;
        }

        if (appts.patientExists(profile)) {
            System.out.println("Invalid Command!");
            return;
        }


        // date, timeslot, patient's first name, last name, dob, provider's last name
        Appointment appt = new Appointment(date, timeslot, profile, provider);

        appts.add(appt);
        System.out.println(appt.toString() + " booked");
    }


    public String cancel(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
     
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
            Appointment appointment = new Appointment(currApp.getDate(), currApp.getTimeslot(), currApp.getProfile(), currApp.getProvider());
            appts.remove(appointment);
            return date.toString() + " " + timeslot1.toString() + " " + profile.toString() + " has been canceled.";
        }
        return date.toString() + " " + timeslot1.toString() + " " + profile.toString() + " does not exist.";
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
        else {
            return splittedInput[2] + " is not a valid time slot.";
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
        else {
            return splittedInput[2] + " is not a valid time slot.";
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
        Appointment appt = new Appointment(appts.getAppointment(apptIndex).getDate(), appts.getAppointment(apptIndex).getTimeslot(), appts.getAppointment(apptIndex).getProfile(), appts.getAppointment(apptIndex).getProvider());
        int clashingIndex = appts.timeslotTaken(provider, timeslot2);

        if (apptIndex != -1) {
            if (clashingIndex!=-1) {
                System.out.println(appts.getAppointment(clashingIndex).getProfile().getFirstName() + " " + appts.getAppointment(clashingIndex).getProfile().getLastName() + " " + appts.getAppointment(clashingIndex).getProfile().getDob().toString() + " has an existing appointment at the same time slot.");
            }
            else {
                appts.getAppointment(apptIndex).setTimeslot(timeslot2);
            }
        }
        return appt.toString() + " booked";
    }

}