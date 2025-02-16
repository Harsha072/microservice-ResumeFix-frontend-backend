package com.backend.resume.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {

    @JsonProperty("parsed_text")
    private String resumeId;
     @JsonProperty("parsed_text")
    private String parsedText;
    private List<String> typos;
    private String status;
    public String getResumeId() {
        return resumeId;
    }
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
    public String getParsedText() {
        return parsedText;
    }
    public void setParsedText(String parsedText) {
        this.parsedText = parsedText;
    }
    public List<String> getTypos() {
        return typos;
    }
    public void setTypos(List<String> typos) {
        this.typos = typos;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

   
}
