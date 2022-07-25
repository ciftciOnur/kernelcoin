package tr.edu.yeditepe.kernelcoin.infrastructure.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tr.edu.yeditepe.kernelcoin.domain.model.StompSessions.StompSessions;
import tr.edu.yeditepe.kernelcoin.domain.model.blockchain.BlockChain;

@Configuration
public class SingletonBeanConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public BlockChain blockChain() {
        return new BlockChain();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public StompSessions stompSessions() {
        return new StompSessions();
    }
}
