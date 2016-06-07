package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.CashPortfolioEntity;
import stockexchange.model.to.CashPortfolioTo;

@Component
public class CashPortfolioMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(CashPortfolioEntity.class, CashPortfolioTo.class).byDefault().register();
    }
}
