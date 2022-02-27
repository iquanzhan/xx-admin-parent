package com.chengxiaoxiao.xx.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.xx.system.entity.DepartmentInfo;
import com.chengxiaoxiao.xx.system.vo.vo.DepartmentTreeVo;

import java.util.List;


/**
 * 部门 服务类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:20:14
 */
public interface DepartmentInfoService extends IService<DepartmentInfo> {
    /**
     * 获取部门详情
     *
     * @param id ID
     * @return 单个部门详情
     */
    DepartmentInfo detailById(String id);

    /**
     * 增加部门信息
     *
     * @param departmentInfo 部门信息
     */
    void saveDepartmentInfo(DepartmentInfo departmentInfo);

    /**
     * 修改部门信息
     *
     * @param departmentInfo 部门对象
     */
    void updateDepartmentInfoById(DepartmentInfo departmentInfo);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);

    /**
     * 查询部门树形结构
     *
     * @return 部门树形结构
     */
    List<DepartmentTreeVo> tree();
}
