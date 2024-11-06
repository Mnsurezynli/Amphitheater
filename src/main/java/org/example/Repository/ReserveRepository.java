package org.example.Repository;

import org.example.Model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    Reserve saveAndFlush(Reserve reserve);
    void deleteById(Long id);
    Optional<Reserve> findById(Long id);

    Optional<Reserve> findByUserIdAndConferenceId(Long userId, Long conferenceId);
}
