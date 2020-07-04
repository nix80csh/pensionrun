package com.pensionrun.entity;
// Generated 2016. 4. 27 오전 12:18:51 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RoomAmenity generated by hbm2java
 */
@Entity
@Table(name = "room_amenity", catalog = "PensionRun")
public class RoomAmenity implements java.io.Serializable {

	private RoomAmenityId id;
	private Amenity amenity;
	private Room room;
	private Date regDate;

	public RoomAmenity() {
	}

	public RoomAmenity(RoomAmenityId id, Amenity amenity, Room room, Date regDate) {
		this.id = id;
		this.amenity = amenity;
		this.room = room;
		this.regDate = regDate;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idfRoom", column = @Column(name = "idf_room", nullable = false)),
			@AttributeOverride(name = "idfAmenity", column = @Column(name = "idf_amenity", nullable = false)) })
	public RoomAmenityId getId() {
		return this.id;
	}

	public void setId(RoomAmenityId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_amenity", nullable = false, insertable = false, updatable = false)
	public Amenity getAmenity() {
		return this.amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_room", nullable = false, insertable = false, updatable = false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", nullable = false, length = 19, insertable=false, updatable=false)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}