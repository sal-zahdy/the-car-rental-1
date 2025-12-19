/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */
public abstract class Person {
    protected String name;
    protected String id;
    protected String phone_number;
    protected String email;

    public Person(String name, String id, String phone_number, String email) {
        this.name = name;
        this.id = id;
        this.phone_number = phone_number;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
