/*
 * Project Name: ecp
 * File Name: EGender.java
 * Class Name: EGender
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

package hengtiansoft.com.ecp.showcase.enumeration;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import hengtiansoft.com.ecp.common.enumeration.PageEnum;
import hengtiansoft.com.ecp.common.util.PageEnumSerializer;

/**
 * Class Name: EProductCagetory
 * 
 * @author SC
 * 
 */
@JsonSerialize(using = PageEnumSerializer.class)
public enum EProductCagetory implements PageEnum {

    BIRDS("B", "Birds"), FISH("F", "Fish"), DOGS("D", "Dogs"), REPTILES("R", "Reptiles"), CATS("C", "Cats");

    private String code;

    private String text;

    EProductCagetory(String code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}
