package com.pensionrun.entity;
// Generated 2016. 4. 27 오전 12:18:51 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PensionFacilityId generated by hbm2java
 */
@Embeddable
public class PensionFacilityId implements java.io.Serializable {

	private int idfPension;
	private int idfFacility;

	public PensionFacilityId() {
	}

	public PensionFacilityId(int idfPension, int idfFacility) {
		this.idfPension = idfPension;
		this.idfFacility = idfFacility;
	}

	@Column(name = "idf_pension", nullable = false)
	public int getIdfPension() {
		return this.idfPension;
	}

	public void setIdfPension(int idfPension) {
		this.idfPension = idfPension;
	}

	@Column(name = "idf_facility", nullable = false)
	public int getIdfFacility() {
		return this.idfFacility;
	}

	public void setIdfFacility(int idfFacility) {
		this.idfFacility = idfFacility;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PensionFacilityId))
			return false;
		PensionFacilityId castOther = (PensionFacilityId) other;

		return (this.getIdfPension() == castOther.getIdfPension())
				&& (this.getIdfFacility() == castOther.getIdfFacility());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdfPension();
		result = 37 * result + this.getIdfFacility();
		return result;
	}

}