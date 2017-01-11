package com.prolificinteractive.simcoe;

import java.util.Date;
import java.util.List;

/**
 * A Simcoe Event.
 */
class Event {

  /**
   * The write actions that occurred in this event.
   */
  final List<WriteEvent> writeEvents;

  /**
   * The description of the event.
   */
  final String description;

  /**
   * The event's timestamp.
   */
  final Date timeStamp;

  /**
   * Creates an event instance.
   *
   * @param writeEvents The write events.
   * @param description The description.
   * @param timeStamp The timestamp.
   */
  Event(final List<WriteEvent> writeEvents, final String description, final Date timeStamp) {
    this.writeEvents = writeEvents;
    this.description = description;
    this.timeStamp = timeStamp != null
                     ? timeStamp
                     : new Date();
  }
}
