package in.purohith.Purohith.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.purohith.Purohith.Model.PriestModel;
import in.purohith.Purohith.Repository.PriestRepository;

import java.util.List;

@Service
public class PriestService {
	
	@Autowired
    private PriestRepository priestRepository;

    public PriestModel addPriest(PriestModel priest){
        return priestRepository.save(priest);
    }

    public List<PriestModel> getAllPriests(){
        return priestRepository.findAll();
    }

    public PriestModel getPriestById(Long id){
        return priestRepository.findById(id).orElse(null);
    }

}
