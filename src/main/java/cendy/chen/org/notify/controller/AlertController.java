package cendy.chen.org.notify.controller;

import cendy.chen.org.notify.result.Restfulesponse;
import cendy.chen.org.notify.service.AlertService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微信企业号告警通知
 */
@RestController
@RequestMapping("/alert")
public class AlertController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AlertService alertService;

    @PostMapping("/tag/{tag}")
    public Restfulesponse alertTag(
             @PathVariable("tag") String tag,
             @RequestParam("message") String message) {
        this.logger.info("alertTag 收到消息: tag:{}, message:{}", tag, message);
        alertService.alertTextToTag(message, tag);
        return Restfulesponse.success();
    }

    @PostMapping("/department/{department}")
    public Restfulesponse alertDepartment(
             @PathVariable("department") Integer department,
             @RequestParam("message") String message) {
        this.logger.info("alertTag 收到消息: department:{}, message:{}", department, message);
        alertService.alertTextToDepartment(message, department);
        return Restfulesponse.success();
    }

    @PostMapping("/users")
    public Object alertUsers(
             @RequestParam("users") List<String> users,
             @RequestParam("message") String message) throws WxErrorException {
        this.logger.info("alertUsers 收到消息: users:{}, message:{}", users, message);
        alertService.alertTextToUsers(message, users);
        return Restfulesponse.success();
    }

}