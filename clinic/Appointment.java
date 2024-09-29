package clinic;

public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot; // will the timeslot be in military time because there is no mention of AM or PM?
    private Profile patient;
    private Provider provider;
    private Location location;
    private Specialty specialty;

    // Constructor
    // Do we have to check to make sure input is right primitive type?
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider, Location location, Specialty specialty) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public int compareTo(Appointment o) { // ASK ABOUT THIS
        int dateComparison = this.date.compareTo(o.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (dateComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(o.timeslot); // is this because i didnt make Timeslot class yet?
            return timeslotComparison;
        }

        return dateComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    public int compareByLocation(Appointment o) {
        // comparing the counties first
        int locationComparison = this.location.getCounty().compareTo(o.provider.getLocation().getCounty());

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (locationComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(o.timeslot); // is this because i didnt make Timeslot class yet?
            return timeslotComparison;
        }

        return locationComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    public int compareByAppointment(Appointment o) { // ASK ABOUT THIS
        int timeslotComparison = this.date.compareTo(o.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (timeslotComparison == 0) {
            int providerComparison = this.provider.compareTo(o.provider); // is this because i didnt make Timeslot class yet?
            return providerComparison;
        }

        return timeslotComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Appointment) { // checks to make sure the object o is of type Appointment
            Appointment appt = (Appointment) o;

            // making sure that each instance variable is not null and has the same value as the comparing Appointment object
            return (this.date != null ? this.date.equals(appt.date) : appt.date == null)
                    && (this.timeslot != null ? this.timeslot.equals(appt.timeslot) : appt.timeslot == null)
                    && (this.patient != null ? this.patient.equals(appt.patient) : appt.patient == null)
                    && (this.provider != null ? this.provider.equals(appt.provider) : appt.provider == null);

        }
        return false;
    }

    @Override
    public String toString() {
        return "Date: " + this.date + " " + this.timeslot + " " + this.patient + " " + this.provider;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    public Profile getProfile() {
        return this.patient;
    }

    public Date getDate() {
        return this.date;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Specialty getSpecialty()
    {
        return this.specialty;
    }

    public static void main(String [] args) {
        for(int i = 0; i<5; i++) {
            //DELETE THIS
        }
    }


}