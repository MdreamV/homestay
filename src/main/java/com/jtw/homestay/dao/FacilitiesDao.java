package com.jtw.homestay.dao;

import com.jtw.homestay.domain.Facilities;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public interface FacilitiesDao {

    /**
     * 添加设施
     * @param facilities
     * @return
     */
    @Insert("insert into tab_facilities(wifi,network,airconditioner,heating,hotshower,shampoo,showergel,tableware,gasstove,freeparking)" +
            "values(#{wifi},#{network},#{airconditioner},#{heating},#{hotshower},#{shampoo},#{showergel},#{tableware},#{gasstove},#{freeparking})")
    @Options(useGeneratedKeys = true,keyProperty = "fid",keyColumn = "fid")
    Boolean addFacilities(Facilities facilities);

    /**
     * 删除设施
     * @param fid
     * @return
     */
    @Delete("delete from tab_facilities where fid = #{fid}")
    Boolean deleteFacilities(Integer fid);

    /**
     * 更新设施
     * @param facilities
     * @return
     */
    @Update("<script>update tab_facilities set " +
            "<if test = 'wifi != null' > wifi = #{wifi},</if>" +
            "<if test = 'network != null' > network = #{network},</if>" +
            "<if test = 'airconditioner != null' > airconditioner = #{airconditioner},</if>" +
            "<if test = 'heating != null' > heating = #{heating},</if>" +
            "<if test = 'hotshower != null' > hotshower = #{hotshower},</if>" +
            "<if test = 'shampoo != null' > shampoo = #{shampoo},</if>" +
            "<if test = 'showergel != null' > showergel = #{showergel},</if>" +
            "<if test = 'tableware != null' > tableware = #{tableware},</if>" +
            "<if test = 'gasstove != null' > gasstove = #{gasstove},</if>" +
            "<if test = 'freeparking != null' > freeparking = #{freeparking}</if>" +
            "where fid = #{fid}" +
            "</script>")
    Boolean updateFacilities(Facilities facilities);

    /**
     * 根据设施id查询
     * @param fid
     * @return
     */
    @Select("select * from tab_facilities where fid = #{fid}")
    Facilities findByFid(Integer fid);

    /**
     * 查询所有设施
     * @return
     */
    @Select("select * from tab_facilities")
    List<Facilities> findAll();

}
