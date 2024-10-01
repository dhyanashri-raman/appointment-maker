package clinic;
import java.text.DecimalFormat;

/**
 * Represents a list of appointments in the clinic.
 * Provides methods to manage, sort, and retrieve appointment information.
 */
public class List {
    private Appointment[] appointments;
    private int size;
    int NOT_FOUND = -1;

    /**
     * Constructs an empty list of appointments with an initial capacity.
     */
    public List() {
        appointments = new Appointment[4];
        size = 0;
    }

    /**
     * Finds the index of the specified appointment in the list.
     * @param appointment The appointment to find.
     * @return The index of the appointment if found, otherwise NOT_FOUND.
     */
    private int find(Appointment appointment) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Identifies an appointment based on the profile, date, and timeslot.
     * @param profile The profile associated with the appointment.
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @return The index of the identified appointment if found, otherwise NOT_FOUND.
     */
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

    /**
     * Retrieves the appointment at the specified index.
     * @param index The index of the appointment to retrieve.
     * @return The appointment at the specified index.
     */
    public Appointment getAppointment(int index) {
        return appointments[index];
    }

    /**
     * Returns the current number of appointments in the list.
     * @return The size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Increases the capacity of the appointments array by 4.
     */
    private void grow() {
        Appointment[] newAppointments = new Appointment[appointments.length + 4];
        for (int i = 0; i < size; i++) {
            newAppointments[i] = appointments[i];
        }
        appointments = newAppointments;
    }

    /**
     * Checks if the specified appointment is present in the list.
     * @param appointment The appointment to check.
     * @return True if the appointment exists, otherwise false.
     */
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

    /**
     * Adds a new appointment to the list if it does not already exist.
     * @param appointment The appointment to add.
     */
    public void add(Appointment appointment) {
        if (size == appointments.length) {
            grow();
        }
        if (!contains(appointment)) {
            appointments[size] = appointment;
            size++;
        }
    }

    /**
     * Removes the specified appointment from the list.
     * @param appointment The appointment to remove.
     */
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

    /**
     * Prints all appointments ordered by date, time, and provider.
     */
    public void printByAppointment() {
        System.out.println();
        System.out.println("** Appointments ordered by date/time/provider **");
        sortByAppointment();
        printAppointments();
        System.out.println("** end of list **");
    }

    /**
     * Prints all appointments ordered by patient, date, and time.
     */
    public void printByPatient() {
        System.out.println();
        System.out.println("** Appointments ordered by patient/date/time **");
        sortByPatient();
        printAppointments();
        System.out.println("** end of list **");
    }

    /**
     * Prints all appointments ordered by location, date, and time.
     */
    public void printByLocation() {
        System.out.println();
        System.out.println("** Appointments ordered by county/date/time **");
        sortByLocation();
        printAppointments();
        System.out.println("** end of list **");
    }

    /**
     * Sorts the appointments in the list by date, time, and provider using bubble sort.
     */
    private void sortByAppointment() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByAppointment(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the appointments in the list by patient using bubble sort.
     */
    private void sortByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByPatient(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the appointments in the list by location using bubble sort.
     */
    private void sortByLocation() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByLocation(appointments[j + 1]) > 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the appointments in the list by profile using bubble sort.
     */
    private void sortByProfile() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].getProfile().compareTo(appointments[j + 1].getProfile()) < 0) {
                    swapAppointments(j, j + 1);
                }
            }
        }
    }

    /**
     * Swaps two appointments in the list at specified indices.
     * @param i The index of the first appointment.
     * @param j The index of the second appointment.
     */
    private void swapAppointments(int i, int j) {
        Appointment temp = appointments[i];
        appointments[i] = appointments[j];
        appointments[j] = temp;
    }

    /**
     * Prints all appointments in the list.
     */
    private void printAppointments() {
        for (int i = 0; i < size; i++) {
            System.out.println(formatAppointment(appointments[i]));
        }
    }

    /**
     * Formats an appointment for display.
     * @param app The appointment to format.
     * @return A formatted string representing the appointment.
     */
    private String formatAppointment(Appointment app) {
        return String.format("%s %s %s %s %s %s",
                app.getDate(),
                app.getTimeslot(),
                app.getProfile().getFirstName(),
                app.getProfile().getLastName(),
                app.getProfile().getDob(),
                app.getProvider().toString().toUpperCase());
        }

        /**
         * Checks if a specified timeslot is already taken by a provider.
         * @param provider The provider to check.
         * @param timeslot The timeslot to check.
         * @return The index of the appointment if the timeslot is taken, otherwise -1.
         */
        public int timeslotTaken(Provider provider, Timeslot timeslot) {
            for (int i = 0; i<size; i++) {
                if (appointments[i].getProvider().equals(provider) && appointments[i].getTimeslot().equals(timeslot)) {
                    return i;
                }
            }
        return -1;
    }

    /**
     * Checks if a specified timeslot is taken by a patient with a given provider.
     * @param provider The provider to check.
     * @param timeslot The timeslot to check.
     * @param patient The patient to check.
     * @return The index of the appointment if the timeslot is taken, otherwise -1.
     */
    public int timeslotTakenByPatient(Provider provider, Timeslot timeslot, Profile patient) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].getProvider().equals(provider) && appointments[i].getTimeslot().equals(timeslot) && appointments[i].getProfile().equals(patient)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if there are any appointments on a specific date.
     * @param date The date to check.
     * @return The index of the appointment if it exists, otherwise -1.
     */
    public int dateExists (Date date)
    {
        for(int i = 0; i < size; i++)
        {
            if(appointments[i].getDate().equals(date))
                return i;
        }
        return -1;
    }

    /**
     * Prints all charges for appointments, ordered by patient.
     */
    public void printAllCharge() {
        if (size == 0) {
            System.out.println("There are no appointments in the system.");
            return;
        }
        System.out.println("** Billing statement ordered by patient **");
        sortByProfile();
        DecimalFormat formatDec = new DecimalFormat("$#,##0.00");
        int counter = 1;
        Profile currentProfile = null;
        int currentCharge = 0;

        for (int i = 0; i < size; i++) {
            Appointment appt = appointments[i];
            Profile profile = appt.getProfile();
            int charge = appt.getProvider().getSpecialty().getCharge();

            if (currentProfile == null || !currentProfile.equals(profile)) {
                if (currentProfile != null) {
                    System.out.printf("(%d) %s [amount due: %s]%n",
                            counter++,
                            currentProfile.toString(),
                            formatDec.format(currentCharge));
                }
                currentProfile = profile;
                currentCharge = charge;
            } else {
                currentCharge += charge;
            }
        }
        if (currentProfile != null) {
            System.out.printf("(%d) %s [amount due: %s]%n",
                    counter,
                    currentProfile.toString(),
                    formatDec.format(currentCharge));
        }
        System.out.println("** end of list **");
    }

}