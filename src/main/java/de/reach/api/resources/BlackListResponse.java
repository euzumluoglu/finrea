package de.reach.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListResponse {

    @JsonProperty("blacklist")
    private List<String> blackList;

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    @Override
    public String toString() {
        return "BlackListResponse{" +
                "blackList=" + blackList +
                '}';
    }
}
