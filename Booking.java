/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Vehicle vehicle;
    private final Date rentDate;
    private final Date returnDate;
    private final int days;
    private final int hours;
    private final int totalPrice;

    private Booking(String bookingId, User user, Vehicle vehicle, Date rentDate, Date returnDate, int days, int hours, int totalPrice) {
        this.bookingId = bookingId;
        this.user = user;
        this.vehicle = vehicle;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.days = days;
        this.hours = hours;
        this.totalPrice = totalPrice;
    }

    public static Booking createBooking(User user, Vehicle vehicle, int days, int hours) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        Date ret = cal.getTime();

        int price = vehicle.calculatePrice(days, hours);
        String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new Booking(id, user, vehicle, now, ret, days, hours, price);
    }

    public String getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public Date getRentDate() { return rentDate; }
    public Date getReturnDate() { return returnDate; }
    public int getDays() { return days; }
    public int getHours() { return hours; }
    public int getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return String.format("Booking[%s] Vehicle:%s | User:%s | Price:%d | Rent:%s | Return:%s",
                bookingId, vehicle.getModel() + " (" + vehicle.getCompany() + ")", user.toString(),
                totalPrice, rentDate.toString(), returnDate.toString());
    }
}
