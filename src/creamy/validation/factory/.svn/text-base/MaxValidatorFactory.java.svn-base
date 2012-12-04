/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.validation.factory;

import com.avaje.ebean.validation.factory.NoAttributesValidator;
import com.avaje.ebean.validation.factory.Validator;
import com.avaje.ebean.validation.factory.ValidatorFactory;
import creamy.validation.Max;
import java.lang.annotation.Annotation;

/**
 *
 * @数値の最大値のバリデーションfactory
 */
public class MaxValidatorFactory implements ValidatorFactory {

    @Override
    public Validator create(Annotation annotation, Class<?> type) {
        if (!type.equals(Number.class)) {
            String msg = "You can only specify @Max on Number types";
            throw new RuntimeException(msg);
        }
        Max value = (Max) annotation;
        return create(value.max());
    }

    /**
     * Create or get a MaxValidator single threaded.
     */
    public synchronized static Validator create(long max) {
        Validator validator = new MaxValidator(max);
        return validator;
    }

    public static final class MaxValidator extends NoAttributesValidator {

        private final long max;

        private MaxValidator(long max) {
            this.max = max;
        }
        
        @Override
        public String getKey() {
            return "max";
        }

        @Override
        public boolean isValid(Object object) {
            if (object == null) {
                return true;
            }
            Number number = (Number) object;
            return number.longValue() <= max;
        }
    }
}
