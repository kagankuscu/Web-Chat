package com.Feelfree2code.STA.subStructure;

import com.Feelfree2code.STA.model.domain.PartBuyHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartBuyHistoryRepository extends JpaRepository<PartBuyHistoryDTO, Integer> {
}
