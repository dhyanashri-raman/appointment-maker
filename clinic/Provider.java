package clinic;

/**
 * The Provider enum represents various healthcare providers, each associated
 * with a specific location and specialty.
 */
public enum Provider {
    Patel (Location.Bridgewater, Specialty.Family),
    Lim (Location.Bridgewater, Specialty.Pediatrician),
    Zimnes (Location.Clark, Specialty.Family),
    Harper (Location.Clark, Specialty.Family),
    Kaur (Location.Princeton, Specialty.Allergist),
    Taylor (Location.Piscataway, Specialty.Pediatrician),
    Ramesh (Location.Morristown, Specialty.Allergist),
    Ceravolo (Location.Edison, Specialty.Pediatrician);

    private final Location location;
    private final Specialty specialty;

    /**
     * Constructor: Initializes a Provider enum instance with the specified location
     * and specialty.
     *
     * @param location The location where the provider practices
     * @param specialty The specialty of the provider
     */
    Provider (Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * Method: Returns a string representation of the provider, including
     * its name, location, county, zip code, and specialty.
     *
     * @return A formatted string with provider details
     */
    @Override
    public String toString() {
        return "[" + this.name().toUpperCase() +
                ", " + this.location.toString().toUpperCase() +
                ", " + this.location.getCounty() +
                " " + this.location.getZip() +
                ", " + this.specialty + "]";
    }

    /**
     * Method: Retrieves the location of the provider.
     *
     * @return The Location object representing the provider's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Method: Retrieves the specialty of the provider.
     *
     * @return The Specialty object representing the provider's specialty
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Method: Retrieves the name of the provider as a string.
     *
     * @return The name of the provider
     */
    public String getProvider() {
        return this.name();
    }
}