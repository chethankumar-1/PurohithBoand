package in.purohith.Purohith.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.purohith.Purohith.Model.BookingModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingModel, Long> {
    // Find bookings for a priest on a specific date
    List<BookingModel> findByPriestAvailability_Priest_PriestIdAndBookingDate(Long priestId, LocalDate date);

    // Find bookings by user
    List<BookingModel> findByUser_UserId(Long userId);
    
    // 🔹 New method to find expired bookings
    List<BookingModel> findByEndTimeBeforeAndStatus(LocalDateTime time, String status);
}