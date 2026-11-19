/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgPoEPart1TEST {

    @Test
    public void testCheckUsernameTrue() {
        boolean result = ProgPOEPART1.checkUsername("tha_1");
        assertEquals(true, result);
    }

    @Test
    public void testCheckUsernameFalse() {
        boolean result = ProgPOEPART1.checkUsername("thabo!!!!!!!");
        assertEquals(false, result);
    }

    @Test
    public void testPasswordTrue() {
        boolean result = ProgPOEPART1.checkPasswordComplexity("Ch&&sec@ke99!");
        assertEquals(true, result);
    }

    @Test
    public void testPasswordFalse() {
        boolean result = ProgPOEPART1.checkPasswordComplexity("password");
        assertEquals(false, result);
    }

    @Test
    public void testCellTrue() {
        boolean result = ProgPOEPART1.checkCellPhoneNumber("+27838968976");
        assertEquals(true, result);
    }

    @Test
    public void testCellFalse() {
        boolean result = ProgPOEPART1.checkCellPhoneNumber("08966553");
        assertEquals(false, result);
    }
}
