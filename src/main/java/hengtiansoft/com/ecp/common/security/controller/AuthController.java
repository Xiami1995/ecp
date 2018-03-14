/*
 * Project Name: ecp
 * File Name: AccountController.java
 * Class Name: AccountController
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
package hengtiansoft.com.ecp.common.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.CredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hengtiansoft.com.ecp.common.dto.ResultDto;
import hengtiansoft.com.ecp.common.dto.ResultDtoFactory;
import hengtiansoft.com.ecp.common.dto.annotation.OnValid;
import hengtiansoft.com.ecp.common.security.KaptchaSupport;
import hengtiansoft.com.ecp.common.security.SecurityContext;
import hengtiansoft.com.ecp.common.util.web.WebUtil;
import hengtiansoft.com.ecp.common.validation.ValidateException;
import hengtiansoft.com.ecp.showcase.dto.upstream.SignInDto;

/**
 * Class Name: AuthController
 * Description: authentication controller 
 * 
 * @author SC
 * 
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private transient KaptchaSupport kaptchaSupport;

    
    /**
    * Description: render login page
    *
    * @return
    */
    @RequestMapping(value = "/login")
    public String login() {
        return "auth/login";
    }

    
    /**
    * Description: login interface 
    *
    * @param signInDto
    * @param result
    * @param session
    * @return
    */
    @RequestMapping(value = "/login/authc", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto doLogin(@OnValid @RequestBody SignInDto signInDto, BindingResult result, HttpSession session) {
        if (!kaptchaSupport.validateCaptcha(signInDto.getCaptcha(), session)) {
            result.rejectValue("captcha", "error.invalid.captcha");
        }
        if (result.hasErrors()) {
            throw new ValidateException(result);
        }
        try {
            SecurityContext.login(signInDto.getUserName(), signInDto.getPassword());
        } catch (AccountException e) {
            result.rejectValue("userName", "error.invalid.username");
        } catch (CredentialsException e) {
            result.rejectValue("password", "error.invalid.password");
        }
        if (result.hasErrors()) {
            throw new ValidateException(result);
        }
        return ResultDtoFactory.toRedirect(WebUtil.getFullUrlBasedOn("/web/products"));
    }

    
    /**
    * Description: render captcha
    *
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        kaptchaSupport.captcha(request, response);
    }

}
