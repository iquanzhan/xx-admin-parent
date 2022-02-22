package ${package.Controller};

import com.chengxiaoxiao.core.validator.group.AddGroup;
import com.chengxiaoxiao.core.validator.group.UpdateGroup;
import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import ${cfg.basePackage!}.service.${entity}Service;
import ${cfg.basePackage!}.api.${entity}ControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ${cfg.basePackage!}.entity.${entity};

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

<#--声明表名称-->
<#assign tableName="${table.comment?substring(0,table.comment?length-1)}"/>


/**
 * ${tableName!} 接口控制器
 *
 * @Description:
 * @Author: ${author}
 * @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} implements ${table.controllerName}Api{
    <#else>
public class ${table.controllerName} implements ${table.controllerName}Api {
    </#if>
    @Resource
    private ${entity}Service ${entity?uncap_first}Service;


    /**
    * 获取条件查询信息
    *
    * @param userInfoQuery 用户查询条件
    * @return MP查询封装
    */
    private QueryWrapper<${entity}> get${entity}QueryWrapper(${entity}Query ${entity?uncap_first}Query) {
    QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
<#list table.fields as field>
    <#if !field.keyIdentityFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='createTime' && field.propertyName!='updateTime' && field.propertyName!='deleteStatus'>
        <#if field.propertyType=='String'>
        if (!StringUtils.isEmpty(${entity?uncap_first}Query.get${field.propertyName?cap_first}())) {
            wrapper.like("${field.name}", ${entity?uncap_first}Query.get${field.propertyName?cap_first}());
        }
        </#if>
        <#if field.propertyType=='Integer'>
        if (${entity?uncap_first}Query.get${field.propertyName?cap_first}()!=null) {
            wrapper.eq("${field.name}", ${entity?uncap_first}Query.get${field.propertyName?cap_first}());
        }
        </#if>
    </#if>
</#list>
        return wrapper;
    }

    /**
    * 按照条件查询用户信息
    *
    * @param userInfoQuery 查询条件
    * @return 结果信息
    */
    @Override
    @GetMapping
    public Result<List<${entity}>> list(${entity}Query ${entity?uncap_first}Query) {
        return Result.success(${entity?uncap_first}Service.list(get${entity}QueryWrapper(${entity?uncap_first}Query)));
    }

    /**
    * 分页及条件查询用户信息
    * @param page 页码
    * @param size 分页大小
    * @param  ${entity?uncap_first}Query 查询条件
    * @return 分页结果信息
    */
    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<${entity}>> search(@PathVariable Long page, @PathVariable Long size, ${entity}Query ${entity?uncap_first}Query) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<${entity}> pageParam = new Page<>(page, size);

        ${entity?uncap_first}Service.page(pageParam, get${entity}QueryWrapper(${entity?uncap_first}Query));
        PageResult<${entity}> pageResult = new PageResult<${entity}>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
    * 根据ID获取${tableName}详情
    *
    * @param id ${tableName}ID
    * @return ${tableName}详细信息
    */
    @Override
    @GetMapping("/{id}")
    public Result<${entity}> detail(@PathVariable String id) {
        return Result.success(${entity?uncap_first}Service.detailById(id));
    }

    /**
    * 增加${tableName}
    *
    * @param ${entity?uncap_first} ${tableName}增加实体
    * @return 增加后的${tableName}信息
    */
    @Override
    @PostMapping
    public Result<${entity}> add(@RequestBody @Validated(AddGroup.class) ${entity} ${entity?uncap_first}) {
        ${entity?uncap_first}Service.save${entity}(${entity?uncap_first});
        return Result.success(${entity?uncap_first});
    }

    /**
    * 修改${tableName}
    *
    * @param id             ${tableName}ID
    * @param ${entity?uncap_first} ${tableName}修改实体
    * @return 修改后的${tableName}信息
    */
    @Override
    @PutMapping("/{id}")
    public Result<${entity}> updateById(@PathVariable String id,@RequestBody @Validated(UpdateGroup.class) ${entity} ${entity?uncap_first}) {
        ${entity?uncap_first}.setId(id);
        ${entity?uncap_first}Service.update${entity}ById(${entity?uncap_first});
        return Result.success(${entity?uncap_first}Service.getById(id));
    }


    /**
    * 删除${tableName}
    *
    * @param id ${tableName}ID
    * @return 删除结果信息
    */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable String id) {
        ${entity?uncap_first}Service.deleteById(id);
        return Result.success(true);
    }

    /**
    * 删除${tableName}
    *
    * @param idList ${tableName}ID数组
    * @return 删除结果信息
    */
    @Override
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        ${entity?uncap_first}Service.removeByIds(idList);
        return Result.success(true);
    }
}
</#if>
