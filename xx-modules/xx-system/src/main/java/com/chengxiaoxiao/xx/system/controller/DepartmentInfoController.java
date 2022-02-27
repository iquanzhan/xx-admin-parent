package com.chengxiaoxiao.xx.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.core.validator.group.AddGroup;
import com.chengxiaoxiao.core.validator.group.UpdateGroup;
import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xx.system.api.DepartmentInfoControllerApi;
import com.chengxiaoxiao.xx.system.entity.DepartmentInfo;
import com.chengxiaoxiao.xx.system.service.DepartmentInfoService;
import com.chengxiaoxiao.xx.system.vo.query.DepartmentInfoQuery;
import com.chengxiaoxiao.xx.system.vo.vo.DepartmentTreeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 部门 接口控制器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:20:14
 */
@RestController
@RequestMapping("/department-info")
public class DepartmentInfoController implements DepartmentInfoControllerApi {
    @Resource
    private DepartmentInfoService departmentInfoService;


    /**
     * 获取条件查询信息
     *
     * @param departmentInfoQuery 查询条件
     * @return 查询封装
     */
    private QueryWrapper<DepartmentInfo> getDepartmentInfoQueryWrapper(DepartmentInfoQuery departmentInfoQuery) {
        QueryWrapper<DepartmentInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(departmentInfoQuery.getDepartmentName())) {
            wrapper.like("department_name", departmentInfoQuery.getDepartmentName());
        }
        if (!StringUtils.isEmpty(departmentInfoQuery.getRemark())) {
            wrapper.like("remark", departmentInfoQuery.getRemark());
        }
        return wrapper;
    }

    /**
     * 按照条件查询部门信息
     *
     * @param departmentInfoQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<DepartmentInfo>> list(DepartmentInfoQuery departmentInfoQuery) {
        return Result.success(departmentInfoService.list(getDepartmentInfoQueryWrapper(departmentInfoQuery)));
    }

    /**
     * 分页及条件查询部门信息
     *
     * @param page                页码
     * @param size                分页大小
     * @param departmentInfoQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<DepartmentInfo>> search(@PathVariable Long page, @PathVariable Long size, DepartmentInfoQuery departmentInfoQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<DepartmentInfo> pageParam = new Page<>(page, size);

        departmentInfoService.page(pageParam, getDepartmentInfoQueryWrapper(departmentInfoQuery));
        PageResult<DepartmentInfo> pageResult = new PageResult<DepartmentInfo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 根据ID获取部门详情
     *
     * @param id 部门ID
     * @return 部门详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<DepartmentInfo> detail(@PathVariable("id") String id) {
        return Result.success(departmentInfoService.detailById(id));
    }

    /**
     * 增加部门信息
     *
     * @param departmentInfo 部门增加实体
     * @return 增加后的部门信息
     */
    @Override
    @PostMapping
    public Result<DepartmentInfo> add(@RequestBody @Validated(AddGroup.class) DepartmentInfo departmentInfo) {
        departmentInfoService.saveDepartmentInfo(departmentInfo);
        return Result.success(departmentInfo);
    }

    /**
     * 修改部门信息
     *
     * @param id             部门ID
     * @param departmentInfo 部门修改实体
     * @return 修改后的部门信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<DepartmentInfo> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) DepartmentInfo departmentInfo) {
        departmentInfo.setId(id);
        departmentInfoService.updateDepartmentInfoById(departmentInfo);
        return Result.success(departmentInfoService.getById(id));
    }


    /**
     * 删除部门信息
     *
     * @param id 部门ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        departmentInfoService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除部门信息
     *
     * @param idList 部门ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        departmentInfoService.removeByIds(idList);
        return Result.success(true);
    }

    /**
     * 查询部门树形结构
     *
     * @return 部门树形结构
     */
    @Override
    @GetMapping("/tree")
    public Result<List<DepartmentTreeVo>> tree() {
        return Result.success(departmentInfoService.tree());
    }
}
