package clinic;

public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array
    int NOT_FOUND = -1;

    public List() {
        appointments = new Appointment[4];
        size = 0;
    }

    // finding appointment based on profile and date
    private int find(Profile profile, Date date) {
        //helper method
        for (int i =0; i<appointments.length; i++) {
            if (appointments[i].getProfile().equals(profile)) {
                if (appointments[i].getDate().equals(date)) {
                    if (appointments[i].getTimeslot().equals(appointments.getTimeslot())) {
                        return i;
                    }
                }
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
        //make sure the appointment is not taken
        if(!appointments.contains(appointment)) {
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
        for (int i=0; i<appointments.length-1; i++) {
            for (int j = 0; j < i-1; j++) {
                if (appointments[j].compareTo(appointments[j+1])>0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        for (int i=0; i<appointments.length; i++) {
            System.out.print(appointments[i] + ", ");
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

        for (int i=0; i<appointments.length; i++) {
            System.out.print(appointments[i] + ", ");
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

        for (int i=0; i<appointments.length; i++) {
            System.out.print(appointments[i] + ", ");
        }
    }

    public void printByStatement() {
        for (int i=0; i<appointments.length-1; i++) {
            appointments[i].getProfile().getCharge();
        }

        for (int i= 0; i<appointments.length-1; i++) {

        }
        //DELETE THIS

        for (int i=0; i<appointments.length; i++) {
            System.out.print(appointments[i] + ", ");
        }

    }
}