package clinic;
import java.text.DecimalFormat;


public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    int NOT_FOUND = -1;

    public List() {
        appointments = new Appointment[4];
        size = 0;
    }

    private int find(Appointment appointment) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    // finding appointment based on profile and date
    public int identifyAppointment(Profile profile, Date date, Timeslot timeslot) {
        //helper method
        for (int i =0; i<size; i++) {
            if (appointments[i].getProfile().equals(profile)) {
                if (appointments[i].getDate().equals(date)) {
                    if (appointments[i].getTimeslot().equals(timeslot)) {
                        return i;
                    }
                }
            }
        }
        return NOT_FOUND;
    }

    public Appointment getAppointment(int index) {
        return appointments[index];
    }

    public int getSize() {
        return size;
    }

    //helper method to increase the capacity by 4
    private void grow() {
        Appointment[] newAppointments = new Appointment[appointments.length + 4];
        for (int i = 0; i < size; i++) {
            newAppointments[i] = appointments[i];
        }
        appointments = newAppointments;
    }

    //check before add/remove
    public boolean contains(Appointment appointment) {
        if (appointment == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (appointments[i] != null && appointments[i].equals(appointment)) {
                return true;
            }
        }
        return false;
    }

    public void add(Appointment appointment) {
        if (size == appointments.length) {
            grow();
        }
        if (!contains(appointment)) {
            appointments[size] = appointment;
            size++;
        }
    }

    public void remove(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                for (int j = i; j < size - 1; j++) {
                    appointments[j] = appointments[j + 1];
                }
                appointments[size - 1] = null;
                size--;
                return;
            }
        }
    }

    public void printByAppointment() {
        System.out.println();
        System.out.println("** Appointments ordered by date/time/provider **");
        sortByAppointment();
        printAppointments();
        System.out.println("** end of list **");
    }

    public void printByPatient() {
        System.out.println();
        System.out.println("** Appointments ordered by patient/date/time **");
        sortByPatient();
        printAppointments();
        System.out.println("** end of list **");
    }

    public void printByLocation() {
        System.out.println();
        System.out.println("** Appointments ordered by county/date/time **");
        sortByLocation();
        printAppointments();
        System.out.println("** end of list **");
    }


    private void sortByAppointment() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByAppointment(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    private void sortByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareTo(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    private void sortByLocation() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByLocation(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    private void swapAppointments(int i, int j) {
        Appointment temp = appointments[i];
        appointments[i] = appointments[j];
        appointments[j] = temp;
    }

    private void printAppointments() {
        for (int i = 0; i < size; i++) {
            System.out.println(formatAppointment(appointments[i]));
        }
    }

    private String formatAppointment(Appointment app) {
        return String.format("%s %s %s %s %s %s",
                app.getDate(),
                app.getTimeslot(),
                app.getProfile().getFirstName(),
                app.getProfile().getLastName(),
                app.getProfile().getDob(),
                app.getProvider().toString().toUpperCase());
        }

        public int timeslotTaken(Provider provider, Timeslot timeslot) {
            for (int i = 0; i<size; i++) {
                if (appointments[i].getProvider().equals(provider) && appointments[i].getTimeslot().equals(timeslot)) {
                    return i;
                }
        }
        return -1;
    }

    public int timeslotTakenByPatient(Provider provider, Timeslot timeslot, Profile patient) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].getProvider().equals(provider) && appointments[i].getTimeslot().equals(timeslot) && appointments[i].getProfile().equals(patient)) {
                return i;
            }
        }
        return -1;
    }

    public int dateExists (Date date)
    {
        for(int i = 0; i < size; i++)
        {
            if(appointments[i].getDate().equals(date))
                return i;
        }
        return -1;
    }

//    public void createMedicalRecord(MedicalRecord record)
//    {
//        if(this.size > 0)
//        {
//            for(int i = 0; i < this.size; i++)
//            {
//                if(record.getPatientArrSize() == 0)
//                {
//                    Visit patientVisit = new Visit(appointments[i]);
//                    Patient newPatient = new Patient(appointments[i].getProfile(), patientVisit);
//                    record.add(newPatient);
//                }
//                else {
//                    if(record.getPatientArrSize() > 0 && record.containsPatient(appointments[i].getProfile()) != -1)
//                    {
//                        Visit newPatientVisit = new Visit(appointments[i]);
//                        record.getPatient(record.containsPatient(appointments[i].getProfile())).addVisit(newPatientVisit);
//                    }
//                    else {
//                        Visit patientVisit = new Visit(appointments[i]);
//                        Patient newPatient = new Patient(appointments[i].getProfile(), patientVisit);
//                        record.add(newPatient);
//                    }
//
//                }
//            }
//        }
//
//    }
//
//    public int getCharge(MedicalRecord record, Patient patientProfile)
//    {
//        Patient pat = record.getPatient(record.containsPatient(patientProfile.getProfileThroughPatient()));
//        return pat.getTotalCharge();
//    }
//
//    public String printCharge(MedicalRecord record)
//    {
//        String charges = "";
//        for(int i = 0; i<record.getPatientArrSize(); i++)
//        {
//            charges += record.getPatient(i).getProfileThroughPatient().toString() + ": $" + getCharge(record, record.getPatient(i)) + "\n";
//        }
//        return charges;
//    }


    public void printTotalCharges() {
        if (size == 0) {
            System.out.println("There are no appointments in the system.");
            return;
        }

        System.out.println("** Billing statement ordered by patient **");

        // First, sort appointments by patient
        sortAppointmentsByPatient();

        // Then, calculate and print total charges
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        int counter = 1;
        Profile currentProfile = null;
        int currentCharge = 0;

        for (int i = 0; i < size; i++) {
            Appointment appt = appointments[i];
            Profile profile = appt.getProfile();
            int charge = appt.getProvider().getSpecialty().getCharge();

            if (currentProfile == null || !currentProfile.equals(profile)) {
                // Print previous patient's total charge (if any)
                if (currentProfile != null) {
                    System.out.printf("(%d) %s [amount due: %s]%n",
                            counter++,
                            currentProfile.toString(),
                            df.format(currentCharge));
                }
                // Start new patient
                currentProfile = profile;
                currentCharge = charge;
            } else {
                // Add to current patient's charge
                currentCharge += charge;
            }
        }

        // Print last patient's total charge
        if (currentProfile != null) {
            System.out.printf("(%d) %s [amount due: %s]%n",
                    counter,
                    currentProfile.toString(),
                    df.format(currentCharge));
        }

        System.out.println("** end of list **");
    }

    private void sortAppointmentsByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].getProfile().compareTo(appointments[j + 1].getProfile()) < 0) {
                    // Swap appointments
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }
    }


}