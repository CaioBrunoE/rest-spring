package br.com.caiobruno.restspring.math;

import br.com.caiobruno.restspring.converters.NumberConverter;


public class SimpleMath {

    public Double sum (Double numberOne,  Double numberTwo ) {
        return numberOne + numberTwo;
    }

    public Double sub(Double numberOne, Double numberTwo ) {
        return numberOne - numberTwo;
    }


    public Double mult( Double numberOne ,  Double numberTwo) {
        return numberOne * numberTwo;
    }


    public Double div(Double numberOne , Double numberTwo) {
        return numberOne / numberTwo;
    }


    public Double media(Double numberOne ,  Double numberTwo, Double numberTre) {
        Double valor= NumberConverter.isMedia(numberOne, numberTwo, numberTre);
        return valor/3;
    }

    public Double raiz( Double number ) {
        return NumberConverter.isRaiz(number) ;
    }
}
