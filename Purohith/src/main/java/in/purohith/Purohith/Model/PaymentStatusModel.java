package in.purohith.Purohith.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="PAYMENT_STATUS")
public class PaymentStatusModel {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long paymentId;

	    @ManyToOne
	    @JoinColumn(name = "BOOKING_ID")
	    private BookingModel booking;

	    private String paymentStatus; // PENDING, SUCCESS, FAILED
	    private String paymentMethod; // PAYPAL, CARD, UPI etc.
	    private String transactionId; // PayPal orderId or gateway transaction ID
	    private Double amount;
		public Long getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(Long paymentId) {
			this.paymentId = paymentId;
		}
		public BookingModel getBooking() {
			return booking;
		}
		public void setBooking(BookingModel booking) {
			this.booking = booking;
		}
		public String getPaymentStatus() {
			return paymentStatus;
		}
		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}

}
