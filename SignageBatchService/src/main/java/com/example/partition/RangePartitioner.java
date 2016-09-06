package com.example.partition;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.web.client.RestTemplate;

import com.example.BatchConfiguration;

public class RangePartitioner implements Partitioner {
	public static final Logger logger = LoggerFactory.getLogger(RangePartitioner.class);
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		int range = 10;
		int fromId = 1;
		int toId = range;
		for (int i = 1; i <= gridSize; i++) {
			ExecutionContext value = new ExecutionContext();
			logger.info("\nStarting : Thread" + i);
			logger.info("fromId : " + fromId);
			logger.info("toId : " + toId);
			value.putInt("fromId", fromId);
			value.putInt("toId", toId);
			value.putString("name", "Thread" + i);
			result.put("partition" + i, value);
			fromId = toId + 1;
			toId += range;
		}

		return result;
	}

}
