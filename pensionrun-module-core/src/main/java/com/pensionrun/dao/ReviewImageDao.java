package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.ReviewImage;

public interface ReviewImageDao extends GenericDao<ReviewImage> {

	List<ReviewImage> readByIdfReview(Integer idfReview);
}
