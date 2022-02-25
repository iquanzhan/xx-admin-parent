package com.chengxiaoxiao.core.validator.validator;


import com.chengxiaoxiao.core.validator.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 电话号码校验器
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date: 2022-02-25 22:28
 * @Version 1.0
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.trim().length() >= 12;
    }
}
