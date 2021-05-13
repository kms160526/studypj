package org.studypj.service;

import org.studypj.domain.PersonalVO;

public interface PersonalService {

    public boolean register(PersonalVO personal);

    public boolean remove(int personal_no);

    public boolean modify(PersonalVO personal);

    public PersonalVO get(int personal_no);


}
