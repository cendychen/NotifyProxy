package cendy.chen.org.notify.controller;

import cendy.chen.org.notify.annotation.Anonymous;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
@Anonymous
public class CommonController {

    /**
     * 获取系统时间，避免客户端时间不一致
     */
    @GetMapping("current")
    public Long current() {
        return System.currentTimeMillis();
    }
}
