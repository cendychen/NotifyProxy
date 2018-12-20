package cendy.chen.org.notify.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 企业微信管理
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxCpService wxCpService;

    @GetMapping("/cp/tagList")
    public Object tagList() throws WxErrorException {
        this.logger.info("tag 收到消息");
        return wxCpService.getTagService().listAll();
    }

    @GetMapping("/cp/tag/{tagId}/userList")
    public Object tagUsers(@PathVariable("tagId") String tagId) throws WxErrorException {
        this.logger.info("tagUsers 收到消息. tag:{}", tagId);
        return wxCpService.getTagService().listUsersByTagId(tagId);
    }

    @GetMapping("/cp/departmentList")
    public Object departmentList( @RequestParam(value = "departmentId", required = false) Integer departmentId) throws WxErrorException {
        this.logger.info("ApiResponse 收到消息. departmentId:{}", departmentId);
        return wxCpService.getDepartmentService().list(departmentId);
    }

    @GetMapping("/cp/department/{departmentId}/userList")
    public Object departmentUsers(@PathVariable(value = "departmentId") Integer departmentId) throws WxErrorException {
        this.logger.info("ApiResponse 收到消息. departmentId:{}", departmentId);
        return wxCpService.getUserService().listSimpleByDepartment(departmentId, true, 0);
    }

}