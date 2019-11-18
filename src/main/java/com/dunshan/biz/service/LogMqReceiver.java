package com.dunshan.biz.service;


import com.alibaba.fastjson.JSONObject;
import com.dunshan.biz.model.User;
import com.dunshan.biz.model.UserMqMessage;
import com.dunshan.biz.utils.UserOperationEnum;
import com.dunshan.common.config.RabbitmqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuxinwei
 * @create 2019-10-21
 */
@Component
public class LogMqReceiver {

  private static final Logger logger = LoggerFactory.getLogger(LogMqReceiver.class);

  @Autowired
  private UserService userService;

  @RabbitHandler
  @RabbitListener(queues = RabbitmqConfig.QUEUE_LOG, containerFactory="rabbitListenerContainerFactory")
  public void receive(String log) {
    logger.info("收到消息：" + log);
    try {
      UserMqMessage message = JSONObject.parseObject(log, UserMqMessage.class);
      User user = message.getData();
      if (UserOperationEnum.add.name().equals(message.getOperation())) {
        userService.add(user);
      }
      if (UserOperationEnum.update.name().equals(message.getOperation())) {
        userService.update(user);
      }
      if (UserOperationEnum.delete.name().equals(message.getOperation())) {
        userService.deleteById(user.getId());
      }
    } catch (Exception e) {
      logger.error("收到消息处理异常：" + log, e);
    }

  }

}
