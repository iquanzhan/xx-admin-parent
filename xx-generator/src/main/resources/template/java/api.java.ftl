package ${cfg.basePackage!}.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ${cfg.basePackage!}.entity.${entity};
import java.util.List;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.core.vo.PageResult;
import ${cfg.basePackage!}.vo.query.${entity}Query;

<#--声明表名称-->
<#assign tableName="${table.comment?substring(0,table.comment?length-1)}"/>

/**
 * ${tableName} 接口Swagger Api
 *
 * @Description:
 * @Author: ${author}
 * @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Api(tags = "${tableName}模块", description = "实现对${tableName}数据的基本操作")
public interface ${entity}ControllerApi {


    /**
    * 分页及条件查询${tableName}信息
    * @param page 页码
    * @param size 分页大小
    * @param  ${entity?uncap_first}Query 查询条件
    * @return 分页结果信息
    */
    @ApiOperation(value = "分页及条件查询${tableName}信息")
    Result<PageResult<${entity}>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                              @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                              ${entity}Query ${entity?uncap_first}Query);

    /**
     * 根据ID获取${tableName}详情
     *
     * @param id ${tableName}ID
     * @return ${tableName}详细信息
     */
    @ApiOperation(value = "获取单个${tableName}详情")
    @ApiParam(name = "id", value = "${tableName}ID", required = true, type = "path")
    Result<${entity}> detail(String id);

    /**
     * 增加${tableName}
     *
     * @param ${entity?uncap_first} ${tableName}增加实体
     * @return 增加后的${tableName}信息
     */
    @ApiOperation(value = "增加${tableName}信息")
    Result<${entity}> add(${entity} ${entity?uncap_first});

    /**
     * 修改${tableName}
     *
     * @param id             ${tableName}ID
     * @param ${entity?uncap_first} ${tableName}修改实体
     * @return 修改后的${tableName}信息
     */
    @ApiOperation(value = "修改${tableName}信息")
    Result<${entity}> updateById(@ApiParam(name = "id", value = "${tableName}ID", type = "path") String id, ${entity} ${entity?uncap_first});

    /**
     * 删除${tableName}
     *
     * @param id ${tableName}ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个${tableName}信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "${tableName}ID", type = "path") String id);

    /**
     * 删除${tableName}
     *
     * @param idList ${tableName}ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除${tableName}信息")
    Result<Boolean> batchRemove(List<String> idList);

}
