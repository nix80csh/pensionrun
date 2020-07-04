package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.PensionImage;
@Repository
public class PensionImageDaoImpl extends GenericDaoImpl<PensionImage> implements PensionImageDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
    public List<PensionImage> readByIdfPension(Integer idfPension) {
        Query q = em.createQuery("from PensionImage pi where pi.pension.idfPension = :idfPension", PensionImage.class);
        q.setParameter("idfPension", idfPension);
        return q.getResultList();
    }

    @Override
    public PensionImage readMainImageByIdfPension(Integer idfPension) {
        PensionImage pensionImage = new PensionImage();
        try {
            String sql = "from PensionImage pi where pi.pension.idfPension = :idfPension order by pi.priority asc";
            Query q = em.createQuery(sql, PensionImage.class);
            q.setParameter("idfPension", idfPension);
            pensionImage = (PensionImage) q.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            pensionImage = null;
        }
        return pensionImage;
    }


}
