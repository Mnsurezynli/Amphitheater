package org.example.Repository;

import org.example.Model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {

    void deleteById(Long reserveId);
}
