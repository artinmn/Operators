//enum Operator {+, -, *, /};
//import Operators
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.junit.Assert;
@RunWith(Parameterized.class)
public class OperatorsTest {
    @Parameters
    public static Object[][] data() { return new Object[][] { 
        { 3, 3, 4, 0, 1, 2, "/", null }, { 3, 3, 4, 0, 1, 2, "+", null },
        { 3, 3, 4, 0, 1, 2, "*", null }, { 3, 3, 4, 0, 1, 2, "-", null },
        { 0, 1, 4, 1, 1, 3, "-", null }, { 0, 0, 1, 0, 0, 2, "*", null },
        { 0, 3, 1, 0, 4, 1, "*", null }, { 0, 3, 1, 0, 4, 1, "-", null },
        { 0, 3, 1, 0, 4, 1, "-", null }  };
    }

    private Integer w1;
    private Integer n1;
    private Integer d1;
    private Integer w2;
    private Integer n2;
    private Integer d2;
    private String opr;
    private Float exp; 

    public OperatorsTest(Integer whl1, Integer num1, Integer den1, 
        Integer whl2, Integer num2, Integer den2, String operation, Float expected) {
        w1 = whl1;
        n1 = num1;
        d1 = den1;
        w2 = whl2;
        n2 = num2;
        d2 = den2;
        opr = operation;
        exp = expected;
    }
    @Test
    public void givenInput_whenCalculateOperation_thenReturnResult() {
        String num1 = Integer.toString(w1) + "_" + Integer.toString(n1) + "/" + Integer.toString(d1);
        String num2 = Integer.toString(w2) + "_" + Integer.toString(n2) + "/" + Integer.toString(d2);
        Integer[] result = new Operators().calculateOperation(num1, opr, num2);
        System.out.println(result[0] + "_" + result[1] + "/" + result[2]);
        Float actualResult = result[3].floatValue()*(result[0]*result[2]+result[1])/result[2].floatValue();
        Float expectedResult = (float) 0;
        if (exp == null) {
            Float left = (w1*d1+n1)/d1.floatValue();
            Float right = (w2*d2+n2)/d2.floatValue();
            if (opr.equals("*"))
                expectedResult = left * right;
            else if (opr.equals("/"))
                expectedResult = left / right;
            else if (opr.equals("+"))
                expectedResult = left + right;
            else if (opr.equals("-"))
                expectedResult = left - right;
        }
        else
            expectedResult = exp;
        System.out.println(expectedResult + " = " + actualResult);
        Assert.assertEquals(expectedResult, actualResult); 
    }
  }