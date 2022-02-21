package com.chengxiaoxiao.core.validator.validator;


import com.chengxiaoxiao.core.validator.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 列表值校验器
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/21 16:58
 * @Version 1.0
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    /**
     * 要包含的值信息
     */
    private final Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        for (int i : constraintAnnotation.value()) {
            set.add(i);
        }

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
