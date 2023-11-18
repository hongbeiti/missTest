package cn.service;

import cn.model.TestModel;
import cn.mapper.TestModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestModelService implements ITestModelService {

    @Autowired
    private TestModelMapper testModelMapper;

    @Override
    public void add(TestModel testModel) {
        testModelMapper.insert(testModel);
    }
}
