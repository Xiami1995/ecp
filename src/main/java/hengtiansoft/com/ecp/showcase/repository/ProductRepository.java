package hengtiansoft.com.ecp.showcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hengtiansoft.com.ecp.showcase.domain.Product;


/**
* Class Name: ProductRepository
* Description: TODO
* @author SC
*
*/
public interface ProductRepository extends JpaRepository<Product, String>,
		JpaSpecificationExecutor<Product> {

}