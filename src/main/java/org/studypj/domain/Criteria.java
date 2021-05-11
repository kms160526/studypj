package org.studypj.domain;


import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {
    private int pageNum;
    private int amount;
    private int startRownum;

    // 기본 페이지 1, 10
    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
        this.startRownum = (pageNum-1) * amount;
    }

}
