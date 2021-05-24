package org.studypj.domain;


import lombok.Data;

@Data
public class ResumeAttachVO {
    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;

    private int resume_no;
}
