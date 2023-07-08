package dto;



public class MeasurementDTO {


    private double value;

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
