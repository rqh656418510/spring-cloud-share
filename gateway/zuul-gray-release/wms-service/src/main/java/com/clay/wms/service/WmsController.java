package com.clay.wms.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clay.wms.api.WmsApi;

@RestController
@RequestMapping("/wms")
public class WmsController implements WmsApi {

	@Override
	@RequestMapping(value = "/delivery/{productId}", method = RequestMethod.PUT)
	public String delivery(@PathVariable("productId") Long productId) {
		System.out.println("对商品【productId=" + productId + "】进行发货");    
		return "{'msg': 'success'}";
	}
	
}