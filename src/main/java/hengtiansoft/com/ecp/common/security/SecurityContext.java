/*
 * Project Name: ecp-trunk
 * File Name: PageSecurityContext.java
 * Class Name: PageSecurityContext
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

package hengtiansoft.com.ecp.common.security;

import org.springframework.stereotype.Component;

/**
 * Class Name: PageSecurityContext Description: TODO
 * 
 * @author SC
 * 
 */
@Component
public class SecurityContext extends BaseSecurityContext {

    public static SecurityContext getInstance() {
        return new SecurityContext();
    }

}
