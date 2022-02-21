package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;


<#--声明表名称-->
<#assign tableName="${table.comment?substring(0,table.comment?length-1)}"/>


/**
* ${tableName!} 服务实现类
*
* @Description:
* @Author: ${author}
* @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    /**
    * 获取${tableName!}详情
    * @param id ID
    * @return 单个${tableName!}详情
    */
    @Override
    public ${entity} detailById(String id){
        return this.getById(id);
    }

    /**
    * 增加${tableName!}信息
    * @param ${entity?uncap_first} ${tableName!}信息
    */
    @Override
    public void save${entity}(${entity} ${entity?uncap_first}){
        this.baseMapper.insert(${entity?uncap_first});
    }

    /**
    * 修改${tableName!}
    * @param ${entity?uncap_first} ${table.comment!}对象
    */
    @Override
    public void update${entity}ById(${entity} ${entity?uncap_first}){
        this.baseMapper.updateById(${entity?uncap_first});
    }

    /**
    * 根据ID删除
    * @param id ID
    */
    @Override
    public void deleteById(String id){
        this.removeById(id);
    }
}
</#if>
