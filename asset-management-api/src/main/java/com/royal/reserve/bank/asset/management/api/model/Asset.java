package com.royal.reserve.bank.asset.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents an asset.
 */
@Entity
@Table(name = "t_asset")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assetCode;
    private String assetName;
    private int value;

    /**
     * Overrides the equals method to provide custom comparison logic for testing purposes.
     * <p>
     * This override ensures that the expected and actual Asset objects are considered equal
     * when they have the same assetCode, assetName, and value properties.
     * <p>
     * This is necessary to resolve the error where the expected and actual Asset
     * objects were not considered equal due to reference inequality.
     *
     * @param obj the object to compare for equality
     * @return {@code true} if the objects are considered equal based on their properties, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Asset other = (Asset) obj;
        return Objects.equals(assetCode, other.assetCode) &&
                Objects.equals(assetName, other.assetName) &&
                value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetCode, assetName, value);
    }
}
