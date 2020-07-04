package com.pensionrun.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.RoomPrice;

@Repository
public class RoomPriceDaoImpl extends GenericDaoImpl<RoomPrice> implements RoomPriceDao {
	@PersistenceContext
    private EntityManager em;

    @Override
    public RoomPrice readTodayPriceByIdfPension(Integer idfPension) {
        RoomPrice roomPrice = new RoomPrice();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String subSql = "SELECT r.idfRoom FROM Room r Where r.pension.idfPension=:idfPension";
            String sql = "from RoomPrice rp where rp.id.idfRoom in(" + subSql + ") and rp.id.checkDate=:todayDate";
            sql += " order by rp.priceDiscount asc";
            Query q = em.createQuery(sql, RoomPrice.class);
            q.setParameter("idfPension", idfPension);
            q.setParameter("todayDate", dateFormat.format(new Date()));
            roomPrice = (RoomPrice) q.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            roomPrice = null;
        }

        return roomPrice;
    }

	@Override
	public List<RoomPrice> readByCheckDate(Integer idfRoom, String startCheckDate, String endCheckDate) {
		String sql = "from RoomPrice rp where rp.id.idfRoom=:idfRoom and rp.id.checkDate between :startCheckDate and :endCheckDate";
		Query q = em.createQuery(sql, RoomPrice.class);
		q.setParameter("idfRoom", idfRoom);
		q.setParameter("startCheckDate", startCheckDate);
		q.setParameter("endCheckDate", endCheckDate);
		return q.getResultList();
	}

	
}
