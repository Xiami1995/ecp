/*
 * Project Name: ecp
 * File Name: ProductController.java
 * Class Name: ProductController
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hengtiansoft.com.ecp.showcase.controller;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hengtiansoft.com.ecp.common.constant.ApplicationConstant;
import hengtiansoft.com.ecp.common.controller.AbstractController;
import hengtiansoft.com.ecp.common.converter.ConverterService;
import hengtiansoft.com.ecp.common.dto.ResultDto;
import hengtiansoft.com.ecp.common.dto.ResultDtoFactory;
import hengtiansoft.com.ecp.common.dto.annotation.OnValid;
import hengtiansoft.com.ecp.common.dto.widget.DataTablesResponseDto;
import hengtiansoft.com.ecp.common.enumeration.EErrorCode;
import hengtiansoft.com.ecp.common.exception.BizServiceException;
import hengtiansoft.com.ecp.common.util.EnumHelper;
import hengtiansoft.com.ecp.common.util.web.WebUtil;
import hengtiansoft.com.ecp.showcase.domain.Product;
import hengtiansoft.com.ecp.showcase.dto.ProductDto;
import hengtiansoft.com.ecp.showcase.dto.ProductDto.CreateProduct;
import hengtiansoft.com.ecp.showcase.dto.upstream.ProductSearchDto;
import hengtiansoft.com.ecp.showcase.enumeration.EProductCagetory;
import hengtiansoft.com.ecp.showcase.service.ProductService;

/**
 * Class Name: ProductController Description: TODO
 * 
 * @author SC
 * 
 */
@Controller
public class ProductController extends AbstractController {

    @Autowired
    private transient ProductService productService;

    
    /**
    * Description: render home page
    *
    * @param model
    * @return
    */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("categoryList", EnumHelper.inspectConstants(EProductCagetory.class));
        return "product/product_list";
    }

    
    /**
    * Description: search product list on the home page
    *
    * @param request
    * @return
    */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public DataTablesResponseDto<Product> search(@RequestBody ProductSearchDto request) {
        DataTablesResponseDto<Product> resp = productService.searchProduct(request);
        resp.setEcho(request.getEcho());
        return resp;
    }

    
    /**
    * Description: render add-product page
    *
    * @param model
    * @return
    */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getAddPage(Model model) {
        model.addAttribute("categoryList", EnumHelper.inspectConstants(EProductCagetory.class));
        return "product/product_add";
    }

    
    /**
    * Description: add a product
    *
    * @param dto
    * @return
    */
    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseBody
    public ResultDto add(@RequestBody @OnValid(value = {CreateProduct.class, Default.class}) ProductDto dto) {
        Product entity = ConverterService.convert(dto, Product.class);
        productService.saveProduct(entity);
        return ResultDtoFactory.toRedirect(WebUtil.getFullUrlBasedOn(ApplicationConstant.GLOBAL_CONTEXT + "/products"));
    }

    
    /**
    * Description: render the detail page of a product
    *
    * @param id
    * @param model
    * @return
    */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable(value = "id") String id, Model model) {
        Product detail = productService.findProductById(id);
        if (detail == null) {
            throw new BizServiceException(EErrorCode.PRODUCT_NOT_FOUND);
        }
        model.addAttribute("detail", detail);
        model.addAttribute("categoryList", EnumHelper.inspectConstants(EProductCagetory.class));
        return "product/product_edit";
    }
    
    
    /**
    * Description: update a product
    *
    * @param id
    * @param dto
    * @return
    */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto update(@PathVariable(value = "id") String id, @RequestBody @OnValid ProductDto dto) {
        Product entity = ConverterService.convert(dto, Product.class);
        entity.setId(id);
        productService.saveProduct(entity);
        return ResultDtoFactory.toAck("successfully updated!");
    }

    
    /**
    * Description: delete a product
    *
    * @param id
    * @return
    */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDto delete(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResultDtoFactory.toAck("successfully deleted!");
    }

}
