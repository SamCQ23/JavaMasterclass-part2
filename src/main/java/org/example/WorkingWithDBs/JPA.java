package org.example.WorkingWithDBs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.WorkingWithDBs.music.Artist;

public class JPA {

    public static void main(String[] args) {

        try (var sessionFactory =
                Persistence.createEntityManagerFactory(
                        "org.example.WorkingWithDBs.music");
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {

            var transaction = entityManager.getTransaction();
            transaction.begin();
            Artist artist = entityManager.find(Artist.class, 103);
//            artist.setArtistName("Muddy Waters");
            System.out.println(artist);
//            artist.addAlbum("The Best of Muddy Waters");
//            artist.removeDuplicates();
//            System.out.println(artist);
//            entityManager.remove(artist);
//            entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
