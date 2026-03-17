package in.purohith.Purohith.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import in.purohith.Purohith.Model.PoojatypeModel;

import java.util.List;

public interface PoojatypesRepository extends JpaRepository<PoojatypeModel, Long> {
	List<PoojatypeModel> findByPoojaNameContainingIgnoreCase(String poojaName);

}
