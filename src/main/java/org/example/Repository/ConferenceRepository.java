package org.example.Repository;

import org.example.Model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference,Long> {

    Optional<Conference> findById(Long id);
}
