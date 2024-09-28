package clinic;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    public Patient (Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    @Override
    public int compareTo(Patient patient) {
        if this.profile.equals(patient.profile) {

        }
    }

    @Override
    public boolean equals(Patient patient) {

    }

    @Override
    public String toString(Patient patient) {

    }

    //traverse the linked list to compute the charge
    public int charge() {
        int totalCharge = 0;
        Visit todaysVisit = this.visits;

        while (todaysVisit!=null) {
            totalCharge += todaysVisit.getAppointment().getCharge();
            todaysVisit = todaysVisit.next;
        }
        return totalCharge;
    }
}
