package cn.service;

import cn.mapper.GameSkillsMapper;
import cn.mapper.TestModelMapper;
import cn.model.GameSkills;
import cn.model.TestModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameSkillsService implements IGameSkillsService {

    @Autowired
    private GameSkillsMapper gameSkillsMapper;

    @Override
    public List<GameSkills> search(String keyword, Integer skipNum) {
        List<String> keywords = splitString(keyword, skipNum);

        LinkedHashSet<Long> orderSet = new LinkedHashSet<>();
        for (String key : keywords) {
            List<GameSkills> list = searchBySkillName(key);
            orderSet.addAll( list.stream().map(GameSkills::getId).collect(Collectors.toSet()));
        }

        if(CollectionUtils.isEmpty(orderSet)){
            return new ArrayList<>();
        }

        List<GameSkills> result = searchBySkillIds(orderSet);
        return result;
//        String res = buildResult(result).toString();
//        return res;
    }

    @Override
    public List<GameSkills> getAllData() {
        QueryWrapper<GameSkills> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<GameSkills> list = gameSkillsMapper.selectList(queryWrapper);
        return list;
    }

    /**
     * 打印结果
     * @param result
     */
    private StringBuffer buildResult(List<GameSkills> result) {
        StringBuffer html = new StringBuffer();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>" +
                "    <title>课程表</title>\n" +
                "</head>" +
                "<body>");
        html.append("<table>");

        for (GameSkills sk: result) {
            html.append("<tr>");
            html.append("<td>");
            html.append("<img width="+"\"" + "40" + "\""+ " height=" + "\"" + "30" + "\""
                    + " src="+"\""+ "data:image/jpg;base64," + sk.getSkillImage() + "\"" +" />");
            html.append("</td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td>");
            html.append("<font size=\"3\">" + sk.getSkillName() + "</font>");
            html.append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
        return html;
    }

    private List<GameSkills> searchBySkillIds(LinkedHashSet<Long> orderSet) {
        QueryWrapper<GameSkills> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "skill_type_name", "skill_image", "skill_name");
        queryWrapper.in("id", orderSet);
        List<GameSkills> list = gameSkillsMapper.selectList(queryWrapper);
        return list;
    }

    private List<GameSkills> searchBySkillName(String key) {
        QueryWrapper<GameSkills> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.like("skill_name", key);
        List<GameSkills> list = gameSkillsMapper.selectList(queryWrapper);
        return list;
    }

    public static List<String> splitString(String str, int length) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i += length) {
            result.add(str.substring(i, Math.min(i + length, str.length())));
        }
        return result;
    }
}
