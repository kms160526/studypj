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

    // redirect 처리를 위한 메서드
    public String getListLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.getAmount());

        return builder.toUriString();

    }
}
