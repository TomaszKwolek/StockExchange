package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.StockPortfolioEntity;
import stockexchange.model.to.StockPortfolioTo;

@Component
public class StockPortfolioMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(StockPortfolioEntity.class, StockPortfolioTo.class).byDefault().register();
    }
}
