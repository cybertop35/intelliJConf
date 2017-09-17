package com.fantagame.be.business.controller.user;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fantagame.be.application.MessagePropertyUtil;
import com.fantagame.be.business.controller.ControllerMapping;
import com.fantagame.be.business.controller.validator.UserValidator;
import com.fantagame.be.business.data.bean.Persona;
import com.fantagame.be.business.service.ServiceConstants;
import com.fantagame.be.business.service.Iface.IMessageBean;
import com.fantagame.be.business.service.Iface.IPersonaService;
import com.fantagame.be.util.ImageUtils;

@Controller
@Scope("session")
@RequestMapping(ControllerMapping.DEFAULT_PUB_APP + ControllerMapping.PERSONA_SERVICE)
public class UserRegitration {

	private static final int IMG_WIDTH = 90;
	private static final int IMG_HEIGHT = 120;
	
	private byte[] img;
	
	@Autowired
	private IPersonaService personaService;
	
	
	
	public UserRegitration(){
		
	}
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");        
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	public void simpleForm(Model model){
		model.addAttribute(new Persona());
	}
	
	  /**
     * For every request for this controller, this will 
     * create a person instance for the form.
     */
    @ModelAttribute
    public Persona newRequest(@RequestParam(required=false) Integer id) {
        return  (Persona) (id != null ? personaService.getUserByID(id).getResult() : new Persona());
    }
    
    /**
     * <p>Person form request.</p>
     * 
     * <p>Expected HTTP GET and request.</p>
     */
    @RequestMapping(value=ControllerMapping.REGISTER_USER, method=RequestMethod.GET)
    public void form() {}
    
    /**
     * <p>Saves a person.</p>
     * 
     * <p>Expected HTTP POST and request.</p>
     */
    @RequestMapping(value=ControllerMapping.REGISTER_USER, method=RequestMethod.POST)
    public void form(@Valid Persona person,BindingResult bindingResult, Model model){        //@RequestParam("file") MultipartFile file) {  //,@RequestParam("pws2")String pws2) {
    	 
    	if(!bindingResult.hasErrors()){
    		 if (img != null && img.length > 0) {
    	    	person.getUserLogin().setImg(img);
			}
    		IMessageBean messageBean = personaService.registerUser(person, person.getUserLogin(), null);        
		    if(messageBean.getCode() == ServiceConstants.SUCCESS_CODE){
		    	 Persona newPersona = messageBean.getResult();
		    	 person.setIdUser(newPersona.getId());
		    	 person = new Persona();
		    	 model.addAttribute("statusMessageCode",messageBean.getCode() );
		    	 model.addAttribute("statusMessage", MessagePropertyUtil.getMessage("regitrstion.success") );
		     }
		     else{
		    	 model.addAttribute("statusMessageError", messageBean.getMessage());
		     }
    	}
    }
    
    @RequestMapping(value=ControllerMapping.CHECK_USERNAME, method=RequestMethod.GET)
    public @ResponseBody IMessageBean checkUserName(@RequestParam String username){
    	
    	return personaService.checkUserNameExist(username);
    }
    

    @RequestMapping(value=ControllerMapping.PREVIEW_IMG, method=RequestMethod.POST)
    public ResponseEntity<byte[]> testphotoPost(@RequestParam("file") MultipartFile file)  {

    	ResponseEntity<byte[]> result = null;
    	
    	if(file != null){
	    		final HttpHeaders headers = new HttpHeaders();
	    	 headers.setContentType(MediaType.IMAGE_PNG);	    	
	    	try{
	    		BufferedImage bufferedImage = ImageUtils.resizeImageWithHint(file.getInputStream(), IMG_WIDTH, IMG_HEIGHT);
	    		img = ImageUtils.BufferedImageToByte(bufferedImage);
	    		result = new ResponseEntity<byte[]>(img, headers, HttpStatus.CREATED); 
	    		
	    		
	    	} catch (Exception e) {
				
			}
    	}
       

        return  result;

    }
    
    
}
