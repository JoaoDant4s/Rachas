package tech.grupo4.java.rachas.model.openweather;

import java.util.List;

public record Alert(String sender_name, String event, long start, long end, String description,
List<String> tags) {}
