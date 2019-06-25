package com.sapient.learning.service;

import com.sapient.learning.vo.Request;
import com.sapient.learning.vo.Response;

public interface CacheData {

	public Response getDataCache(int id);

	public Response update(Request dtoRequest);

	public void flushCache();
}