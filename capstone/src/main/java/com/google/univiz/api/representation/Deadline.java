package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;

/**
 * Contains the date when applications are due for a particular college. Will be made serializable
 * for the purpose of graphing due dates.
 */
@AutoValue
@GenerateTypeAdapter
public abstract class Deadline {
  public abstract int openingMonth();

  public abstract int openingDay();

  public abstract int openingYear();

  public abstract int closingMonth();

  public abstract int closingDay();

  public abstract int closingYear();

  public static Builder builder() {
    return new AutoValue_Deadline.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setOpeningMonth(int value);

    public abstract Builder setOpeningDay(int value);

    public abstract Builder setOpeningYear(int value);

    public abstract Builder setClosingMonth(int value);

    public abstract Builder setClosingDay(int value);

    public abstract Builder setClosingYear(int value);

    public abstract Deadline build();
  }
}
