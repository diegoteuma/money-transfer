package com.revolut.moneytransfer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

/**
 * 
 * @author Diego
 *
 */
@Entity
@Table(name = "transfers")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;
	@Min(1)
	private long amount;
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	private Account originAccount;
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	private Account destinyAccount;

	public Transfer() {
	}

	public Transfer(long amount, Account originAccount, Account destinyAccount) {
		this.setAmount(amount);
		this.setOriginAccount(originAccount);
		this.setDestinyAccount(destinyAccount);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}

	/**
	 * @return the originAccount
	 */
	public Account getOriginAccount() {
		return originAccount;
	}

	/**
	 * @param originAccount the originAccount to set
	 */
	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

	/**
	 * @return the destinyAccount
	 */
	public Account getDestinyAccount() {
		return destinyAccount;
	}

	/**
	 * @param destinyAccount the destinyAccount to set
	 */
	public void setDestinyAccount(Account destinyAccount) {
		this.destinyAccount = destinyAccount;
	}


}
