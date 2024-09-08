package cn.mapper;

import cn.model.GameSkills;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface GameSkillsMapper extends BaseMapper<GameSkills> {

    List<GameSkills> searchByKeyWord(String key);
}
