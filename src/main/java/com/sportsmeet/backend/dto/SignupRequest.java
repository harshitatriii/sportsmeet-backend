package com.sportsmeet.backend.dto;

import com.sportsmeet.backend.model.Sport;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String city;
    private String profileImageUrl;
    private Set<Sport> preferredSports;

}
