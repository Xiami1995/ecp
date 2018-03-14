package hengtiansoft.com.ecp.showcase.repository.mybatis.typehandler;

import org.apache.ibatis.type.MappedTypes;

import hengtiansoft.com.ecp.showcase.enumeration.EProductCagetory;

@MappedTypes(EProductCagetory.class)
public class EProductCagetoryHandler extends EnumTypeHandler<EProductCagetory> {

    public EProductCagetoryHandler(Class<EProductCagetory> type) {
        super(type);
    }

}
