package com.jtw.homestay.web.servlet;

import com.jtw.homestay.domain.ResultInfo;
import com.jtw.homestay.domain.RoomImg;
import com.jtw.homestay.service.Impl.RoomImgServiceImpl;
import com.jtw.homestay.service.RoomImgService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-10
 */
@WebServlet(name = "RoomImgServlet",urlPatterns = "/imageFile/*")
public class RoomImgServlet extends BaseServlet{
    
    private RoomImgService roomImgService =new RoomImgServiceImpl();

    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("/img/");

        File file= new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        ResultInfo info = new ResultInfo();
        info.setData("上传失败！");

        HttpSession session = request.getSession();

        for (FileItem item:fileItems) {
            if(item.isFormField()){

            }else{
                String fileName = item.getName();
                item.write(new File(path,fileName));
                item.delete();
                RoomImg roomImg =new RoomImg();
                String imgType = fileName.split("/")[0];
                switch (imgType) {
                    case "bigpic":
                        roomImg.setBigpic(fileName.split("/")[1]);
                        break;
                    case "smallpic1":
                        roomImg.setSmallpic1(fileName.split("/")[1]);
                        break;
                    case "smallpic2":
                        roomImg.setSmallpic2(fileName.split("/")[1]);
                        break;
                    case "smallpic3":
                        roomImg.setSmallpic3(fileName.split("/")[1]);
                        break;
                    case "smallpic4":
                        roomImg.setSmallpic4(fileName.split("/")[1]);
                        break;
                }
                //roomImgService.addRoomImg(roomImg);
                info.setData("img/"+fileName);
            }
        }
        writeValue(info,response);
    }
}
