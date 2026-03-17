package in.purohith.Purohith.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.purohith.Purohith.Model.PriestAvailabilityModel;
import java.util.List;

public interface PriestAvailabilityRepository extends JpaRepository<PriestAvailabilityModel, Long>{

   List<PriestAvailabilityModel> findByPriest_PriestId(Long priestId);
}
