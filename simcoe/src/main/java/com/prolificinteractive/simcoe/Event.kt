package com.prolificinteractive.simcoe

/**
 * A Simcoe Event.
 *
 * @param writeEvents The write actions that occurred in this event.
 * @param description The description of the event.
 */
internal class Event(
    val writeEvents: List<WriteEvent>,
    val description: String
)
