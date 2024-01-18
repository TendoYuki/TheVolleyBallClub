import com.volleyball.club.login.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class TestLogin extends TestCase{
    private LoginManager login = LoginManager.getInstance();
    
    @Test
    public void testHashPassword() {
        Assertions.assertEquals("9d47755165724c274efa872531a8bfdeef3e58b03483d9d19b0a4ab7e67f8897",login.hashPassword("Shaun123456789!"));
    }

    @Test
    public void testIsConnectedFalse(){
        Assertions.assertFalse(login.isConnected());
    }

    @Test
    public void testIsConnectedTrue(){
        try{
            login.authentify("shaun","Shaun123456789!");
        }catch(Exception e){
            e.printStackTrace();
        }
        Assertions.assertTrue(login.isConnected());
    }

    @Test
    public void testDeauthentify(){
        login.deauthentify();
        Assertions.assertFalse(login.isConnected());
    }


}