package org.zwh.util;

import net.sf.json.JSONObject;

public class Result {
	public JSONObject data;
	public Status status;

	

	public JSONObject getData() {
		return data;
	}



	public void setData(JSONObject data) {
		this.data = data;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Result(Boolean success, String message ,JSONObject data) {
		super();
		this.data = data;
		this.status = new Status();
		this.status.setSuccess(success);;
		this.status.setMessage(message);;
	}

}
