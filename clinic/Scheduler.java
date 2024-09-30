package clinic;

import java.util.Scanner;

//CHECK FOR EMPTY LINES

public class Scheduler {
    List appts = new List();

    public void run() {
        System.out.println("Scheduler is running");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter input: ");
        String input = in.nextLine().trim();
        String [] splittedInput = input.split(",");

        System.out.println();
        while(true) {
            input = in.nextLine();
            splittedInput = input.split(",");
            if(input.isEmpty() || splittedInput[0] == null)
            {
                input = in.nextLine();
                splittedInput = input.split(",");
            }
            if(splittedInput[0].equals("Q"))
            {
                System.out.println("Scheduler terminated.");
                return;
            }
            if (splittedInput.length == 7 || splittedInput.length == 1) {
                if (splittedInput.length > 0 && !splittedInput[0].isEmpty()) {
                    String command = splittedInput[0];

                    switch (command) {
                        case "S":
                            schedule(splittedInput);
                            break;
                        case "C":
                            cancel(splittedInput);
                            break;
                        case "R":
                            reschedule(splittedInput);
                            break;
                        case "PA":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByAppointment();
                            }
                            break;
                        case "PP":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByPatient();
                            }
                            break;
                        case "PL":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByLocation();
                            }
                            break;
                        case "PS":
                            // Implement PS logic here
                            break;
                        default:
                            if (command.length() > 0 && Character.isLowerCase(command.charAt(0))) {
                                System.out.println("Invalid command.");
                            } else if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                return;
                            }
                            break;
                    }
                } else {

                }
            }
        }
    }

    public void schedule(String [] splittedInput) {
        String [] dateString = splittedInput[1].split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        String providerName = splittedInput[6];
        providerName = splittedInput[6].toLowerCase();
        providerName = Character.toString(Character.toUpperCase(providerName.charAt(0))) + providerName.substring(1);

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
            return;
        }
        if (date.onWeekend()) {
            System.out.println("Appointment date " + date.toString() + " is Saturday or Sunday.");
            return;
        }
        if (!date.isWithinSixMonths()) {
            System.out.println("Appointment date " + date.toString() + " is not within six months.");
            return;
        }

        Timeslot timeslot = null;
        int time;

        try {
            time = Integer.parseInt(splittedInput[2]);

            switch (time) {
                case 1:
                    timeslot = Timeslot.SLOT1;
                    break;
                case 2:
                    timeslot = Timeslot.SLOT2;
                    break;
                case 3:
                    timeslot = Timeslot.SLOT3;
                    break;
                case 4:
                    timeslot = Timeslot.SLOT4;
                    break;
                case 5:
                    timeslot = Timeslot.SLOT5;
                    break;
                case 6:
                    timeslot = Timeslot.SLOT6;
                    break;
                default:
                    System.out.println(time + " is not a valid time slot.");
                    return;
            }
        } catch (NumberFormatException e) {
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

        String firstName = splittedInput[3];
        String lastName = splittedInput[4];
        String [] dobString = splittedInput[5].split("/");
        int dobMonth = Integer.parseInt(dobString[0]);
        int dobDay = Integer.parseInt(dobString[1]);
        int dobYear = Integer.parseInt(dobString[2]);
        Date dob = new Date(dobYear, dobMonth, dobDay);
        Profile profile = new Profile(firstName, lastName, dob);

        if (!dob.isValidDate()) {
            System.out.println("Patient dob: " + dob.toString() + " is not a valid calendar date.");
            return;
        }

        if (dob.isFutureDate() || dob.isToday()) {
            System.out.println("Patient dob: " + dob.toString() + " is today or a date after today.");
            return;
        }

        // date, timeslot, patient's first name, last name, dob, provider's last name
        Appointment appt = new Appointment(date, timeslot, profile, provider);

        if (appts.timeslotTakenByPatient(provider, timeslot, profile) != -1 && appts.dateExists(date) != -1) {
            System.out.println(appts.getAppointment(appts.timeslotTakenByPatient(provider, timeslot, profile)).getProfile().toString() + " has an existing appointment at the same timeslot.");
            return;
        }
        else if (appts.timeslotTaken(provider, timeslot) != -1 && appts.dateExists(date) != -1) {
            Appointment app = appts.getAppointment(appts.timeslotTaken(provider, timeslot));
            System.out.println(app.getProvider().toString() + " is not available at slot " + splittedInput[2]);
            //System.out.println("[" + app.getProvider() + ", " + app.getProvider().getLocation() + ", " + app.getProvider().getLocation().getCounty() + " " + app.getProvider().getLocation().getZip() + ", " + app.getProvider().getSpecialty() + "] is not available at slot " + timeslot);
            return;
        }

        appts.add(appt);
        System.out.println(appt.toString() + " booked");
    }

    public void cancel(String [] splittedInput) {
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
            System.out.println(date.toString() + " " + timeslot1.toString() + " " + profile.toString() + " has been canceled.");
            return;
        }
        System.out.println(date.toString() + " " + timeslot1.toString() + " " + profile.toString() + " does not exist.");
    }
 
    public void reschedule(String [] splittedInput) {
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
            System.out.println(splittedInput[2] + " is not a valid time slot.");
            return;
        }

        Timeslot timeslot2 = null;
        int time2 = Integer.parseInt(splittedInput[6]);
        if (time2==1) {
            timeslot2 = Timeslot.SLOT1;
        }
        else if (time2==2) {
            timeslot2 = Timeslot.SLOT2;
        }
        else if (time2==3) {
            timeslot2 = Timeslot.SLOT3;
        }
        else if (time2==4) {
            timeslot2 = Timeslot.SLOT4;
        }
        else if (time2==5) {
            timeslot2 = Timeslot.SLOT5;
        }
        else if (time2==6) {
            timeslot2 = Timeslot.SLOT6;
        }
        else {
            System.out.println(splittedInput[6] + " is not a valid time slot.");
            return;
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
        int clashingIndex = appts.timeslotTaken(provider, timeslot2);

        if (apptIndex != -1) {
            if (clashingIndex!=-1) {
                System.out.println(appts.getAppointment(clashingIndex).getProfile().getFirstName() + " " + appts.getAppointment(clashingIndex).getProfile().getLastName() + " " + appts.getAppointment(clashingIndex).getProfile().getDob().toString() + " does not exist.");
                return;
            }
            else {
                appts.getAppointment(apptIndex).setTimeslot(timeslot2);
                System.out.println("Rescheduled to " + appts.getAppointment(apptIndex).toString());
            }
        }

    }

}