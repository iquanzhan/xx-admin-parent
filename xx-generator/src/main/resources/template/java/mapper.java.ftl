package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

<#--声明表名称-->
<#assign tableName="${table.comment?replace('信息','')?replace('表','')}"/>


/**
* ${tableName!}信息 Mapper 接口
*
* @Description:
* @Author: ${author}
* @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
