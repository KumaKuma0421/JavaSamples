package Annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

/**
 * InjectionInfo<br>
 * メソッド検索用に定義します。
 */
@java.lang.annotation.Retention(RUNTIME)
@Target({ METHOD })
public @interface InjectionInfo {
    int stream() default 0;

    int function() default 0;
}