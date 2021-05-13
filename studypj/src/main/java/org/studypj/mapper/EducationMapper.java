package org.studypj.mapper;

import org.studypj.domain.EducationVO;

public interface EducationMapper {

    // insert Education
    public int insert(EducationVO education);

    // delete Education
    public int delete(int education_group_no);

    // update Education
    public int update(EducationVO education);

    // read - Education
    public EducationVO read(int education_group_no);


}
