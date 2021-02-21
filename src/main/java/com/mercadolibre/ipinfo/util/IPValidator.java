package com.mercadolibre.ipinfo.util;

import org.apache.commons.validator.routines.InetAddressValidator;

public class IPValidator {

    private static final InetAddressValidator validator
            = InetAddressValidator.getInstance();

    public static boolean isValid(final String ip) {
        return validator.isValid(ip);
    }

}
