package com.pensionrun.entity;
// Generated 2016. 5. 27 오전 10:36:21 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PaymentVbank generated by hbm2java
 */
@Entity
@Table(name = "payment_vbank", catalog = "PensionRun")
public class PaymentVbank implements java.io.Serializable {

	private String idfPayment;
	private Payment payment;
	private String expiryDate;
	private String name;
	private String number;
	private String code;
	private String depositDate;
	private String depositName;
	private String receiptNumber;
	private Character receiptType;
	private String receiptAuthCode;

	public PaymentVbank() {
	}

	public PaymentVbank(Payment payment, String expiryDate, String name, String number, String code) {
		this.payment = payment;
		this.expiryDate = expiryDate;
		this.name = name;
		this.number = number;
		this.code = code;
	}

	public PaymentVbank(Payment payment, String expiryDate, String name, String number, String code, String depositDate,
			String depositName, String receiptNumber, Character receiptType, String receiptAuthCode) {
		this.payment = payment;
		this.expiryDate = expiryDate;
		this.name = name;
		this.number = number;
		this.code = code;
		this.depositDate = depositDate;
		this.depositName = depositName;
		this.receiptNumber = receiptNumber;
		this.receiptType = receiptType;
		this.receiptAuthCode = receiptAuthCode;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "payment"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "idf_payment", unique = true, nullable = false, length = 30)
	public String getIdfPayment() {
		return this.idfPayment;
	}

	public void setIdfPayment(String idfPayment) {
		this.idfPayment = idfPayment;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Column(name = "expiry_date", nullable = false, length = 12)
	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "number", nullable = false, length = 20)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "code", nullable = false, length = 3)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "deposit_date", length = 12)
	public String getDepositDate() {
		return this.depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	@Column(name = "deposit_name", length = 20)
	public String getDepositName() {
		return this.depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	@Column(name = "receipt_number", length = 30)
	public String getReceiptNumber() {
		return this.receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	@Column(name = "receipt_type", length = 1)
	public Character getReceiptType() {
		return this.receiptType;
	}

	public void setReceiptType(Character receiptType) {
		this.receiptType = receiptType;
	}

	@Column(name = "receipt_auth_code", length = 30)
	public String getReceiptAuthCode() {
		return this.receiptAuthCode;
	}

	public void setReceiptAuthCode(String receiptAuthCode) {
		this.receiptAuthCode = receiptAuthCode;
	}

}