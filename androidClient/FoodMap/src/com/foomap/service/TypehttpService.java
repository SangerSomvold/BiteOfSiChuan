package com.foomap.service;

import com.foomap.service.HttpServiceHelper.IOnHttpRequeseListener;

import android.content.Context;

public class TypehttpService extends HttpServiceHelper{
	private final String BASE_URL=SERVICE_URL+"type/";
	private final String TYPE_GET_LIST=BASE_URL+"type_get";

	public TypehttpService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String postMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 获得所有商铺类型
	public void getTypeList(IOnHttpRequeseListener listener) {
		setRequestListener(listener);
		get_Asyn(TYPE_GET_LIST, null);

	}

}
