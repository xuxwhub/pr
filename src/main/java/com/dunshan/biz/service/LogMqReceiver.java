package com.dunshan.biz.service;


import static com.dunshan.biz.util.CommonControllerInterceptor.TEST_FLAG;

import com.alibaba.fastjson.JSONObject;
import com.dunshan.biz.model.User;
import com.dunshan.biz.model.UserMqMessage;
import com.dunshan.biz.util.TestFlagHolder;
import com.dunshan.biz.util.UserOperationEnum;
import com.dunshan.biz.config.RabbitmqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
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
  public void receive(String log, @Header(TEST_FLAG) String testFlag) {
    logger.info("收到消息：{} {}", log, testFlag);
    TestFlagHolder.set(testFlag);
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
