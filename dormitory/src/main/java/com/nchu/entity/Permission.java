package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;;

@Entity
@Table(name="PERMISSION")
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer pid;
	private String pname;
	private String mark;
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}


	@Override
	public String toString() {
		return "Permission [pid=" + pid + ", pname=" + pname +  "]";
	}

}
