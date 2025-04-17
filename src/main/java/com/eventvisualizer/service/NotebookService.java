package com.eventvisualizer.service;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class NotebookService {
    private final GenericDao<Notebook> notebookDao;

    public NotebookService (GenericDao<Notebook> notebookDao) {
        this.notebookDao = notebookDao;
    }

    public GenericDao<Notebook> getNotebookDao() {
        return notebookDao;
    }

    public Notebook getNotebookById(int id) {
        return notebookDao.getById(id);
    }

    public Notebook notebookMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Notebook.class);
    }

    public List<Notebook> getAllNotebooksForUser(int id) {
        return notebookDao.findByPropertyEqual("user_id", 5);
    }

    public void updateNotebook(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getJsonNodeFromString(json);
        Notebook notebookToUpdate = getNotebookById(node.get("id").asInt());
        Notebook updatedNotebook = ObjectMapperUtil.getMapper().updateValue(notebookToUpdate, node);
        notebookDao.update(updatedNotebook);
    }


}