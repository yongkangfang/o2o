package com.light.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.light.model.Department;
import com.light.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("department")
@Api(value="Department测试", tags={"部门测试接口"})
@ResponseBody
public class DepartmentController {
	  @Autowired
	  private DepartmentService departmentService;
	  
	  @ApiOperation("保存部门信息")
	  @PostMapping("save")
	  public Map<String,Object> save(Department department) {
	    this.departmentService.save(department);
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("code", "200");
	    map.put("msg", "保存成功");
	    return map;
	  }
	  
	  @ApiOperation("通过id获取部门信息")
	  @GetMapping("get/{id}")
	  public Map<String,Object> get(@PathVariable("id") Integer id) {
	    Department department = this.departmentService.getDepartmentById(id);
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("code", "200");
	    map.put("msg", "获取成功");
	    map.put("data", department);
	    return map;
	  }
	  
	  @ApiOperation("更新部门信息")
	  @PostMapping("update")
	  public Map<String,Object> update(Department department) {
	    this.departmentService.update(department);
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("code", "200");
	    map.put("msg", "修改成功");
	    return map;
	  }
	  
	  @ApiOperation("删除部门信息")
	  @PostMapping("delete/{id}")
	  public Map<String,Object> delete(@PathVariable("id") Integer id) {
	    this.departmentService.delete(id);
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("code", "200");
	    map.put("msg", "删除成功");
	    return map;
	  }
	  
	}
