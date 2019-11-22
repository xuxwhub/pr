package com.dunshan.biz.service;

import com.dunshan.biz.mapper.UserMapper;
import com.dunshan.biz.model.User;
import com.dunshan.common.log.SystemLog;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author xuxinwei
 * @create 2019-10-18
 */
@Service
public class UserService {

  @Resource
  private UserMapper mapper;

  public List<User> listAll() {
    return mapper.selectAll();
  }

  public User getById(String id) {
    User u = new User();
    u.setId(id);
    return mapper.selectOne(u);
  }

  @SystemLog
  public Boolean add(User user) {
    return mapper.insertSelective(user) > 0;
  }

  @SystemLog
  public Boolean addMock() {
    Random random = new Random();
    Integer x = random.nextInt(10000);
    User mockUser = new User();
    mockUser.setEmail("test" + x + "@dunshan.com");
    mockUser.setMobile("1760000" + x);
    mockUser.setUserName("张三" + x);
    mockUser.setUserNumber("0000" + x);
    return add(mockUser);
  }

  @SystemLog
  public Boolean deleteById(String id) {
    User u = new User();
    u.setId(id);
    return mapper.delete(u) > 0;
  }

  @SystemLog
  public Boolean update(User user) {
    if (StringUtils.isEmpty(user.getId())) {
      throw new RuntimeException("id can not be null！");
    }
    return mapper.updateByPrimaryKeySelective(user) > 0;
  }


}
