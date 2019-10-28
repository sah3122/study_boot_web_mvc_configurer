package me.study.study_boot_web_mvc_confiturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dongchul on 2019-10-28.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
