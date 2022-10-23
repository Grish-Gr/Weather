package com.example.weather.model.mappers

interface Mapper<SOURCE, RECIPIENT> {
    fun mapping(source: SOURCE): RECIPIENT
}