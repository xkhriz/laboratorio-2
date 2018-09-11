/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Actor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdap_
 */
@Stateless
@LocalBean
public class ActorManager {

    @PersistenceContext(unitName = "laboratorio2-ejbPU")
    private EntityManager em;

    public List<Actor> getAllActors() {
        Query query = em.createNamedQuery("Actor.findAll");
        return query.getResultList();
    }    

    public Actor update(Actor actor) {
        return em.merge(actor);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
