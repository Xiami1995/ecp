package hengtiansoft.com.ecp.showcase.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import hengtiansoft.com.ecp.common.util.EnumHelper;
import hengtiansoft.com.ecp.showcase.enumeration.EProductCagetory;


/**
* Class Name: EProductCagetoryConverter
* Description: TODO
* @author SC
*
*/
@Converter
public class EProductCagetoryConverter implements AttributeConverter<EProductCagetory, String> {

    @Override
    public String convertToDatabaseColumn(EProductCagetory attribute) {
        return attribute.getCode();
    }

    @Override
    public EProductCagetory convertToEntityAttribute(String dbData) {
        return EnumHelper.translate(EProductCagetory.class, dbData);
    }

}
