package com.chengxiaoxiao.core.exception;


import cn.hutool.core.util.StrUtil;
import com.chengxiaoxiao.core.vo.CodeMsg;
import lombok.Getter;

/**
 * 统一异常
 *
 * @Description: 所有的异常都抛出此异常
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 16:33
 */
@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 7331906799009231998L;
    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(StrUtil.format("{}-{}",cm.getCode(),cm.getMsg()));
        this.cm = cm;
    }
}
