package com.nchu.dto;

public class LeaveResult {
	
	private Integer leaid;
	private String applyName;
	private String treatName;
	private String beginTime;//起始日期
	private String endTime;//结束日期
	private String createTime;//创建日期
	private String reason;//请假原因
	private Integer status;//审批状态
	private Integer iscancel;//销假状态
	private String cancelTime;//销假时间
	private boolean operate;//能否销假
	private String applyid;
	
	public Integer getLeaid() {
		return leaid;
	}
	public void setLeaid(Integer leaid) {
		this.leaid = leaid;
	}
	public boolean isOperate() {
		return operate;
	}
	public void setOperate(boolean operate) {
		this.operate = operate;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getTreatName() {
		return treatName;
	}
	public void setTreatName(String treatName) {
		this.treatName = treatName;
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
	@Override
	public String toString() {
		return "LeaveResult [applyName=" + applyName + ", treatName=" + treatName + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + ", reason=" + reason + ", status=" + status
				+ ", iscancel=" + iscancel + ", cancelTime=" + cancelTime + ", getApplyName()=" + getApplyName()
				+ ", getTreatName()=" + getTreatName() + ", getBeginTime()=" + getBeginTime() + ", getEndTime()="
				+ getEndTime() + ", getCreateTime()=" + getCreateTime() + ", getReason()=" + getReason()
				+ ", getStatus()=" + getStatus() + ", getIscancel()=" + getIscancel() + ", getCancelTime()="
				+ getCancelTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public String getApplyid() {
		return applyid;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}
	
}
