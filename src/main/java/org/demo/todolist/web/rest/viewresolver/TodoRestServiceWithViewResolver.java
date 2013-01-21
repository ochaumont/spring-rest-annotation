package org.demo.todolist.web.rest.viewresolver;

import org.demo.todolist.domain.Todo;
import org.demo.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/todo")
public class TodoRestServiceWithViewResolver {

    @Autowired
    private TodoService todoService;
    
          
    
    /*
     * Return Json or XML data or HTML , depends on extension value.
     */   
    @RequestMapping(value="/{id}", method= RequestMethod.GET)   
    public ModelAndView find(@PathVariable("id") Long id)   
    {    
       Todo todo = todoService.find(id);
	   return new ModelAndView("todo", "todo", todo);  	
    	
    }      
    
    /*
     * Use to return data in JSP View
     * @ResponseBody can't do that.
     */
    @RequestMapping(value="/{id}.html", method= RequestMethod.GET) 
    public ModelAndView findAsView(@PathVariable("id") Long id)   
    {    
       Todo todo = todoService.find(id);
	   return new ModelAndView("todo", "todo", todo);   	
    	
    }  
    
}
