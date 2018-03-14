/*
 * Project Name: ecp
 * File Name: PageEnumSerializer.java
 * Class Name: PageEnumSerializer
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

package hengtiansoft.com.ecp.common.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import hengtiansoft.com.ecp.common.enumeration.PageEnum;

/**
 * Class Name: PageEnumSerializer Description: TODO
 * 
 * @author SC
 * 
 */

public class PageEnumSerializer extends SerializerBase<PageEnum> {

    public PageEnumSerializer(){
        this(PageEnum.class);
    }
    
    protected PageEnumSerializer(Class<PageEnum> t) {
        super(t);
    }

    @Override
    public void serialize(PageEnum value, JsonGenerator generator, SerializerProvider provider) throws IOException {

        generator.writeStartObject();
        generator.writeFieldName("code");
        generator.writeString(value.getCode());
        generator.writeFieldName("text");
        generator.writeString(value.getText());
        generator.writeFieldName("name");
        generator.writeString(value.name());
        generator.writeEndObject();
    }
}
