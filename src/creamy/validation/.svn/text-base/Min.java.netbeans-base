/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation;

import com.avaje.ebean.validation.ValidatorMeta;
import creamy.validation.factory.MinValidatorFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @数値の最小値のバリデーション
 */
@ValidatorMeta(factory=MinValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Min {
    long min() default Long.MIN_VALUE;
}
