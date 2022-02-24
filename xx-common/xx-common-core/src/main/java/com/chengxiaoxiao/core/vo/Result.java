package com.chengxiaoxiao.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.chengxiaoxiao.core.constant.Constant.CODE_SUCCESS;

/**
 * Result
 *
 * @Description: 统一的结果返回类
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("统一结果返回类")
public class Result<T> implements Serializable {
    public static final String OPERATE_SUCCESS = "操作成功";
    @ApiModelProperty("错误码")
    private String code;
    @ApiModelProperty("消息提示")
    private String msg;
    @ApiModelProperty("数据实体")
    private T data;

    private Result(T data) {
        this.code = "0";
        this.msg = "请求成功";
        this.data = data;
    }

    /**
     * 执行成功的回调-用于操作处理
     *
     * @return 请求成功的数据信息
     */
    public static Result<?> success() {
        Result<Object> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        result.setMsg(OPERATE_SUCCESS);
        return result;
    }

    /**
     * 成功时的回调
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 结果对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(CodeMsg codeMsg) {
        if (codeMsg == null) {
            codeMsg = CodeMsg.SUCCESS;
        }
        return new Result<>(codeMsg.getCode(), codeMsg.getMsg(), null);
    }


    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * 错误的回调
     *
     * @param cm  错误码
     * @param <T> 泛型
     * @return 结果对象
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }
}
