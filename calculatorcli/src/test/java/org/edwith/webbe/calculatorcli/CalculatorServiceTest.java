package org.edwith.webbe.calculatorcli;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CalculatorServiceTest {
	
	@Autowired
	CalculatorService calculatorService;
	
	@Before
	public void init() {
		this.calculatorService = new CalculatorService();
	}
	
	@Test
	public void plus() throws Exception {
		// given
		int value1 = 10;
		int value2 = 5;
		
		// when
		int result = calculatorService.plus(value1, value2);
		
		// then
		Assert.assertEquals(15, result); // 결과와 15가 같을 경우에만 성공
	}
	
    @Test
    public void divide() throws Exception{
        // given
        int value1 = 10;
        int value2 = 5;

        // when
        int result = calculatorService. divide (value1, value2);

        // then
        Assert.assertEquals(3, result); // 결과와 2가 같을 경우에만 성공
    }
    
    @Test
    public void divideExceptionTest() throws Exception {
    	// given
    	int value1 = 10;
    	int value2 = 0;
    	
    	try {
    		
    	} catch (ArithmeticException e) {
    		Assert.assertTrue(true); // 이 부분이 실행되었다면 성공
    		return;
    	}
    	
    	Assert.assertFalse(false);
    }
}
