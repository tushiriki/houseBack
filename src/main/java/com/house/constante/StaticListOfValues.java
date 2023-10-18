package com.house.constante;

import java.util.ArrayList;
import java.util.List;

import com.house.constante.Constante.roles;
import com.house.constante.Constante.trimestre; 
public class StaticListOfValues {
	private List<StaticValue> staticValues = new ArrayList<>();
 

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

	public List<StaticValue> getTypeTrimestres() {
		staticValues.clear();
		int i = 0;
		for (trimestre quarter : trimestre.values()) {
			StaticValue sv = new StaticValue(i, quarter.toString());
			staticValues.add(sv); 
			i++;
		
		}
		return staticValues;
	}

		public List<StaticValue> getRoles() {
		staticValues.clear();
		int i = 0;
		for (roles quarter : roles.values()) {
			StaticValue sv = new StaticValue(i, quarter.toString());
			staticValues.add(sv); 
			i++;
		
		}
		return staticValues;
	}

}
