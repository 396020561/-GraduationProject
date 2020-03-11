package com.nchu.dto;

public class Result<T> {
	
	public boolean status;
	
	public T data;
	
	public String msg;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Result(boolean status,T data){
		this.status=status;
		this.data=data;
	}
	
	public Result(boolean status,String msg){
		this.status=status;
		this.msg=msg;
	}
	
	public Result(boolean status,T data,String msg){
		this.status=status;
		this.data=data;
		this.msg=msg;
	}
	
	public Result(boolean status) {
		super();
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
