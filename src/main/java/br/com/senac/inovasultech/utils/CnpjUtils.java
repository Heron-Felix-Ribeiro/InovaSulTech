package br.com.senac.inovasultech.utils;

public class CnpjUtils {

    public static boolean cpnjValido (String cnpj) {

        String cnpjFormatado = cnpj.replaceAll("[^\\d]", "");

        return cnpjFormatado.length() == 14;
    }
}
