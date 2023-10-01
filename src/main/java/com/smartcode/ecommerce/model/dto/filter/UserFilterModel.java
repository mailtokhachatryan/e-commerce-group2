package com.smartcode.ecommerce.model.dto.filter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Setter
@Getter
public class UserFilterModel {

    private Filter filter;
    private Search search;

    @Setter
    @Getter
    public static class Filter {
        @Positive
        private Integer startAge;
        @Positive
        private Integer endAge;
        private Boolean isVerified;
    }

    @Setter
    @Getter
    public static class Search {
        private String text;
    }

}
