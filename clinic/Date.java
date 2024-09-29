package clinic;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isValid() {
        return isValidDate() && !isToday() && !isBeforeToday() && isWithinSixMonths() && !onWeekend();
    }

    // HELPER METHOD 0
    public boolean isValidDate() {
        // check the months that are 30 days
        // check the months that are leap years
        // check the months that are 31 days
        // make sure month is between 1 and 12
        // make sure day is >=1

        // checking to make sure year, month and day inputs are greater than 1
        if (this.year < 0 || (this.month < 0 || this.month > 12) || this.day < 0) {
            return false;
        }
        // checking to make sure all months with 31 days are <31, and same for months
        // with 30 days
        // also making sure if there is a leap year that february has 29 days and if
        // not, 28
        else {
            if (this.month == 2 && isLeapYear() && this.day > 29) {
                return false;
            } else if (this.month == 2 && !isLeapYear() && this.day > 28) {
                return false;
            }

            if (this.day > 31 && (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7
                    || this.month == 8 || this.month == 10 || this.month == 12)) {
                return false;
            }
            if (this.day > 30 && (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11)) {
                return false;
            }
        }
        return true;
    }

    // HELPER METHOD 1: checking if the year is a leap year
    public boolean isLeapYear() {
        boolean leapYearCheck;
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if (this.year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // HELPER METHOD 2: checking if the date is today's date
    public boolean isToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        return (this.year == calendar.get(Calendar.YEAR)
                && this.month == calendar.get(Calendar.MONTH)
                && this.day == calendar.get(Calendar.DAY_OF_MONTH));
    }

    // HELPER METHOD 3: checking to see if the date is before today
    public boolean isBeforeToday() {

        if (this.year < Calendar.YEAR) {
            return true;
        } else if (this.month < Calendar.MONTH) {
            return true;
        } else if (this.day < Calendar.DAY_OF_MONTH) {
            return true;
        }
        // if all these if statements are false that means the date is after the current
        // date so we return true
        return false;
    }

    // HELPER METHOD 4: checking if the date is on a saturday or sunday
    public boolean onWeekend() {
        // creating an appointment date with this object's year, month and day
        Calendar appointmentDate = new Calendar.Builder()
                .setDate(this.year, this.month - 1, this.day)
                .build();
        int dayofweek = appointmentDate.get(Calendar.DAY_OF_WEEK);
        return (dayofweek == Calendar.SATURDAY || dayofweek == Calendar.SUNDAY);
    }

    // HELPER METHOD 5: checking to make sure the date is within 6 months from
    // current date
    public boolean isWithinSixMonths() {
        // creating an appointment date with this object's year, month, and day
        Calendar appointmentDate = new Calendar.Builder()
                .setDate(this.year, this.month - 1, this.day)
                .build();
        // creating an appointment date 6 months from now
        Calendar sixMonthsLater = new Calendar.Builder()
                .setDate(this.year, this.month - 1 + 6, this.day)
                .build();
        return appointmentDate.before(sixMonthsLater);
    }

    // HELPER METHOD 6
    public boolean isFutureDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setLenient(false);
        Calendar date = new Calendar.Builder()
            .setDate(this.year, this.month-1, this.day)
            .build();

        return date.after(currentDate);
    }

    @Override
    public int compareTo(Date o) { // in which unit should this return, years, months or days?
        int yearComparison = this.year - o.year;
        int monthComparison = this.month - o.month;
        int dayComparison = this.day - o.day;

        if (yearComparison > 0) {
            return 1;
        } else if (yearComparison < 0) {
            return -1;
        }

        if (monthComparison > 0) {
            return 1;
        } else if (monthComparison < 0) {
            return -1;
        }

        // if the year and month are both equal, then we return the difference in the
        // days
        if (dayComparison > 0) {
            return 1;
        } else if (dayComparison < 0) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Date) { // checks to make sure the object o is of type Date
            Date dateEx = (Date) o;

            // making sure that each instance variable is not null and has the same value as
            // the comparing Date object
            return this.year == dateEx.year
                    && this.month == dateEx.month
                    && this.day == dateEx.day;
        }
        return false;
    }

    @Override
    public String toString() { // not sure if this is correct
        return this.month + "/" + this.day + "/" + this.year; // MM/DD/YY format
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public static void main (String[]args) {
        System.out.println("Testing Date class...");
    }
}