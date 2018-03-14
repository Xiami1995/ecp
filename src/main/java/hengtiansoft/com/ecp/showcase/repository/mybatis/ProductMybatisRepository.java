package hengtiansoft.com.ecp.showcase.repository.mybatis;

import hengtiansoft.com.ecp.showcase.domain.Product;


/**
* Class Name: ProductMybatisRepository
* Description: TODO
* @author SC
*
*/
public interface ProductMybatisRepository {

    Product findOne(String productId);

}
