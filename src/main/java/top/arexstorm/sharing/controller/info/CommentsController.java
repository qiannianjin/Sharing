package top.arexstorm.sharing.controller.info;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CustomerComments;
import top.arexstorm.sharing.bean.user.CustomerUser;
import top.arexstorm.sharing.service.info.CommentsService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.UUIDUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @ResponseBody
    @PostMapping(value = "/list")
    public AppResponse list(Short status, String userid, String informationid) {

        List<CustomerComments> list = commentsService.findAllComments(status, userid, informationid);

        return AppResponse.okList(list);
    }

    /**
     * 添加或者更新评论
     * @param comments
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public AppResponse addComments(@ModelAttribute(value="comments") Comments comments, HttpSession session) {

        CustomerUser customerUser = (CustomerUser) session.getAttribute("user");

        if (customerUser != null) {
            if (StringUtils.isNotBlank(comments.getCommentid())) { // 更新
                CustomerComments customerComments = commentsService.findCommentById(comments.getCommentid());
                if (customerComments != null) {
                    commentsService.updateComment(comments, comments.getCommentid());
                    return AppResponse.okData(null, 0, "更新成功", "/");
                } else {
                    return AppResponse.okData(-1, "更新的信息不存在");
                }
            } else { // 添加
                comments.setStatus(Short.parseShort("1"));
                comments.setUserid(customerUser.getUserid());
//TODO
                comments.setOrerid(null);
                comments.setCommentid(UUIDUtils.generateUUIDString());
                commentsService.addComment(comments);
                return AppResponse.okData(null, 0, "添加成功", "/");
            }

        }
        else {
            return AppResponse.okData(null, -1, "请登录", "/user/login");
        }
    }

    @ResponseBody
    @PostMapping(value = "/change")
    public AppResponse changeCommentsStatus(String commentid, Short status) {
        commentsService.updateCommentStatus(status, commentid);

        return AppResponse.okData(0, "修改成功!");
    }
}
