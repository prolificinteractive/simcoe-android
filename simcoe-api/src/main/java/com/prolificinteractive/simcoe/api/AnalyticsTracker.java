package com.prolificinteractive.simcoe.api;

public class AnalyticsTracker {
  protected String tag;
  protected boolean log;
  protected boolean toast;

  private AnalyticsTracker(final String tag, final boolean log, final boolean toast) {
    this.tag = tag;
    this.log = log;
    this.toast = toast;
  }

  public AnalyticsTracker() {
  }

  protected void log(final Object... objects) {
    // TODO
  }

  public static class AnalyticsBuilder {
    public String tag = "AnalyticsTracker";
    public boolean log = false;
    public boolean toast = false;

    /**
     * Set a different tag name for your log. Default is {@link AnalyticsBuilder#tag}.
     *
     * @param tag String to tag your log
     */
    public AnalyticsBuilder setTag(String tag) {
      this.tag = tag;
      return this;
    }

    /**
     * Enable log display.
     *
     * @param log boolean to show logs
     */
    public AnalyticsBuilder setLog(boolean log) {
      this.log = log;
      return this;
    }

    /**
     * Enable toast display.
     *
     * @param toast boolean to show toasts
     */
    public AnalyticsBuilder setToast(boolean toast) {
      this.toast = toast;
      return this;
    }

    public AnalyticsTracker build() {
      return new AnalyticsTracker(tag, log, toast);
    }
  }
}
