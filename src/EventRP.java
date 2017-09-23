import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by deutsch on 23/09/17.
 */
public class EventRP {

    Object o;
    String event;

    public EventRP(Vehicule vh, String event){
        this.o = vh;
        this.event = event;
    }

    public EventRP(ConcurrentLinkedQueue voie, String event){
        this.o = voie;
        this.event = event;
    }
}
