package hengtiansoft.com.ecp.showcase.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import hengtiansoft.com.ecp.common.validation.BaseValidator;
import hengtiansoft.com.ecp.showcase.domain.Product;
import hengtiansoft.com.ecp.showcase.service.ProductService;


/**
* Class Name: ProductExistenceValidator
* Description: Customized validator.
* @author SC
*
*/
public class ProductExistenceValidator extends BaseValidator implements
        ConstraintValidator<ProductExistenceCheck, String> {

    @Autowired
    private ProductService productService;

    @Override
    public void initialize(ProductExistenceCheck check) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Product product = productService.findProductById(value);
        return product == null;
    }

}
