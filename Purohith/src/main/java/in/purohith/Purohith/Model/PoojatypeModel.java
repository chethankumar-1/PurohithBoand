package in.purohith.Purohith.Model;

import jakarta.persistence.*;

@Entity
@Table(name="POOJA_TYPES")
public class PoojatypeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Poojatype_Id;
	
	@Column(name="POOJA_NAME")
	private String poojaName;

    private String description;

    private Double price;

    private Integer duration;

    private String status;

	public Long getPoojatype_Id() {
		return Poojatype_Id;
	}

	public void setPoojatype_Id(Long poojatype_Id) {
		Poojatype_Id = poojatype_Id;
	}

	public String getPoojaName() {
		return poojaName;
	}

	public void setPoojaName(String poojaName) {
		this.poojaName = poojaName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
