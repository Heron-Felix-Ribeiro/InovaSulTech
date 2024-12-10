package br.com.senac.inovasultech.utils;

public class CepUtils {
    public static boolean CepValido(String cep) {
        return cep != null && cep.matches("\\d{5}-\\d{3}|\\d{8}");
    }
}
