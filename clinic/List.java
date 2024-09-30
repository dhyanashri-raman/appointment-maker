package clinic;

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
                if (appointments[j].compareByPatient(appointments[j + 1]) > 0) {
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
}