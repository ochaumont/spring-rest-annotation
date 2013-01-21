package org.demo.todolist.service;

import org.demo.todolist.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 07/06/12
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Init {

    @Autowired
    TodoService todoService;

    private void dataInit() {
System.err.println("Load des data !!!");
        Todo todo1 = new Todo("Courses", "Penser à faire les courses en rentrant !!");
        todoService.create(todo1);

        Todo todo2 = new Todo("Veille techno", "Faire un sample de Sencha Touch !!");
        todoService.create(todo2);

    }
 }
