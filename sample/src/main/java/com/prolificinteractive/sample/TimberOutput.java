package com.prolificinteractive.sample;

import com.prolificinteractive.simcoe.Output;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class TimberOutput implements Output {

  @Override public void print(@NotNull final String message) {
    Timber.i(message);
  }
}
