package com.chengxiaoxiao.core.validator.annotation;

import com.chengxiaoxiao.core.validator.validator.PhoneConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否为电话号码验证
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-25 22:28
 */
@Documented
@Constraint(
        validatedBy = {PhoneConstraintValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "电话号码格式不正确";

    /**
     * 分组校验
     *
     * @return 分组
     */
    Class<?>[] groups() default {};

    /**
     * 载荷信息
     *
     * @return 载荷信息
     */
    Class<? extends Payload>[] payload() default {};
}

