package cloud.nextsol.core.config;

import cloud.nextsol.core.client.UserClient;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeans {

    @Bean
    public UserClient userClient() {
        String target = System.getenv("USER_GRPC_TARGET");
        if (target == null || target.isEmpty()) target = "core-user:8181";
        return new UserClient(target);
    }

}
