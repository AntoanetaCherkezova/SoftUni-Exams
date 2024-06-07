package scubaDive;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class DivingTests {
    @Test(expected = NullPointerException.class)
    public void testCreateDivingExceptionWhenNullName() {
        new Diving(null, 30);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateDivingExceptionWhenEmptyName() {
        new Diving("         ", 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDivingExceptionWhenNegativeCapacity() {
        new Diving("valid", -10);
    }

    @Test
    public void testCreateDivingWithZeroCapacity() {
        new Diving("valid", 0);
    }

    @Test
    public void testCreateDivingWithValidNameAndPositiveCapacity() {
        new Diving("valid", 30);
    }

    @Test
    public void testGetName() {
        Diving valid = new Diving("valid", 30);

        assertEquals("valid", valid.getName());
    }

    @Test
    public void testGetCapacity() {
        Diving valid = new Diving("valid", 30);

        assertEquals(30, valid.getCapacity());
    }

    @Test
    public void testGetCountIsZeroOnEmptyDiving() {
        Diving valid = new Diving("valid", 30);

        assertEquals(0, valid.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddDiverWithSameName() {
        Diving valid = new Diving("valid", 30);
        DeepWaterDiver diver = new DeepWaterDiver("name", 30);

        valid.addDeepWaterDiver(diver);
        valid.addDeepWaterDiver(diver);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddDiverWithFullCapacity() {
        Diving valid = new Diving("valid", 2);
        DeepWaterDiver diver1 = new DeepWaterDiver("name1", 30);
        DeepWaterDiver diver2 = new DeepWaterDiver("name2", 30);
        DeepWaterDiver diver3 = new DeepWaterDiver("name3", 30);

        valid.addDeepWaterDiver(diver1);
        valid.addDeepWaterDiver(diver2);
        valid.addDeepWaterDiver(diver3);
    }

    @Test
    public void testAddDiversIncreasesCount() {
        Diving valid = new Diving("valid", 30);
        DeepWaterDiver diver1 = new DeepWaterDiver("name1", 30);
        DeepWaterDiver diver2 = new DeepWaterDiver("name2", 30);
        DeepWaterDiver diver3 = new DeepWaterDiver("name3", 30);

        valid.addDeepWaterDiver(diver1);
        valid.addDeepWaterDiver(diver2);
        valid.addDeepWaterDiver(diver3);

        assertEquals(3, valid.getCount());
    }

    @Test
    public void testRemoveDiverReturnsFalseForMissingName() {
        Diving valid = new Diving("valid", 30);
        DeepWaterDiver diver1 = new DeepWaterDiver("name1", 30);
        valid.addDeepWaterDiver(diver1);

        boolean result = valid.removeDeepWaterDiver("name2");

        assertFalse(result);
        assertEquals(1, valid.getCount());
    }

    @Test
    public void testRemoveDiverDecreasesCount() {
        Diving valid = new Diving("valid", 30);
        DeepWaterDiver diver1 = new DeepWaterDiver("name1", 30);
        DeepWaterDiver diver2 = new DeepWaterDiver("name2", 30);
        DeepWaterDiver diver3 = new DeepWaterDiver("name3", 30);

        valid.addDeepWaterDiver(diver1);
        valid.addDeepWaterDiver(diver2);
        valid.addDeepWaterDiver(diver3);

        boolean result = valid.removeDeepWaterDiver(diver1.getName());

        assertTrue(result);
        assertEquals(2, valid.getCount());
    }

    @Test
    public void testRemoveArcheologistOnEmptyExcavationWillNotThrow() {
        Diving valid = new Diving("valid", 30);

        boolean result = valid.removeDeepWaterDiver("some-name");

        assertFalse(result);
    }


}
