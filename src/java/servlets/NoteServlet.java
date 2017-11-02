package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        NoteService us = new NoteService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedNoteId = request.getParameter("selectedNoteId");
            try {
                Note note = us.get(Integer.parseInt(selectedNoteId));
                request.setAttribute("selectedNoteId", note);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ArrayList<Note> notes = null;        
        try {
            notes = (ArrayList<Note>) us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String noteId = request.getParameter("noteId");
        String dateCreated = request.getParameter("dateCreated");
        String content = request.getParameter("content");
        int active = request.getParameter("active") != null ? 1 : 0;

        NoteService us = new NoteService();

        try {
            if (action.equals("delete")) {
                String selectedNoteID = request.getParameter("selectedNoteID");
                us.delete(Integer.parseInt(selectedNoteID));
            } else if (action.equals("edit")) {
                us.update(Integer.parseInt(noteId),content);
            } else if (action.equals("add")) {
                us.insert(content);
}
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        ArrayList<Note> notes = null;
        try {
            notes = (ArrayList<Note>) us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}