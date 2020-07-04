package com.pensionrun.entity;
// Generated 2016. 5. 19 오후 11:24:48 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name = "review", catalog = "PensionRun")
public class Review implements java.io.Serializable {

	private Integer idfReview;
	private Account account;
	private Pension pension;
	private String content;
	private byte pointClean;
	private byte pointLocation;
	private byte pointService;
	private Date regDate;
	private Set<ReviewImage> reviewImages = new HashSet<ReviewImage>(0);

	public Review() {
	}

	public Review(Account account, Pension pension, String content, byte pointClean, byte pointLocation,
			byte pointService, Date regDate) {
		this.account = account;
		this.pension = pension;
		this.content = content;
		this.pointClean = pointClean;
		this.pointLocation = pointLocation;
		this.pointService = pointService;
		this.regDate = regDate;
	}

	public Review(Account account, Pension pension, String content, byte pointClean, byte pointLocation,
			byte pointService, Date regDate, Set<ReviewImage> reviewImages) {
		this.account = account;
		this.pension = pension;
		this.content = content;
		this.pointClean = pointClean;
		this.pointLocation = pointLocation;
		this.pointService = pointService;
		this.regDate = regDate;
		this.reviewImages = reviewImages;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idf_review", unique = true, nullable = false)
	public Integer getIdfReview() {
		return this.idfReview;
	}

	public void setIdfReview(Integer idfReview) {
		this.idfReview = idfReview;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_account", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_pension", nullable = false)
	public Pension getPension() {
		return this.pension;
	}

	public void setPension(Pension pension) {
		this.pension = pension;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "point_clean", nullable = false)
	public byte getPointClean() {
		return this.pointClean;
	}

	public void setPointClean(byte pointClean) {
		this.pointClean = pointClean;
	}

	@Column(name = "point_location", nullable = false)
	public byte getPointLocation() {
		return this.pointLocation;
	}

	public void setPointLocation(byte pointLocation) {
		this.pointLocation = pointLocation;
	}

	@Column(name = "point_service", nullable = false)
	public byte getPointService() {
		return this.pointService;
	}

	public void setPointService(byte pointService) {
		this.pointService = pointService;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", nullable = false, length = 19, insertable=false, updatable=false)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
	public Set<ReviewImage> getReviewImages() {
		return this.reviewImages;
	}

	public void setReviewImages(Set<ReviewImage> reviewImages) {
		this.reviewImages = reviewImages;
	}

}