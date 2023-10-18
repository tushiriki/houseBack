package com.house.constante;

import java.util.List;

public class StaticValue {
	private Integer key;
	private String value;

	public StaticValue() {

	}

	public StaticValue(Integer k, String v) {
		this.key = k;
		this.value = v;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StaticValue findByKey(String key, List<StaticValue> slov) {

		StaticValue sv = new StaticValue();

		for (StaticValue _sv : slov) {
			if (_sv.getKey().equals(key)) {
				sv = new StaticValue(_sv.getKey(), _sv.getValue());
				break;
			}
		}
		return sv;
	}
	
	public StaticValue findByValues(String values , List<StaticValue> slov) {

		StaticValue sv = new StaticValue();

		for (StaticValue _sv : slov) {
		
			if (_sv.getValue().equals(values)) {
				sv = new StaticValue(_sv.getKey(), _sv.getValue());
				break;
			}
		}
		return sv;
	}

}
