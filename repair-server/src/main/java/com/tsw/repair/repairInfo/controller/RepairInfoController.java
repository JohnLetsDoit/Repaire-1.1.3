package com.tsw.repair.repairInfo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.tsw.repair.common.BaseController;
import com.tsw.repair.repairInfo.model.RepairInfo;
import com.tsw.repair.repairInfo.service.RepairInfoService;
import com.tsw.repair.repairInfo.vo.SearchVo;
import com.tsw.repair.result.Result;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/info")
public class RepairInfoController extends BaseController {

    @PostMapping("/search")
    public Result getRepairInfoBySearch(@RequestBody SearchVo searchVo){
        PageHelper.startPage(searchVo.getPageNum(),searchVo.getPageSize());
        List<RepairInfo> info = repairInfoService.getRepairInfoBySearch(searchVo);
        PageInfo<RepairInfo> pageInfo = new PageInfo<>(info);
        return Result.success(pageInfo);
    }

    @GetMapping("/all")
    public Result getRepairInfo(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<RepairInfo> info = repairInfoService.getRepairInfo();
        PageInfo<RepairInfo> pageInfo = new PageInfo<>(info);
        return Result.success(pageInfo);
    }

    @PostMapping("/updateStateByRepairId")
    public Result updateStateById(int state, Long repairId ){
        repairInfoService.updateStateByRepairId(state,repairId);
        return Result.success();
    }

    @PostMapping("/updateRemarkByRepairId")
    public Result updateRemarkByRepairId(Long repairId , String remark){
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setRemark(remark);
        repairInfo.setRepairId(repairId);
        repairInfoService.updateRepairInfo(repairInfo);
        return Result.success(repairInfoService.getRepairInfo());
    }

    @PostMapping("/updateRepairInfo")
    public Result updateRepairInfo(@RequestBody RepairInfo repairInfo){
        repairInfoService.updateRepairInfo(repairInfo);
        return Result.success();
    }

    @PostMapping("/addServiceRepairInfo")
    public Result addServiceRepairInfo(@RequestBody RepairInfo repairInfo){
        return Result.success();
    }
    @PostMapping("/addRepairInfo")
    public Result addRepairInfo(@RequestBody RepairInfo repairInfo){
        repairInfoService.addRepairInfo(repairInfo);
        return Result.success(repairInfo);
    }

    @DeleteMapping("/deleteRepairInfo")
    public Result deleteRepairInfo(@RequestParam("repairId") Long  repairId){
        repairInfoService.deleteRepairInfo(repairId);
        return Result.success();
    }
}
