package clinic;

public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot; 
    private Profile patient;
    private Provider provider;

    // Constructor
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    @Override
    public int compareTo(Appointment appt) { // ASK ABOUT THIS
        int dateComparison = this.date.compareTo(appt.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (dateComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(appt.timeslot);
            return timeslotComparison;
        }

        return dateComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

public int compareByLocation(Appointment appt) {
    // Compare counties first
    int locationComparison = this.provider.getLocation().getCounty().compareTo(appt.provider.getLocation().getCounty());
    if (locationComparison != 0) {
        return locationComparison;
    }

    // If counties are equal, compare dates
    int dateComparison = this.date.compareTo(appt.date);
    if (dateComparison != 0) {
        return dateComparison;
    }

    // If both counties and dates are equal, compare the timeslot
    return this.timeslot.compareTo(appt.timeslot);
}


    public int compareByPatient(Appointment appt) {
        // Compare by last name
        int lastNameComparison = this.patient.getLastName().compareTo(appt.patient.getLastName());
        // If last names are equal, compare by first name
        if (lastNameComparison == 0) {
            int firstNameComparison = this.patient.getFirstName().compareTo(appt.patient.getFirstName());
            // If first names are equal, compare by date of birth
            if (firstNameComparison == 0) {
                int dobComparison = this.patient.getDob().compareTo(appt.patient.getDob());
                // If date of birth is also equal, compare by appointment date and time
                if (dobComparison == 0) {
                    int dateComparison = this.date.compareTo(appt.date);

                    // If dates are equal, compare by timeslot
                    if (dateComparison == 0) {
                        return this.timeslot.compareTo(appt.timeslot);
                    }
                    return dateComparison;
                }
                return dobComparison;
            }
            return firstNameComparison;
        }
        return lastNameComparison;
    }

    public int compareByAppointment(Appointment appt) {
        int timeslotComparison = this.date.compareTo(appt.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (timeslotComparison == 0) {
            int providerComparison = this.provider.compareTo(appt.provider); // is this because i didnt make Timeslot class yet?
            return providerComparison;
        }

        return timeslotComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Appointment) { // checks to make sure the object o is of type Appointment
            Appointment appt = (Appointment) object;

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
        return this.date.toString() + " " + this.timeslot.toString() + " " + this.patient.toString() + " [" + this.provider.getProvider().toUpperCase() + ", " + this.provider.getLocation() + ", " + this.getLocationByProvider().getCounty() + " " + this.getLocationByProvider().getZip() + ", " + this.getSpecialtyByProvider() + "]";
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }


    public Date getDate() {
        return this.date;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    public Profile getProfile() {
        return this.patient;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Location getLocationByProvider() {
        return this.provider.getLocation();
    }

    public Specialty getSpecialtyByProvider() {
        return this.provider.getSpecialty();
    }

}