package br.com.caiobruno.restspring.converters;

public class NumberConverter {

    public static Double isRaiz(Double number){
        return Math.sqrt(number);
    }

    public static  Double isMedia(Double numberOne, Double numberTwo, Double numberTre) {
        return numberOne + numberTwo + numberTre;
    }

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;

        String number = strNumber.replaceAll(",",".");

        if (isNumeric(strNumber)) return Double.parseDouble(number);

        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",",".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
