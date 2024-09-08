package cn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 Create table game_skills(
    id serial primary key,
    skill_type int,
    skill_type_name varchar(255),
    skill_name varchar(255),
    skill_image text,
    skill_url varchar(255)

 );
 */
@Data
@TableName("game_skills")
public class GameSkills {

    @TableField(value = "id")
    @TableId(type = IdType.AUTO) // 设置为自增ID
    private Long id;

    @TableField("skill_type")
    private Integer skillType;

    @TableField("skill_type_name")
    private String skillTypeName;

    @TableField("skill_name")
    private String skillName;

    @TableField("skill_image")
    private String skillImage;

    @TableField("skill_url")
    private String skillUrl;
}
