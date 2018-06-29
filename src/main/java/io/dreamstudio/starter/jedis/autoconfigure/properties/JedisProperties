package io.dreamstudio.starter.jedis.autoconfigure.properties;

import io.dreamstudio.starter.jedis.util.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author Ricky Fung
 */
@ConfigurationProperties(prefix = Constants.PREFIX)
public class JedisProperties {

    /**
     * Database index used by the connection factory.
     */
    private int database = Constants.DEFAULT_DATABASE;

    /**
     * Redis server host.
     */
    private String host = Constants.DEFAULT_HOST;

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = Constants.DEFAULT_PORT;

    /**
     * Enable SSL.
     */
    private boolean ssl;

    /**
     * Connection timeout in milliseconds.
     */
    private Integer timeout;

    private JedisPoolConfig pool;

    private SentinelConfig sentinel;

    private ClusterConfig cluster;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public JedisPoolConfig getPool() {
        return pool;
    }

    public void setPool(JedisPoolConfig pool) {
        this.pool = pool;
    }

    public SentinelConfig getSentinel() {
        return sentinel;
    }

    public void setSentinel(SentinelConfig sentinel) {
        this.sentinel = sentinel;
    }

    public ClusterConfig getCluster() {
        return cluster;
    }

    public void setCluster(ClusterConfig cluster) {
        this.cluster = cluster;
    }

    /**
     * Redis sentinel properties.
     */
    public static class SentinelConfig {

        /**
         * Name of Redis server.
         */
        private String master;

        /**
         * Comma-separated list of "host:port" pairs.
         */
        private List<String> nodes;

        public String getMaster() {
            return this.master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

    }


    /**
     * Cluster properties.
     */
    public static class ClusterConfig {

        /**
         * Comma-separated list of "host:port" pairs to bootstrap from. This represents an
         * "initial" list of cluster nodes and is required to have at least one entry.
         */
        private List<String> nodes;

        /**
         * Maximum number of redirects to follow when executing commands across the
         * cluster.
         */
        private Integer maxRedirects;

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public Integer getMaxRedirects() {
            return this.maxRedirects;
        }

        public void setMaxRedirects(Integer maxRedirects) {
            this.maxRedirects = maxRedirects;
        }

    }
}
