package ru.oksana.springcourse.RestAPISensor.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

    @Column(name = "value")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than +100")
    private double value;

    @NotNull
  private Boolean isRaining;

    private SensorDTO sensor;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }



    public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
