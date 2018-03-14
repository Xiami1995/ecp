package hengtiansoft.com.ecp.showcase.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hengtiansoft.com.ecp.common.dto.widget.DataTablesRequestDto;
import hengtiansoft.com.ecp.common.dto.widget.DataTablesResponseDto;
import hengtiansoft.com.ecp.common.util.EnumHelper;
import hengtiansoft.com.ecp.showcase.domain.Product;
import hengtiansoft.com.ecp.showcase.dto.upstream.ProductSearchDto;
import hengtiansoft.com.ecp.showcase.enumeration.EProductCagetory;
import hengtiansoft.com.ecp.showcase.repository.ProductRepository;
import hengtiansoft.com.ecp.showcase.repository.mybatis.ProductMybatisRepository;
import hengtiansoft.com.ecp.showcase.service.ProductService;

/**
 * Class Name: ProductServiceImpl
 * Description: service implementation
 * 
 * @author SC
 * 
 */
@Transactional(value = "jpaTransactionManager")
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMybatisRepository productMybatisRepository;

    @Cacheable(value = "productCache", key = "'PROD_' + #id")
    @Override
    public Product findProductById(String id) {
        // JPA
         return productRepository.findOne(id);

        // MyBatis
        // return productMybatisRepository.findOne(id);
    }

    @CacheEvict(value = "productCache", key = "'PROD_' + #product.id")
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(value = "productCache", key = "'PROD_' + #id")
    @Override
    public void deleteProduct(String id) {
        productRepository.delete(id);
    }

    @Override
    public DataTablesResponseDto<Product> searchProduct(final ProductSearchDto searchDto) {
        Pageable pageRequest = buildPageRequest(searchDto);

        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                List<Expression<Boolean>> expressions = predicate.getExpressions();

                if (searchDto != null) {
                    if (StringUtils.isNotBlank(searchDto.getKeyword())) {
                        expressions.add(cb.or(
                                cb.like(cb.lower(root.<String> get("name")), "%"
                                        + searchDto.getKeyword().trim().toLowerCase() + "%"),
                                cb.like(cb.lower(root.<String> get("description")), "%"
                                        + searchDto.getKeyword().trim().toLowerCase() + "%")));
                    }
                    if (StringUtils.isNotBlank(searchDto.getCategory())) {
                        expressions.add(cb.equal(root.<EProductCagetory> get("category"),
                                EnumHelper.translate(EProductCagetory.class, searchDto.getCategory())));
                    }
                }
                return predicate;
            }
        };

        Page<Product> products = productRepository.findAll(specification, pageRequest);

        DataTablesResponseDto<Product> responseDtoList = new DataTablesResponseDto<Product>();
        List<Product> itemList = new ArrayList<Product>();
        responseDtoList.setEcho(searchDto.getEcho());
        for (Product product : products) {
            itemList.add(product);
        }
        responseDtoList.setData(itemList);
        responseDtoList.setTotalDisplayRecords(products.getTotalElements());
        responseDtoList.setTotalRecords(products.getTotalElements());
        return responseDtoList;
    }

    private Pageable buildPageRequest(DataTablesRequestDto requestDto) {
        Sort sort = null;
        List<Integer> sortedColumns = requestDto.getSortedColumns();
        List<String> sortDirections = requestDto.getSortDirections();
        List<String> dataProps = requestDto.getDataProps();

        if (sortedColumns != null) {
            for (Integer item : sortedColumns) {
                String sortColumn = dataProps.get(item);
                int indexOf = 0;
                if (StringUtils.isNotBlank(sortColumn) && sortColumn.endsWith(".text")) {
                    indexOf = sortColumn.lastIndexOf(".text");
                }
                if (indexOf > 0) {
                    sortColumn = sortColumn.substring(0, indexOf);
                }
                String sortDir = sortDirections.get(0);
                sort = new Sort(Direction.fromString(sortDir), sortColumn);
            }
        } else {
            sort = new Sort(Direction.ASC, "id");
        }
        PageRequest page = new PageRequest(requestDto.getDisplayStart() / requestDto.getDisplayLength(),
                requestDto.getDisplayLength(), sort);
        return page;
    }

}
