package org.demo.todolist.service;

import java.util.List;

import org.demo.todolist.domain.Todo;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 27/07/12
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public interface TodoService {

    public void create(Todo todo);

    public void delete(Todo todo);
    public void deleteById(Long id);

    public Todo find(Long id);

    public Todo update(Todo todo);

    public List<Todo> findAll();
}
