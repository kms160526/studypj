package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.TrainingVO;
import org.studypj.mapper.TrainingMapper;

@Service
@Log4j
public class TrainingServiceImpl implements TrainingService{

    @Setter(onMethod_ = @Autowired)
    private TrainingMapper trainingMapper;


    @Override
    public boolean register(TrainingVO training) {

        log.info("training register..........");

        boolean registerResult = trainingMapper.insert(training) == 1;

        return registerResult;
    }

    @Override
    public boolean remove(int training_group_no) {

        log.info("training remove..........");

        boolean removeResult = trainingMapper.delete(training_group_no) == 1;

        return removeResult;
    }

    @Override
    public boolean modify(TrainingVO training) {

        log.info("training modify............ targetNo -> " + training.getTraining_group_no());

        boolean modifyResult = trainingMapper.update(training) == 1;

        return modifyResult;
    }

    @Override
    public TrainingVO get(int training_group_no) {

        log.info("training get.......... targetNo -> " + training_group_no);

        return trainingMapper.read(training_group_no);
    }
}
