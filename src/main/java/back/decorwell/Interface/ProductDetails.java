package back.decorwell.Interface;
import back.decorwell.Enum.State;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public interface ProductDetails {
    Long getId();
    String getName();
    String getDescription();
    Short getAmount();
    BigDecimal getPrice();
    State getState();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
