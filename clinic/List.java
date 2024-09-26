package clinic;

public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    int NOT_FOUND = -1;
    private int find(Appointment appointment) {
        //helper method
        for (int i =0; i<appointments.length; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    //helper method to increase the capacity by 4
    private void grow() {
        if (size == appointments.length)
        {
            appointments = new Appointment[size+4];
        }
    }
    //check before add/remove
    public boolean contains(Appointment appointment)  {
        for(int i = 0; i < size; i++)
        {
            if (appointments[i].equals(appointment))
            {
                return true;
            }
        }
        return false;
    }
    public void add(Appointment appointment)  {
        //make sure the array is not full yet
        if(size != appointments.length) {
            appointments[size] = appointment;
            size++;
        }
        else
        {
            grow();
            add(appointment);
        }
    }
    public void remove(Appointment appointment) {
        for(int i = 0; i < size; i++)
        {
            if (appointments[i].equals(appointment))
            {
                appointments[i] = null;
                size--;
            }
        }

    }
    //ordered by patient profile, date/timeslot
    // sorting using the compareTo method ascending - ask about this just in case
    public void printByPatient() {
        //we need to use the compareTo method in profile to compare the values
        boolean didSwap;

        for (int i=0; i<appointments.length-1; i++) {
            didSwap = false;
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareTo(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }
    }
    //ordered by county, date/timeslot
    public void printByLocation() {
        //we need to use the compareTo method in profile to compare the values
        boolean didSwap;

        for (int i=0; i<appointments.length-1; i++) {
            didSwap = false;
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareByLocation(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }
    }
    //ordered by date/timeslot, provider name
    public void printByAppointment() {
        //we need to use the compareTo method in profile to compare the values
        boolean didSwap;

        for (int i=0; i<appointments.length-1; i++) {
            didSwap = false;
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareByAppointment(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }
    }
}
