package cn.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@TableName("test_model_new2")
public class TestModel {

    @TableField("my_name")
    private String myName;

    @TableField("create_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    /**
     * org.postgresql.util.PSQLException: Can't infer the SQL type to use for an instance of java.time.LocalDateTime. Use setObject() with an explicit Types value to specify the type to use.
     * 	at org.postgresql.jdbc2.AbstractJdbc2Statement.setObject(AbstractJdbc2Statement.java:2195) ~[postgresql-9.4-1206-jdbc42.jar:9.4]
     * LocalDatetime 错误, Date不报错
     */
    private LocalDateTime createDate;
}
