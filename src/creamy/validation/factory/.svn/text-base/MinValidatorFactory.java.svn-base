/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation.factory;

import com.avaje.ebean.validation.factory.NoAttributesValidator;
import com.avaje.ebean.validation.factory.Validator;
import com.avaje.ebean.validation.factory.ValidatorFactory;
import creamy.validation.Min;
import java.lang.annotation.Annotation;

/**
 *
 * @数値の最小値のバリデーションfactory
 */
public class MinValidatorFactory implements ValidatorFactory {

    @Override
    public Validator create(Annotation annotation, Class<?> type) {
        if (!type.equals(Number.class)) {
            String msg = "You can only specify @Min on Number types";
            throw new RuntimeException(msg);
        }
        Min value = (Min) annotation;
        return create(value.min());
    }

    /**
     * Create or get a MinValidator single threaded.
     */
    public synchronized static Validator create(long min) {
        Validator validator = new MinValidator(min);
        return validator;
    }

    public static final class MinValidator extends NoAttributesValidator {

        private final long min;

        private MinValidator(long min) {
            this.min = min;
        }

        @Override
        public String getKey() {
            return "min";
        }

        @Override
        public boolean isValid(Object object) {
            if (object == null) {
                return true;
            }
            Number number = (Number) object;
            return number.longValue() >= min;
        }
    }
}
