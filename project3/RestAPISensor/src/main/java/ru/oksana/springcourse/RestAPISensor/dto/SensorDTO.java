package ru.oksana.springcourse.RestAPISensor.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Name not should be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
