package com.example.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.Product;
import com.example.data.PriceDTO;
import com.example.data.PromotionDTO;
import com.example.data.SinageDTO;
import com.example.data.TemplateDTO;

@Component("itemProcessor")
@Scope(value = "step")
public class UserProcessor implements ItemProcessor<Product, SinageDTO> {
	public static final Logger logger = LoggerFactory.getLogger(UserProcessor.class);

	@Value("#{stepExecutionContext[name]}")
	private String threadName;
	
	private  RestTemplate restTemplate = new RestTemplate(); 

	@Override
	public SinageDTO process(Product product) throws Exception {

		logger.info(threadName + " processing : " + product.getId() + " : " + product.getStoreid());
	
		List<SinageDTO> sinageres = fetchPriceDataFromAPI(product.getId(),product.getStoreid());

		SinageDTO sinagedto = sinageres.get(0);

		return sinagedto;
	
	}
	
	
	private List<SinageDTO> fetchPriceDataFromAPI(String Id,String storeid) {
		
		  String priceapiUrl="http://172.16.245.34:9006/ESEL/price/stores/"+storeid+"/products/"+Id;
		  String promotionapiUrl="http://172.16.245.34:9008/ESEL/promotion/stores/"+storeid+"/products/"+Id;
		  String templateapiUrl="http://172.16.245.34:9010/ESEL/template/products/"+Id;
		 
		  logger.info("priceapiUrl######"+priceapiUrl);
		  logger.info("promotionapiUrl######"+promotionapiUrl);
		  logger.info("templateapiUrl#####"+templateapiUrl);
		 
       

		ResponseEntity<PriceDTO[]> priceresponse = restTemplate.getForEntity(priceapiUrl, PriceDTO[].class);
		PriceDTO[] priceData = priceresponse.getBody();

		ResponseEntity<PromotionDTO[]> promotionResponse = restTemplate.getForEntity(promotionapiUrl,
				PromotionDTO[].class);
		PromotionDTO[] promotionData = promotionResponse.getBody();

		ResponseEntity<TemplateDTO[]> templateResponse = restTemplate.getForEntity(templateapiUrl, TemplateDTO[].class);
		TemplateDTO[] templateData = templateResponse.getBody();

		PriceDTO priceDTO = priceData[0];
		Integer priceId = priceDTO.getPriceId();
		String productId = priceDTO.getProductId();
		String storeId = priceDTO.getStoreId();

		PromotionDTO promotionDTO = promotionData[0];
		Double promoPrice = promotionDTO.getPromoPrice();
		String promotionId = promotionDTO.getPromotionId();

		TemplateDTO templateDTO = templateData[0];

		String templateId = templateDTO.getTemplateId();
		String ePISYSTemplateId = templateDTO.getEPISYSTemplateId();
		String dynamicCentralTemplateID = templateDTO.getDynamicCentralTemplateID();

		SinageDTO sinageDTO = new SinageDTO();

		sinageDTO.setPriceId(priceId);
		sinageDTO.setProductId(productId);
		sinageDTO.setStoreId(storeId);
		sinageDTO.setPromoPrice(promoPrice);
		sinageDTO.setPromotionId(promotionId);

		sinageDTO.setDynamicCentralTemplateID(dynamicCentralTemplateID);
		sinageDTO.setTemplateId(templateId);
		sinageDTO.setEPISYSTemplateId(ePISYSTemplateId);

		List<SinageDTO> sinageDTOList = new ArrayList<SinageDTO>();

		sinageDTOList.add(sinageDTO);

		return sinageDTOList;
    }
	
	
	

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
