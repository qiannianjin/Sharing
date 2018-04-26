package top.arexstorm.sharing.service.info;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CustomerComments;
import top.arexstorm.sharing.utils.UUIDUtils;

import javax.xml.stream.events.Comment;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentsServiceTest {

    @Autowired
    private CommentsService commentsService;


    @Test
    public void findCommentById() {
        String commentid = "b13a7ec9251341e2ae862ec9c2404dd4";
        CustomerComments customerComments = commentsService.findCommentById(commentid);
        System.err.println(customerComments);
    }

    @Test
    public void findAllComments() {
        Short status = 1;
        String userid = "dd47e3c60200473ba40b6e535bd1a439";
        String informationid = "cce2d9601bcb4360a15801e11192e0ed";
        List<CustomerComments> list = commentsService.findAllComments(status, userid, informationid);
        for (CustomerComments customerComments : list) {
            System.err.println(customerComments);
        }
    }

    @Test
    public void addComment() {
        Comments comments = new Comments();
        comments.setContent("这是第four条评论");
        comments.setInformationid("cce2d9601bcb4360a15801e11192e0ed");
        comments.setOrerid(null);
        comments.setStatus(Short.parseShort("1"));
        comments.setUserid("ae4d62cf5d324e5aa14703b6dfb3b9cd");
        comments.setCommentid(UUIDUtils.generateUUIDString());

        commentsService.addComment(comments);
    }

    @Test
    public void updateCommentStatus() {
        Short status = 9;
        String commentid = "eb06a9835819438690d3240d575a9aeb";
        commentsService.updateCommentStatus(status, commentid);
    }

    @Test
    public void updateComment() {
        Comments comments = new Comments();
        comments.setContent("thank you ");
        comments.setStatus(Short.parseShort("1"));

        String commentid = "eb06a9835819438690d3240d575a9aeb";

        commentsService.updateComment(comments, commentid);
    }

    @Test
    public void deleteCommentById() {

        String commentid = "eb06a9835819438690d3240d575a9aeb";

        commentsService.deleteCommentById(commentid);
    }
}