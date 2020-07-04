package com.pensionrun.entity;
// Generated 2016. 6. 8 오전 11:35:54 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AppInfo generated by hbm2java
 */
@Entity
@Table(name = "app_info", catalog = "PensionRun")
public class AppInfo implements java.io.Serializable {

	private AppInfoId id;
	private String urlDownload;
	private String description;
	private char state;
	private Date regDate;

	public AppInfo() {
	}

	public AppInfo(AppInfoId id, String urlDownload, String description, char state, Date regDate) {
		this.id = id;
		this.urlDownload = urlDownload;
		this.description = description;
		this.state = state;
		this.regDate = regDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "version", column = @Column(name = "version_", nullable = false, length = 15)),
			@AttributeOverride(name = "typeOs", column = @Column(name = "type_os", nullable = false, length = 1)) })
	public AppInfoId getId() {
		return this.id;
	}

	public void setId(AppInfoId id) {
		this.id = id;
	}

	@Column(name = "url_download", nullable = false, length = 500)
	public String getUrlDownload() {
		return this.urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "state", nullable = false, length = 1)
	public char getState() {
		return this.state;
	}

	public void setState(char state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", nullable = false, length = 19)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}