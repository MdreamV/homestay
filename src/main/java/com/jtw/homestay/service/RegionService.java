package com.jtw.homestay.service;

import com.jtw.homestay.dao.RegionDao;
import com.jtw.homestay.domain.Region;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-04
 */

public interface RegionService {

    /**
     * 查询所有地区
     * @return
     */
    List<Region> findAll();

    /**
     * 根据id查询地区
     * @param rid
     * @return
     */
    Region findByRid(Integer rid);
}
