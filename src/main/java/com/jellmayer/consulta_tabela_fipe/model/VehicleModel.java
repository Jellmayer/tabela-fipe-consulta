package com.jellmayer.consulta_tabela_fipe.model;

import java.util.HashMap;
import java.util.Map;

public class VehicleModel {
    private String brand = "";
    private String model;
    private String codeFipe;
    private Map<Integer,String> priceYearMap = new HashMap<Integer, String>();

    public void fetchData(ModelYearData yearData){
        if (this.brand.isEmpty()){
            this.brand = yearData.brand();
            this.model = yearData.model();
            this.codeFipe = yearData.codeFipe();
        }

        this.priceYearMap.put(yearData.modelYear(), yearData.price());
    }

    @Override
    public String toString() {
        return "Modelo " + codeFipe +
                brand +
                " - " + model +
                "\nPreços: " + priceYearMap;
    }
}
