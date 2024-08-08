package back.decorwell.Interface;

import back.decorwell.Enum.State;

import java.time.LocalDateTime;

public interface CategoryDetails {
    Long getId();
    String getName();
    State getState();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
