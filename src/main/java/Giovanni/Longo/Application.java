package Giovanni.Longo;

import Giovanni.Longo.dao.EventoDao;
import Giovanni.Longo.entities.EventType;
import Giovanni.Longo.entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventoDao ed = new EventoDao(em);

        Evento evento1 = new Evento("Evento 1", LocalDate.of(2023, 12, 31), "Concerto di capodanno", EventType.PUBBLICO, 1000);
        Evento evento2 = new Evento("Evento 2", LocalDate.of(2023, 12, 25), "Concerto di natale", EventType.PUBBLICO, 1000);
        Evento evento3 = new Evento("Evento 3", LocalDate.of(2023, 12, 31), "Concerto di capodanno", EventType.PRIVATO, 1000);

        ed.save(evento1);
        ed.save(evento2);
        ed.save(evento3);

        Scanner input = new Scanner(System.in);
        System.out.println("Inserire l'id dell'evento desiderato");
        long id = Long.parseLong(input.nextLine());
        Evento eventoCercato = ed.findById(id);
        if (eventoCercato != null) {
            System.out.println(eventoCercato);
        } else {
            System.out.println("Evento con id " + id + " non trovato");
        }

        System.out.println("Inserire l'id dell'evento che si desidera eliminare");
        long idDelete = Long.parseLong(input.nextLine());
        ed.findByIdAndDelete(idDelete);

    }
}
