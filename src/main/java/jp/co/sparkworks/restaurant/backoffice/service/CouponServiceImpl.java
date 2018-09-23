package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.CouponCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CouponDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Coupon;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponCustomDao couponCustomDao;

    @Autowired
    CouponDao couponDao;

    @Transactional
    @Override
    public List<CouponDto> search() {

        List<Coupon> couponList = couponCustomDao.selectAll();

        List<CouponDto> couponDtoList = new ArrayList<CouponDto>();

        for (Coupon coupon : couponList) {

            CouponDto couponDto = new CouponDto();
            
            couponDto.setCouponId(coupon.getCouponId());
            couponDto.setRestaurantId(coupon.getRestaurantId());
            couponDto.setTitle(coupon.getTitle());
            couponDto.setDetail(coupon.getDetail());
            couponDto.setCouponStartDate(coupon.getCouponStartDate());
            couponDto.setCouponEndDate(coupon.getCouponEndDate());
            couponDto.setCouponTotalAmount(coupon.getCouponTotalAmount());
            
            couponDtoList.add(couponDto);
        }

        return couponDtoList;
    }

    @Transactional
    @Override
    public CouponDto create(CouponDto couponDto) {

        // DTO->Entity
        Coupon coupon = new Coupon();
        
        coupon.setRestaurantId(couponDto.getRestaurantId());
        coupon.setTitle(couponDto.getTitle());
        coupon.setDetail(couponDto.getDetail());
        coupon.setCouponStartDate(couponDto.getCouponStartDate());
        coupon.setCouponEndDate(couponDto.getCouponEndDate());
        coupon.setCouponTotalAmount(couponDto.getCouponTotalAmount());

        // DB access
        couponDao.insert(coupon);

        // Entity->DTO
        CouponDto newCouponDto = new CouponDto();

        couponDto.setCouponId(coupon.getCouponId());
        couponDto.setRestaurantId(coupon.getRestaurantId());
        couponDto.setTitle(coupon.getTitle());
        couponDto.setDetail(coupon.getDetail());
        couponDto.setCouponStartDate(coupon.getCouponStartDate());
        couponDto.setCouponEndDate(coupon.getCouponEndDate());
        couponDto.setCouponTotalAmount(coupon.getCouponTotalAmount());

        return newCouponDto;
    }

    @Transactional
    @Override
    public CouponDto getById(Long couponId) {

        Coupon coupon = couponDao.selectById(couponId);
        if (coupon == null) {
            throw new BusinessException(ErrorCodeConstant.E20012);
        }
        CouponDto couponDto = new CouponDto();

        couponDto.setCouponId(coupon.getCouponId());
        couponDto.setRestaurantId(coupon.getRestaurantId());
        couponDto.setTitle(coupon.getTitle());
        couponDto.setDetail(coupon.getDetail());
        couponDto.setCouponStartDate(coupon.getCouponStartDate());
        couponDto.setCouponEndDate(coupon.getCouponEndDate());
        couponDto.setCouponTotalAmount(coupon.getCouponTotalAmount());

        return couponDto;

    }

    @Transactional
    @Override
    public void update(CouponDto couponDto) {

        Coupon coupon = couponDao.selectById(couponDto.getCouponId());

        couponDto.setRestaurantId(coupon.getRestaurantId());
        couponDto.setTitle(coupon.getTitle());
        couponDto.setDetail(coupon.getDetail());
        couponDto.setCouponStartDate(coupon.getCouponStartDate());
        couponDto.setCouponEndDate(coupon.getCouponEndDate());
        couponDto.setCouponTotalAmount(coupon.getCouponTotalAmount());

        couponDao.update(coupon);

    }

    @Transactional
    @Override
    public void delete(Long couponId) {

        Coupon coupon = couponDao.selectById(couponId);
        if (coupon == null) {
            throw new BusinessException(ErrorCodeConstant.E20012);
        }
        couponDao.delete(coupon);

    }



}
