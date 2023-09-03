package com.tperuch.edstest.stub;

import org.springframework.data.domain.PageRequest;

public class PageRequestStub {
    public static PageRequest getStub(){
        return PageRequest.of(0,5);
    }
}
