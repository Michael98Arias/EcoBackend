package back.decorwell.Repository;

import back.decorwell.Entity.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketingRepository extends JpaRepository<Marketing, Long> {

}
