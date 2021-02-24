package com.mercadolibre.ipinfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "country_stat_info",
        uniqueConstraints = {@UniqueConstraint(columnNames={"iso_code", "name"})},
        indexes = {
                @Index(name = "fn_country_stat_info_index_iso_code", columnList = "iso_code"),
                @Index(name = "fn_country_stat_info_index_name", columnList = "name"),
                @Index(name = "fn_country_stat_info_index_unique_iso_code", columnList = "iso_code", unique = true),
                @Index(name = "fn_country_stat_info_index_unique_name", columnList = "name", unique = true),
                @Index(name = "fn_country_stat_info_index_multi", columnList = "iso_code, name")
        })
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatInfo {

    @Id
    @Column(unique = true, updatable = false, name = "iso_code", nullable = false, length=3)
    private String isoCode;

    @Column(unique = true, name = "name", nullable = false, length=100)
    private String name;

    @Column(name = "distance", nullable = false, columnDefinition="double precision default '0'")
    private Double distance;

    @Column(name = "invocations", nullable = false, columnDefinition = "integer default 0")
    private Integer invocations;

}
