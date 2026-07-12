package com.lauracercas.moviecards.service.actor;


import com.lauracercas.moviecards.model.Actor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Service
public class ActorServiceImpl implements ActorService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${moviecards.service.url}")
    private String serviceUrl;

    

    @Override
    public List<Actor> getAllActors() {
    ResponseEntity<Actor[]> response =
            restTemplate.getForEntity(serviceUrl + "/actors", Actor[].class);

    return List.of(response.getBody());
}
@Override
public Actor save(Actor actor) {

    if (actor.getId() == null) {
        restTemplate.postForObject(serviceUrl + "/actors", actor, Actor.class);
    } else {
        restTemplate.put(serviceUrl + "/actors", actor);
    }

    return actor;
}

    @Override
public Actor getActorById(Integer actorId) {
    return restTemplate.getForObject(
            serviceUrl + "/actors/" + actorId,
            Actor.class);
}
}