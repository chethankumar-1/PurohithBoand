package in.purohith.Purohith.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import in.purohith.Purohith.Model.RegistrationModel;

public interface RegisterRepository extends JpaRepository<RegistrationModel, Long> {
	Optional<RegistrationModel> findByUsername(String username);

}
