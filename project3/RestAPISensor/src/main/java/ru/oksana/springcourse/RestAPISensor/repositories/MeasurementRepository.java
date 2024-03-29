package ru.oksana.springcourse.RestAPISensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksana.springcourse.RestAPISensor.models.Measurement;
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
