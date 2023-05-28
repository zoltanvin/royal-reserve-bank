package com.royal.reserve.bank.asset.management.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the {@link Asset} class.
 */
class AssetTest {

    /**
     * Test for the {@link Asset#equals(Object)} and {@link Asset#hashCode()} methods.
     */
    @Test
    void testEqualsAndHashCode() {
        // Given
        Asset asset1 = new Asset();
        asset1.setAssetCode("RUT");
        asset1.setAssetName("Russel 2000");
        asset1.setValue(1000);

        Asset asset2 = new Asset();
        asset2.setAssetCode("RUT");
        asset2.setAssetName("Russel 2000");
        asset2.setValue(1000);

        // When and Then
        assertEquals(asset1, asset2);
        assertEquals(asset1.hashCode(), asset2.hashCode());

        asset2.setValue(2000);
        assertNotEquals(asset1, asset2);
        assertNotEquals(asset1.hashCode(), asset2.hashCode());
    }
}
