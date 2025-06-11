package ua.opnu.practice1_template.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.opnu.practice1_template.entity.CarEntity;
import ua.opnu.practice1_template.entity.ServiceRecordEntity;
import ua.opnu.practice1_template.entity.ServiceTypeEntity;

@Repository
public interface ServiceRecordRepo extends JpaRepository<ServiceRecordEntity, Long>{
    
    @Query("""
        SELECT COALESCE(SUM(st.standardPrice), 0)
        FROM ServiceRecordEntity sr
        JOIN sr.serviceTypes st
        WHERE sr.car.id = :carId
        """)
    BigDecimal findTotalServiceCostByCarId(@Param("carId") Long carId);

    List<ServiceRecordEntity> findAllByMechanicId(@Param("mechanicId") Long mechanicId);

    @Query("SELECT sr.car FROM ServiceRecordEntity sr " +
           "GROUP BY sr.car " +
           "ORDER BY COUNT(sr) DESC")
    List<CarEntity> findTopCarsByServiceCount(Pageable pageable);

    List<ServiceRecordEntity> findByDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT st FROM ServiceRecordEntity sr " +
           "JOIN sr.serviceTypes st " +
           "GROUP BY st " +
           "ORDER BY COUNT(sr) DESC")
    List<ServiceTypeEntity> findTopServiceTypes(Pageable pageable);
}





 