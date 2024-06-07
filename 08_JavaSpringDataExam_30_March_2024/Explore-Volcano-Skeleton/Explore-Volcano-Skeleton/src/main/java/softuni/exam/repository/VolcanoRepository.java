package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.Optional;
import java.util.Set;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano, Long> {

    Optional<Volcano> findByName(String name);

    @Query("SELECT v FROM Volcano v \n" +
            "WHERE v.isActive = true\n" +
            "AND v.elevation > 3000\n" +
            "AND v.lastEruption IS NOT NULL\n" +
            "ORDER BY v.elevation DESC")
    Set<Volcano> findAllByVolcanoIsActiveAndAbove3000m();

    //Volcano findAllVolcanoById(long id);
}
