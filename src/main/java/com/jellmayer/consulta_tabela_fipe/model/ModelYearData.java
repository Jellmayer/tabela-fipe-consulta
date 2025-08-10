package com.jellmayer.consulta_tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record ModelYearData (String brand,
                             String model,
                             String codeFipe,
                             Integer modelYear,
                             String price) {}
