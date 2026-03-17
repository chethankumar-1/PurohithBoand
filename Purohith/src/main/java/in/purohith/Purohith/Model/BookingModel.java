package in.purohith.Purohith.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "BOOKINGS")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegistrationModel user;

    @ManyToOne
    @JoinColumn(name = "priest_availability_id")
    private PriestAvailabilityModel priestAvailability;

    @ManyToOne
    @JoinColumn(name = "pooja_type_id")
    private PoojatypeModel poojaType;

    private LocalDate bookingDate;
    private LocalDateTime  startTime;
    private LocalDateTime  endTime;

    private String status;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public RegistrationModel getUser() {
		return user;
	}

	public void setUser(RegistrationModel user) {
		this.user = user;
	}

	public PriestAvailabilityModel getPriestAvailability() {
		return priestAvailability;
	}

	public void setPriestAvailability(PriestAvailabilityModel priestAvailability) {
		this.priestAvailability = priestAvailability;
	}

	public PoojatypeModel getPoojaType() {
		return poojaType;
	}

	public void setPoojaType(PoojatypeModel poojaType) {
		this.poojaType = poojaType;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDateTime  getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime  startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime  getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime  endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@PrePersist
    public void prePersist() {
        if (this.bookingDate == null) {
            this.bookingDate = LocalDate.now();
        }
    }
}