package io.dreamstudio.starter.jedis.annotation;

import java.lang.annotation.*;

/**
 * @author Ricky Fung
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableJedisClient {

}
