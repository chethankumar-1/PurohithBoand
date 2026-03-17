package in.purohith.Purohith.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.purohith.Purohith.Model.BookingModel;
import in.purohith.Purohith.Model.PoojatypeModel;
import in.purohith.Purohith.Model.PriestAvailabilityModel;
import in.purohith.Purohith.Model.RegistrationModel;
import in.purohith.Purohith.Repository.BookingRepository;
import in.purohith.Purohith.Repository.PoojatypesRepository;
import in.purohith.Purohith.Repository.PriestAvailabilityRepository;
import in.purohith.Purohith.Repository.RegisterRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PriestAvailabilityRepository priestAvailabilityRepository;

    @Autowired
    private RegisterRepository registrationRepository;

    // @Autowired
    // private PaymentRepository paymentRepository; // Payment not used

    // @Autowired
    // private PaypalService paypalService; // PayPal disabled

    @Autowired
    private PoojatypesRepository poojatypeRepository;

    @Transactional
    public BookingModel bookPriest(Long userId, Long priestAvailabilityId, Long poojaTypeId) throws Exception {

        RegistrationModel user = registrationRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        PriestAvailabilityModel availability = priestAvailabilityRepository
                .findById(priestAvailabilityId)
                .orElseThrow(() -> new RuntimeException("Priest availability not found"));

        if (!"AVAILABLE".equalsIgnoreCase(availability.getStatus())) {
            throw new RuntimeException("Priest is not available");
        }

        PoojatypeModel poojaType = poojatypeRepository.findById(poojaTypeId)
                .orElseThrow(() -> new Exception("Pooja type not found"));

        // 1️⃣ Create booking only
        BookingModel booking = new BookingModel();
        booking.setUser(user);
        booking.setPriestAvailability(availability);
        booking.setPoojaType(poojaType);
        booking.setStartTime(LocalDateTime.of(availability.getAvailableDate(), availability.getStartTime()));
        booking.setEndTime(LocalDateTime.of(availability.getAvailableDate(), availability.getEndTime()));
        booking.setStatus("BOOKED");
        bookingRepository.save(booking);

        // 2️⃣ Mark priest as booked
        availability.setStatus("BOOKED");
        priestAvailabilityRepository.save(availability);

        return bookingRepository.save(booking);
    }

    public List<BookingModel> getUserBookings(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    @Transactional
    public String cancelBooking(Long bookingId) throws Exception {
        BookingModel booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new Exception("Booking not found"));

        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new Exception("Booking already cancelled");
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        PriestAvailabilityModel availability = booking.getPriestAvailability();
        availability.setStatus("AVAILABLE");
        priestAvailabilityRepository.save(availability);

        return "Booking cancelled successfully";
    }

    // =======================
    // Payment methods commented
    // =======================
    /*
    @Transactional
    public Map<String, Object> bookPriestWithPayment(Long userId, Long priestAvailabilityId, Long poojaTypeId) { ... }

    @Transactional
    public boolean capturePayment(String orderId) { ... }
    */
}