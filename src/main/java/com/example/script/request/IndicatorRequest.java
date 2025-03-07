package com.example.script.request;

import com.example.script.models.DateInterval;

import java.util.List;
import java.util.Objects;

public class IndicatorRequest {
    private List<String> entityCodes;
    private DateInterval instanceInterval;


    public IndicatorRequest(List<String> entityCodes, DateInterval instanceInterval) {
        this.entityCodes = entityCodes;
        this.instanceInterval = instanceInterval;
    }

    public IndicatorRequest() {
    }

    public List<String> getEntityCodes() {
        return entityCodes;
    }

    public void setEntityCodes(List<String> entityCodes) {
        this.entityCodes = entityCodes;
    }

    public DateInterval getInstanceInterval() {
        return instanceInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndicatorRequest that = (IndicatorRequest) o;
        return Objects.equals(entityCodes, that.entityCodes) && Objects.equals(instanceInterval, that.instanceInterval) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityCodes, instanceInterval);
    }

    @Override
    public String toString() {
        return "IndicatorRequest{" +
                "entityCodes=" + entityCodes +
                ", instanceInterval=" + instanceInterval +
                '}';
    }
}
