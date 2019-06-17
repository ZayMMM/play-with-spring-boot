package com.next.easynotes.controller;

import com.next.easynotes.exception.ResourceNotFoundException;
import com.next.easynotes.model.Note;
import com.next.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    // Get a single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") long noteId){
        return noteRepository.findById(noteId)
                    .orElseThrow( () ->
                        new ResourceNotFoundException( "Note", "id", noteId ));
    }

    // Create a new Note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        return noteRepository.save( note );
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") long noteId,
                           @Valid @RequestBody Note noteDetail){
           Note note =  noteRepository.findById( noteId )
                            .orElseThrow(() -> new ResourceNotFoundException( "Note", "id", noteId ));

           note.setTitle( noteDetail.getTitle() );
           note.setContent( noteDetail.getContent() );

           Note updatedNote = noteRepository.save( note );

           return  updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") long noteId){
        Note note = noteRepository.findById( noteId )
                        .orElseThrow( () -> new ResourceNotFoundException( "Note", "id", noteId ) );
        noteRepository.delete( note );

        return ResponseEntity.ok().build();
    }


}
