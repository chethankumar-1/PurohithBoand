package in.purohith.Purohith.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "PRIEST_AVAILABILITY")
public class PriestAvailabilityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIEST_AVAILABILITY_ID")
    private Long priestAvailabilityId;

    @ManyToOne
    @JoinColumn(name = "PRIESTID")
    private PriestModel priest;

    @Column(name = "AVAILABLE_DATE")
    private LocalDate availableDate;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "STATUS")
    private String status;

	public Long getPriestAvailabilityId() {
		return priestAvailabilityId;
	}

	public void setPriestAvailabilityId(Long priestAvailabilityId) {
		this.priestAvailabilityId = priestAvailabilityId;
	}

	public PriestModel getPriest() {
		return priest;
	}

	public void setPriest(PriestModel priest) {
		this.priest = priest;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // getters and setters
}