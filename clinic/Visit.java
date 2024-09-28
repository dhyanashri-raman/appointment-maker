package clinic;

public class Visit {
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    public void add(Visit visit) {
        this.next= visit;
    }

    public Appointment getAppointment () {
        return appointment;
        //comment
    }

    public Visit getVisit () {
        return next;
    }
}
