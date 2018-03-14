package hengtiansoft.com.ecp.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hengtiansoft.com.ecp.showcase.domain.Product;
import hengtiansoft.com.ecp.showcase.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/standard-code-dao-test.xml" })
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productJpaRepository;

    @Test
    public void testFindOneProduct() {
        Product product = productJpaRepository.findOne("FI-SW-01");
        assertNotNull(product);
    }
}
