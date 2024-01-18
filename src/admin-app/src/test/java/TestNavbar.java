import com.volleyball.club.models.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.JMenu;

import junit.framework.TestCase;

public class TestNavbar extends TestCase{
    private NavbarModel navbar = new NavbarModel();
    private JMenu testMenu = new JMenu("Test");
    @Test
    public void testNavbarModel() {
        Assertions.assertTrue(navbar.getMenus().isEmpty());
    }

    @Test
    public void testAddModel() {
        navbar.addMenu(testMenu);
        Assertions.assertFalse(navbar.getMenus().isEmpty());
    }

    @Test
    public void testRemoveModel() {
        navbar.removeMenu(testMenu);
        Assertions.assertTrue(navbar.getMenus().isEmpty());
    }

}