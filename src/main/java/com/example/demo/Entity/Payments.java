package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "code")
	private Integer code;
	
	@Column(name = "user_code")
	private Integer userCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "bank_blunch")
	private Integer bankBlunch;
	
	@Column(name = "bank_num")
	private Integer bankNum;
	
	@Column(name = "bank_type")
	private Integer bankType;
	
	@Column(name = "delete_flag")
	private Integer deleteFlag;
	
	
	public Payments() {
		super();
	}
	
	public Payments(
			Integer code,
			Integer userCode,
			String name,
			Integer bankBlunch,
			Integer bankNum,
			Integer bankType,
			Integer deleteFlag) {
		
		this.code = code;
		this.userCode = userCode;
		this.name=name;
		this.bankBlunch = bankBlunch;
		this.bankNum = bankNum;
		this.bankType = bankType;
		this.deleteFlag = deleteFlag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBankBlunch() {
		return bankBlunch;
	}

	public void setBankBlunch(Integer bankBlunch) {
		this.bankBlunch = bankBlunch;
	}

	public Integer getBankNum() {
		return bankNum;
	}

	public void setBankNum(Integer bankNum) {
		this.bankNum = bankNum;
	}

	public Integer getBankType() {
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	

}
