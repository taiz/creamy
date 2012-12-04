/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation;

import com.avaje.ebean.validation.ValidatorMeta;
import com.avaje.ebean.validation.factory.EmailValidatorFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @ebeansのEmail Validationのwrap
 */
@ValidatorMeta(factory = EmailValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    
}
