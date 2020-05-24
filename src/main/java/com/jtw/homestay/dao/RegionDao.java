package com.jtw.homestay.dao;

import com.jtw.homestay.domain.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-04
 */

public interface RegionDao {

    /**
     * 添加地区
     * @param region
     * @return
     */
    @Insert("insert into tab_region(region)values(#{region})")
    @Options(useGeneratedKeys = true,keyProperty = "rid",keyColumn = "rid")
    Boolean addRegion(Region region);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from tab_region")
    List<Region> findAll();

    /**
     * 根据id查询地区
     * @param rid
     * @return
     */
    @Select("select * from tab_region where rid = #{rid}")
    Region findByRid(Integer rid);
}
