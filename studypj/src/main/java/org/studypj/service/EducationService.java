package org.studypj.service;

import org.studypj.domain.EducationVO;
import org.studypj.domain.PersonalVO;

public interface EducationService {

    public boolean register(EducationVO education);

    public boolean remove(int education_group_no);

    public boolean modify(EducationVO education);

    public EducationVO get(int education_group_no);

}
