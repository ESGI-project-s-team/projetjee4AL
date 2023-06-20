package fr.esgi.rent.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnergyClassificationEntityTest {
    @Test
    void shouldHavePrivateFieldsWithPublicConstructor2()  {
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity(
                1,
                "Test Designation"
        );
        assertEquals(1, energyClassificationEntity.getId());
        assertEquals("Test Designation", energyClassificationEntity.getDesignation());
    }

    @Test
    void shouldHavePrivateFieldsWithPublicConstructor1()  {
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity(
                "Test Designation"
        );
        assertEquals("Test Designation", energyClassificationEntity.getDesignation());
    }
    @Test
    void shouldCreateInstanceWithNoArgumentConstructor() {
        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity();
        energyClassificationEntity.setId(1);
        energyClassificationEntity.setDesignation("Test Designation");
        assertEquals(1, energyClassificationEntity.getId());
        assertEquals("Test Designation", energyClassificationEntity.getDesignation());
        assertNotNull(energyClassificationEntity);
    }
}
