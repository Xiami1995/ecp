/*
 * Project Name: ecp
 * File Name: CategoryService.java
 * Class Name: CategoryService
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

package hengtiansoft.com.ecp.showcase.ws;

import javax.jws.WebService;

import hengtiansoft.com.ecp.showcase.domain.Product;

/**
 * Class Name: CategoryService Description: Just a sample
 * 
 * @author SC
 * 
 */

@WebService
public interface ProductWebService {

    Product getProductById(String productId);

}
