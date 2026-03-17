package in.purohith.Purohith.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.purohith.Purohith.Model.PoojatypeModel;
import in.purohith.Purohith.Repository.PoojatypesRepository;

import java.util.List;

@Service
public class PoojaService {
	
	 @Autowired
	    private PoojatypesRepository repository;

	    public List<PoojatypeModel> getAllPoojas(){
	        return repository.findAll();
	    }

	    public List<PoojatypeModel> searchPooja(String name){
	        return repository.findByPoojaNameContainingIgnoreCase(name);
	    }

	    public PoojatypeModel addPooja(PoojatypeModel pooja){
	        return repository.save(pooja);
	    }

}
