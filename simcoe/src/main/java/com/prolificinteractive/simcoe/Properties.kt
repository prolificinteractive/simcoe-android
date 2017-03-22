package com.prolificinteractive.simcoe

private val PROPERTY_STRING_FORMAT = "=> %s"

typealias Properties = Map<String, Any>

fun Properties.format(): String = String.format(PROPERTY_STRING_FORMAT, toString())
