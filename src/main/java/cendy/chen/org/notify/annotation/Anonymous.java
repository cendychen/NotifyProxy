package cendy.chen.org.notify.annotation;

import java.lang.annotation.*;

/**
 * 不验权
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Anonymous {
}
