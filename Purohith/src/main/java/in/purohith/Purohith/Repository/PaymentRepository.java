package in.purohith.Purohith.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.purohith.Purohith.Model.PaymentStatusModel;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentStatusModel, Long> {
    List<PaymentStatusModel> findByPaymentStatus(String paymentStatus);
    List<PaymentStatusModel> findByBooking_BookingId(Long bookingId);
 // Find payment by PayPal order/transaction ID
    Optional<PaymentStatusModel> findByTransactionId(String transactionId);
}