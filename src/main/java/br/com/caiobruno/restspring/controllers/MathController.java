package br.com.caiobruno.restspring.controllers;

import br.com.caiobruno.restspring.converters.NumberConverter;
import br.com.caiobruno.restspring.exceptions.UnsupportedMathOperationException;
import br.com.caiobruno.restspring.math.SimpleMath;
import org.springframework.web.bind.annotation.*;




@RestController
public class MathController {
    private  SimpleMath  math = new SimpleMath();

@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",method = RequestMethod.GET )
    public Double sum (@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo ) throws Exception{

    Double numConv = NumberConverter.convertToDouble(numberOne);
    Double numConv1 = NumberConverter.convertToDouble(numberTwo);

    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
        throw new UnsupportedMathOperationException("Please set a numeric value");
    }
     return math.sum(numConv,numConv1);
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}",method = RequestMethod.GET )
    public Double sub(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo ) throws Exception{

        Double numConv = NumberConverter.convertToDouble(numberOne);
        Double numConv1 = NumberConverter.convertToDouble(numberTwo);

        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return math.sub(numConv,numConv1);
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}")
    public Double mult(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        Double numConv = NumberConverter.convertToDouble(numberOne);
        Double numConv1 = NumberConverter.convertToDouble(numberTwo);

        if (!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)){
         throw new UnsupportedMathOperationException("Please set a numeric value");
     }

     return math.mult(numConv,numConv1);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo) throws Exception{

        Double numConv = NumberConverter.convertToDouble(numberOne);
        Double numConv1 = NumberConverter.convertToDouble(numberTwo);

        if (!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return math.div(numConv, numConv1);
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}/{numberTre}")
    public Double media(@PathVariable(value = "numberOne") String numberOne , @PathVariable(value = "numberTwo") String numberTwo,@PathVariable(value = "numberTre") String numberTre) throws Exception{

        Double numConv = NumberConverter.convertToDouble(numberOne);
        Double numConv1 = NumberConverter.convertToDouble(numberTwo);
        Double numConv2 = NumberConverter.convertToDouble(numberTre);


        if (!NumberConverter.isNumeric(numberOne)|| !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return NumberConverter.isMedia(numConv,numConv1,numConv2)/3;

    }

    @RequestMapping(value = "/raiz/{number}")
    public Double raiz(@PathVariable(value = "number") String number ) throws Exception{

    Double numCov = NumberConverter.convertToDouble(number);

    if (!NumberConverter.isNumeric(number)){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return NumberConverter.isRaiz(numCov) ;
    }

};