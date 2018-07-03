package io.dreamstudio.starter.jedis.autoconfigure;

import io.dreamstudio.starter.jedis.autoconfigure.properties.JedisProperties;
import io.dreamstudio.starter.jedis.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ricky Fung
 */
@Configuration
@ConditionalOnClass({Jedis.class})
@EnableConfigurationProperties(JedisProperties.class)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class JedisAutoConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisProperties properties;

    @Bean
    @ConditionalOnMissingBean(JedisCluster.class)
    @Conditional(ClusterCondition.class)
    public JedisCluster jedisCluster() {
        JedisProperties.ClusterConfig clusterConfig = properties.getCluster();
        Assert.notNull(clusterConfig, "cluster is null");

        List<String> list = clusterConfig.getNodes();
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : list) {
            logger.info("add redis cluster node:{}", node);
            String[] arr = node.split("\\:");
            nodes.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));
        }
        int timeout = properties.getTimeout() !=null ? properties.getTimeout() : Constants.DEFAULT_TIMEOUT;
        int maxAttempts = clusterConfig.getMaxRedirects() !=null ? clusterConfig.getMaxRedirects() : Constants.DEFAULT_MAX_REDIRECTS;
        JedisCluster jc = new JedisCluster(nodes,
                timeout, timeout, maxAttempts, properties.getPassword(), buildJedisPoolConfig(properties.getPool()));
        return jc;
    }

    @Bean
    @ConditionalOnMissingBean(JedisSentinelPool.class)
    @Conditional(SentinelCondition.class)
    public JedisSentinelPool jedisSentinelPool() {

        JedisProperties.SentinelConfig sentinelConfig = properties.getSentinel();
        Assert.notNull(sentinelConfig, "sentinel is null");

        String masterName = sentinelConfig.getMaster();
        //nodes
        Set<String> nodes = new HashSet<>();
        nodes.addAll(sentinelConfig.getNodes());

        int timeout = properties.getTimeout() !=null ? properties.getTimeout() : Constants.DEFAULT_TIMEOUT;
        JedisSentinelPool pool = new JedisSentinelPool(masterName, nodes,
                buildJedisPoolConfig(properties.getPool()), timeout, properties.getPassword(), properties.getDatabase());
        return pool;
    }

    @Bean
    @ConditionalOnMissingBean(JedisPool.class)
    @Conditional(StandaloneCondition.class)
    public JedisPool jedisPool() {
        int timeout = properties.getTimeout() !=null ? properties.getTimeout() : Constants.DEFAULT_TIMEOUT;
        JedisPool pool = new JedisPool(buildJedisPoolConfig(properties.getPool()), properties.getHost(), properties.getPort(),
                timeout, properties.getPassword(), properties.getDatabase());
        return pool;
    }

    public static class StandaloneCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

            String host = conditionContext.getEnvironment().getProperty(Constants.REDIS_HOST_KEY);
            if (host!=null && host.length()>0 && !Constants.DEFAULT_HOST.equals(host)) {
                return true;
            }
            return false;
        }
    }

    public static class SentinelCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

            return conditionContext.getEnvironment().containsProperty(Constants.REDIS_SENTINEL_NODES_KEY);
        }
    }

    public static class ClusterCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

            return conditionContext.getEnvironment().containsProperty(Constants.REDIS_CLUSTER_NODES_KEY);
        }
    }

    private JedisPoolConfig buildJedisPoolConfig(JedisProperties.PoolConfig config) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(config.getMaxTotal());
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMinIdle(config.getMinIdle());

        poolConfig.setTestWhileIdle(config.isTestWhileIdle());
        poolConfig.setTestOnBorrow(config.isTestOnBorrow());
        poolConfig.setTestOnReturn(config.isTestOnReturn());

        poolConfig.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        return poolConfig;
    }
}
