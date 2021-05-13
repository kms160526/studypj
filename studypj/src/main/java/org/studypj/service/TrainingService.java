package org.studypj.service;

import org.studypj.domain.EducationVO;
import org.studypj.domain.TrainingVO;

import java.util.Date;

public interface TrainingService {

    public boolean register(TrainingVO training);

    public boolean remove(int training_group_no);

    public boolean modify(TrainingVO training);

    public TrainingVO get(int training_group_no);

}
