package io.dreamstudio.starter.jedis.util;

/**
 * @author Ricky Fung
 */
public class Constants {

    public static final String PREFIX = "redis";

    public static final String REDIS_HOST_KEY = "redis.host";

    public static final String REDIS_CLUSTER_NODES_KEY = "redis.cluster.nodes";
    public static final String REDIS_SENTINEL_NODES_KEY = "redis.sentinel.nodes";


    /** default value **/
    public static final int DEFAULT_DATABASE = 0;

    public static final String DEFAULT_HOST = "localhost";

    public static final int DEFAULT_PORT = 6379;

    public static final int DEFAULT_TIMEOUT = 2000;

    public static final int DEFAULT_MAX_REDIRECTS = 3;
}
