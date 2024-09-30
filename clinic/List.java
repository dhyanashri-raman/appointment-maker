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
        //make sure the appointment is not taken
        if(!contains(appointment)) {
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
        for (int i=0; i<size - 1; i++) {
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareTo(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        for (int i=0; i<size; i++) {
            System.out.print(appointments[i] + ", ");
        }
    }
    //ordered by county, date/timeslot
    public void printByLocation() {
        //we need to use the compareTo method in profile to compare the values
        for (int i=0; i<size - 1; i++) {
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareByLocation(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        for (int i=0; i<size; i++) {
            System.out.print(appointments[i] + ", ");
        }
    }
    //ordered by date/timeslot, provider name
    public void printByAppointment() {
        //we need to use the compareTo method in profile to compare the values
        boolean didSwap;

        for (int i=0; i<size-1; i++) {
            didSwap = false;
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareByAppointment(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        for (int i=0; i<size; i++) {
            System.out.print(appointments[i] + ", ");
        }
    }

    public int timeslotTaken(Provider provider, Timeslot timeslot) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].getProvider().equals(provider) && appointments[i].getTimeslot().equals(timeslot)) {
                return i;
            }
        }
        return -1;
    }

    public boolean patientExists(Profile patient) {
        for (int i = 0; i<size; i++) {
            if (appointments[i].getProfile().equals(patient)) {
                return true;
            }
        }
        return false;
    }

    public boolean dateExists (Date date)
    {
        for(int i = 0; i < size; i++)
        {
            if(appointments[i].getDate().equals(date))
                return true;
        }
        return false;
    }
}