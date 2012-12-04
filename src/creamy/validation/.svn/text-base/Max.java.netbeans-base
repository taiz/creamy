/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation;

import com.avaje.ebean.validation.ValidatorMeta;
import creamy.validation.factory.MaxValidatorFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @数値の最大値のバリデーション
 */
@ValidatorMeta(factory=MaxValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Max {
    
    long max() default Long.MAX_VALUE;
    
}
