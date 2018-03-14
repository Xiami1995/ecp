package hengtiansoft.com.ecp.showcase.service;

import hengtiansoft.com.ecp.common.dto.widget.DataTablesResponseDto;
import hengtiansoft.com.ecp.showcase.domain.Product;
import hengtiansoft.com.ecp.showcase.dto.upstream.ProductSearchDto;


/**
* Class Name: ProductService
* Description: TODO
* @author SC
*
*/
public interface ProductService {

    
    /**
    * Description: find a product
    *
    * @param id
    * @return
    */
    Product findProductById(String id);

    
    /**
    * Description: save a product
    *
    * @param product
    * @return
    */
    Product saveProduct(Product product);

    
    /**
    * Description: delete a product
    *
    * @param id
    */
    void deleteProduct(String id);

    
    /**
    * Description: search for products
    *
    * @param searchDto
    * @return
    */
    DataTablesResponseDto<Product> searchProduct(final ProductSearchDto searchDto);

}
