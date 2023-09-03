package com.tperuch.edstest.stub;

import com.tperuch.edstest.dto.SearchVehicleCriteria;

public class SearchVehicleCriteriaStub {
    public static SearchVehicleCriteria getStub(){
        SearchVehicleCriteria searchVehicleCriteria = new SearchVehicleCriteria();
        searchVehicleCriteria.setField("brand");
        searchVehicleCriteria.setValue("Audi");
        searchVehicleCriteria.setOperator("=");
        return searchVehicleCriteria;
    }
}
