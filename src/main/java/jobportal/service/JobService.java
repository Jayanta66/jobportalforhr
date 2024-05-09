package jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jobportal.beans.Job;
import jobportal.repository.JobRepository;

@Service
public class JobService {
	@Autowired
	private JobRepository repo;
	
//	@Override
	public List<Job> listAll() {
				return repo.findAll();
	}
	
	public List<Job> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}
	
	public void save(Job product) {
		repo.save(product);
	}
	
	public Job get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	

	
}
