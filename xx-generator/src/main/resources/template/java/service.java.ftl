package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};


<#--声明表名称-->
<#assign tableName="${table.comment?substring(0,table.comment?length-1)}"/>
/**
* ${tableName!} 服务类
*
* @Description:
* @Author: ${author}
* @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    /**
    * 获取${tableName!}详情
    * @param id ID
    * @return 单个${tableName!}详情
    */
    ${entity} detailById(String id);

    /**
    * 增加${tableName!}信息
    * @param ${entity?uncap_first} ${tableName!}信息
    */
    void save${entity}(${entity} ${entity?uncap_first});

    /**
    * 修改${tableName!}
    * @param ${entity?uncap_first} ${table.comment!}对象
    */
    void update${entity}ById(${entity} ${entity?uncap_first});

    /**
    * 根据ID删除
    * @param id ID
    */
    void deleteById(String id);
}
</#if>
