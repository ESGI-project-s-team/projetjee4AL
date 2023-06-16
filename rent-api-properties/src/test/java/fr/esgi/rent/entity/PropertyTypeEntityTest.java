package fr.esgi.rent.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;


public class PropertyTypeEntityTest {
    @Test
    void shouldHavePrivateFieldsWithPublicConstructor2()  {
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(
                1,
                "Test Designation"
        );
        assertEquals(1, propertyTypeEntity.getId());
        assertEquals("Test Designation", propertyTypeEntity.getDesignation());
    }

    @Test
    void shouldHavePrivateFieldsWithPublicConstructor1()  {
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity(
                "Test Designation"
        );
        assertEquals("Test Designation", propertyTypeEntity.getDesignation());
    }
    @Test
    void shouldCreateInstanceWithNoArgumentConstructor() {
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity();
        propertyTypeEntity.setId(1);
        propertyTypeEntity.setDesignation("Test Designation");
        assertEquals(1, propertyTypeEntity.getId());
        assertEquals("Test Designation", propertyTypeEntity.getDesignation());
        assertNotNull(propertyTypeEntity);
    }
}
