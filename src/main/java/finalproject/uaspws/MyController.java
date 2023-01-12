/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.uaspws;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wiratama
 * //20200140103
 */

@ResponseBody
@RestController
@CrossOrigin
public class MyController {
    
    Uaspws2022 data = new Uaspws2022();
    Uaspws2022JpaController dctrl = new Uaspws2022JpaController();
   
    
    @PostMapping("/POST")
	public String sendData(HttpEntity<String> kiriman) throws Exception{
		Uaspws2022 datas = new Uaspws2022();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws2022.class);
	        dctrl.create(datas);
		return d;
	}
	
	@PutMapping("/PUT")
	public String editData(HttpEntity<String> kiriman) throws Exception{
		Uaspws2022 datas = new Uaspws2022();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws2022.class);
	        dctrl.edit(datas);
		return d;
	}
	
	@DeleteMapping("/DELETE")
	public String deleteData(HttpEntity<String> kiriman) throws Exception{
		Uaspws2022 datas = new Uaspws2022();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws2022.class);
	        dctrl.destroy(datas.getId());
		return "id: "+datas.getId()+" deleted";
	}
	
	@GetMapping("/GET")
	public List<Uaspws2022> getTabel(){
		List<Uaspws2022> list = new ArrayList<>();
		try {
			list = dctrl.findUaspws2022Entities();
		}
		catch (Exception e){}
		return list;
	}
}
