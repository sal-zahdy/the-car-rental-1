/*
 * Person interface for TheCarRental system
 * Replaces the abstract Person class
 */
package TheCarRental;

public interface Person {
    
    // ============= GETTER METHODS =============
    String getName();
    String getId();
    String getPhoneNumber();
    String getEmail();
    
    // ============= SETTER METHODS =============
    void setName(String name);
    void setPhoneNumber(String phoneNumber);
    void setEmail(String email);
    
    // ============= COMMON BEHAVIOR =============
    /**
     * Display person information
     */
    String displayInfo();
    
    /**
     * Validate person data
     */
    boolean validateData();
    
    /**
     * toString method for displaying basic info
     */
    String toString();
}