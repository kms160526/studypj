package org.studypj.mapper;

import org.studypj.domain.PersonalVO;

public interface PersonalMapper {

    // insert Resume
    public int insert(PersonalVO personal);

    // delete Resume
    public int delete(int personal_no);

    // update Resume
    public int update(PersonalVO personal);

    // read - selectOne
    public PersonalVO read(int personal_no);

}
