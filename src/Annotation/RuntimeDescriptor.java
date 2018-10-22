package Annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

/**
 * RuntimeDescriptor<br>
 * クラスやメソッドに付加するアノテーションを定義します。
 */
@java.lang.annotation.Retention(RUNTIME) // 実行時にもアノテーション情報を残す
@Target({ TYPE, FIELD, CONSTRUCTOR, METHOD })
public @interface RuntimeDescriptor {
    String value() default "Unknown value.";

    String description() default "説明を入力してください。";
}