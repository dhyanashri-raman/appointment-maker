package clinic;

/**
 * The MedicalRecord class is responsible for managing a collection of Patient objects.
 * It provides functionality to add new patients, retrieve patient information,
 * and check for the existence of patients in the records.
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size;

    /**
     * Retrieves the array of Patient objects.
     *
     * @return An array of Patient objects
     */
    public Patient[] getPatientArray() {
        return patients;
    }

    /**
     * Retrieves the current number of patients in the array.
     *
     * @return The size of the patient array
     */
    public int getPatientArrSize() {
        return size;
    }

    /**
     * Default constructor that initializes the patient array with a capacity of 4
     * and sets the initial size to 0.
     */
    public MedicalRecord() {
        patients = new Patient[4];
        size = 0;
    }

    /**
     * Increases the size of the patient array when it is full.
     * The new size will be the current size plus 4.
     */
    private void grow() {
        if(size == patients.length) {
            patients = new Patient[size + 4];
        }
    }

    /**
     * Retrieves a Patient object from the array at a specified index.
     *
     * @param i The index of the patient to retrieve
     * @return The Patient object at the specified index, or null if the array is empty
     */
    public Patient getPatient(int i) {
        if(size != 0) {
            return patients[i];
        }
        return null;
    }

    /**
     * Adds a new Patient object to the array. If the array is full, it will increase
     * the array size and then add the new patient.
     *
     * @param newPatient The Patient object to be added
     */
    public void add(Patient newPatient) {
        if(size != patients.length) {
            patients[size] = newPatient;
            size++;
            return;
        } else {
            grow();
            add(newPatient);
        }
    }

    /**
     * Checks if a patient with the specified profile exists in the array.
     *
     * @param patient The Profile object of the patient to check
     * @return The index of the patient if found, otherwise -1
     */
    public int containsPatient(Profile patient) {
        for (int i = 0; i < size; i++) {
            if(patients[i].getProfileThroughPatient().equals(patient)) {
                return i;
            }
        }
        return -1;
    }
}
