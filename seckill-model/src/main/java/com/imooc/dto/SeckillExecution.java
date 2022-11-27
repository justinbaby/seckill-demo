package com.imooc.dto;

import com.imooc.entity.SuccessKilled;
import com.imooc.enums.SeckillStateEnum;

import java.io.Serializable;

/**
 * 封装秒杀执行后结果
 * 
 * @author yan
 */
public class SeckillExecution implements Serializable {

	private Long seckillId;

	// 秒杀执行结果状态
	private Integer state;

	// 状态标识
	private String stateInfo;

	// 秒杀成功对象
	private SuccessKilled successKilled;

	public SeckillExecution(Long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public SeckillExecution(Long seckillId, SeckillStateEnum stateEnum) {
		this.seckillId = seckillId;
		if(stateEnum!=null){
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
		}
	}

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}

}
