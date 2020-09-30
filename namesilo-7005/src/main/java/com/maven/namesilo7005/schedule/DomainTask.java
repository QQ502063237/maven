package com.maven.namesilo7005.schedule;


import com.maven.common.utils.DateUtil;
import com.maven.namesilo7005.service.TbDomainService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ProjectName: agent
 * @Package: com.wssbdc.modles.business.Schedule
 * @ClassName: PartnerTask
 * @Author: zzy
 * @Description: 合伙人金额定时任务
 * @Date: 2020/7/1 9:59
 * @Version: 1.0
 * cron表达式 [秒] [分] [小时] [日] [月] [周] [年]
 * “30 * * * * ?” 每半分钟触发任务
 * “30 10 * * * ?” 每小时的10分30秒触发任务
 * “30 10 1 * * ?” 每天1点10分30秒触发任务
 * “30 10 1 20 * ?” 每月20号1点10分30秒触发任务
 * “30 10 1 20 10 ? *” 每年10月20号1点10分30秒触发任务
 * “30 10 1 20 10 ? 2011” 2011年10月20号1点10分30秒触发任务
 * “30 10 1 ? 10 * 2011” 2011年10月每天1点10分30秒触发任务
 * “30 10 1 ? 10 SUN 2011” 2011年10月每周日1点10分30秒触发任务
 * “15,30,45 * * * * ?” 每15秒，30秒，45秒时触发任务
 * “15-45 * * * * ?” 15到45秒内，每秒都触发任务
 * “15/5 * * * * ?” 每分钟的每15秒开始触发，每隔5秒触发一次
 * “15-30/5 * * * * ?” 每分钟的15秒到30秒之间开始触发，每隔5秒触发一次
 * “0 0/3 * * * ?” 每小时的第0分0秒开始，每三分钟触发一次
 * “0 15 10 ? * MON-FRI” 星期一到星期五的10点15分0秒触发任务
 * “0 15 10 L * ?” 每个月最后一天的10点15分0秒触发任务
 * “0 15 10 LW * ?” 每个月最后一个工作日的10点15分0秒触发任务
 * “0 15 10 ? * 5L” 每个月最后一个星期四的10点15分0秒触发任务
 * “0 15 10 ? * 5#3” 每个月第三周的星期四的10点15分0秒触发任务
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class DomainTask {
    @Resource
    TbDomainService tbDomainService;

    //定时查询域名更新
    @Scheduled(cron="30 40 23 * * ?")
    public void addDomain() {
        try {
            String time = DateUtil.dateToString(new Date(System.currentTimeMillis()), "yyyy-MM-dd 'at' HH:mm:ss z");
            log.info("凌晨更新域名数据,23:50:30 定时器运行");
            tbDomainService.upAll(3);
            log.info(time+"域名插入成功.");
        }catch (Exception e){
            String time = DateUtil.dateToString(new Date(System.currentTimeMillis()), "yyyy-MM-dd 'at' HH:mm:ss z");
            e.printStackTrace();
            log.info(time+"域名插入异常");
        }
    }




}
