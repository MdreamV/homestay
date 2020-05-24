package com.jtw.homestay.web.servlet;

import com.jtw.homestay.domain.*;
import com.jtw.homestay.service.*;
import com.jtw.homestay.service.Impl.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-03
 */
@WebServlet(name = "housresServlet", urlPatterns = "/housres/*")
public class HousresServlet extends BaseServlet {

    private HousresService housresService = new HousresServiceImpl();
    private RegionService regionService = new RegionServiceImpl();
    private RoomImgService roomImgService = new RoomImgServiceImpl();
    private CommentService commentService = new CommentServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 查询所有地区
     *
     * @param request
     * @param response
     */
    public void findAllRegion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Region> regions = regionService.findAll();
        System.out.println(regions);
        writeValue(regions, response);
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void findLimitByRegion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ridStr = request.getParameter("rid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int rid = 1;
        if (ridStr != null && !"null".equals(ridStr) && !"".equals(ridStr)) {
            rid = Integer.parseInt(ridStr);
        }
        int pageSize = 1;
        if (pageSizeStr != null && !"".equals(pageSize) && !"null".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int currentPage = 1;
        if (currentPageStr != null && !"".equals(currentPageStr) && !"null".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        PageBean<Housres> pageBean = housresService.findLimit(rid, currentPage, pageSize);
        writeValue(pageBean, response);
    }

    /**
     * 根据房间id查询
     *
     * @param request
     * @param response
     */
    public void findHousresByHid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String hid = request.getParameter("hid");
        Housres housres = housresService.findByHid(Integer.parseInt(hid));
        writeValue(housres, response);
    }

    /**
     * 发布房源
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void releaseHousres(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        String hname = request.getParameter("hname");
        String hdetail = request.getParameter("hdetail");
        String bedtype = request.getParameter("bedtype");
        String bedcount = request.getParameter("bedcount");
        String checkincount = request.getParameter("checkincount");
        String harea = request.getParameter("harea");
        String hprice = request.getParameter("hprice");
        String detailregion = request.getParameter("detailregion");
        String bigpic = request.getParameter("bigpic");
        String smallpic1 = request.getParameter("smallpic1");
        String smallpic2 = request.getParameter("smallpic2");
        String smallpic3 = request.getParameter("smallpic3");
        String smallpic4 = request.getParameter("smallpic4");

        Housres housres = new Housres();
        housres.setRid(Integer.parseInt(rid));
        housres.setHname(hname);
        housres.setHdetail(hdetail);
        housres.setBedtype(bedtype);
        housres.setBedcount(Integer.parseInt(bedcount));
        housres.setCheckincount(Integer.parseInt(checkincount));
        housres.setHarea(Double.parseDouble(harea));
        housres.setHprice(Double.parseDouble(hprice));
        housres.setDetailregion(detailregion);

        RoomImg roomImg = new RoomImg();
        roomImg.setBigpic(bigpic);
        roomImg.setSmallpic1(smallpic1);
        roomImg.setSmallpic2(smallpic2);
        roomImg.setSmallpic3(smallpic3);
        roomImg.setSmallpic4(smallpic4);

        User user = (User) request.getSession().getAttribute("user");
        housres.setUid(user.getUid());

        Boolean aBoolean = housresService.addRoom(housres);
        roomImg.setHid(housres.getHid());
        Boolean aBoolean1 = roomImgService.addRoomImg(roomImg);
        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        if (aBoolean) {
            info.setFlag(true);
        }
        writeValue(info, response);
    }

    /**
     * 根据用户查询房源
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void findAllHousresByUid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Housres> housresList = housresService.findAllHousresByUid(user.getUid());
        writeValue(housresList, response);
    }

    /**
     *
     * 根据id删除房源
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteHourseByHid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer hid = Integer.valueOf(request.getParameter("hid"));
        Boolean deleteHousreByHid = housresService.deleteHousreByHid(hid);
        Boolean deleteCommentByHid = commentService.deleteCommentByHid(hid);
        Boolean deleteImgByHid = roomImgService.deleteImgByHid(hid);
        Boolean deleteOrderByHid = orderService.deleteOrderByHid(hid);

        ResultInfo info =new ResultInfo();
        info.setFlag(false);
        if(deleteHousreByHid){
            info.setData("删除成功！");
            info.setFlag(true);
        }else{
            info.setData("删除失败！");
        }
        writeValue(info, response);
    }

}
