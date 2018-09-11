/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;
import entities.Actor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import session.ActorManager;
/**
 *
 * @author cdap_
 */
@Named(value = "actorMBean")
@SessionScoped
public class ActorMBean implements Serializable {

    @EJB
    private ActorManager actorManager;
    private Actor actor;
    private List<Actor> actors;
    /**
     * Creates a new instance of ActorMBean
     */
    public ActorMBean() {
    }    
//retorna la lista de actores para mostrarse en una tabla de datos
    public List<Actor> getActors() {
        
        if(actors == null || actors.isEmpty()){
            this.refresh();
        }
        
        return actors;
    }
    
    private void refresh(){
        actors = actorManager.getAllActors();
    }
    //retorna los detalles de un actor, util para mostrar en un formulario los detalles de un actor
    public Actor getActor(){
        
        return actor;
    }
    //manejador de acciones que es llamado cuando se clickea una linea en la tabla
    public String showDetails(Actor actor){
        this.actor=actor;
        return "ActorDetails"; //mostrara la pagina JSF "ActorDetails.xml" 
    }
    //manejador de accion, actualiza el modelo actor en la base de datos cuando presionan el boton actualizar en el formulario
    public String update(){
        System.out.println("managedbeans.ActorMBean.update() " + "###UPDATE###");
        actor = actorManager.update(actor);
        return "ActorList"; //mostrara la lista de actores en una tabla
    }
    //manejador de accion, retorna la lista de actores en la tabla
    public String list(){
        System.out.println("###LIST###");
        return "ActorList";
    }
}
