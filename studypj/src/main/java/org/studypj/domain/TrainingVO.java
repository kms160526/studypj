package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TrainingVO {

    private int training_group_no;
    private int training_no;
    private String training_term;
    private String training_name;
    private int training_completion;

    private Date regdate;
}
