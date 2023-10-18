package com.house.helper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseHelper {

private String message;
private String type;
private Boolean success;
private Object data;




public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Boolean getSuccess() {
	return success;
}

public void setSuccess(Boolean success) {
	this.success = success;
}

public Object getData() {
	return data;
}

public void setData(Object data) {
	this.data = data;
}

public ResponseHelper(String message, Object data) {
this.message = message;
this.setData(data);
}

public ResponseHelper(String message, Object data, boolean sucess) {
this.setMessage(message);
this.setSuccess(sucess);
this.setData(data);
}

public ResponseHelper(String message) {
this.setMessage(message);
}

public ResponseHelper(Object data, boolean success) {
this.setSuccess(success);
this.setData(data);
}

public ResponseHelper(String message , boolean success) {
this.setMessage(message);
this.setSuccess(success);
}

String messageType(int type) {

List<String> types = new ArrayList<>();
types.add("success");
types.add("error");

return types.get(type);
}
}
