package com.backend.resume.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {

    @JsonProperty("resumeId")
    private String resumeId;

    @JsonProperty("parsed_text") // Ensure this matches the JSON key
    private String parsedText;

    @JsonProperty("typos") // Add this if the JSON key is "typos"
    private List<String> typos;

    @JsonProperty("status") // Add this if the JSON key is "status"
    private String status;

    // Default constructor (required for Jackson deserialization)
    public Feedback() {
    }

    // Parameterized constructor
    public Feedback(String resumeId, String parsedText, List<String> typos, String status) {
        this.resumeId = resumeId;
        this.parsedText = parsedText;
        this.typos = typos;
        this.status = status;
    }

    // Getters and setters
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

    @Override
    public String toString() {
        return "Feedback{" +
                "resumeId='" + resumeId + '\'' +
                ", parsedText='" + parsedText + '\'' +
                ", typos=" + typos +
                ", status='" + status + '\'' +
                '}';
    }
}