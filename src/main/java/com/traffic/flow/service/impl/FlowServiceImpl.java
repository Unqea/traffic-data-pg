package com.traffic.flow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.traffic.flow.entity.FlowNode;
import com.traffic.flow.entity.FlowProcess;
import com.traffic.flow.mapper.FlowNodeMapper;
import com.traffic.flow.mapper.FlowProcessMapper;
import com.traffic.flow.param.FlowNodeParam;
import com.traffic.flow.param.FlowParam;
import com.traffic.flow.service.FlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FlowServiceImpl implements FlowService {

    @Resource
    private FlowNodeMapper flowNodeMapper;

    @Resource
    private FlowProcessMapper flowProcessMapper;

    /**
     * 新增流程和节点
     *
     * @param flowParam 流程参数
     * @return 新增流程是否成功
     */
    @Override
    public Boolean insertFlow(FlowParam flowParam) {
        //校验参数
        checkFlowParam(flowParam);
        FlowProcess flowProcess = new FlowProcess();
        flowProcess.setFlowName(flowParam.getFlowName());
        flowProcess.setRecipientId(flowParam.getRecipientId());
        flowProcess.setWithdraw(flowParam.getWithdraw());
        flowProcess.setCreateTime(new Date());
        flowProcess.setModifyTime(new Date());
        flowProcess.setRemark(flowParam.getRemark());
        if (flowProcessMapper.insert(flowProcess) <= 0){
            return false;
        }
        Long processId = flowProcess.getId();

        List<FlowNodeParam> nodeList = flowParam.getNodeList();
        for (FlowNodeParam flowNodeParam : nodeList) {
            FlowNode flowNode = new FlowNode();
            flowNode.setProcessId(processId);
            flowNode.setNodeName(flowNodeParam.getNodeName());
            flowNode.setNodeOrderId(flowNodeParam.getNodeOrderId());
            flowNode.setApproverIds(flowNodeParam.getApproverIds());
            flowNode.setApproverDeps(flowNodeParam.getApproverDeps());
            flowNode.setCreateTime(new Date());
            flowNode.setModifyTime(new Date());
            flowNode.setRemark(flowNodeParam.getRemark());
            if (flowNodeMapper.insert(flowNode) <= 0){
                return false;
            }
        }
        return true;
    }


    /**
     * 校验流程节点参数
     * @param flowParam 流程节点参数
     */
    private void checkFlowParam(FlowParam flowParam) {
        if (ObjUtil.isEmpty(flowParam)){
            throw new RuntimeException("参数不能为null");
        }
        String flowName = flowParam.getFlowName();
        LambdaQueryWrapper<FlowProcess> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FlowProcess::getFlowName,flowName);
        Integer count = flowProcessMapper.selectCount(wrapper);
        if (count > 0){
            throw new RuntimeException("该流程名称已存在");
        }
        List<FlowNodeParam> nodeList = flowParam.getNodeList();
        if (CollUtil.isEmpty(nodeList)){
            throw new RuntimeException("节点流程不能为空");
        }
        for (FlowNodeParam flowNodeParam : nodeList) {
            if (StrUtil.isBlank(flowNodeParam.getNodeName())){
                throw new RuntimeException("节点名称不能为空");
            }

            if (StrUtil.isBlank(flowNodeParam.getApproverDeps()) && StrUtil.isBlank(flowNodeParam.getApproverIds())){
                throw new RuntimeException("节点审批人或审批部门至少有一个");
            }

            if (ObjUtil.isEmpty(flowNodeParam.getNodeOrderId())){
                throw new RuntimeException("节点顺序不能为空");
            }
        }
    }
}
