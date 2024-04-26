package jobportal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface JobRepository extends JpaRepository<Job, Long> {
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ " OR p.brand LIKE %?1%"
//			+ " OR p.madein LIKE %?1%"
//			+ " OR CONCAT(p.price, '') LIKE %?1%")
	
//	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")
	
	@Query("SELECT j FROM Job j WHERE CONCAT(j.applicationid, ' ',j.jobdesignation, ' ', j.department, ' ', j.salary, ' ', j.location,' ', j.noticepriod,' ', j.jobtype,' ', j.jobdescription) LIKE %?1%")
	public List<Job> search(String keyword);
	
//	Optional<Product> findById(long id);
	
}