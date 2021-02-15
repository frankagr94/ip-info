package com.mercadolibre.ipinfo.integration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Language {

    private String iso639_1;

    private String iso639_2;

    private String name;

    private String nativeName;
}
