
/*
* Project Name: ecp-trunk
* File Name: BaseException.java
* Class Name: BaseException
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
	
package hengtiansoft.com.ecp.common.exception;

import hengtiansoft.com.ecp.common.enumeration.EErrorCode;


/**
 * Class Name: BaseException
 * Description: TODO
 * @author SC
 *
 */

public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    protected final DisplayableError errorCode;
    
    
    /**
    * BaseException Constructor
    *
    */
    public BaseException(){
        super();
        errorCode = EErrorCode.COMM_INTERNAL_ERROR;
    }
    
    
    /**
    * BaseException Constructor
    *
    * @param error
    */
    public BaseException(DisplayableError error){
        this.errorCode = error;
    }
    
    
    /**
    * BaseException Constructor
    *
    * @param error
    * @param message
    */
    public BaseException(DisplayableError error, String message){
        super(message);
        this.errorCode = error;
        
    }
    
    
    /**
    * BaseException Constructor
    *
    * @param error
    * @param message
    * @param cause
    */
    public BaseException(DisplayableError error, String message, Throwable cause){
        super(message, cause);
        this.errorCode = error;
    }

    
    /**
    * Description: TODO
    *
    * @return
    */
    public DisplayableError getError() {
        return errorCode;
    }
}
