/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation;

import com.avaje.ebean.validation.ValidatorMeta;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.LengthValidatorFactory;

/**
 * 
 * @文字列の長さのバリデーション
 * ebeanのモノをそのまま利用
 * Length(min = 0, max = 10)
 */
@ValidatorMeta(factory=LengthValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

	/**
	 * The minimum length of a String. Defaults to 0.
	 * <p>
	 * Checks that the string length is greater than or equal to min.
	 * </p>
	 */
	int min() default 0;

	/**
	 * The maximum length of a String. Defaults to Integer.MAX_VALUE.
	 * <p>
	 * Checks that the string length is less than or equal to max.
	 * </p>
	 */
	int max() default Integer.MAX_VALUE;
}
