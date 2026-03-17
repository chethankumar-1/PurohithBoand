package in.purohith.Purohith.Repository;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	import in.purohith.Purohith.Model.RegistrationModel;
	import java.util.Optional;

	@Repository
	public interface LoginRepository extends JpaRepository<RegistrationModel, Long> {
	    Optional<RegistrationModel> findByUsername(String username);
	}

