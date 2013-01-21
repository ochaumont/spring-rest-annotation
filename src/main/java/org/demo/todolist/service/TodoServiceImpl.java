package org.demo.todolist.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demo.todolist.domain.Todo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 27/07/12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional (readOnly = false)
public class TodoServiceImpl implements TodoService {

    @PersistenceContext(name="todoPU")
    EntityManager entityManager;

    public Todo find(final Long id) {
        return entityManager.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        List<Todo> todos = entityManager.createQuery("from " + Todo.class.getName())
                .getResultList();

        return todos;
    }

    public void create(final Todo todo) {
        entityManager.persist(todo);
    }

    public Todo update(final Todo todo) {
        return entityManager.merge(todo);
    }

    public void delete(final Todo todo) {
        entityManager.remove(entityManager.merge(todo));
    }

    public void deleteById(final Long id) {
        final Todo entity = find(id);
        delete(entity);
    }


}
