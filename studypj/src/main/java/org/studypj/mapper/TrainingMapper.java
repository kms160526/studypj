package org.studypj.mapper;

import org.studypj.domain.TrainingVO;

public interface TrainingMapper {

    // insert Training
    public int insert(TrainingVO training);

    // delete Training
    public int delete(int training_group_no);

    // update Training
    public int update(TrainingVO training);

    // read - Training
    public TrainingVO read(int training_group_no);


}
