package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.CurrencyRateEntity;
import stockexchange.model.to.CurrencyRateTo;

@Component
public class CurrencyRateMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(CurrencyRateEntity.class, CurrencyRateTo.class).byDefault().register();
    }
}
