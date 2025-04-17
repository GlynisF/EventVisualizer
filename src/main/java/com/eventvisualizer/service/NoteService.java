package com.eventvisualizer.service;

import com.eventvisualizer.entity.Note;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;


public class NoteService {

    private final GenericDao<Note> noteDao;

    public NoteService() {
        noteDao = new GenericDao<>(Note.class);
    }

    public NoteService(GenericDao<Note> noteDao) {
        this.noteDao = noteDao;
    }

    public GenericDao<Note> getNoteDao() {
        return noteDao;
    }

    public Note getNoteById(int id) {
        return noteDao.getById(id);
    }

    public Note noteMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Note.class);
    }

    public void updateNote(String json) throws JsonProcessingException {
        JsonNode jsonNode = ObjectMapperUtil.getMapper().readTree(json);
        Note noteToUpdate = noteDao.getById(jsonNode.get("id").asInt());
        Note updatedNote = ObjectMapperUtil.getMapper().updateValue(noteToUpdate, jsonNode);
        noteDao.update(updatedNote);
    }

}