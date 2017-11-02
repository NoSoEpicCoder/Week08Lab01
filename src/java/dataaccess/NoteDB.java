package dataaccess;

import domainmodel.Note;
import java.time.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class NoteDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        Instant instant = note.getDateCreated().toInstant();
        ZoneId zoneid = ZoneId.of("America/Montreal");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneid);
        LocalDate localDate = zdt.toLocalDate();
        
        try {
            trans.begin();
            em.persist(note);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            em.close();
        }
        return 0;
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(note);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating user");
        } finally {
            em.close();
        }
        return 0;
    }

    public List<Note> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Note> notes = em.createNamedQuery("Note.findall", Note.class).getResultList();
            return notes;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Note getNote(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Note note = em.find(Note.class, noteId);
            return note;
        } catch (NoResultException ex) {
            return null;
        } finally {
           em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(note);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting Note");
        } finally {
            em.close();
        }
        return 0;
    }
}