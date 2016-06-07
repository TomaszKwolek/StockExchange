package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.StockPriceEntity;
import stockexchange.model.to.StockPriceTo;

@Component
public class StockPriceMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(StockPriceEntity.class, StockPriceTo.class).byDefault().register();
    }
}
