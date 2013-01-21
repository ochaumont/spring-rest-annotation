package org.demo.todolist.web.rest.httpconverter;

import org.demo.todolist.domain.Todo;
import org.demo.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/todo")
public class TodoRestServiceWithHTTPConverter {

    @Autowired
    private TodoService todoService;

    
    /*
     * Return always Json data (Accept Http Header value has no impact)
     */
    @RequestMapping(value="/{id}.json", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Todo findAsJsonWithProducesParam(@PathVariable("id") Long id) {
    	return todoService.find(id);           
    }    
    
    /*
     * Return always XML data  (Accept Http Header value has no impact)
     */
    @RequestMapping(value="/{id}.xml", method= RequestMethod.GET, produces="application/xml")
    @ResponseBody
    public Todo findAsXMLWithProducesParam(@PathVariable("id") Long id) {
    	return todoService.find(id); 
    }  
    
    /*
     * Return Json or XML data, depends on Accept Http Header value.
     * If no Accept Header value, return XML because XML Converter is declared as first. 
     */
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @ResponseBody
    public Todo findAllWithHttpHeader(@PathVariable("id") Long id) {
    	return todoService.find(id); 
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
