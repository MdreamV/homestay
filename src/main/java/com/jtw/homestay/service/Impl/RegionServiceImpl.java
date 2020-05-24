package com.jtw.homestay.service.Impl;

import com.jtw.homestay.dao.RegionDao;
import com.jtw.homestay.domain.Region;
import com.jtw.homestay.service.RegionService;
import com.jtw.homestay.utils.DaoUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-04
 */

public class RegionServiceImpl implements RegionService {


    /**
     * 查询所有地区
     * @return
     */
    @Override
    public List<Region> findAll() {
        SqlSession session = DaoUtils.getSession();
        RegionDao regionDao = session.getMapper(RegionDao.class);
        List<Region> regionList = regionDao.findAll();
        session.close();
        return regionList;
    }

    /**
     * 根据id查询地区
     * @param rid
     * @return
     */
    @Override
    public Region findByRid(Integer rid) {
        SqlSession session = DaoUtils.getSession();
        RegionDao regionDao = session.getMapper(RegionDao.class);
        Region region = regionDao.findByRid(rid);
        session.close();
        return region;
    }
}
