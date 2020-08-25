package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.time.LocalDate;

/**
 * Contains the date when applications are due for a particular college. Will be made serializable
 * for the purpose of graphing due dates.
 */
@AutoValue
@GenerateTypeAdapter
public abstract class Deadline {
  public abstract LocalDate openingDate();

  public abstract LocalDate closingDate();

  public static Builder builder() {
    return new AutoValue_Deadline.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setOpeningDate(LocalDate value);

    public abstract Builder setClosingDate(LocalDate value);

    public abstract Deadline build();
  }
}
