package com.baha.bankapp.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class AccountDto {
	private String custNo;
	private String accNo;
	private BigDecimal accBal;
	private BigDecimal depoAmt;
	private BigDecimal wdAmt;
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public BigDecimal getAccBal() {
		return accBal;
	}
	public void setAccBal(BigDecimal accBal) {
		this.accBal = accBal;
	}
	public BigDecimal getDepoAmt() {
		return depoAmt;
	}
	public void setDepoAmt(BigDecimal depoAmt) {
		this.depoAmt = depoAmt;
	}
	public BigDecimal getWdAmt() {
		return wdAmt;
	}
	public void setWdAmt(BigDecimal wdAmt) {
		this.wdAmt = wdAmt;
	}
}