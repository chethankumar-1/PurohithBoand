package in.purohith.Purohith.Model;

import jakarta.persistence.*;

@Entity
@Table(name="PRIEST")
public class PriestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRIESTID")
    private Long priestId;

    private String name;
    private String phone;
    private String email;
    private Integer experience;
    private String specialization;
    private String city;
    private String status;
    
	public Long getPriestId() {
		return priestId;
	}
	public void setPriestId(Long priestId) {
		this.priestId = priestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    // getters and setters
    
}