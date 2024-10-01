package clinic;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    Profile(String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public boolean dobValid() {
        if (!this.dob.isValidDate()) {
            return false;
        }
        else if (this.dob.isToday()) {
            return false;
        }
        else if (this.dob.isFutureDate()) {
            return false;
        }
        return true;
    }

    public String getFirstName() {
        return this.fname;
    }

    public String getLastName() {
        return this.lname;
    }

    public Date getDob() {
        return this.dob;
    }

    @Override
    public boolean equals(Object patientProfile)
    {
        if (patientProfile instanceof Profile)
        {
            Profile profile = (Profile) patientProfile;
            return (profile.fname.equals(this.fname)
                    && profile.lname.equals(this.lname)
                    && profile.dob.equals(this.dob));
        }
        return false;
    }

    @Override
    public int compareTo(Profile patientProfile)
    {
        // compare last names
        if(this.lname.compareTo(patientProfile.lname) < 0) {
            return -1;
        }
        else if(this.lname.compareTo(patientProfile.lname) > 0) {
            return 1;
        }
        // if they are equal, compare first names
        else if(this.fname.compareTo(patientProfile.fname) < 0){
            return -1;
        }
        else if(this.fname.compareTo(patientProfile.fname) > 0){
            return 1;
        }
        // if first names are equal compare dob
        else if(this.dob.compareTo(patientProfile.dob) < 0){
            return -1;
        }
        else if(this.dob.compareTo(patientProfile.dob) > 0) {
            return 1;
        }

        // if everything is the same return 0
        return 0;
    }

    @Override
    public String toString()
    {
        return fname + " " + lname + " " + dob.toString();
    }

    public static void main(String[] args) {
        Date date1 = new Date(2003, 11, 20);
        Profile profile1 = new Profile("John", "Doe", date1);
        Profile profile2 = new Profile("John", "Doe", date1);
        System.out.println(profile1.toString() + " compared to " + profile2.toString() + " is: " + profile1.compareTo(profile2));

        Date date2 = new Date(1997, 1, 18);
        Profile profile3 = new Profile("Jone", "Doe", date2);
        Profile profile4 = new Profile("John", "Zoe", date2);
        System.out.println(profile3.toString() + " compared to " + profile4.toString() + " is: " + profile3.compareTo(profile4));

        Date date3 = new Date(1984, 7, 24);
        Profile profile5 = new Profile("David", "Mit", date3);
        Profile profile6 = new Profile("Allen", "Sue", date3);
        System.out.println(profile5.toString() + " compared to " + profile6.toString() + " is: " + profile5.compareTo(profile6));

        Date date4 = new Date (2011, 8, 23);
        Date date5 = new Date (2012, 9, 18);
        Profile profile7 = new Profile("Minho", "Lee", date4);
        Profile profile8 = new Profile("Minho", "Lee", date5);
        System.out.println(profile7.toString() + " compared to " + profile8.toString() + " is: " + profile7.compareTo(profile8));

        Date date6 = new Date(1994, 1, 30);
        Profile profile9 = new Profile("Adam", "Sandler", date2);
        Profile profile10 = new Profile("Zack", "Gar", date2);
        System.out.println(profile9.toString() + " compared to " + profile10.toString() + " is: " + profile9.compareTo(profile10));

        Date date7 = new Date(1950, 6, 5);
        Profile profile12 = new Profile("Chris", "Bang", date7);
        Profile profile11 = new Profile("Peter", "Han", date7);
        System.out.println(profile11.toString() + " compared to " + profile12.toString() + " is: " + profile11.compareTo(profile12));

        Date date8 = new Date (2009, 8, 23);
        Date date9 = new Date (2008, 9, 18);
        Profile profile13 = new Profile("Jeff", "Brian", date8);
        Profile profile14 = new Profile("Jeff", "Brian", date9);
        System.out.println(profile13.toString() + " compared to " + profile14.toString() + " is: " + profile13.compareTo(profile14));

    }


}
