package com.jtw.homestay.dao;

import com.jtw.homestay.domain.Housres;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public interface HousresDao {

    /**
     * 添加房源
     * @param housres
     * @return
     */
    @Insert("insert into tab_housres(hname,hdetail,checkincount,bedtype,bedcount,harea,hprice,fid,issell,rid,uid,detailregion,des,hygiene,location,hscore)" +
            "values(#{hname},#{hdetail},#{checkincount},#{bedtype},#{bedcount},#{harea},#{hprice},#{fid},#{issell},#{rid},#{uid},#{detailregion},0,0,0,0)")
    @Options(useGeneratedKeys = true,keyProperty = "hid",keyColumn = "hid")
    Boolean addHousres(Housres housres);

    /**
     * 更新房源
     * @param housres
     * @return
     */
    @Update("<script>update tab_housres set " +
            "<if test = 'hname != null'>hname = #{hname},</if>" +
            "<if test = 'hdetail != null'>hdetail = #{hdetail},</if>" +
            "<if test = 'checkincount != null'>checkincount = #{checkincount},</if>" +
            "<if test = 'bedtype != null'>bedtype = #{bedtype},</if>" +
            "<if test = 'bedcount != null'>bedcount = #{bedcount},</if>" +
            "<if test = 'harea != null'>harea = #{harea},</if>" +
            "<if test = 'hprice != null'>hprice = #{hprice},</if>" +
            "<if test = 'rid != null'>rid = #{rid},</if>" +
            "<if test = 'hscore != null'>hscore = #{hscore},</if>" +
            "<if test = 'des != null'>des = #{des},</if>" +
            "<if test = 'hygiene != null'>hygiene = #{hygiene},</if>" +
            "<if test = 'location != null'>location = #{location},</if>" +
            "<if test = 'issell != null'>issell = #{issell}</if>" +
            "where hid = #{hid}" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "hid",keyColumn = "hid")
    Boolean updateHousres(Housres housres);

    /**
     * 删除房源
     * @param hid
     * @return
     */
    @Delete("delete from tab_housres where hid = #{hid}")
    Boolean deleteHousres(Integer hid);


    /**
     * 根据id查询房源
     * @param hid
     * @return
     */
    @Select("select * from tab_housres where hid = #{hid}")
    @Results(id = "housresMap" , value = {
            @Result(id = true, property = "hid", column = "hid"),
            @Result(property = "hname", column = "hname"),
            @Result(property = "hdetail", column = "hdetail"),
            @Result(property = "checkincount", column = "checkincount"),
            @Result(property = "bedtype", column = "bedtype"),
            @Result(property = "bedcount", column = "bedcount"),
            @Result(property = "harea", column = "harea"),
            @Result(property = "hprice", column = "hprice"),
            @Result(property = "fid", column = "fid"),
            @Result(property = "issell", column = "issell"),
            @Result(property = "rid", column = "rid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "des",column = "des"),
            @Result(property = "hygiene",column = "hygiene"),
            @Result(property = "location",column = "location"),
            @Result(property = "hscore",column = "hscore"),
            @Result(property = "detailregion",column = "detailregion"),
            @Result(property = "facilities", column = "fid", one = @One(select = "com.jtw.homestay.dao.FacilitiesDao.findByFid", fetchType = FetchType.EAGER)),
            @Result(property = "user", column = "uid", one = @One(select = "com.jtw.homestay.dao.UserDao.findByUid",fetchType = FetchType.EAGER)),
            @Result(property = "roomImg", column = "hid", one = @One(select = "com.jtw.homestay.dao.RoomImgDao.findByHid",fetchType = FetchType.EAGER)),
            @Result(property = "region", column = "rid", one = @One(select = "com.jtw.homestay.dao.RegionDao.findByRid",fetchType = FetchType.EAGER))
    })
    Housres findByHid(Integer hid);

    /**
     * 查询所有房源
     * @return
     */
    @Select("select * from tab_housres")
    @ResultMap("housresMap")
    List<Housres> findAll();

    /**
     * 分页查询
     * @param startPage
     * @param count
     * @return
     */
    @Select("select * from tab_housres where rid = #{rid} and issell=false limit #{startPage},#{count}")
    @ResultMap("housresMap")
    List<Housres> findLimit(@Param("rid") Integer rid,@Param("startPage") Integer startPage,@Param("count") Integer count);

    /**
     * 某个地区的总记录数
     * @param rid
     * @return
     */
    @Select("select count(hid) from tab_housres where rid = #{rid}")
    Integer findCountByRegion(@Param("rid") Integer rid);

    /**
     * 查询所有地区
     * @return
     */
    @Select("select region from tab_housres")
    List<String> findAllRegion();

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(hid) from tab_housres")
    Integer findCount();

    /**
     * 查询单个用户的所有房源
     * @param uid
     * @return
     */
    @Select("select * from tab_housres where uid = #{uid}")
    List<Housres> findAllHousresByUid(Integer uid);
}
