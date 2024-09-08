package cn.enums;

import lombok.Data;

public enum SkillType {

    Mount("坐骑技能", 1),
    Assistant("辅助技能", 2),
    Story("剧情技能", 3),
    Sect("门派技能", 4);

    public String getName() {
        return name;
    }

    private String name;

    public Integer getCode() {
        return code;
    }

    private Integer code;

    SkillType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static SkillType getSkillTypeByName(String name){
        for(SkillType skillType : SkillType.values()){
            if (skillType.getName().equals(name)){
                return skillType;
            }
        }
        return SkillType.Sect;
    }

}
