package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TLEAVE")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer leaid;//请假id
	private String applyid;//申请者用户id
	private String firsttreatid;//第一个处理者用户id
	private String secondtreatid;//第二个处理者用户id
	private String beginTime;//起始日期
	private String endTime;//结束日期
	private String createTime;//创建日期
	private String reason;//请假原因
	private Integer status;//审批状态
	private Integer iscancel;//销假状态
	private String cancelTime;//销假时间
	public Integer getLeaid() {
		return leaid;
	}
	public void setLeaid(Integer leaid) {
		this.leaid = leaid;
	}
	public String getApplyid() {
		return applyid;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}

	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIscancel() {
		return iscancel;
	}
	public void setIscancel(Integer iscancel) {
		this.iscancel = iscancel;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getFirsttreatid() {
		return firsttreatid;
	}
	public void setFirsttreatid(String firsttreatid) {
		this.firsttreatid = firsttreatid;
	}
	public String getSecondtreatid() {
		return secondtreatid;
	}
	public void setSecondtreatid(String secondtreatid) {
		this.secondtreatid = secondtreatid;
	}
	@Override
	public String toString() {
		return "Leave [leaid=" + leaid + ", applyid=" + applyid + ", firsttreatid=" + firsttreatid + ", secondtreatid="
				+ secondtreatid + ", beginTime=" + beginTime + ", endTime=" + endTime + ", createTime=" + createTime
				+ ", reason=" + reason + ", status=" + status + ", iscancel=" + iscancel + ", cancelTime=" + cancelTime
				+ "]";
	}

}
