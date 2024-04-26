package jobportal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ " OR p.brand LIKE %?1%"
//			+ " OR p.madein LIKE %?1%"
//			+ " OR CONCAT(p.price, '') LIKE %?1%")
	
//	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.applicationid, ' ',p.jobdesignation, ' ', p.department, ' ', p.salary, ' ', p.location,' ', p.noticepriod,' ', p.jobtype,' ', p.jobdescription) LIKE %?1%")
	public List<Product> search(String keyword);
	
//	Optional<Product> findById(long id);
	
}