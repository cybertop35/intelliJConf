package com.fantagame.be.business.controller.json.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fantagame.be.business.controller.ControllerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-application-context.xml"})
public class GroupControllerTest {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    
    @Test
    public void testController() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET",ControllerMapping.DEFAULT_PUB_APP+
																		  ControllerMapping.JSON + 
																		  ControllerMapping.GROUP_SERVICE +
																		  ControllerMapping.GET_GRUPPI_JSON);
       
        request.addParameter("username", "cybertop");
        
        
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = handlerMapping.getHandler(request).getHandler();
        ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

        // modelAndView and/or response asserts here
    }


}
