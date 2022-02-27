package com.chengxiaoxiao.xx.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.xx.system.entity.DepartmentInfo;
import com.chengxiaoxiao.xx.system.mapper.DepartmentInfoMapper;
import com.chengxiaoxiao.xx.system.service.DepartmentInfoService;
import com.chengxiaoxiao.xx.system.vo.vo.DepartmentTreeVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 部门信息 服务实现类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:20:14
 */
@Service
public class DepartmentInfoServiceImpl extends ServiceImpl<DepartmentInfoMapper, DepartmentInfo> implements DepartmentInfoService {
    /**
     * 获取部门详情
     *
     * @param id ID
     * @return 单个部门详情
     */
    @Override
    public DepartmentInfo detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加部门信息
     *
     * @param departmentInfo 部门对象
     */
    @Override
    public void saveDepartmentInfo(DepartmentInfo departmentInfo) {
        this.baseMapper.insert(departmentInfo);
    }

    /**
     * 修改部门信息
     *
     * @param departmentInfo 部门对象
     */
    @Override
    public void updateDepartmentInfoById(DepartmentInfo departmentInfo) {
        this.baseMapper.updateById(departmentInfo);
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    @Override
    public void deleteById(String id) {
        this.removeById(id);
    }

    /**
     * 查询部门树形结构
     *
     * @return 部门树形结构
     */
    @Override
    public List<DepartmentTreeVo> tree() {
        List<DepartmentInfo> allDepartment = this.list(new QueryWrapper<DepartmentInfo>().orderByAsc("id"));

        List<DepartmentTreeVo> list = new ArrayList<>();

        //进行递归查询，封装成树结构
        for (DepartmentInfo departmentInfo : allDepartment) {
            if ("0".equals(departmentInfo.getParentId())) {
                //存在多个一级节点
                list.add(treeSelectChildren(departmentInfo, allDepartment));
            }
        }

        return list;
    }

    /**
     * 递归处理获取子节点
     *
     * @param departmentInfo 当前树节点
     * @param allDepartment  所有的树节点
     * @return 包含子节点的树结构
     */
    private DepartmentTreeVo treeSelectChildren(DepartmentInfo departmentInfo, List<DepartmentInfo> allDepartment) {
        DepartmentTreeVo departmentTreeVo = new DepartmentTreeVo();
        BeanUtil.copyProperties(departmentInfo, departmentTreeVo);

        if (departmentTreeVo.getChildren() == null) {
            departmentTreeVo.setChildren(new ArrayList<DepartmentTreeVo>());
        }

        for (DepartmentInfo item : allDepartment) {
            if (item.getParentId().equals(departmentInfo.getId())) {
                departmentTreeVo.getChildren().add(treeSelectChildren(item, allDepartment));
            }
        }
        return departmentTreeVo;
    }
}
