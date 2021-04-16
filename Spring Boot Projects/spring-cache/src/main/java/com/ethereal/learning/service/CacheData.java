package com.ethereal.learning.service;

import com.ethereal.learning.vo.Request;
import com.ethereal.learning.vo.Response;

public interface CacheData {

	public Response getDataCache(int id);

	public Response update(Request dtoRequest);

	public void flushCache();
}