package cn.service;

import cn.model.GameSkills;
import cn.model.TestModel;

import java.util.List;

public interface IGameSkillsService {

    List<GameSkills> search(String keyword, Integer skipNum);

    List<GameSkills> getAllData();
}
