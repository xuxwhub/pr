package com.dunshan.biz.model;

import com.dunshan.biz.util.TestFlagHolder;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.IDynamicTableName;

/**
 * @author xuxinwei
 * @create 2019-11-21
 */
public class DynamicTableEntity implements IDynamicTableName {

  @Transient
  @Override
  public String getDynamicTableName() {
    StringBuilder tableName = new StringBuilder(this.getClass().getAnnotation(Table.class).name());
    if (!StringUtils.isEmpty(TestFlagHolder.get())) {
      tableName.append("_").append(TestFlagHolder.get());
    }
    return tableName.toString();
  }
}
