package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalStatementVO {

    private int personal_statement_no;
    private String home_environment;
    private String pros_and_cons;
    private String experiance;
    private String job_espirations;

    private Date regdate;

}
