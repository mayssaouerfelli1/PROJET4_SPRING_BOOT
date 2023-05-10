package com.mayssa.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayssa.demo.entities.Chanson;
import com.mayssa.demo.entities.Artiste;
import com.mayssa.demo.services.chansonService;

@Controller
public class chansonController {
   @Autowired
	chansonService chansonService;
   @RequestMapping("/showCreate")
   public String showCreate(ModelMap modelMap) {
       List<Artiste> arts = chansonService.getAllArtistes();
       Chanson chans = new Chanson();
       modelMap.addAttribute("chanson", chans);
       modelMap.addAttribute("mode", "new");
       modelMap.addAttribute("artistes", arts);
       return "formChanson";
   }

	
	
   @RequestMapping("/saveChanson")
   public String saveChanson(@Valid Chanson chanson, BindingResult bindingResult, ModelMap modelMap,
   			@RequestParam(value = "page", required = false) Integer page, 
   			@RequestParam(name = "size", defaultValue = "3") int size) 
   {
   		if (bindingResult.hasErrors()) return "formChanson";
   		chansonService.saveChanson(chanson);
   		if (page == null || page < 0) {
   			page = 0; // valeur par défaut
   		}
   		Page<Chanson> chansons = this.chansonService.getAllChansonsParPage(page, size);
   		modelMap.addAttribute("pages", new int[chansons.getTotalPages()]);
   		modelMap.addAttribute("currentPage", page);
   		modelMap.addAttribute("chansons", chansons);
   		return "ListeChansons";
   }

	
	
	
@RequestMapping("/ListeChansons")
public String listeChansons(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "3") int size)
{
	Page<Chanson> f = chansonService.getAllChansonsParPage(page, size);
	modelMap.addAttribute("chansons", f);
	 modelMap.addAttribute("pages", new int[f.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeChansons";
}

@RequestMapping("/supprimerChanson")
public String supprimerChanson(@RequestParam("id") Long id,
 ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "2") int size)
{ 
chansonService.deleteChansonById(id);
Page<Chanson> f = chansonService.getAllChansonsParPage(page, size);
		modelMap.addAttribute("chansons", f);
		modelMap.addAttribute("pages", new int[f.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);

return "listechansons";
}
@RequestMapping("/modifierChanson")
public String editerChanson(@RequestParam("id") Long id,ModelMap modelMap,
		@RequestParam("page") int page, 
		@RequestParam(name = "size", defaultValue = "3") int size
		)
{
Chanson c= chansonService.getChanson(id);
List<Artiste> arts = chansonService.getAllArtistes();
modelMap.addAttribute("chanson", c);
modelMap.addAttribute("mode", "edit");
modelMap.addAttribute("page", page);
modelMap.addAttribute("artistes", arts);
return "formChanson";
}


@RequestMapping("/updateChanson")
public String updateChanson(@ModelAttribute("chanson") Chanson chanson,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException 
{
	 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateCreation = dateformat.parse(String.valueOf(date));
	 chanson.setDateCreation(dateCreation);
	 
	chansonService.updateChanson(chanson);
	 List<Chanson> f = chansonService.getAllChansons();
	 modelMap.addAttribute("chansons", f);
	return "listeChansons";
	}
}
