package com.pensionrun.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pensionrun.dao.ReviewDao;
import com.pensionrun.dao.ReviewImageDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ReviewDto;
import com.pensionrun.entity.Account;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.Review;
import com.pensionrun.entity.ReviewImage;
import com.pensionrun.util.ImageUtil;

@Service
public class ReviewServiceImpl implements ReviewService {

	public static final String s3BucketName = "review-image.pensionrun.com";

	@Autowired
	ReviewDao reviewDao;
	@Autowired
	ReviewImageDao reviewImageDao;
	@Autowired
	ImageUtil imageUtil;

	@Override
	public JsonDto<ReviewDto> reviewCreate(ReviewDto _reviewDto) {
		JsonDto<ReviewDto> jDto = new JsonDto<ReviewDto>();
		Review review = new Review();
		Pension pension = new Pension();
		Account account = new Account();
		String content = "";
		try {
			content = new String(_reviewDto.getContent().getBytes("8859_1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(_reviewDto, review);
		pension.setIdfPension(_reviewDto.getIdfPension());
		account.setIdfAccount(_reviewDto.getIdfAccount());
		review.setAccount(account);
		review.setPension(pension);
		review.setContent(content);
		reviewDao.create(review);

		if (_reviewDto.getFiles() != null) {
			MultipartFile[] images = _reviewDto.getFiles();
			List<String> fileNames = imageUtil.uploadImage(s3BucketName, review.getIdfReview().toString(), images,
					"review");
			System.out.println(fileNames.size());

			for (int i = 0; i < fileNames.size(); i++) {
				System.out.println(i);
				ReviewImage reviewImage = new ReviewImage();
				reviewImage.setReview(review);
				reviewImage.setImage(fileNames.get(i));
				reviewImage.setPriority((byte) i);
				reviewImageDao.create(reviewImage);
			}
		}

		_reviewDto.setIdfReview(review.getIdfReview());
		_reviewDto.setContent(content);
		_reviewDto.setFiles(null);
		jDto.setResultCode("S");
		jDto.setDataObject(_reviewDto);

		return jDto;
	}

	@Override
	public JsonDto<ReviewDto> reviewDelete(ReviewDto _reviewDto) {
		JsonDto<ReviewDto> jDto = new JsonDto<ReviewDto>();
		Review review = reviewDao.readById(_reviewDto.getIdfReview());

		if (review == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Review");
		} else {
			List<ReviewImage> reviewImage = reviewImageDao.readByIdfReview(_reviewDto.getIdfReview());
			if (reviewImage.size() > 0) {
				for (int i = 0; i < reviewImage.size(); i++) {
					imageUtil.deleteImage(s3BucketName, _reviewDto.getIdfReview().toString()+"/"+reviewImage.get(i).getImage());		
				}
				imageUtil.deleteImage(s3BucketName, _reviewDto.getIdfReview().toString());
			}
			reviewDao.delete(review);
			jDto.setResultCode("S");
		}
		return jDto;
	}

}
