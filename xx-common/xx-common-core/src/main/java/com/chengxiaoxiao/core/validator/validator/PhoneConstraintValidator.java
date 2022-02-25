package com.chengxiaoxiao.core.validator.validator;


import com.chengxiaoxiao.core.validator.annotation.Phone;
import org.apache.commons.lang3.StringUtils;

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
        if (StringUtils.isNotBlank(value)) {
            return value.trim().length() == 11;
        }
        return true;
    }
}
