package io.dreamstudio.starter.jedis.autoconfigure;

import io.dreamstudio.starter.jedis.annotation.EnableJedisClient;
import io.dreamstudio.starter.jedis.autoconfigure.properties.JedisClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author Ricky Fung
 */
@Configuration
@ConditionalOnClass({Jedis.class})
@ConditionalOnBean(annotation = EnableJedisClient.class)
@EnableConfigurationProperties(JedisClientProperties.class)
public class JedisAutoConfiguration {

}
