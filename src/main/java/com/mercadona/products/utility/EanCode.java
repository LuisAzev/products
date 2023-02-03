package com.mercadona.products.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EanCode {

    private final static String EAN_CODE_REGEX = "^(\\d{7})(\\d{5})(\\d{1})";

    private Integer providerCode;

    private Integer productCode;

    private Integer destinyCode;

    public EanCode(BigInteger completeEanCode){

        Pattern pattern = Pattern.compile(EAN_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(completeEanCode));

        if (matcher.find()){
            this.providerCode = Integer.valueOf(matcher.group(1));
            this.productCode = Integer.valueOf(matcher.group(2));
            this.destinyCode = Integer.valueOf(matcher.group(3));
        }

    }

    public BigInteger getCompleteEanCode(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(providerCode);
        stringBuilder.append(productCode);
        stringBuilder.append(destinyCode);

        return new BigInteger(stringBuilder.toString());
    }

}
