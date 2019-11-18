package com.dunshan.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/***
 * @Description rabbitmq统一配置
 * @Author xuxinwei
 * @Date 2018/7/5 上午11:45
 */
@Configuration
@Component
public class RabbitmqConfig {

  /**
   * queue
   */
  public static final String QUEUE_LOG = "q.pr.log";

  /**
   * exchange-common
   */
  public static final String EXCHANGE_COMMON = "e.common.direct";

  /**
   * Routing-Key
   */
  public static final String ROUTING_KEY_LOG = "k.direct.pr.log";


  /**
   * queue
   */
  @Bean
  public Queue logQueue() {
    Queue queue = new Queue(QUEUE_LOG);
    return queue;
  }

  /**
   * exchange
   */
  @Bean
  DirectExchange commonExchange() {
    DirectExchange directExchange = new DirectExchange(EXCHANGE_COMMON);
    return directExchange;
  }


  @Bean
  Binding bindingLogExchange(Queue logQueue, DirectExchange commonExchange) {
    Binding binding = BindingBuilder.bind(logQueue).to(commonExchange)
        .with(ROUTING_KEY_LOG);
    return binding;
  }


}
